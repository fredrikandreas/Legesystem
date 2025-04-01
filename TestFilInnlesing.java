public class TestFilInnlesing {
    public static void main(String[] args) {
        Legesystem system = new Legesystem();
        system.lesFraFil("legedata.txt");
        System.out.println(system.pasientListe);
        System.out.println("\n");
        System.out.println("\n");
        System.out.println(system.legeListe);
        System.out.println("\n");
        System.out.println("\n");
        System.out.print(system.legemidler);
        System.out.println("\n");
        System.out.println("\n");
        for (Lege l : system.legeListe) {
            System.out.println(l.utskrevneResepter);
            System.out.println("\n");
        }
    }
}
