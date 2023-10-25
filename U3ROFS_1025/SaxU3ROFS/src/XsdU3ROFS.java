import javax.xml.XMLConstants;
import javax.xml.transform.sax.SAXSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
public class XsdU3ROFS {
    public static void main(String[] args) {
        try {
            // SchemaFactory létrehozása
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

            // Schema betöltése az XSD fájlból
            File schemaLocation = new File("src/U3ROFS_kurzusfelvetel.xsd");
            Schema schema = factory.newSchema(schemaLocation);

            // Validator létrehozása a Schema alapján
            Validator validator = schema.newValidator();

            // XML fájl betöltése
            File xmlFile = new File("src/U3ROFS_kurzusfelvetel.xml");
            SAXSource source = new SAXSource(new InputSource(xmlFile.toURI().toString()));

            // Validáció
            validator.validate(source);
            System.out.println("Az XML fájl érvényes az XSD sémához képest.");

        } catch (SAXException e) {
            System.out.println("Hiba az XML validációjában: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO Hiba: " + e.getMessage());
        }
    }
}
