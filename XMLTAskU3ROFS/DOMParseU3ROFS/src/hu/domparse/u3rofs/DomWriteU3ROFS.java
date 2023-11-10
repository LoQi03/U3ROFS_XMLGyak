package hu.domparse.u3rofs;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;

public class DomWriteU3ROFS {
    public static void WriteElementsToConsole(ArrayList<Element> elements) {
        for (Element element : elements) {
            WriteXMLDocumentToConsole(element);
        }
    }
    public static void WriteElementsToFile(ArrayList<Element> elements,String filePath)
    {
        for (Element element : elements) {
            WriteXMLDocumentToFile(element,filePath);
        }
    }
    public static void WriteXMLDocumentToConsole(Element element)
    {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            //Beállítom a transformert
            Transformer transformer = transformerFactory.newTransformer();
            //Megadom a forrás fájlt amit fent létrehoztam
            DOMSource source = new DOMSource(element);
            //Megnyitom a streamet és konzolra kiíratom sys.out-al a fájlt
            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public static void WriteXMLDocumentToFile(Element element, String filePath) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            //Beállítom a transformert
            Transformer transformer = transformerFactory.newTransformer();

            DOMSource source = new DOMSource(element);
            // Létrehoz egy StreamResult példányt, amely a megadott fájlra mutat
            StreamResult fileResult = new StreamResult(new File(filePath));

            // Az XML dokumentumot írja a megadott fájlba
            transformer.transform(source, fileResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

