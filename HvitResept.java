public class HvitResept extends Resept {
    public HvitResept(Legemiddel legemiddel, Lege lege, Pasient pasient, int reit) {
        super(legemiddel, lege, pasient, reit);
    }

    public String farge() {
        return "hvit";
    }

    public double prisAaBetale() {
        return legemiddel.hentPris();
    }

    @Override
    public String toString() {
        String s1 = "Hvit resept\n\n";
        String s2 = super.toString();
        return s1+s2;
    }
}
