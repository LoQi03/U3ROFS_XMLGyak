package domU3ROFS1108;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

public class DomWriteU3ROFS {
    public static void WriteElements(ArrayList<Element> elements) {
        for (Element element : elements) {
            System.out.println("Element: " + element.getTagName());

            NamedNodeMap attributes = element.getAttributes();
            for (int i = 0; i < attributes.getLength(); i++) {
                Node attribute = attributes.item(i);
                System.out.println("Attribute: " + attribute.getNodeName() + " = " + attribute.getNodeValue());
            }

            NodeList childNodes = element.getChildNodes();
            for (int i = 0; i < childNodes.getLength(); i++) {
                Node node = childNodes.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element childElement = (Element) node;
                    String tagName = childElement.getTagName();
                    String textContent = childElement.getTextContent();
                    System.out.println(tagName + " : " + textContent);
                }
            }
        }
    }
}
