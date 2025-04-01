import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class HovedProgram {
//[START] -> (Main metode)    
    public static void main(String[] args) {
        //Lager scanner objekt som tar input fra bruker
        Scanner sc = new Scanner(System.in);

        System.out.println("\n+-----------------+");
        System.out.println("| STARTER PROGRAM |");
        System.out.println("+-----------------+");
        //Starter programmet
        start(sc);

        System.out.println("\n+-------------------+");
        System.out.println("| PROGRAM AVSLUTTES |");
        System.out.println("+-------------------+\n");
        sc.close();
    }
//[SLUTT] -> (Main metode) 

//[START] -> (Hovedmeny)
    private static void start(Scanner sc) {
        //Oppretter Legesystem og leser fra filen
        Legesystem legeSystem = new Legesystem();
        legeSystem.lesFraFil("nystorfil.txt");

        //Legger funksjonene til meny i en indeksert liste
        IndeksertListe<Runnable> meny = new IndeksertListe<>();
        meny.leggTil(0, null);
        meny.leggTil(1, () -> utskriftsMeny(legeSystem, sc));
        meny.leggTil(2, () -> opprettElement(sc, legeSystem));
        meny.leggTil(3, () -> brukResept(sc, legeSystem));
        meny.leggTil(4, () -> skrivStatistikk(sc, legeSystem));
        meny.leggTil(5, () -> skrivTilFil(sc, legeSystem));

        //Lager utskriften for funksjonene til meny
        String utskrift = 
            "\n** Hovedmeny **\n" + 
            "1: Skriv ut oversikt\n" +
            "2: Opprett et nytt element i systemet\n" + 
            "3: Bruk en resept til en pasient\n" +
            "4: Hent statistikk\n" +
            "5: Skriv data til fil\n" +
            "0: Avslutt";

        brukerInput(utskrift, sc, meny, false);
    }
//[SLUTT] -> (Hovedmeny)

//[START] -> (Utskriftsfunksjoner for oversikt)
    private static void utskriftsMeny(Legesystem legesystem, Scanner sc) {
        //Legger funksjonene til meny i en indeksert liste
        IndeksertListe<Runnable> meny = new IndeksertListe<>();
        meny.leggTil(0, null);
        meny.leggTil(1, () -> skrivPasienter(legesystem));
        meny.leggTil(2, () -> skrivLeger(legesystem));
        meny.leggTil(3, () -> skrivLegemidler(legesystem));
        meny.leggTil(4, () -> skrivResepter(legesystem));
        meny.leggTil(5, () -> skrivFullstendig(legesystem));

        //Lager utskriften for funksjonene til meny
        String utskrift = 
            "\n** Velg utskrift **\n" +
            "1: Pasientoversikt\n" +
            "2: Legeoversikt\n" + 
            "3: Legemiddeloversikt\n" +
            "4: Reseptoversikt\n" +
            "5: Fullstendig oversikt\n" +
            "0: Tilbake";

        brukerInput(utskrift, sc, meny, false);
    }
    //[START] -> (Oversikt over elementer)
        private static void skrivPasienter(Legesystem legesystem) {
            System.out.println("\n** Pasientoversikt **\n");
            System.out.println(legesystem.pasientListe);
        }
        private static void skrivLeger(Legesystem legesystem) {
            System.out.println("\n** Legeoversikt **\n");
            System.out.println(legesystem.legeListe);
        }
        private static void skrivLegemidler(Legesystem legesystem) {
            System.out.println("\n** Legemiddeloversikt **\n");
            System.out.println(legesystem.legemidler);
        }
        private static void skrivResepter(Legesystem legesystem) {
            System.out.println("\n** Reseptoversikt **\n");
            System.out.println(legesystem.resepter);
        }
        private static void skrivFullstendig(Legesystem legesystem) {
            System.out.println("\n** Fullstendig oversikt **\n");
            skrivPasienter(legesystem);
            skrivLeger(legesystem);
            skrivLegemidler(legesystem);
            skrivResepter(legesystem);
        }
    //[SLUTT] -> (Oversikt over elementer)
//[SLUTT] -> (Utskriftsfunksjoner for oversikt)

//[START] -> (Opprettelse av nye objekter)
    private static void opprettElement(Scanner sc, Legesystem ls) {
        //Legger funksjonene til meny i en indeksert liste
        IndeksertListe<Runnable> meny = new IndeksertListe<>();
        meny.leggTil(0, null);
        meny.leggTil(1, () -> nyPasient(sc, ls));
        meny.leggTil(2, () -> nyLege(sc, ls));
        meny.leggTil(3, () -> opprettLegemiddel(sc, ls));
        meny.leggTil(4, () -> nyResept(sc, ls));

        //Lager utskriften for funksjonene til meny
        String utskrift = 
            "\n** Opprett **\n" +
            "1: Ny pasient\n" + 
            "2: Ny lege\n" +
            "3: Nytt legemiddel\n" +
            "4: Ny resept\n" +
            "0: Tilbake";

        brukerInput(utskrift, sc, meny, false);
    }
    private static void nyPasient(Scanner sc, Legesystem ls) {
        String navn = "";
        String foedselsNummer = "";
        System.out.println("Fullt navn:");
        System.out.print("> ");
        String inp = sc.nextLine();
        if (1 < inp.split(" ").length) {
            navn = inp;
        } else {
            System.out.println("Vennligst oppgi fullt navn");
            return;
        }
        System.out.println("Foedselnummer:");
        System.out.print("> ");
        inp = sc.nextLine().strip();
        if (inp.length() == 11) {
            try {
                Long.parseLong(inp);
                foedselsNummer = inp;
                ls.pasientListe.leggTil(new Pasient(navn, foedselsNummer));
                System.out.println("Pasienten " + navn + " ble lagt til i systemet");
            }
            catch (NumberFormatException e) {
                System.out.println("Foedselsnummeret inneholdt ugyldige tegn. Vennligst oppgi gyldig foedselsnummer");
                return;
            }
        } else {
            System.out.println("Ugyldig lengde paa foedselsnummeret. Oppgitt lengde var " + inp.length() + ", gyldig lengde er 11");
        }
    }
    private static void nyLege(Scanner sc, Legesystem ls) {
        String navn = "";
        String kontrollKode = "0";
        System.out.println("Fullt navn:");
        System.out.print("> ");
        String inp = sc.nextLine();
        if (1 < inp.split(" ").length) {
            navn = inp;
        } else {
            System.out.println("Vennligst oppgi fullt navn");
            return;
        }
        System.out.println("Oppgi kontrollkode hvis " + navn + " er spesialist, ellers trykk ENTER");
        System.out.print("> ");
        inp = sc.nextLine().strip();
        if (inp.equals("")) {
            ls.legeListe.leggTil(new Lege(navn));
            System.out.println("Lege " + navn + " ble lagt til i systemet");
            return;
        } 
        try {
            Long.parseLong(inp);
            kontrollKode = inp;
            ls.legeListe.leggTil(new Spesialist(navn, kontrollKode));
            System.out.println("Spesialist " + navn + " ble lagt til i systemet");
        }
        catch (NumberFormatException e) {
            System.out.println("Kontrollkoden inneholdt ugyldige tegn");
            return;
        }
    } 
    //[START] -> (Opprettelse av nye legemidler)
        private static void opprettLegemiddel(Scanner sc, Legesystem ls) {
            //Legger funksjonene til meny i en indeksert liste
            IndeksertListe<Runnable> meny = new IndeksertListe<>();
            meny.leggTil(0, null);
            meny.leggTil(1, () -> nyVanlig(sc, ls));
            meny.leggTil(2, () -> nyVanedannende(sc, ls));
            meny.leggTil(3, () -> nyNarkotisk(sc, ls));

            //Lager utskriften for funksjonene til meny
            String utskrift = 
                "\n** Velg type legemiddel **\n" +
                "1: Vanlig\n" + 
                "2: Vanedannende\n" +
                "3: Narkotisk\n" +
                "0: Tilbake";
            
            brukerInput(utskrift, sc, meny, false);
        }
        private static void nyVanlig(Scanner sc, Legesystem ls) {
            System.out.println("Legemiddel navn:");
            System.out.print("> ");
            String navn = sc.nextLine();

            int pris = 0;
            double virkestoff = 0;
            String inp = "";

            System.out.println("Pris:");
            System.out.print("> ");
            inp = sc.nextLine();
            try {
                pris = Integer.parseInt(inp);
                if (pris < 0) throw new Exception("Prisen kan ikke vaere lavere enn 0");
                System.out.println("Virkestoff:");
                System.out.print("> ");
                inp = sc.nextLine();
                try {
                    virkestoff = Double.parseDouble(inp);
                    if (virkestoff < 0) throw new Exception("Verdien for virkestoff kan ikke vaere lavere enn 0");
                    ls.legemidler.leggTil(new Vanlig(navn, pris, virkestoff));
                    System.out.println("Legemiddelet " + navn + " ble lagt til i systemet");
                }
                catch (NumberFormatException e) {
                    System.out.println("Ugyldig verdi for virkestoff, vennligst oppgi et tall");
                    return;
                }
            }
            catch (NumberFormatException e) {
                System.out.println("Ugyldig verdi for pris, vennligst oppgi et heltall");
                return;
            }
            catch (Exception e) {
                System.out.println(e);
                return;
            }
        }
        private static void nyVanedannende(Scanner sc, Legesystem ls) {
            System.out.println("Legemiddel navn:");
            System.out.print("> ");
            String navn = sc.nextLine();

            int pris = 0;
            double virkestoff = 0;
            int styrke = 0;
            String inp = "";

            System.out.println("Pris:");
            System.out.print("> ");
            inp = sc.nextLine();
            try {
                pris = Integer.parseInt(inp);
                if (pris < 0) throw new Exception("Prisen kan ikke vaere lavere enn 0");
                System.out.println("Virkestoff:");
                System.out.print("> ");
                inp = sc.nextLine();
                try {
                    virkestoff = Double.parseDouble(inp);
                    if (virkestoff < 0) throw new Exception("Verdien for virkestoff kan ikke vaere lavere enn 0");
                    System.out.println("Styrke:");
                    System.out.print("> ");
                    inp = sc.nextLine();
                    try {
                        styrke = Integer.parseInt(inp);
                        if (styrke < 0) {throw new Exception("Verdien for styrke kan ikke vaere lavere enn 0");}
                        ls.legemidler.leggTil(new Vanedannende(navn, pris, virkestoff, styrke));
                        System.out.println("Legemiddelet " + navn + " ble lagt til i systemet");
                    }
                    catch (NumberFormatException e) {
                        System.out.println("Ugyldig verdi for styrke, vennligst oppgi et tall");
                        return;
                    }
                }
                catch (NumberFormatException e) {
                    System.out.println("Ugyldig verdi for virkestoff, vennligst oppgi et tall");
                    return;
                }
            }
            catch (NumberFormatException e) {
                System.out.println("Ugyldig verdi for pris, vennligst oppgi et heltall");
                return;
            }
            catch (Exception e) {
                System.out.println(e);
                return;
            }
        }
        private static void nyNarkotisk(Scanner sc, Legesystem ls) {
            System.out.println("Legemiddel navn:");
            System.out.print("> ");
            String navn = sc.nextLine();

            int pris = 0;
            double virkestoff = 0;
            int styrke = 0;
            String inp = "";

            System.out.println("Pris:");
            System.out.print("> ");
            inp = sc.nextLine();
            try {
                pris = Integer.parseInt(inp);
                if (pris < 0) throw new Exception("Prisen kan ikke vaere lavere enn 0");
                System.out.println("Virkestoff:");
                System.out.print("> ");
                inp = sc.nextLine();
                try {
                    virkestoff = Double.parseDouble(inp);
                    if (virkestoff < 0) throw new Exception("Verdien for virkestoff kan ikke vaere lavere enn 0");
                    System.out.println("Styrke:");
                    System.out.print("> ");
                    inp = sc.nextLine();
                    try {
                        styrke = Integer.parseInt(inp);
                        if (styrke < 0) {throw new Exception("Verdien for styrke kan ikke vaere lavere enn 0");}
                        ls.legemidler.leggTil(new Narkotisk(navn, pris, virkestoff, styrke));
                        System.out.println("Legemiddelet " + navn + " ble lagt til i systemet");
                    }
                    catch (NumberFormatException e) {
                        System.out.println("Ugyldig verdi for styrke, vennligst oppgi et tall");
                        return;
                    }
                }
                catch (NumberFormatException e) {
                    System.out.println("Ugyldig verdi for virkestoff, vennligst oppgi et tall");
                    return;
                }
            }
            catch (NumberFormatException e) {
                System.out.println("Ugyldig verdi for pris, vennligst oppgi et heltall");
                return;
            }
            catch (Exception e) {
                System.out.println(e);
                return;
            }
        }
    //[SLUTT] -> (Opprettelse av nye legemidler)
    //[START] -> (Opprettelse av nye resepter)
        private static void nyResept(Scanner sc, Legesystem ls) {
            //Legger funksjonene til meny i en indeksert liste
            IndeksertListe<Runnable> meny = new IndeksertListe<>();
            meny.leggTil(0, null);
            meny.leggTil(1, () -> nyHvit(sc, ls));
            meny.leggTil(2, () -> nyMilitaer(sc, ls));
            meny.leggTil(3, () -> nyPResept(sc, ls));
            meny.leggTil(4, () -> nyBlaa(sc, ls));

            //Lager utskriften for funksjonene til meny
            String utskrift = 
                "\n** Velg type resept **\n" +
                "1: Hvit\n" + 
                "2: Militaer\n" +
                "3: Prevensjon\n" +
                "4: Blaa\n" +
                "0: Tilbake";
            
            brukerInput(utskrift, sc, meny, false);
        }
        private static void nyHvit(Scanner sc, Legesystem ls) {
            Lege utskrivendeLege = velgLege(sc, ls);
            if (utskrivendeLege == null) return;

            Legemiddel legemiddel = velgLegemiddel(sc, ls);
            if (legemiddel == null) return;

            Pasient pasient = velgPasient(sc, ls);
            if (pasient == null) return;

            System.out.println("Oppgi antall reiterasjoner");
            System.out.print("> ");
            int reit = 0;
            String inp = sc.nextLine();
            try {
                reit = Integer.parseInt(inp);
                if (reit < 1) {
                    System.out.println("Ugyldig antall reiterasjoner");
                    return;
                } 
            }
            catch (NumberFormatException e) {
                System.out.println("Inputen inneholdt ugyldige tegn. Vennligst oppgi et heltall");
                return;
            }

            try {
                ls.resepter.leggTil(utskrivendeLege.skrivHvitResept(legemiddel, pasient, reit));
                System.out.println(
                    "Resepten til " + pasient.hentNavn() + 
                    " for " + legemiddel.hentNavn() + 
                    " ble skrevet ut av " + utskrivendeLege.hentNavn()
                );
            }
            catch (UlovligUtskrift e) {
                System.out.println(e);
                return;
            }
        }
        private static void nyMilitaer(Scanner sc, Legesystem ls) {
            Lege utskrivendeLege = velgLege(sc, ls);
            if (utskrivendeLege == null) return;

            Legemiddel legemiddel = velgLegemiddel(sc, ls);
            if (legemiddel == null) return;

            Pasient pasient = velgPasient(sc, ls);
            if (pasient == null) return;

            try {
                ls.resepter.leggTil(utskrivendeLege.skrivMilResept(legemiddel, pasient));
                System.out.println(
                    "Resepten til " + pasient.hentNavn() + 
                    " for " + legemiddel.hentNavn() + 
                    " ble skrevet ut av " + utskrivendeLege.hentNavn()
                );
            }
            catch (UlovligUtskrift e) {
                System.out.println(e);
                return;
            }
        }
        private static void nyPResept(Scanner sc, Legesystem ls) {
            Lege utskrivendeLege = velgLege(sc, ls);
            if (utskrivendeLege == null) return;

            Legemiddel legemiddel = velgLegemiddel(sc, ls);
            if (legemiddel == null) return;

            Pasient pasient = velgPasient(sc, ls);
            if (pasient == null) return;

            System.out.println("Oppgi antall reiterasjoner");
            System.out.print("> ");
            int reit = 0;
            String inp = sc.nextLine();
            try {
                reit = Integer.parseInt(inp);
                if (reit < 1) {
                    System.out.println("Ugyldig antall reiterasjoner");
                    return;
                } 
            }
            catch (NumberFormatException e) {
                System.out.println("Inputen inneholdt ugyldige tegn. Vennligst oppgi et heltall");
                return;
            }

            try {
                ls.resepter.leggTil(utskrivendeLege.skrivPResept(legemiddel, pasient, reit));
                System.out.println(
                    "Resepten til " + pasient.hentNavn() + 
                    " for " + legemiddel.hentNavn() + 
                    " ble skrevet ut av " + utskrivendeLege.hentNavn()
                );
            }
            catch (UlovligUtskrift e) {
                System.out.println(e);
                return;
            }
        }
        private static void nyBlaa(Scanner sc, Legesystem ls) {
            Lege utskrivendeLege = velgLege(sc, ls);
            if (utskrivendeLege == null) return;

            Legemiddel legemiddel = velgLegemiddel(sc, ls);
            if (legemiddel == null) return;

            Pasient pasient = velgPasient(sc, ls);
            if (pasient == null) return;

            System.out.println("Oppgi antall reiterasjoner");
            System.out.print("> ");
            int reit = 0;
            String inp = sc.nextLine();
            try {
                reit = Integer.parseInt(inp);
                if (reit < 1) {
                    System.out.println("Ugyldig antall reiterasjoner");
                    return;
                } 
            }
            catch (NumberFormatException e) {
                System.out.println("Inputen inneholdt ugyldige tegn. Vennligst oppgi et heltall");
                return;
            }

            try {
                ls.resepter.leggTil(utskrivendeLege.skrivBlaaResept(legemiddel, pasient, reit));
                System.out.println(
                    "Resepten til " + pasient.hentNavn() + 
                    " for " + legemiddel.hentNavn() + 
                    " ble skrevet ut av " + utskrivendeLege.hentNavn()
                );
            }
            catch (UlovligUtskrift e) {
                System.out.println(e);
                return;
            }
        }
    //[SLUTT] -> (Opprettelse av nye resepter)
//[SLUTT] -> (Opprettelse av nye objekter)

//[START] -> (Bruker resept)
    private static void brukResept(Scanner sc, Legesystem ls) {
        Pasient pasient = velgPasient(sc, ls);
        int teller = 1;
        System.out.println("\n** Reseptmeny **");
        for (Resept r : pasient.hentResepter()) {
            System.out.println(teller + ": " + r.hentLegemiddel().hentNavn() + " - Gjenvaerende: " + r.hentReit());
            teller++;
        }
        System.out.println("0: Tilbake");

        System.out.println("Tast nummer paa oensket resept");
        System.out.print("> ");
        String inp = sc.nextLine();

        try {
            int i = Integer.parseInt(inp) - 1;
            try {
                if (i == -1) return;
                if (pasient.hentResepter().hent(i).bruk()) {
                    System.out.println(
                        "Brukt resepten paa " + pasient.hentResepter().hent(i).hentLegemiddel().hentNavn() + 
                        " - Gjenvaerende: " + pasient.hentResepter().hent(i).hentReit()
                    );
                } else {
                    System.out.println(
                        "Kunne ikke bruke resepten paa " + pasient.hentResepter().hent(i).hentLegemiddel().hentNavn() + 
                        " - Ingen gjenvaerende reiterasjoner"
                    );
                }
            }
            catch (UgyldigListeindeks e) {
                System.out.println("Ugyldig valg. Vennligst velg et tall fra menyen");
                return;
            }
        }
        catch (NumberFormatException e) {
            System.out.println("Inputen inneholdt ugyldige tegn. Vennligst oppgi et heltall");
            return;
        }
    }
//[SLUTT] -> (Bruker resept)

//[START] -> (Statistikk oversikt)
    private static void skrivStatistikk(Scanner sc, Legesystem ls) {
        //Legger funksjonene til meny i en indeksert liste
        IndeksertListe<Runnable> meny = new IndeksertListe<>();
        meny.leggTil(0, null);
        meny.leggTil(1, () -> statResVanedannende(ls));
        meny.leggTil(2, () -> statResNarkotisk(ls));
        meny.leggTil(3, () -> statLegeNarkotisk(ls));
        meny.leggTil(4, () -> statPasNarkotisk(ls));

        //Lager utskriften for funksjonene til meny
        String utskrift = 
            "\n** Statistikkmeny **\n" + 
            "1: Oversikt over resepter for vanedannende legemidler\n" +
            "2: Oversikt over resepter for narkotiske legemidler\n" + 
            "3: Oversikt over leger som har skrevet ut resepter for narkotiske legemidler\n" +
            "4: Oversikt over pasienter som har resepter for narkotiske legemidler\n" +
            "0: Tilbake";

        brukerInput(utskrift, sc, meny, false);
    }
    //[START] -> (Skriver statistikk)
        private static void statResVanedannende(Legesystem ls) {
            int ant = 0;
            for (Resept r : ls.resepter) {
                if (r.hentLegemiddel() instanceof Vanedannende) ant++;
            }
            System.out.println("Antall utskrevende resepter for vanedannende legemidler: " + ant);
        }
        private static void statResNarkotisk(Legesystem ls) {
            int ant = 0;
            for (Resept r : ls.resepter) {
                if (r.hentLegemiddel() instanceof Narkotisk) ant++;
            }
            System.out.println("Antall utskrevende resepter for narkotiske legemidler: " + ant);
        }
        private static void statLegeNarkotisk(Legesystem ls) {
            for (Lege l : ls.legeListe) {
                int ant = 0;
                for (Resept r : l.hentUtskrevneResepter()) {
                    if (r.hentLegemiddel() instanceof Narkotisk) ant++;
                }
                if (0 < ant) {
                    System.out.println(l.hentNavn() + " har skrevet ut " + ant + " resepter for narkotiske legemidler");
                }
            }
        }
        private static void statPasNarkotisk(Legesystem ls) {
        for (Pasient p : ls.pasientListe) {
            int ant = 0;
            for (Resept r : p.hentResepter()) {
                if (r.hentLegemiddel() instanceof Narkotisk) ant++;
            }
            if (0 < ant) {
                System.out.println(p.hentNavn() + " har " + ant + " resepter for narkotiske legemidler");
            }
        }
    }
    //[START] -> (Skriver statistikk) 
//[SLUTT] -> (Statistikk oversikt)

//[START] -> (Skriver til fil)
    private static void skrivTilFil(Scanner sc, Legesystem ls) {
        try {
            File utskriftsFil = new File("nylegedata.txt");
            utskriftsFil.createNewFile();
            FileWriter skriver = new FileWriter(utskriftsFil);

            skriver.write("# Pasienter (navn, fnr)\n");
            for (Pasient e : ls.pasientListe) {
                skriver.write(e.hentNavn() + "," + e.hentFoedselsnummer() + "\n");
            }

            skriver.write("# Legemidler (navn,type,pris,virkestoff,[styrke])\n");
            for (Legemiddel e : ls.legemidler) {
                if (e instanceof Vanlig) {
                    skriver.write(e.hentNavn() + ",vanlig," + e.hentPris() + "," + e.hentVirkestoff() + "\n");
                } else if (e instanceof Vanedannende) {
                    Vanedannende vane = (Vanedannende)e;
                    skriver.write(vane.hentNavn() + ",vanedannende," + vane.hentPris() + "," + vane.hentVirkestoff() + "," + vane.hentStyrke() + "\n");
                } else if (e instanceof Narkotisk) {
                    Narkotisk nark = (Narkotisk)e;
                    skriver.write(nark.hentNavn() + ",narkotisk," + nark.hentPris() + "," + nark.hentVirkestoff() + "," + nark.hentStyrke() + "\n");
                }
            }

            skriver.write("# Leger (navn,kontrollid / 0 hvis vanlig lege)\n");
            for (Lege e : ls.legeListe) {
                if (e instanceof Spesialist) {
                    Spesialist spes = (Spesialist)e;
                    skriver.write(spes.hentNavn() + "," + spes.hentKontrollKode() + "\n");
                } else {
                    skriver.write(e.hentNavn() + ",0\n");
                }
            }

            skriver.write("# Resepter (legemiddelNummer,legeNavn,pasientID,type,[reit])\n");
            for (Resept e : ls.resepter) {
                if (e instanceof MilResept) {
                    skriver.write(e.hentLegemiddel().hentId() + "," + e.hentLege().hentNavn() + "," + e.hentPasient().hentId() + "," + "militaer\n");
                } else if (e instanceof PResept) {
                    skriver.write(e.hentLegemiddel().hentId() + "," + e.hentLege().hentNavn() + "," + e.hentPasient().hentId() + "," + "p," + e.hentReit() + "\n");
                } else if (e instanceof HvitResept) {
                    skriver.write(e.hentLegemiddel().hentId() + "," + e.hentLege().hentNavn() + "," + e.hentPasient().hentId() + "," + "hvit," + e.hentReit() + "\n");
                } else if (e instanceof BlaaResept) {
                    skriver.write(e.hentLegemiddel().hentId() + "," + e.hentLege().hentNavn() + "," + e.hentPasient().hentId() + "," + "blaa," + e.hentReit() + "\n");
                }
            }

            skriver.close();
            System.out.println("Skrevet til filen " + utskriftsFil.getName());
        }
        catch (IOException e) {
            System.out.print("Det skjedde en feil");
            return;
        }
    }
//[SLUTT] -> (Skriver til fil)

//[START] -> (Hjelpefunksjoner)
    private static void brukerInput(String utskrift, Scanner sc, IndeksertListe<Runnable> meny, Boolean ferdig) {
        while (!ferdig) {
            //Erklaerer i som en ugyldig indeks
            int i = -1;

            //Skriver ut menyen
            System.out.println(utskrift);
            System.out.print("> ");

            //Tar input fra bruker
            String inp = sc.nextLine();

            //Proever aa konvertere inp til int, hvis ikke det er et tall forblir i en ugyldig indeks
            try {
                i = Integer.parseInt(inp);
                if (i == 0) ferdig = true;
                else {
                    //Proever aa kjoere funksjonen med indeksen til "inp"
                    try {meny.hent(i).run();}

                    //Catcher ugyldig indeks hvis det ikke er en funksjon i meny med indeks "inp"
                    catch (UgyldigListeindeks e) {
                        System.out.println("Ugyldig kommando, vennligst oppgi et tall mellom 0 og " + (meny.stoerrelse() - 1));
                        return;
                    }
                }
            }
            //Catcher ugydlig input hvis input ikke er et tall
            catch (NumberFormatException e) {
                System.out.println("Ugyldig input, vennligst oppgi et heltall");
                return;
            }
        }
    }
    private static Lege velgLege(Scanner sc, Legesystem ls) {
        Prioritetskoe<Lege> leger = ls.legeListe;
        System.out.println("\n** Legemeny **");
        int teller = 0;

        for (Lege l : leger) {
            if (l instanceof Spesialist) {
                System.out.println((teller + 1) + ": " + l.hentNavn() + " - Spesialist");
            } else {
                System.out.println((teller + 1) + ": " + l.hentNavn() + " - Vanlig lege");
            }
            teller++;
        }
        System.out.println("0: Tilbake");

        System.out.println("Tast nummer paa oensket lege");
        System.out.print("> ");
        String inp = sc.nextLine();

        try {
            int i = Integer.parseInt(inp);
            try {
                if (i == 0) return null;
                return leger.hent(i - 1);
            }
            catch (UgyldigListeindeks e) {
                System.out.println("Ugyldig valg. Vennligst velg et tall fra menyen");
                return null;
            }
        }
        catch (NumberFormatException e) {
            System.out.println("Inputen inneholdt ugyldige tegn. Vennligst oppgi et heltall");
            return null;
        }
    }
    private static Legemiddel velgLegemiddel(Scanner sc, Legesystem ls) {
        IndeksertListe<Legemiddel> legemidler = ls.legemidler;
        System.out.println("\n** Legemiddelmeny **");
        int teller = 1;

        for (Legemiddel l : legemidler) {
            System.out.println(teller + ": " + l.hentNavn());
            teller++;
        }
        System.out.println("0: Tilbake");

        System.out.println("Tast nummer paa oensket legemiddel");
        System.out.print("> ");
        String inp = sc.nextLine();

        try {
            int i = Integer.parseInt(inp) - 1;
            try {
                if (i == -1) return null;
                return legemidler.hent(i);
            }
            catch (UgyldigListeindeks e) {
                System.out.println("Ugyldig valg. Vennligst velg et tall fra menyen");
                return null;
            }
        }
        catch (NumberFormatException e) {
            System.out.println("Inputen inneholdt ugyldige tegn. Vennligst oppgi et heltall");
            return null;
        }
    }
    private static Pasient velgPasient(Scanner sc, Legesystem ls) {
        IndeksertListe<Pasient> pasienter = ls.pasientListe;
        System.out.println("\n** Pasientmeny **");
        int teller = 1;

        for (Pasient p : pasienter) {
            System.out.println(teller + ": " + p.hentNavn());
            teller++;
        }
        System.out.println("0: Tilbake");

        System.out.println("Tast nummer paa oensket pasient");
        System.out.print("> ");
        String inp = sc.nextLine();

        try {
            int i = Integer.parseInt(inp) - 1;
            try {
                if (i == -1) return null;
                return pasienter.hent(i);
            }
            catch (UgyldigListeindeks e) {
                System.out.println("Ugyldig valg. Vennligst velg et tall fra menyen");
                return null;
            }
        }
        catch (NumberFormatException e) {
            System.out.println("Inputen inneholdt ugyldige tegn. Vennligst oppgi et heltall");
            return null;
        }
    }
//[SLUTT] -> (Hjelpefunksjoner)
}