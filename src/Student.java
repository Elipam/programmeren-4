// Eliam Traas - 1003233
import java.util.List;

class Student {
    private String studentnummer;
    private String naam;
    private String klas;
    private String studierichting;
    private String studiejaar;
    private List<Vak<String, Float, Void>> resultaten;
    private List<Vak<String, String, Integer>> vakkenpakket;
    
    // constructor voor de student
    public Student(String studentnummer, String naam, String klas, String studierichting, String studiejaar,
                   List<Vak<String, Float, Void>> resultaten, List<Vak<String, String, Integer>> vakkenpakket) {
        this.studentnummer = studentnummer;
        this.naam = naam;
        this.klas = klas;
        this.studierichting = studierichting;
        this.studiejaar = studiejaar;
        this.resultaten = resultaten;
        this.vakkenpakket = vakkenpakket;
    }

    // getters 
    public String getStudentnummer() {
        return studentnummer;
    }

    public String getNaam() {
        return naam;
    }

    public String getKlas() {
        return klas;
    }

    public List<Vak<String, Float, Void>> getResultaten() {
        return resultaten;
    }

    public List<Vak<String, String, Integer>> getVakkenpakket(){
        return vakkenpakket;
    }
}
