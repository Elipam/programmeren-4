import java.util.List;
import java.util.stream.Collectors;

public class Filters {
    static int totaalStudiePunten = 0;
    static int behaaldeStudiePunten = 0;
    static int max = 0;
    static int tel = 0;
    static float temp = 0;

    public static void inKlas(List<Student> studenten, String testKlas){
        List<Student> filter = studenten.stream()
        .filter(student -> student.klas.equals(testKlas))                                         
        .collect(Collectors.toList());
        for (Student student : filter) {                                                             
            System.out.println(student.naam);
        }
    }

    public static void vakVolgen(List<Student> studenten, String testVak){
        List<Student> filter = studenten.stream()
        .filter(student -> student.vakkenpakket.stream()                                        
        .anyMatch(vak -> vak.naamOfCijfer.equals(testVak)))                               
        .collect(Collectors.toList());
        for (Student student : filter) {                                                             
            System.out.println(student.naam);
        }
                
    }

    public static void vakkenPakket(List<Student> studenten, String testStudentNummer){
        List<Student> filter = studenten.stream()
        .filter(student -> student.studentnummer.equals(testStudentNummer))
        .collect(Collectors.toList());
        for (Student student : filter) { 
            for (Vakken<String,String,Integer> vak : student.vakkenpakket) {
                System.out.println(vak.vakcode);
                System.out.println(vak.naamOfCijfer);
                System.out.println(vak.ec);
            }
        }
    }

    public static void nogHalen(List<Student> studenten, String testStudentNummer){
        List<Student> filter = studenten.stream()   
        .filter(student -> student.studentnummer.equals(testStudentNummer))
        .collect(Collectors.toList());
        for (Student student : filter) {
            for (Vakken<String,Float,Void> vak : student.resultaten) {
                float cijfer = (float) vak.naamOfCijfer;
                if (cijfer < 5.5) {
                    System.out.println(vak.vakcode);
                    System.out.println(vak.naamOfCijfer);
                }
            }
        }
    }

    public static void vakkenGehaald(List<Student> studenten, String testVak){
        List<Student> filter = studenten.stream()   
        .filter(student -> student.resultaten.stream()                                        
        .anyMatch(vak -> vak.vakcode.equals(testVak)))                               
        .collect(Collectors.toList());
        for (Student student : filter) {                                                             
            for (Vakken<String,Float,Void> vak : student.resultaten) {
                float cijfer = (float) vak.naamOfCijfer;
                String vakcode = (String) vak.vakcode;
                if (cijfer > 5.5 && vakcode.equals(testVak)) {
                    System.out.println(student.naam);
                }
            }
        }
    }

    public static void gemiddeldeVak(List<Student> studenten, String testVak){
        List<Student> filter = studenten.stream()
        .filter(student -> student.resultaten.stream()                                        
        .anyMatch(vak -> vak.vakcode.equals(testVak)))                               
        .collect(Collectors.toList());
        for (Student student : filter) {                                                             
            for (Vakken<String,Float,Void> vak : student.resultaten) {
                float cijfer = (float) vak.naamOfCijfer;
                String vakcode = (String) vak.vakcode;
                if (vakcode.equals(testVak)) {
                    temp = temp + cijfer;
                    tel++;
                }
            }
        }
        System.out.println(temp / tel);
    }

    public static void gewogenGemiddelde(List<Student> studenten, String testStudentNummer){
        List<Student> filter = studenten.stream()
        .filter(student -> student.studentnummer.equals(testStudentNummer))
        .collect(Collectors.toList());
        for (Student student : filter) {
            for (Vakken<String,Float,Void> vak : student.resultaten) {
                for (Vakken<String,String,Integer> vak2 : student.vakkenpakket) {
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
    }

    public static void percentageBehaald(List<Student> studenten, String testVak){
        List<Student> filter = studenten.stream()
        .filter(student -> student.resultaten.stream()                                        
        .anyMatch(vak -> vak.vakcode.equals(testVak)))                               
        .collect(Collectors.toList());
        for (Student student : filter) {                                                             
            for (Vakken<String,Float,Void> vak : student.resultaten) {
                float cijfer = (float) vak.naamOfCijfer;
                String vakcode = (String) vak.vakcode;
                if (cijfer >= 5.5 && vakcode.equals(testVak)) {
                    max++;
                    tel++;
                }
                else if (cijfer < 5.5 && vakcode.equals(testVak)) {
                    max++;
                }
            }
        }
        System.out.println(((float)tel / max)*100 + "%");
    }

    public static void wanneerKlaar(List<Student> studenten, String testStudentNummer){
        List<Student> filter = studenten.stream()
        .filter(student -> student.studentnummer.equals(testStudentNummer))
        .collect(Collectors.toList());
        for (Student student : filter) {
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