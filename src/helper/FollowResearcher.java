package helper;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
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

public class FollowResearcher {
    public void addFollower(String currentResearcher, String toBeFollowed) {
        try {
            // Load the XML file
            File xmlFile = new File("researchers.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);

            // Find the researcher element with the corresponding username
            NodeList researcherNodes = document.getElementsByTagName("researcher");
            findResearcher(currentResearcher, toBeFollowed, researcherNodes, "following");
            findResearcher(toBeFollowed, currentResearcher, researcherNodes, "followedBy");

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

    private void findResearcher(String currentResearcher, String toBeFollowed, NodeList researcherNodes, String tagName) {
        for (int i = 0; i < researcherNodes.getLength(); i++) {
            Element researcherElement = (Element) researcherNodes.item(i);
            String researcherUsername = researcherElement.getElementsByTagName("username").item(0).getTextContent();

            if (researcherUsername.equals(currentResearcher)) {
                // Find the following element
                ifFound(toBeFollowed, researcherElement, tagName);
                break;
            }
        }
    }

    private void ifFound(String toBeFollowed, Element researcherElement, String tagName) {
        Element followingElement = (Element) researcherElement.getElementsByTagName(tagName).item(0);

        // Append the new follower
        String currentFollowing = followingElement.getTextContent();
        if (currentFollowing.isEmpty()) {
            followingElement.setTextContent(toBeFollowed);
        } else {
            followingElement.setTextContent(currentFollowing + "," + toBeFollowed);
        }
    }

}
