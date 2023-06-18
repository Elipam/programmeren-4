import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        // Bestandsnaam en locatie van het JSON-bestand
        String fileName = "C:\\Users\\eliam\\code\\programmeren 4\\programmeren 4\\src\\studentData_v1.json";

        // ArrayList om de studentgegevens op te slaan
        ArrayList<Student> studenten = new ArrayList<>();

        // JSON-parser initialiseren
        JSONParser parser = new JSONParser();

        try {
            // JSON-bestand inlezen en parsen
            JSONArray studentenArray = (JSONArray) parser.parse(new FileReader(fileName));

            // Loop door alle studenten in de JSON-array
            for (Object obj : studentenArray) {
                JSONObject studentObj = (JSONObject) obj;

                // Studentgegevens uit het JSON-object halen
                String studentnummer = (String) studentObj.get("studentnummer");
                String naam = (String) studentObj.get("naam");
                String klas = (String) studentObj.get("klas");
                String studierichting = (String) studentObj.get("studierichting");
                String studiejaar = (String) studentObj.get("studiejaar");

                // Aanmaken van Student-object en toevoegen aan de ArrayList
                Student student = new Student(studentnummer, naam, klas, studierichting, studiejaar);
                studenten.add(student);
            }

            // Verifieer of de gegevens correct zijn ingelezen
            for (Student student : studenten) {
                System.out.println(student);
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}