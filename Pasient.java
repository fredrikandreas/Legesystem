public class Pasient {
    public static int idTeller = 1;
    public String navn;
    public String fodselsnummer;
    public int pasientID;
    public IndeksertListe<Resept> resepter = new IndeksertListe<>();

    public Pasient(String navn, String fodselsnummer) {
        this.navn = navn;
        this.fodselsnummer = fodselsnummer;
        pasientID = idTeller;
        idTeller++;
    }

    public void leggTilResept(Resept r) {
        resepter.leggTil(r);
    }

    public IndeksertListe<Resept> hentResepter() {
        return this.resepter;
    }

    public String hentNavn() {
        return this.navn;
    }

    public String hentFoedselsnummer() {
        return this.fodselsnummer;
    }

    public int hentId() {
        return this.pasientID;
    }

    @Override
    public String toString() {
        return "Navn : "+navn + "\n"+
                "Fodselsnummer: "+fodselsnummer+"\n"+
                "ID: "+pasientID+"\n"+
                "Antall resepter: "+resepter.stoerrelse()+"\n";
    }
}
