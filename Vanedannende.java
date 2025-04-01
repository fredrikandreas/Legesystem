public class Vanedannende extends Legemiddel {
    public final int styrke;

    public Vanedannende(String navn, double pris, double virkestoff, int styrke) {
        super(navn, pris, virkestoff);
        this.styrke = styrke;
    }
    @Override
    public String toString() {
        return "Type: Vanedannende"+"\nNavn: "+navn+"\nID: "+id+"\nPris: "+pris+"\nVirkestoff: "+virkestoff+"\nStyrke: "+styrke+"\n";
    }

    public int hentStyrke() {
        return this.styrke;
    }
}
