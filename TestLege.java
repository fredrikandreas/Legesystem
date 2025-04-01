public class TestLege {
    public static void main (String[] args) {
        Lege l1 = new Lege("Dr. Paus");
        Lege l2 = new Spesialist("Dr. Ueland", "101");
        Legemiddel vanlig = new Vanlig("Paracet", 200, 14);
        Legemiddel narko = new Narkotisk("Morphine", 500, 15, 5);
        Pasient p = new Pasient("Per", "999999");
        
        try {
            l1.skrivHvitResept(vanlig, p, 4);
            System.out.println("Test 1 riktig");
            
        } catch (UlovligUtskrift e) {
            System.out.println("Test 1 feil");
        }

        try {
            l1.skrivHvitResept(narko, p, 4);
            System.out.println("Test 2 feil");
            
        } catch (UlovligUtskrift e) {
            System.out.println("Test 1 riktig");
        }

        try {
            l2.skrivHvitResept(vanlig, p, 4);
            System.out.println("Test 3 riktig");
            
        } catch (UlovligUtskrift e) {
            System.out.println("Test 3 feilet");
        }

        try {
            l2.skrivHvitResept(narko, p, 4);
            System.out.println("Test 1 feil");
            
        } catch (UlovligUtskrift e) {
            System.out.println("Test 1 riktig");
        }

        try {
            l2.skrivBlaaResept(narko, p, 4);
            System.out.println("Test 1 riktig");
            
        } catch (UlovligUtskrift e) {
            System.out.println("Test 1 feilet");
        }
    }
}
