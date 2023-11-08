import domU3ROFS1108.DomReadU3ROFS;
import domU3ROFS1108.DomWriteU3ROFS;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        ArrayList<Element> elements = DomReadU3ROFS.ReadXMLDocument("./src/U3ROFS_orarend.xml");
        DomWriteU3ROFS.WriteElements(elements);
    }
}