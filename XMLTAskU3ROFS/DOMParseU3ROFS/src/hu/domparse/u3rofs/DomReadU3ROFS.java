package hu.domparse.u3rofs;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DomReadU3ROFS {
    public static ArrayList<Element> ReadXMLDocument(String filePath){
        try {
            // Egy új File objektumot hozunk létre, ami a megadott útvonalon lévő fájlra mutat.
            File inputFile = new File(filePath);

            // Új DocumentBuilderFactory példány létrehozása, ami XML fájlok feldolgozására szolgál.
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

            // DocumentBuilder példányt hozunk létre a Factory segítségével, amit aztán használhatunk az XML fájlok elemzésére.
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            // Az XML dokumentumot beolvassuk és egy Document objektumba töltjük, ami lehetővé teszi a struktúrájának kezelését.
            Document doc = dBuilder.parse(inputFile);

            // Az XML dokumentum normalizálása, ami többek között azt jelenti, hogy eltávolítjuk a felesleges whitespace karaktereket,
            // és egyéb normalizálási lépéseket hajtunk végre, hogy egységes legyen a dokumentum szerkezete.
            doc.getDocumentElement().normalize();

            // Kiírjuk a konzolra az XML dokumentum gyökérelemének nevét.
            System.out.println("Gyökérelem: " + doc.getDocumentElement().getNodeName());

            // Egy NodeList objektumot hozunk létre, amely az összes "U3ROFS_Autosiskolak" tag nevű elemet tartalmazza az XML dokumentumból.
            NodeList nList = doc.getElementsByTagName("U3ROFS_Autosiskolak");

            // Egy új Element típusú ArrayListet hozunk létre, ami tárolni fogja az elemeket.
            ArrayList<Element> elements = new ArrayList<Element>();

            // Végigiterálunk a NodeList elemein.
            for (int temp = 0; temp < nList.getLength(); temp++) {
                // Kivesszük a NodeList-ből az aktuális Node elemet.
                Node nNode = nList.item(temp);

                // Ellenőrizzük, hogy az aktuális Node elem tipusa ELEMENT_NODE-e, ami azt jelenti, hogy egy elemről van szó.
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    // A Node elemet Elementté konvertáljuk, hogy hozzáférhessünk az elem specifikus metódusaihoz.
                    Element eElement = (Element) nNode;

                    // Hozzáadjuk az Elementet az elemeket tartalmazó listához.
                    elements.add(eElement);
                }
            }
            // Visszatérünk az elemek listájával.
            return elements;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
