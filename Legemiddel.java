
abstract class Legemiddel {
     public final String navn;
     public final int id;
     public double pris;
     public final double virkestoff;
     public static int idTeller = 1;

     protected Legemiddel(String navn, double pris, double virkestoff) {
        this.navn = navn;
        this.pris = pris;
        this.virkestoff = virkestoff;
        this.id = idTeller;
        idTeller++;
     }
     public String hentNavn() {
      return navn;
      
     }
     public double hentPris() {
        return pris;
     }

     public void settNyPris(int nyPris) {
         pris = nyPris;
     }

     public double hentVirkestoff() {
         return this.virkestoff;
     }

     public int hentId() {
      return this.id;
     }

     @Override
     public String toString() {
        return "Navn: "+navn+"\nID: "+id+"\nPris: "+pris+"\nVirkestoff: "+virkestoff+"\n";
     }

}