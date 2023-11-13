package hu.domparse.u3rofs;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.*;

public class DomReadU3ROFS {
    public static void ReadXMLDocument(String filePath) {
            try {
                File inputFile = new File(filePath);
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(inputFile);
                doc.getDocumentElement().normalize();

                System.out.println("Gyökérelem: " + doc.getDocumentElement().getNodeName() + "beolvasása sikeres!");
                NodeList root = doc.getElementsByTagName("U3ROFS_Autosiskolak");
                NodeList autosiskolaList = doc.getElementsByTagName("Autosiskola");
                NodeList ugyfelList = doc.getElementsByTagName("Ugyfel");
                NodeList oktatoList = doc.getElementsByTagName("Oktato");
                NodeList autoList = doc.getElementsByTagName("Auto");
                NodeList szereloList = doc.getElementsByTagName("Szerelo");
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            }
    }
}

