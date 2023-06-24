// Eliam Traas - 1003233
class Vak <A, B, C>{
    private A vakcode;
    private B naamOfCijfer;
    private C ec;

    // de generic class om vakgegevens met een willekeurig datatype op te slaan
    public Vak(A vakcode, B naamOfCijfer, C ec) {
        this.vakcode = vakcode;
        this.naamOfCijfer = naamOfCijfer;
        this.ec = ec;
    }

    // getters
    public A getVakcode() {
        return vakcode;
    }
    public B getNaamOfCijfer() {
        return naamOfCijfer;
    }

    public C getEc() {
        return ec;
    }
}