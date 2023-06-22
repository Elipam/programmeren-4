import java.util.List;

class Student {
    public String studentnummer;
    public String naam;
    public String klas;
    public String studierichting;
    public String studiejaar;
    public List<Vakken<String, Float, Void>> resultaten;
    public List<Vakken<String, String, Integer>> vakkenpakket;

    public Student(String studentnummer, String naam, String klas, String studierichting, String studiejaar,
                   List<Vakken<String, Float, Void>> resultaten, List<Vakken<String, String, Integer>> vakkenpakket) {
        this.studentnummer = studentnummer;
        this.naam = naam;
        this.klas = klas;
        this.studierichting = studierichting;
        this.studiejaar = studiejaar;
        this.resultaten = resultaten;
        this.vakkenpakket = vakkenpakket;
    }

    public List<Vakken<String,String,Integer>> getVakkenpakket() {
        return vakkenpakket;
    }

    public String getStudentnummer() {
        return studentnummer;
    }

    public List<Vakken<String, Float, Void>> resultaten() {
        return resultaten;
    }
}
