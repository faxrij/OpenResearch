package Repository;

import Component.Researcher;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XmlResearcherRepository implements ResearcherRepository {
    private String xmlFilePath;

    public XmlResearcherRepository(String xmlFilePath) {
        this.xmlFilePath = xmlFilePath;
    }

    @Override
    public List<Researcher> getResearchers() {
        List<Researcher> researchers = new ArrayList<>();
        try {
            File xmlFile = new File(xmlFilePath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);
            document.getDocumentElement().normalize();

            NodeList nodeList = document.getElementsByTagName("researcher");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String username = element.getElementsByTagName("username").item(0).getTextContent();
                    String password = element.getElementsByTagName("password").item(0).getTextContent();
                    Researcher researcher = new Researcher(username, password);
                    researchers.add(researcher);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return researchers;
    }
}

