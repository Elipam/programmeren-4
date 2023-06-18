class Student {
    private String studentnummer;
    private String naam;
    private String klas;
    private String studierichting;
    private String studiejaar;

    public Student(String studentnummer, String naam, String klas, String studierichting, String studiejaar) {
        this.studentnummer = studentnummer;
        this.naam = naam;
        this.klas = klas;
        this.studierichting = studierichting;
        this.studiejaar = studiejaar;
    }

    // Getters en setters

    @Override
    public String toString() {
        return "Student{" +
                "studentnummer='" + studentnummer + '\'' +
                ", naam='" + naam + '\'' +
                ", klas='" + klas + '\'' +
                ", studierichting='" + studierichting + '\'' +
                ", studiejaar='" + studiejaar + '\'' +
                '}';
    }
}