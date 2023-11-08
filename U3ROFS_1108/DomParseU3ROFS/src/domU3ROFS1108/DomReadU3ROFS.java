package domU3ROFS1108;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DomReadU3ROFS {
    public static ArrayList<Element> ReadXMLDocument(String filePath){
        try {
            File inputFile = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            System.out.println("Gyökérelem: " + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("ora");
            ArrayList<Element> elements = new ArrayList<Element>();

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    elements.add(eElement);
                }
            }
            return elements;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
