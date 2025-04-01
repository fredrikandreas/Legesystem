public class PResept extends HvitResept {
    public PResept(Legemiddel legemiddel, Lege lege, Pasient pasient, int reit) {
        super(legemiddel, lege, pasient, reit);
    }

    public double prisAaBetale() {
        double pris = legemiddel.hentPris();
        if ((pris-108)<0) {
            return 0;
        }
        return pris-108;
    }

    @Override
    public String toString() {
        String s1 = "Prevensjonsresept\n";
        String s2 = super.toString();
        return s1+s2;
    }
}