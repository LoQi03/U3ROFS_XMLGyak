package hu.domparse.u3rofs;

import org.w3c.dom.*;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DomWriteU3ROFS {
    public static void WriteElementsToConsoleAndFile(ArrayList<Element> elements, String filePath) {
        //megnyitjuk a file írást
        try (FileWriter writer = new FileWriter(filePath)) {
            for(var element : elements)
            {
                writeToConsoleAndFile(writer, element, "");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void writeToConsoleAndFile(FileWriter writer, Node node, String indent) {
        try {
            //ellenőrizzük hogy Element-e
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                //Elkezdjük írni a start tag-et
                String startTag = indent  + element.getTagName();

                // Attribútumok formázása
                String attributesText = formatAttributes(element.getAttributes());
                if (!attributesText.isEmpty()) {
                    startTag += " " + attributesText;
                }

                startTag +=  " start";

                System.out.println(startTag);
                writer.write(startTag + "\n");
                indent += "    ";

                NodeList children = element.getChildNodes();
                for (int i = 0; i < children.getLength(); i++) {
                    writeToConsoleAndFile(writer, children.item(i), indent);
                }

                indent = indent.substring(0, indent.length() - 4);
                String endTag = indent  + element.getTagName()  + " end";
                System.out.println(endTag);
                writer.write(endTag + "\n");
            } else if (node.getNodeType() == Node.TEXT_NODE) {
                String nodeValue = node.getTextContent().trim();
                if (!nodeValue.isEmpty()) {
                    System.out.println(indent + nodeValue);
                    writer.write(indent + nodeValue + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String formatAttributes(NamedNodeMap attributes) {
        //Atributumok formázása
        StringBuilder attributesBuilder = new StringBuilder();
        if (attributes != null && attributes.getLength() > 0) {
            attributesBuilder.append("{");
            for (int i = 0; i < attributes.getLength(); i++) {
                Attr attribute = (Attr) attributes.item(i);
                attributesBuilder.append(attribute.getName()).append("=").append(attribute.getValue());
                if (i < attributes.getLength() - 1) {
                    attributesBuilder.append(",");
                }
            }
            attributesBuilder.append("}");
        }
        return attributesBuilder.toString();
    }
}
