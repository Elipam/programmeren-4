import java.util.List;

class Student {
    public String studentnummer;
    public String naam;
    public String klas;
    public String studierichting;
    public String studiejaar;
    public List<Vakken<String, Float>> resultaten;

    public Student(String studentnummer, String naam, String klas, String studierichting, String studiejaar, List<Vakken<String, Float>> resultaten) {
        this.studentnummer = studentnummer;
        this.naam = naam;
        this.klas = klas;
        this.studierichting = studierichting;
        this.studiejaar = studiejaar;
        this.resultaten = resultaten;
    }

    public List<Vakken<String, Float>> getVakcode() {
        return resultaten;
    }
}