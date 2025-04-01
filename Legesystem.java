import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

public class Legesystem {
    Prioritetskoe<Lege> legeListe = new Prioritetskoe<>();
    IndeksertListe<Pasient> pasientListe = new IndeksertListe<>();
    IndeksertListe<Legemiddel> legemidler = new IndeksertListe<>();
    IndeksertListe<Resept> resepter = new IndeksertListe<>();

    public void lesFraFil(String filNavn) {
        try {
            File fil = new File(filNavn);
            Scanner sc = new Scanner(fil);
            String[] linje = sc.nextLine().strip().split(" ",3);
            while (sc.hasNextLine()) {
                // Linje vil alltid begynne med "#" i denne hovedfunksjonen,
                // utenom naar man har kommet til slutten av filen som leses inn
                // Alle hjelpemetodene leser inn objekter til de kommer til en linje
                // som begynner med #, som de returnerer og blir den nye verdien for variabelen linje
                if (linje[1].equals("Pasienter")) linje = lesPasienter(sc);
                else if (linje[1].equals("Resepter")) linje = lesResepter(sc);
                else if (linje[1].equals("Legemidler")) linje = lesLegemidler(sc);
                else if(linje[1].equals("Leger")) linje = lesLeger(sc);
                else return;
            }
            sc.close();

        } catch (FileNotFoundException e) {
            System.out.println("Fil ikke funnet");
            e.printStackTrace();
        }
    }

    private String[] lesPasienter(Scanner sc) {
        String linje = sc.nextLine();
        while (sc.hasNextLine() && linje.charAt(0) != '#') {
            String[] biter = linje.split(",");
            pasientListe.leggTil(new Pasient(biter[0], biter[1]));
            linje = sc.nextLine();
        }
        return linje.split(" ",3);
    }

    private String[] lesLegemidler(Scanner sc) {
        String linje = sc.nextLine();
        while (sc.hasNextLine() && linje.charAt(0) != '#') {
            String[] biter = linje.strip().split(",");
            String navn = biter[0];
            String type = biter[1];
            double pris = Double.parseDouble(biter[2]);
            double virkestoff = Double.parseDouble(biter[3]);
            int styrke = 0;
            if (!type.equals("vanlig")) {
                styrke = Integer.parseInt(biter[4].strip());
            }
            Legemiddel lm;

            if (type.equals("narkotisk")) {
                lm = new Narkotisk(navn, pris, virkestoff, styrke);
            } else if (type.equals("vanedannende")) {
                lm = new Vanedannende(navn, pris, virkestoff, styrke);
            } else {
                lm = new Vanlig(navn, pris, virkestoff);
            }
            legemidler.leggTil(lm);
            linje = sc.nextLine();
        }
        return linje.split(" ",3);
    }

    private String[] lesLeger(Scanner sc) {
        String linje = sc.nextLine();
        while (sc.hasNextLine() && linje.charAt(0) != '#') {
            String[] biter = linje.strip().split(",");
            String navn = biter[0];
            String kontrollkode = biter[1];

            Lege l;
            if (kontrollkode.equals("0")) l = new Lege(navn);
            else l = new Spesialist(navn, kontrollkode);

            legeListe.leggTil(l);
            linje = sc.nextLine();
        }
        return linje.split(" ",3);
    }

    private String[] lesResepter(Scanner sc) {
        String linje = sc.nextLine();
        // Vi kan ikke sjekke for nextLine i denne while-loopen,
        // for da faar vi ikke lest inn siste linje.

        while (linje.charAt(0) != '#' && linje != null) {
            try {
                String[] biter = linje.split(",");

                int lmNr = Integer.parseInt(biter[0]);
                Legemiddel lm = legemidler.hent(lmNr - 1);

                String legeNavn = biter[1];
                Lege lege = null;
                for (Lege l : legeListe) {
                    if (l.hentNavn().equals(legeNavn)) lege = l;
                }

                int pasientID = Integer.parseInt(biter[2]);
                Pasient pasient = pasientListe.hent(pasientID-1);

                String type = biter[3];

                if (type.equals("militaer")) {
                    try {
                        // Legger ogsa resepter i egen liste far aa enklere finne statistikk senere
                        resepter.leggTil(lege.skrivMilResept(lm, pasient));
                        // Ettersom resepter leses inn sist, maa vi sjekke om vi har kommet til slutten av filen,
                        // ellers vil man faa error paa sc.nextLine().
                        if(!sc.hasNextLine()) return null;
                        linje = sc.nextLine();
                    } catch (UlovligUtskrift e) {
                        try {linje = sc.nextLine();}
                        catch (NoSuchElementException l) {break;};
                    }
                } else if (type.equals("blaa")) {
                    try {
                        int reit = Integer.parseInt(biter[4]);
                        resepter.leggTil(lege.skrivBlaaResept(lm, pasient, reit));
                        if(!sc.hasNextLine()) break;
                        linje = sc.nextLine();
                    } catch (UlovligUtskrift e) {
                        try {linje = sc.nextLine();}
                        catch (NoSuchElementException l) {break;};
                    }
                } else if (type.equals("p")) {
                    try {
                        int reit = Integer.parseInt(biter[4]);
                        resepter.leggTil(lege.skrivPResept(lm, pasient, reit));
                        if(!sc.hasNextLine()) break;
                        linje = sc.nextLine();
                    } catch (UlovligUtskrift e) {
                        try {linje = sc.nextLine();}
                        catch (NoSuchElementException l) {break;};
                    }
                } else if (type.equals("hvit")) {
                    try {
                        int reit = Integer.parseInt(biter[4]);
                        resepter.leggTil(lege.skrivHvitResept(lm, pasient, reit));
                        if(!sc.hasNextLine()) break;
                        linje = sc.nextLine();
                    } catch (UlovligUtskrift e) {
                        try {linje = sc.nextLine();}
                        catch (NoSuchElementException l) {break;};
                    }
                } else {
                    if (!sc.hasNextLine()) linje = null;
                }
            } catch (UgyldigListeindeks e) {
                linje = sc.nextLine();
            }
        }   
        return linje.split(" ",3);
    }
}
