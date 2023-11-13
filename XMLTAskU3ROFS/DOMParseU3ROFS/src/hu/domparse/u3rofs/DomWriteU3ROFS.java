package hu.domparse.u3rofs;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DomWriteU3ROFS {
    public static void WriteElementsToFileAndConsole(String currentFilePath) {
        try {
            File inputFile = new File(currentFilePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            // Nyitunk egy fájlt a kiírásra
            File outputFile = new File("XML_U3ROFS1.xml");
            FileWriter writer = new FileWriter(outputFile);

            // Kiírjuk az XML deklarációt
            writer.write("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");

            // Rekurzív függvény a gyűjtéshez és kiíráshoz
            collectAndWriteElements(doc.getDocumentElement(), writer, 0);

            // Fájl bezárása
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }
    }

    private static void collectAndWriteElements(Element element, FileWriter writer, int depth) throws IOException {
        // Nyitó tag kiírása
        writer.write(getIndentation(depth) + "<" + element.getNodeName());
        System.out.print(getIndentation(depth) + "<" + element.getNodeName());

        // Attribútumok kiírása
        NamedNodeMap attributes = element.getAttributes();
        for (int i = 0; i < attributes.getLength(); i++) {
            Node attribute = attributes.item(i);
            writer.write(" " + attribute.getNodeName() + "=\"" + attribute.getNodeValue() + "\"");
            System.out.print(" " + attribute.getNodeName() + "=\"" + attribute.getNodeValue() + "\"");
        }

        writer.write(">");
        System.out.print(">");

        // Gyerek elemek és szöveg kiírása
        NodeList children = element.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);

            if (child.getNodeType() == Node.ELEMENT_NODE) {
                writer.write("\n"); // Új sor az elemek előtt
                System.out.println();
                collectAndWriteElements((Element) child, writer, depth + 1);
            } else if (child.getNodeType() == Node.TEXT_NODE) {
                String textContent = child.getNodeValue().trim();
                if (!textContent.isEmpty()) {
                    writer.write(textContent);
                    System.out.print(textContent);
                }
            }
        }

        // Záró tag
        if (element.getChildNodes().getLength() > 0) {
            System.out.print(getIndentation(depth));
        }
        writer.write("</" + element.getNodeName() + ">");
        System.out.print("</" + element.getNodeName() + ">");
    }

    private static String getIndentation(int depth) {
        StringBuilder indentation = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            indentation.append("  "); // 2 szóköz a behúzás
        }
        return indentation.toString();
    }
}
