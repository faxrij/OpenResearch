package Repository;

import Component.Researcher;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class XmlResearcherRepository implements ResearcherRepository {
    private final String xmlFilePath;

    public XmlResearcherRepository(String xmlFilePath) {
        this.xmlFilePath = xmlFilePath;
    }

    @Override
    public List<Researcher> getResearchers() {
        try {
            File xmlFile = new File(xmlFilePath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);
            document.getDocumentElement().normalize();

            NodeList nodeList = document.getElementsByTagName("researcher");

            return parseAndGetResearchersList(nodeList);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<Researcher> parseAndGetResearchersList(NodeList nodeList) {
        List<Researcher> researcherList = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {

                Element element = (Element) node;

                String username = getTextContent(element, "username");
                String password = getTextContent(element, "password");

                Researcher researcher = new Researcher(username, password);

                researcher.setFollower(getInteractedResearchers(element, "followedBy"));
                researcher.setFollowing(getInteractedResearchers(element, "following"));

                researcherList.add(researcher);
            }
        }
        return researcherList;
    }

    private List<String> getInteractedResearchers(Element element, String interactionResearch) {
        String researcherString = getTextContent(element, interactionResearch);

        if (researcherString == null) {
            return null;
        }
        List<String> followedList = new ArrayList<>(Arrays.asList(researcherString.split(",")));

        System.out.println(followedList);
        return followedList;
    }

    private String getTextContent(Element element, String tagName) {
        Node node = element.getElementsByTagName(tagName).item(0);
        if (node != null) {
            return node.getTextContent();
        }
        return null;
    }
    @Override
    public Researcher authenticate(String username, String password) {
        List<Researcher> researchers = getResearchers();
        for (Researcher researcher : researchers) {
            if (researcher.getUsername().equals(username) && researcher.getPassword().equals(password)) {
                return researcher;
            }
        }
        return null; // Authentication failed
    }

    @Override
    public boolean containsResearcher(String username) {
        List<Researcher> researchers = getResearchers();
        for (Researcher researcher : researchers) {
            if (researcher.getUsername().equals(username)) {
                return true;
            }
        }
        return false; // Authentication failed
    }

    @Override
    public boolean checkIfFollowed(String current, String wanted) {
        List<Researcher> researchers = getResearchers();
        for (Researcher researcher: researchers) {
            if (researcher.getUsername().equals(current) && researcher.getFollowing().contains(wanted)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void addFollower(Researcher currentResearcher, String toBeFollowed) {
        try {
            // Load the XML file
            File xmlFile = new File("researchers.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);

            // Find the researcher element with the corresponding username
            NodeList researcherNodes = document.getElementsByTagName("researcher");
            for (int i = 0; i < researcherNodes.getLength(); i++) {
                Element researcherElement = (Element) researcherNodes.item(i);
                String researcherUsername = researcherElement.getElementsByTagName("username").item(0).getTextContent();

                if (researcherUsername.equals(currentResearcher.getUsername())) {
                    // Find the following element
                    ifFound(toBeFollowed, researcherElement);
                    break;
                }
            }

            // Save the updated XML file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(xmlFile);
            transformer.transform(source, result);
        } catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
            e.printStackTrace();
        }
    }

    private static void ifFound(String toBeFollowed, Element researcherElement) {
        Element followingElement = (Element) researcherElement.getElementsByTagName("following").item(0);

        // Append the new follower
        String currentFollowing = followingElement.getTextContent();
        if (currentFollowing.isEmpty()) {
            followingElement.setTextContent(toBeFollowed);
        } else {
            followingElement.setTextContent(currentFollowing + "," + toBeFollowed);
        }
    }
}
