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

public class UnfollowResearcher {
    public void unFollow(String currentResearcher, String toBeUnfollowed) {
        try {
            // Load the XML file
            File xmlFile = new File("researchers.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);

            // Find the currentResearcher element
            Element currentResearcherElement = findResearcherElement(currentResearcher, document);

            // Find the toBeUnfollowed element
            Element toBeUnfollowedElement = findResearcherElement(toBeUnfollowed, document);

            if (currentResearcherElement != null && toBeUnfollowedElement != null) {
                // Remove toBeUnfollowed from currentResearcher's following list
                removeResearcherFromList(currentResearcherElement, "following", toBeUnfollowed);

                // Remove currentResearcher from toBeUnfollowed's followedBy list
                removeResearcherFromList(toBeUnfollowedElement, "followedBy", currentResearcher);

                // Save the updated XML file
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(document);
                StreamResult result = new StreamResult(xmlFile);
                transformer.transform(source, result);
            }
        } catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
            e.printStackTrace();
        }
    }

    private Element findResearcherElement(String researcherName, Document document) {
        NodeList researcherNodes = document.getElementsByTagName("researcher");
        for (int i = 0; i < researcherNodes.getLength(); i++) {
            Element researcherElement = (Element) researcherNodes.item(i);
            String username = researcherElement.getElementsByTagName("username").item(0).getTextContent();
            if (username.equals(researcherName)) {
                return researcherElement;
            }
        }
        return null;
    }

    private void removeResearcherFromList(Element researcherElement, String tagName, String researcherName) {
        Element element = (Element) researcherElement.getElementsByTagName(tagName).item(0);
        String list = element.getTextContent();
        String updatedList = removeResearcherFromListString(list, researcherName);
        element.setTextContent(updatedList);
    }

    private String removeResearcherFromListString(String list, String researcherName) {
        String[] researchers = list.split(",");
        StringBuilder updatedList = new StringBuilder();
        for (String researcher : researchers) {
            if (!researcher.equals(researcherName)) {
                updatedList.append(researcher).append(",");
            }
        }
        if (updatedList.length() > 0) {
            updatedList.deleteCharAt(updatedList.length() - 1);
        }
        return updatedList.toString();
    }

}
