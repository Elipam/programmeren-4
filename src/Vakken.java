public class Vakken <Thing, Thing2>{
    public Thing vakcode;
    public Thing2 cijfer;

    public Vakken(Thing vakcode, Thing2 cijfer) {
        this.vakcode = vakcode;
        this.cijfer = cijfer;
    }
    public Thing getVakcode() {
        return vakcode;
    }
    public Thing2 getCijfer() {
        return cijfer;
    }
}