public class TestLegemiddel {
    static int testNr = 0;
    public static void main(String[] args) {
        Narkotisk n = new Narkotisk("Narko", 300, 50, 5);
        Vanedannende v = new Vanedannende("Vane", 500, 100, 2);
        Vanlig va = new Vanlig("Paracet", 250, 10);

        testAlt(n, "Narko", 0, 300, 50);
        testStyrke(n, 5);

        testAlt(v, "Vane", 1, 500, 100);
        testStyrke(v, 2);

        testAlt(va, "Paracet", 2, 250, 10);
        System.out.println(va);

    }

    public static void testAlt(Legemiddel lm, String navn, int id, int pris, int virkestoff) {
        testNr++;
        boolean resultat = true;
        if (lm.id != id) {
            System.out.println("id er feil paa test nr "+testNr+",id skulle vart: "+id);
            resultat = false;
        }
        if (lm.hentPris() != pris) {
            System.out.println("hentPris feilet paa test nr "+testNr+", den skulle returnert: "+pris);
            resultat = false;
        }
        lm.settNyPris(400);
        if (lm.hentPris() != 400) {
            System.out.println("settNyPris feilet paa test nr "+testNr+", hentPris skulle returnert 400 na");
            resultat = false;
        }
        if(!lm.navn.equals(navn)) {
            System.out.println("Navn er feil paa test nr "+testNr+", navn skulle vart: "+navn);
            resultat = false;
        }
        if (lm.virkestoff != virkestoff) {
            System.out.println("Virkestoff er feil paa test nr "+testNr+", det skulle hatt verdien: "+virkestoff);
        }
        if (resultat) {
            System.out.println("Alt riktig med test nr "+testNr);
        }
    }

    public static void testStyrke(Legemiddel lm, int styrke) {
        boolean resultat = true;
        if (lm instanceof Narkotisk) {
            Narkotisk n = (Narkotisk) lm;
            if (n.styrke != styrke) {
                System.out.println("styrke har feil verdi pa "+testNr+", styrke skulle vart: "+styrke);
                resultat = false;
            }
        }
        if (lm instanceof Vanedannende) {
            Vanedannende v = (Vanedannende) lm;
            if (v.styrke != styrke) {
                System.out.println("styrke har feil verdi pa "+testNr+", styrke skulle vart: "+styrke);
                resultat = false;
            }
        }
        if (resultat) {
            System.out.println("Styrke har riktig verdi pa test nr "+testNr);
        }
    }
}

