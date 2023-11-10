import hu.domparse.u3rofs.DomModifyU3ROFS;
import hu.domparse.u3rofs.DomQueryU3ROFS;
import hu.domparse.u3rofs.DomReadU3ROFS;
import hu.domparse.u3rofs.DomWriteU3ROFS;
import org.w3c.dom.Element;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        //RootElement beolvasása
        ArrayList<Element> elements = DomReadU3ROFS.ReadXMLDocument("./src/XML_U3ROFS.xml");
        //Elementek kiíratása consolra
        DomWriteU3ROFS.WriteElementsToConsole(elements);
        //Elementek módosítása
        DomModifyU3ROFS.ModifyElement(elements);
        //Elementek lekérdezése
        DomQueryU3ROFS.QueryDetails(elements);
    }
}