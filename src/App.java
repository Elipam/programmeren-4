// Eliam Traas - 1003233
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
        String bestandName = "C:\\Users\\eliam\\code\\programmeren-4\\src\\studentData_v1.json";
        JSONParser parser = new JSONParser();

        // try en catch blok voor de parser
        try {
            // de parser leest door het bestand en zet alle informatie in studentenArray
            JSONArray studentenArray = (JSONArray) parser.parse(new FileReader(bestandName));

            // maak een datastructuur Arraylist om de sudenten in op te slaan
            List<Student> studenten = new ArrayList<>();

            // voor elke student in de array:
            for (Object obj : studentenArray) {
                JSONObject studentObj = (JSONObject) obj;

                // bindt studentengegevens aan variabelen
                String studentnummer = (String) studentObj.get("studentnummer");
                String naam = (String) studentObj.get("naam");
                String klas = (String) studentObj.get("klas");
                String studierichting = (String) studentObj.get("studierichting");
                String studiejaar = (String) studentObj.get("studiejaar");

                // maakt een nieuwe lijst binnen de student om behaalde cijfers van de student op te slaan en haalt array op van bestand
                JSONArray behaaldeCijfers = (JSONArray) studentObj.get("behaalde_cijfers");
                List<Vak<String, Float, Void>> resultaten = new ArrayList<>();

                // loopt door de behaalde cijfers en bindt de gegevens aan variabelen
                for (Object obje : behaaldeCijfers) {
                    JSONObject studentObje = (JSONObject) obje;
                    String vakcode = (String) studentObje.get("vakcode");
                    float cijfer = ((Number) studentObje.get("cijfer")).floatValue();
                    // zet gegevens in een nieuw object en voegt het toe aan de lijst
                    Vak<String, Float, Void> vak = new Vak<>(vakcode, cijfer, null);
                    resultaten.add(vak);
                }

                // maakt weer een lijst aan en haalt gegevens array op uit bestand
                JSONArray vakkenpakketArray = (JSONArray) studentObj.get("vakkenpakket");
                List<Vak<String, String, Integer>> vakkenpakket = new ArrayList<>();

                // loop door array om per student gegevens aan variabelen te binden
                for (Object obje : vakkenpakketArray) {
                    JSONObject vakkenpakketObj = (JSONObject) obje;
                    String vakcode = (String) vakkenpakketObj.get("vakcode");
                    String vaknaam = (String) vakkenpakketObj.get("naam");
                    int ec = ((Number) vakkenpakketObj.get("ec")).intValue();
                    // zet gegevens in lijst mbv de Vakken class
                    Vak<String, String, Integer> vak = new Vak<>(vakcode, vaknaam, ec);
                    vakkenpakket.add(vak);
                }


                // maak student aan met de Student class en voeg hem toe aan de datastructuur
                Student student = new Student(studentnummer, naam, klas, studierichting, studiejaar, resultaten, vakkenpakket);
                studenten.add(student);
            }

            //filters om bepaalde prestaties van de student te bekijken
            String testStudentNummer = "7654422";
            String testVakCode = "TI201";
            String testKlas = "TI2.2";

            // Administratie.inKlas(studenten, testKlas);
            // Administratie.vakVolgen(studenten, testVakCode);
            // Administratie.vakkenPakket(studenten, testStudentNummer);
            // Administratie.nogHalen(studenten, testStudentNummer);
            // Administratie.vakkenGehaald(studenten, testVakCode);
            // Administratie.gemiddeldeVak(studenten, testVakCode);
            // Administratie.gewogenGemiddelde(studenten, testStudentNummer);
            Administratie.percentageBehaald(studenten, testVakCode);
            // Administratie.wanneerKlaar(studenten, testStudentNummer);

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}