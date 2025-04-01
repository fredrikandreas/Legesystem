public abstract class Resept {
    static int idTeller = 1;
    public Legemiddel legemiddel;
    public final int id;
    public Lege utskrivendeLege;
    public final Pasient pasient;
    public int reit;

    public Resept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
        this.legemiddel = legemiddel;
        this.utskrivendeLege = utskrivendeLege;
        this.pasient = pasient;
        this.reit = reit;
        this.id = idTeller;
        idTeller++;
    }

    public int hentId() {
        return id;
    }

    public Legemiddel hentLegemiddel() {
        return legemiddel;
    }

    public Lege hentLege() {
        return utskrivendeLege;
    }

    public Pasient hentPasient() {
        return pasient;
    }

    public int hentReit() {
        return reit;
    }

    public boolean bruk() {
        if (reit > 0) {
            reit -= 1;
            return true;
        }else {
            return false;
        }
    }

    @Override
    public String toString() {
        String s2 = "Legemiddel: "+legemiddel;
        String s3 = "\nLege: "+utskrivendeLege;
        String s4 = "\nPasient: \n"+pasient;
        String s5 = "\nReit: "+reit;
        return s2+s3+s4+s5+"\n";
    }

    abstract public String farge();
    abstract public double prisAaBetale();
}
