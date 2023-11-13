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
    public static void WriteElementsToFileAndConsole(String currentFilePath, String copyFilePath) {
        try {
            File inputFile = new File(currentFilePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            // Nyitunk egy fájlt a kiírásra
            File outputFile = new File(copyFilePath);
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
        // Az elem kezdő tagjének írása a konzolra
        System.out.println(getIndentation(depth) + "<" + element.getNodeName() + ">");

        // Az elem kezdő tagjének írása a fájlba
        writer.write(getIndentation(depth) + "<" + element.getNodeName());

        // Az attribútumok kiírása
        NamedNodeMap attributes = element.getAttributes();
        for (int i = 0; i < attributes.getLength(); i++) {
            Node attribute = attributes.item(i);
            writer.write(" " + attribute.getNodeName() + "=\"" + attribute.getNodeValue() + "\"");
        }

        writer.write(">");

        // Az elem tartalmának kiírása (szöveg és gyerekelemek)
        NodeList children = element.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            if (child.getNodeType() == Node.ELEMENT_NODE) {
                // Rekurzív hívás az elem gyerekeire
                collectAndWriteElements((Element) child, writer, depth + 1);
            } else if (child.getNodeType() == Node.TEXT_NODE) {
                // Szöveges tartalom kiírása a konzolon
                String textContent = child.getNodeValue().trim();
                if (!textContent.isEmpty()) {
                    System.out.println(getIndentation(depth + 1) + textContent);
                }
                // Szöveges tartalom kiírása a fájlba
                writer.write(textContent);
            }
        }

        // Az elem záró tagjének írása a fájlba
        String closingTag = "</" + element.getNodeName() + ">";
        System.out.println(getIndentation(depth) + closingTag);
        writer.write(getIndentation(depth) + closingTag + "\n");
    }

    private static String getIndentation(int depth) {
        StringBuilder indentation = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            indentation.append("  "); // 2 szóköz a behúzás
        }
        return indentation.toString();
    }
}
