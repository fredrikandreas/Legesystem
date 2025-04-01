public class BlaaResept extends Resept {
    public BlaaResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
        super(legemiddel, utskrivendeLege, pasient, reit);
    }

    public String farge() {
        return "Blaa";
    }

    public double prisAaBetale() {
        return legemiddel.hentPris()*0.25;
    }

    @Override
    public String toString() {
        String s1 = "Blaa resept\n\n";
        String s2 = super.toString();
        return s1+s2;
    }
}
