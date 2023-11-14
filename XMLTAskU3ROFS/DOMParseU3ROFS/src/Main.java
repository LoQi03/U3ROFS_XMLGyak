import hu.domparse.u3rofs.DomModifyU3ROFS;
import hu.domparse.u3rofs.DomQueryU3ROFS;
import hu.domparse.u3rofs.DomReadU3ROFS;
import hu.domparse.u3rofs.DomWriteU3ROFS;
import org.w3c.dom.Element;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        //RootElement beolvasása
        DomReadU3ROFS.ReadXMLDocument("./src/XML_U3ROFS.xml");
        //Elementek módosítása
        DomModifyU3ROFS.ModifyElement("./src/XML_U3ROFS.xml");
        //Elemek kiírása
        DomWriteU3ROFS.WriteElementsToFileAndConsole();
        //Elementek lekérdezése
        DomQueryU3ROFS.QueryPrescribedDetails("./src/XML_U3ROFS.xml");
    }
}