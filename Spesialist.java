public class Spesialist extends Lege implements Godkjenningsfritak{ 
    public final String kontrollKode;

    public Spesialist(String navn, String kontrollKode) {
        super(navn);
        this.kontrollKode = kontrollKode;
    }

    public String hentKontrollKode() {
        return kontrollKode;
    }

    @Override
    public String toString() {
        return "Spesialist\n"+"Navn: "+navn+"\nKontrollkode: "+kontrollKode+"\n";
    }

    @Override
    public BlaaResept skrivBlaaResept (Legemiddel legemiddel, Pasient pasient, int reit) {
        BlaaResept resept = new BlaaResept(legemiddel, this, pasient, reit);
        utskrevneResepter.leggTil(resept);
        pasient.leggTilResept(resept);
        return resept;
    }
}
