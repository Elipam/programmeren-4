import java.util.List;

class Student {
    public String studentnummer;
    public String naam;
    public String klas;
    public String studierichting;
    public String studiejaar;
    private List<Vakken> vakken;

    public Student(String studentnummer, String naam, String klas, String studierichting, String studiejaar, List<Vakken> vakken) {
        this.studentnummer = studentnummer;
        this.naam = naam;
        this.klas = klas;
        this.studierichting = studierichting;
        this.studiejaar = studiejaar;
        this.vakken = vakken;
    }
    public List<Vakken> getVakcode() {
        return vakken;
    }
}