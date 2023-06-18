import java.lang.reflect.Array;

class Student {
    public String studentnummer;
    public String naam;
    public String klas;
    public String studierichting;
    public String studiejaar;
    public Array behaaldeCijfers;

    public Student(String studentnummer, String naam, String klas, String studierichting, String studiejaar, Array behaaldeCijfers) {
        this.studentnummer = studentnummer;
        this.naam = naam;
        this.klas = klas;
        this.studierichting = studierichting;
        this.studiejaar = studiejaar;
        this.behaaldeCijfers = behaaldeCijfers;
    }
}