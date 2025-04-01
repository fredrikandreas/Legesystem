public class TestResepter {
    static int testNr = 0;
    public static void main(String[] args) {
        Pasient p = new Pasient("Arne", "28");
        Pasient p2 = new Pasient("Maren", "982761");
        Legemiddel lm1 = new Vanedannende("Medisin", 400, 50, 5);
        Legemiddel lm2 = new Vanlig("Paracet", 100, 100);
        Legemiddel lm3 = new Narkotisk("SterkeSaker", 1000, 100, 15);
        Lege lege = new Lege("Bjarne");
        HvitResept hvitResept = new HvitResept(lm1, lege, p, 5);
        BlaaResept blaaResept = new BlaaResept(lm3, lege, p, 3);
        MilResept milResept = new MilResept(lm2, lege, p2);
        PResept pResept = new PResept(lm1, lege, p2, 4);

        testHvitResept(hvitResept);
        testBlaaResept(blaaResept);
        testMilResept(milResept);
        testPResept(pResept);


    }

    public static void testHvitResept(HvitResept resept) {
        testNr++;
        boolean resultat = true;
        if (resept.prisAaBetale() != 400) {
            System.out.println("prisAaBetale feilet pa HvitResept, resultat skulle vart 400.");
            resultat = false;
        }

        if (!resept.farge().equals("hvit")) {
            System.out.println("farge returnerte feil pa HvitResept, skulle returnert 'hvit'.");
            resultat = false;
        }

        resept.bruk();
        if (resept.reit != 4) {
            System.out.println("Metode bruk feilet pa HvitResept, reit skulle vart 4.");
            resultat = false;
        }
        for (int i=0;i<4;i++) {
            resept.bruk();
        }
        if (resept.bruk()) {
            System.out.println("Metode bruk feilet pa HvitResept, reit er 0 saa metoden skulle returnert false .");
            resultat = false;
        }

        if (resultat) {
            System.out.println("Alt riktig pa HvitResept");
        }
    }

    public static void testMilResept(MilResept resept) {
        if (resept.prisAaBetale() != 0) {
            System.out.println("MilResept feil pris");
        }else {
            System.out.println("MilResept riktig");
        }
    }

    public static void testPResept(PResept resept) {
        if (resept.prisAaBetale() != 292) {
            System.out.println("prisAaBetale feilet pa PResept, skulle returnert 292");
        }else {
            System.out.println("PResept riktig");
        }
    }

    public static void testBlaaResept(BlaaResept resept) {
        if (resept.prisAaBetale() != 250) {
            System.out.println("prisAaBetale returnerte feil pa BlaaResept, skulle returnert 250");
        }else {
            System.out.println("Blaaresept riktig");
        }
    }
}
