package U3ROFS;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONWrite {
    public static void main(String[] args) {
        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader("U3ROFS_1206\\kurzusfelvetelU3ROFS.json")) {
            Object obj = parser.parse(reader);
            File file = new File("U3ROFS_1206\\kurzusfelvetelU3ROFS_1.json");
            JSONObject jsonObject = (JSONObject) obj;
            OutputStreamWriter wr = new OutputStreamWriter(System.out, StandardCharsets.UTF_8);
            wr.write(jsonObject.toString());
            wr.flush();
            PrintWriter pw = new PrintWriter(file);
            pw.println(jsonObject.toString());
            pw.close();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
