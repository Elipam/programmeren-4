// Eliam Traas - 1003233
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Administratie {
    private static int totaalStudiePunten = 0;
    private static int behaaldeStudiePunten = 0;
    private static int max = 0;
    private static int tel = 0;
    private static float temp = 0;
    private static String hold;
    private static boolean bool = true;

    public static List<Student> haalStdNummer(List<Student> studenten, String testStudentNummer){
        // filter op doorgegeven studentnummer door middel van een stream
        List<Student> filter = studenten.stream()
        .filter(student -> student.getStudentnummer().equals(testStudentNummer))
        .toList();
        return filter;
    }

    public static List<Student> haalVak(List<Student> studenten, String testVak){
        // filter op doorgegeven vak door middel van een stream
        List<Student> filter = studenten.stream()
        .filter(student -> student.getResultaten().stream()                                        
        .anyMatch(vak -> vak.getVakcode().equals(testVak)))                               
        .toList();
        return filter;
    }

    public static <T> void klasOfVak(List<Student> studenten, T a) {  
        // filter voor het vinden van studenten in doorgegeven klas
        List<Student> filter = studenten.stream()
        .filter(student -> student.getKlas().equals((String) a))                                         
        .toList();

        List<Student> filter2 = studenten.stream()
        .filter(student -> student.getVakkenpakket().stream()                                        
        .anyMatch(vak -> vak.getVakcode().equals(a)))                               
        .toList();    
            
        if (filter.size() == 0) {
            // filter om te checken of studenten een vak volgen
            for (Student student : filter2) {                                                             
                System.out.println(student.getNaam());
            }    
        }
        else{
            //print elke student in filter
            for (Student student : filter) {             
                System.out.println(student.getNaam());
            }
        }
    }

    // public static void inKlas(List<Student> studenten, String testKlas){
    //     // filter voor het vinden van studenten in doorgegeven klas
    //     List<Student> filter = studenten.stream()
    //     .filter(student -> student.getKlas().equals(testKlas))                                         
    //     .toList();
    //     //print elke student in filter
    //     for (Student student : filter) {             
    //         System.out.println(student.getNaam());
    //     }
    // }
    
    // public static void vakVolgen(List<Student> studenten, String testVak){
    //     // filter om te checken of studenten een vak volgen
    //     List<Student> filter = studenten.stream()
    //     .filter(student -> student.getVakkenpakket().stream()                                        
    //     .anyMatch(vak -> vak.getVakcode().equals(testVak)))                               
    //     .toList();        
    //     for (Student student : filter) {                                                             
    //         System.out.println(student.getNaam());
    //     }    
    // }
    
    public static void vakkenPakket(List<Student> studenten, String testStudentNummer){
        // filter op studentnummer 
        for (Student student : haalStdNummer(studenten, testStudentNummer)) { 
            // print voor elk vak in vakkenpakket van gefilterde student
            for (Vak<String,String,Integer> vak : student.getVakkenpakket()) {
                System.out.println(vak.getVakcode());
                System.out.println(vak.getNaamOfCijfer());
                System.out.println(vak.getEc());
                System.out.println();
            }
        }
    }

    public static void nogHalen(List<Student> studenten, String testStudentNummer) {             
        // filter op studentnummer
        List<Vak<String, Float, Void>> filter2 = haalStdNummer(studenten, testStudentNummer).stream()
        .flatMap(student -> student.getResultaten().stream())
        // filter de cijfers in resultaten van gefilterde student
        .filter(vak -> vak.getNaamOfCijfer() < 5.5)
        .toList();

        // print voor elk vak in de filter
        for (Vak<String, Float, Void> vak : filter2) {
            System.out.println(vak.getVakcode());
        }

        // filter op studentnummer
        for (Student student : haalStdNummer(studenten, testStudentNummer)){
            // filter met for loops ipv streams, omdat ik niet de resultaten en het vakkenpakket tegelijk kan streamen
            for (Vak<String,String,Integer> vak : student.getVakkenpakket()){
                for (Vak<String,Float,Void> vak2 : student.getResultaten()){
                    // loopt door vakken heen totdat het een vak vindt dat in zowel de resultaten als het vakkenpakket zit
                    // zo niet dan heeft de student er nog geen cijfer voor en moet die het vak nog halen
                    if (vak.getVakcode().equals(vak2.getVakcode())) {
                        bool = true;
                        break;                        
                    }
                    else{
                        bool = false;
                    }
                }
                if (bool == false) {
                    // print vak in filter
                    hold = vak.getVakcode();
                    System.out.println(hold);
                }
            }
        }
    }

    public static void vakkenGehaald(List<Student> studenten, String testVak){
        // filter op bepaald vak en check of vak gehaald is
        List<Student> filter = studenten.stream()   
        .filter(student -> student.getResultaten().stream()                                        
        .anyMatch(vak -> vak.getVakcode().equals(testVak) && vak.getNaamOfCijfer() >= 5.5))                               
        .toList();
        for (Student student : filter) {                                                             
            System.out.println(student.getNaam());
        }
    }

    public static void gemiddeldeVak(List<Student> studenten, String testVak){
        // filter op bepaald vak
        List<Vak<String, Float, Void>> filter2 = haalVak(studenten, testVak).stream()
        // stream voor vakken ipv studenten
        .flatMap(student -> student.getResultaten().stream())
        .toList();
        // bereken gemiddelde van alle vakken in filter
        for (Vak<String, Float, Void> vak : filter2) {                                                             
            tel++;
            temp = temp + vak.getNaamOfCijfer();
        }
        System.out.println(temp/tel);
    }

    public static void gewogenGemiddelde(List<Student> studenten, String testStudentNummer){
        // filter op student nummer met minder streams, want ik kan niet vakkenpakket en resultaten tegelijk streamen 
        for (Student student : haalStdNummer(studenten, testStudentNummer)) {
            for (Vak<String,Float,Void> vak : student.getResultaten()) {
                for (Vak<String,String,Integer> vak2 : student.getVakkenpakket()) {
                    if (vak.getVakcode().equals(vak2.getVakcode())) {
                        // bereken voor elk vak van student gewogen gemiddelde
                        float cijfer = (float) vak.getNaamOfCijfer();
                        int studiePunten = (int) vak2.getEc();
                        temp = temp + (cijfer * studiePunten);
                        tel = tel + studiePunten;
                    }
                }
            }
        }
        System.out.println(temp / tel);
    }

    public static void percentageBehaald(List<Student> studenten, String testVak){
        // filter op vakcode in vakkenpakket
        List<Vak<String, String, Integer>> filter = studenten.stream()
        .flatMap(student -> student.getVakkenpakket().stream())
        .filter(vak -> vak.getVakcode().equals(testVak))
        .toList();
        for (Vak<String, String, Integer> vak : filter) {
            max++;
        }
        
        // filter op vakcode in resultaten 
        List<Vak<String, Float, Void>> filter2 = haalVak(studenten, testVak).stream()
        .flatMap(student -> student.getResultaten().stream())
        .toList();
        for (Vak<String, Float, Void> vak : filter2) {
            // als cijfer voldoende is tel en max omhoog                                                             
            float cijfer = (float) vak.getNaamOfCijfer();
            if (cijfer >= 5.5 && vak.getVakcode().equals(testVak)) {
                tel++;
            }
        }
        // tel gedeelt door max keer 100 is het pecentage 
        System.out.println(((float)tel / max)*100 + "%");
        System.out.println(tel + max);
    }

    public static void wanneerKlaar(List<Student> studenten, String testStudentNummer){
        // filter op studentnummer
        for (Student student : haalStdNummer(studenten, testStudentNummer)) {
            for (Vak<String,String,Integer> vak2 : student.getVakkenpakket()) {
                // bereken totaal studiepunten
                int studiePunten = (int) vak2.getEc();
                totaalStudiePunten = totaalStudiePunten + studiePunten;
                for (Vak<String,Float,Void> vak : student.getResultaten()) {
                    // check of vak behaald is, zo ja tel studiepunten
                    if (vak.getVakcode().equals(vak2.getVakcode())) {
                        float cijfer = (float) vak.getNaamOfCijfer();    
                        if (cijfer >= 5.5) {
                            behaaldeStudiePunten = behaaldeStudiePunten + studiePunten;
                        }
                    }
                }
            }
        }
        // behaalde studiepunten delen door totaal studiepunten keer 100 is hoeveel procent van de studie je al achter de rug hebt 
        System.out.println(((float) behaaldeStudiePunten / totaalStudiePunten)*100 + "%");
    }
}