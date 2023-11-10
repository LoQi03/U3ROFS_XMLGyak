package hu.domparse.u3rofs;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.util.ArrayList;

public class DomModifyU3ROFS {
    public static ArrayList<Element> ModifyElement(ArrayList<Element> elements) {
        ArrayList<Element> editedElements = elements;
        for (int i=0;i<editedElements.size();i++)
        {
            ModifyPrescribedElements(editedElements.get(i));
        }
        DomWriteU3ROFS.WriteElementsToConsoleAndFile(editedElements,"./src/XML_U3ROFS1.xml");
        return editedElements;
    }
    private static void ModifyPrescribedElements(Element element)
    {
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
