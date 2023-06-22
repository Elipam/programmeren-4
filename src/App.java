import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        String fileName = "C:\\Users\\eliam\\code\\programmeren-4\\src\\studentData_v1.json";
        JSONParser parser = new JSONParser();

        try {
            JSONArray studentenArray = (JSONArray) parser.parse(new FileReader(fileName));

            List<Student> studenten = new ArrayList<>();

            for (Object obj : studentenArray) {
                JSONObject studentObj = (JSONObject) obj;

                String studentnummer = (String) studentObj.get("studentnummer");
                String naam = (String) studentObj.get("naam");
                String klas = (String) studentObj.get("klas");
                String studierichting = (String) studentObj.get("studierichting");
                String studiejaar = (String) studentObj.get("studiejaar");

                JSONArray behaaldeCijfers = (JSONArray) studentObj.get("behaalde_cijfers");
                List<Vakken<String, Float, Void>> resultaten = new ArrayList<>();

                for (Object obje : behaaldeCijfers) {
                    JSONObject studentObje = (JSONObject) obje;
                    String vakcode = (String) studentObje.get("vakcode");
                    float cijfer = ((Number) studentObje.get("cijfer")).floatValue();
                    Vakken<String, Float, Void> vak = new Vakken<>(vakcode, cijfer, null);
                    resultaten.add(vak);
                }

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

                Student student = new Student(studentnummer, naam, klas, studierichting, studiejaar, resultaten, vakkenpakket);
                studenten.add(student);
            }

            String testStudentNummer = "7654422";
            String testVakCode = "WI101";
            String testKlas = "TI2.2";

            // Filters.inKlas(studenten, testKlas);
            // Filters.vakVolgen(studenten, testVakCode);
            // Filters.vakkenPakket(studenten, testStudentNummer);
            // Filters.nogHalen(studenten, testStudentNummer);
            // Filters.vakkenGehaald(studenten, testVakCode);
            // Filters.gemiddeldeVak(studenten, testVakCode);
            // Filters.gewogenGemiddelde(studenten, testStudentNummer);
            // Filters.percentageBehaald(studenten, testVakCode);
            // Filters.wanneerKlaar(studenten, testStudentNummer);

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
