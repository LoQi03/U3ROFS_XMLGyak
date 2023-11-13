package hu.domparse.u3rofs;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class DomModifyU3ROFS {
    public static void ModifyElement(String filePath) {
        try {
            File inputFile = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            ModifyPrescribedElements(doc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void ModifyPrescribedElements(Document doc)
    {
        //Root Element lekérése
        NodeList nList = doc.getElementsByTagName("U3ROFS_Autosiskolak");
        Element element = (Element) nList.item(0);
        //Megváltoztatom az autosiskolákból az első elem nevét
        NodeList autosiskolaList = element.getElementsByTagName("Autosiskola");
        Element autosiskola = (Element) autosiskolaList.item(0);
        autosiskola.getElementsByTagName("nev").item(0).setTextContent("Fast Car");
        //Megváltoztatom az atributumot is
        autosiskola.setAttribute("ai_id", "4");

        // Például az első Ugyfel nevét változtatja meg
        NodeList ugyfelList = element.getElementsByTagName("Ugyfel");
        Element ugyfel = (Element) ugyfelList.item(0);
        ugyfel.getElementsByTagName("vezeteknev").item(0).setTextContent("Kis");
        ugyfel.getElementsByTagName("keresztnev").item(0).setTextContent("Gábor");
        ugyfel.setAttribute("ai_id", "4");

        // Az első Oktato fizetesét változtatja meg
        NodeList oktatoList = element.getElementsByTagName("Oktato");
        Element oktato = (Element) oktatoList.item(0);
        oktato.getElementsByTagName("fizetes").item(0).setTextContent("350000");
        oktato.setAttribute("ai_id","4");

        // Az első Auto markáját változtatja meg
        NodeList autoList = element.getElementsByTagName("Auto");
        Element auto = (Element) autoList.item(0);
        auto.getElementsByTagName("marka").item(0).setTextContent("Chevrolet");

        // Az első Szerelo nevét változtatja meg
        NodeList szereloList = element.getElementsByTagName("Szerelo");
        Element szerelo = (Element) szereloList.item(0);
        szerelo.getElementsByTagName("nev").item(0).setTextContent("Kovács Béla");

        // Az első cserealkatreszek cserealkatresz elemének tartalmát változtatja meg
        NodeList cserealkatreszekList = element.getElementsByTagName("cserealkatreszek");
        Element cserealkatreszek = (Element) cserealkatreszekList.item(0);
        cserealkatreszek.getElementsByTagName("cserealkatresz").item(0).setTextContent("kuplung");

    }
}
