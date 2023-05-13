package helper;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ResearcherAuthenticator {

    public boolean authenticate(String username, String password) {
        boolean isAuthenticated = false;
        try {
            File xmlFile = new File("researchers.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);
            document.getDocumentElement().normalize();

            NodeList nodeList = document.getElementsByTagName("researcher");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String xmlUsername = element.getElementsByTagName("username").item(0).getTextContent();
                    String xmlPassword = element.getElementsByTagName("password").item(0).getTextContent();

                    if (xmlUsername.equals(username) && xmlPassword.equals(password)) {
                        isAuthenticated = true;
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isAuthenticated;
    }
}
