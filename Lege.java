public class Lege implements Comparable<Lege>{
    public final String navn;
    public IndeksertListe<Resept> utskrevneResepter = new IndeksertListe<>();

    public Lege(String navn) {
        this.navn = navn;
    }

    public String hentNavn() {
        return navn;
    }

    @Override
    public String toString() {
        return "Lege\n"+"Navn: "+navn+"\n";
    }

    @Override
    public int compareTo(Lege lege) {
        if (navn.compareTo(lege.navn) < 0) return -1;
        else if (navn.compareTo(lege.navn) > 0) return 1;
        else return 0;
    }

    public IndeksertListe<Resept> hentUtskrevneResepter() {
        return utskrevneResepter;
    }

    public HvitResept skrivHvitResept (Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        if (legemiddel instanceof Narkotisk) 
            throw new UlovligUtskrift(this, legemiddel);
        
        HvitResept resept = new HvitResept(legemiddel, this, pasient, reit);
        utskrevneResepter.leggTil(resept);
        pasient.leggTilResept(resept);
        return resept;
    }

    public MilResept skrivMilResept (Legemiddel legemiddel, Pasient pasient) throws UlovligUtskrift {
        if (legemiddel instanceof Narkotisk) 
            throw new UlovligUtskrift(this, legemiddel);
        
        MilResept resept = new MilResept(legemiddel, this, pasient);
        utskrevneResepter.leggTil(resept);
        pasient.leggTilResept(resept);
        return resept;
    }

    public BlaaResept skrivBlaaResept (Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        if (legemiddel instanceof Narkotisk) 
            throw new UlovligUtskrift(this, legemiddel);
        
        BlaaResept resept = new BlaaResept(legemiddel, this, pasient, reit);
        utskrevneResepter.leggTil(resept);
        pasient.leggTilResept(resept);
        return resept;
    }

    public PResept skrivPResept (Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        if (legemiddel instanceof Narkotisk) 
            throw new UlovligUtskrift(this, legemiddel);
        
        PResept resept = new PResept(legemiddel, this, pasient, reit);
        utskrevneResepter.leggTil(resept);
        pasient.leggTilResept(resept);
        return resept;
    }


}
