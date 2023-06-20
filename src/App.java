import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
            String testVak = "WI101";
            String testKlas = "TI1.1";
            int tel = 0;
            float temp = 0;
            // List<Student> filter = studenten.stream()
            // .filter(student -> student.klas.equals(testKlas))                                         
            // .collect(Collectors.toList());
            // for (Student student : filter) {                                                             
            //     System.out.println(student.naam);
            // }
                                
            // List<Student> filter = studenten.stream()
            // .filter(student -> student.vakkenpakket.stream()                                        
            // .anyMatch(vak -> vak.naamOfCijfer.equals("Engels 1")))                               
            // .collect(Collectors.toList());
            // for (Student student : filter) {                                                             
            //     System.out.println(student.naam);
            // }
                
            // List<Student> filter = studenten.stream()
            // .filter(student -> student.studentnummer.equals(testStudentNummer))
            // .collect(Collectors.toList());
            // for (Student student : filter) { 
            //     for (Vakken vak : student.vakkenpakket) {
            //         System.out.println(vak.vakcode);
            //         System.out.println(vak.naamOfCijfer);
            //         System.out.println(vak.ec);
            //     }
            // }

            // List<Student> filter = studenten.stream()   
            // .filter(student -> student.studentnummer.equals(testStudentNummer))
            // .collect(Collectors.toList());
            // for (Student student : filter) {
            //     for (Vakken vak : student.resultaten) {
            //         float cijfer = (float) vak.naamOfCijfer;
            //         if (cijfer < 5.5) {
            //             System.out.println(vak.vakcode);
            //             System.out.println(vak.naamOfCijfer);
            //         }
            //     }
            // }

            // List<Student> filter = studenten.stream()   
            // .filter(student -> student.resultaten.stream()                                        
            // .anyMatch(vak -> vak.vakcode.equals(testVak)))                               
            // .collect(Collectors.toList());
            // for (Student student : filter) {                                                             
            //     for (Vakken vak : student.resultaten) {
            //         float cijfer = (float) vak.naamOfCijfer;
            //         String vakcode = (String) vak.vakcode;
            //         if (cijfer > 5.5 && vakcode.equals(testVak)) {
            //             System.out.println(student.naam);
            //         }
            //     }
            // }

            // List<Student> filter = studenten.stream()
            // .filter(student -> student.resultaten.stream()                                        
            // .anyMatch(vak -> vak.vakcode.equals(testVak)))                               
            // .collect(Collectors.toList());
            // for (Student student : filter) {                                                             
            //     for (Vakken vak : student.resultaten) {
            //         float cijfer = (float) vak.naamOfCijfer;
            //         String vakcode = (String) vak.vakcode;
            //         if (vakcode.equals(testVak)) {
            //             temp = temp + cijfer;
            //             tel++;
            //         }
            //     }
            // }
            // System.out.println(temp / tel);

            List<Student> filter = studenten.stream()
                .filter(student -> student.studentnummer.equals(testStudentNummer))
                .collect(Collectors.toList());

            for (Student student : filter) {
                for (Vakken vak : student.resultaten) {
                    for (Vakken vak2 : student.vakkenpakket) {
                        if (vak.vakcode.equals(vak2.vakcode)) {
                            float cijfer = (float) vak.naamOfCijfer;
                            int studiePunten = (int) vak2.ec;
                            temp += cijfer * studiePunten;
                            tel += studiePunten;
                        }
                    }
                }
            }

            System.out.println(temp / tel);


        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
