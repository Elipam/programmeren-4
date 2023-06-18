import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.*;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) {
        // Bestandsnaam en locatie van het JSON-bestand
        String fileName = "C:\\Users\\eliam\\code\\programmeren 4\\programmeren 4\\src\\studentData_v1.json";

        // ArrayList om de studentgegevens op te slaan
        List<Student> studenten = new ArrayList<>();
        List<Vakken> resultaten = new ArrayList<>();

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
                
                JSONArray behaaldeCijfers = (JSONArray) studentObj.get("behaalde_cijfers");
                for (Object obje : behaaldeCijfers){
                    JSONObject studentObje = (JSONObject) obje;
                    String vakcode = (String) studentObje.get("vakcode");
                    float cijfer = ((Number) studentObje.get("cijfer")).floatValue();
                    Vakken vak = new Vakken(vakcode, cijfer);
                    resultaten.add(vak);
                }

                // Aanmaken van Student-object en toevoegen aan de ArrayList
                Student student = new Student(studentnummer, naam, klas, studierichting, studiejaar, resultaten);
                studenten.add(student);
            }

            List<Student> klasFilter = studenten.stream()
                .filter(student -> student.klas.equals("TI1.1"))
                .collect(Collectors.toList());

            klasFilter.forEach(student -> System.out.println(student.resultaten));
            List<Vakken> studentVakken = student.getVakken();

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}