import java.util.List;

public class Filters {
    private static int totaalStudiePunten = 0;
    private static int behaaldeStudiePunten = 0;
    private static int max = 0;
    private static int tel = 0;
    private static float temp = 0;
    private static String hold;
    private static boolean bool;

    public static List<Student> haalStdNummer(List<Student> studenten, String testStudentNummer){
        List<Student> filter = studenten.stream()
        .filter(student -> student.studentnummer.equals(testStudentNummer))
        .toList();
        return filter;
    }

    public static List<Student> haalVak(List<Student> studenten, String testVak){
        List<Student> filter = studenten.stream()
        .filter(student -> student.resultaten.stream()                                        
        .anyMatch(vak -> vak.vakcode.equals(testVak)))                               
        .toList();
        return filter;
    }

    public static void inKlas(List<Student> studenten, String testKlas){
        List<Student> filter = studenten.stream()
        .filter(student -> student.klas.equals(testKlas))                                         
        .toList();
        for (Student student : filter) {             
            System.out.println(student.naam);
        }
    }

    public static void vakVolgen(List<Student> studenten, String testVak){
        List<Student> filter = studenten.stream()
        .filter(student -> student.vakkenpakket.stream()                                        
        .anyMatch(vak -> vak.vakcode.equals(testVak)))                               
        .toList();        
        for (Student student : filter) {                                                             
            System.out.println(student.naam);
        }    
    }

    public static void vakkenPakket(List<Student> studenten, String testStudentNummer){
        for (Student student : haalStdNummer(studenten, testStudentNummer)) { 
            for (Vakken<String,String,Integer> vak : student.vakkenpakket) {
                System.out.println(vak.vakcode);
                System.out.println(vak.naamOfCijfer);
                System.out.println(vak.ec);
                System.out.println();
            }
        }

    }

    public static void nogHalen(List<Student> studenten, String testStudentNummer) {             
        List<Vakken<String, Float, Void>> filter2 = haalStdNummer(studenten, testStudentNummer).stream()
        .flatMap(student -> student.resultaten().stream())
        .filter(vak -> vak.getNaamOfCijfer() < 5.5)
        .toList();

        for (Vakken<String, Float, Void> vak : filter2) {
            System.out.println(vak.vakcode);
        }

        for (Student student : haalStdNummer(studenten, testStudentNummer)){
            for (Vakken<String,String,Integer> vak : student.vakkenpakket){
                for (Vakken<String,Float,Void> vak2 : student.resultaten){
                    if (vak.vakcode.equals(vak2.vakcode)) {
                        bool = true;
                        break;                        
                    }
                    else{
                        bool = false;
                    }
                }
                if (bool == false) {
                    hold = vak.vakcode;
                }
            }
        }
        System.out.println(hold);
    }

    public static void vakkenGehaald(List<Student> studenten, String testVak){
        List<Student> filter = studenten.stream()   
        .filter(student -> student.resultaten.stream()                                        
        .anyMatch(vak -> vak.vakcode.equals(testVak) && vak.naamOfCijfer > 5.4))                               
        .toList();
        for (Student student : filter) {                                                             
            System.out.println(student.naam);
        }
    }

    public static void gemiddeldeVak(List<Student> studenten, String testVak){
        List<Vakken<String, Float, Void>> filter2 = haalVak(studenten, testVak).stream()
        .flatMap(student -> student.resultaten().stream())
        .filter(vak -> vak.vakcode.equals(testVak))
        .toList();
        for (Vakken<String, Float, Void> vak : filter2) {                                                             
            tel++;
            temp = temp + vak.naamOfCijfer;
        }
        System.out.println(temp/tel);
    }

    public static void gewogenGemiddelde(List<Student> studenten, String testStudentNummer){
        for (Student student : haalStdNummer(studenten, testStudentNummer)) {
            for (Vakken<String,Float,Void> vak : student.resultaten) {
                for (Vakken<String,String,Integer> vak2 : student.vakkenpakket) {
                    if (vak.vakcode.equals(vak2.vakcode)) {
                        float cijfer = (float) vak.naamOfCijfer;
                        int studiePunten = (int) vak2.ec;
                        temp = temp + (cijfer * studiePunten);
                        tel = tel + studiePunten;
                    }
                }
            }
        }
        System.out.println(temp / tel);
    }

    public static void percentageBehaald(List<Student> studenten, String testVak){
        List<Vakken<String, Float, Void>> filter2 = haalVak(studenten, testVak).stream()
        .flatMap(student -> student.resultaten().stream())
        .filter(vak -> vak.vakcode.equals(testVak))
        .toList();
        for (Vakken<String, Float, Void> vak : filter2) {                                                             
            float cijfer = (float) vak.naamOfCijfer;
            if (cijfer >= 5.5 && vak.vakcode.equals(testVak)) {
                max++;
                tel++;
            }
            else if (cijfer < 5.5 && vak.vakcode.equals(testVak)) {
                max++;
            }
            
        }
        System.out.println(((float)tel / max)*100 + "%");
    }

    public static void wanneerKlaar(List<Student> studenten, String testStudentNummer){
        for (Student student : haalStdNummer(studenten, testStudentNummer)) {
            for (Vakken<String,String,Integer> vak2 : student.vakkenpakket) {
                int studiePunten = (int) vak2.ec;
                totaalStudiePunten = totaalStudiePunten + studiePunten;
                for (Vakken<String,Float,Void> vak : student.resultaten) {
                    if (vak.vakcode.equals(vak2.vakcode)) {
                        float cijfer = (float) vak.naamOfCijfer;    
                        if (cijfer >= 5.5) {
                            behaaldeStudiePunten = behaaldeStudiePunten + studiePunten;
                        }
                    }
                }
            }
        }
        System.out.println(((float) behaaldeStudiePunten / totaalStudiePunten)*100 + "%");

    }
}