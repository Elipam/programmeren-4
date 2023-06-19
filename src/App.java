import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) {
        // Bestandsnaam en locatie van het JSON-bestand
        String fileName = "C:\\Users\\eliam\\code\\programmeren 4\\programmeren 4\\src\\studentData_v1.json";

        // JSON-parser initialiseren
        JSONParser parser = new JSONParser();

        try {
            // JSON-bestand inlezen en parsen
            JSONArray studentenArray = (JSONArray) parser.parse(new FileReader(fileName));

            List<Student> studenten = new ArrayList<>();

            // Loop door alle studenten in de JSON-array
            for (Object obj : studentenArray) {
                JSONObject studentObj = (JSONObject) obj;

                // Studentgegevens uit het JSON-object halen
                String studentnummer = (String) studentObj.get("studentnummer");
                String naam = (String) studentObj.get("naam");
                String klas = (String) studentObj.get("klas");
                String studierichting = (String) studentObj.get("studierichting");
                String studiejaar = (String) studentObj.get("studiejaar");

                // Resultaten van de student
                JSONArray behaaldeCijfers = (JSONArray) studentObj.get("behaalde_cijfers");
                List<Vakken<String, Float, Void>> resultaten = new ArrayList<>();

                for (Object obje : behaaldeCijfers) {
                    JSONObject studentObje = (JSONObject) obje;
                    String vakcode = (String) studentObje.get("vakcode");
                    float cijfer = ((Number) studentObje.get("cijfer")).floatValue();
                    Vakken<String, Float, Void> vak = new Vakken<>(vakcode, cijfer, null);
                    resultaten.add(vak);
                }

                // Vakkenpakket van de student
                JSONArray vakkenpakketArray = (JSONArray) studentObj.get("vakkenpakket");
                List<Vakken<String, String, Integer>> vakkenpakket = new ArrayList<>();

                for (Object obje : vakkenpakketArray) {
                    JSONObject vakkenpakketObj = (JSONObject) obje;
                    String vakcode = (String) vakkenpakketObj.get("vakcode");
                    String vaknaam = (String) vakkenpakketObj.get("naam");
                    int ec = ((Number) vakkenpakketObj.get("ec")).intValue();
                    Vakken<String, String, Integer> vak = new Vakken<>(vakcode, vaknaam, ec);
                    vakkenpakket.add(vak);
                }

                // Aanmaken van Student-object en toevoegen aan de ArrayList
                Student student = new Student(studentnummer, naam, klas, studierichting, studiejaar, resultaten,
                        vakkenpakket);
                studenten.add(student);
            }

            // Filteren op klas TI1.1 en printen van resultaten
            List<Student> klasFilter = studenten.stream()
                .filter(student -> student.klas.equals("TI1.1"))
                .collect(Collectors.toList());

            for (Student student : klasFilter) {
                System.out.println("Studentnummer: " + student.studentnummer);
                System.out.println("Naam: " + student.naam);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
