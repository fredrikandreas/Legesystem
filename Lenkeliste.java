import java.util.Iterator;
abstract class Lenkeliste <E> implements Liste <E> {
    public Node start = null;
    public Node slutt = null;
    public int lengde = 0;
    /*
     * Instansvariabelen lengde vil alltid tilsvare antall elementer i liste
     * Instansvariabelen start vil alltid refere til det forste elementet,
     * untatt nar listen er tom, da er den null.
     * Instansvariabelen slutt vil tilsvarende alltid referere til siste node
     * i lenken, untatt nar listen er tom.
     */

    public int stoerrelse() {
        return lengde;
    }

    public void leggTil(E x) {
        if (slutt != null) {
            Node nyNode = new Node(x, slutt);
            slutt.neste = nyNode;
            slutt = nyNode;
            lengde++;
        }else {
            start = new Node(x);
            slutt = start;
            lengde++;
        }
        
    }

    public E hent() 
            throws UgyldigListeindeks {
        if (start == null) {
            throw new UgyldigListeindeks(-1);
        }
        return start.data;
    }

    public E fjern() 
            throws UgyldigListeindeks {
        if (start == null) {
            throw new UgyldigListeindeks(-1);
        }
        E verdi = start.data;
        start = start.neste;
        lengde--;
        if (start == null) slutt = null;
        return verdi;
    }

    @Override
    public String toString() {
        if (start == null) {
            return "Listen er tom";
        }
        Node peker = start;
        int teller = 0;
        String str = "1\n" + start.data;
        while (peker.neste != null) {
            peker = peker.neste;
            teller++;
            str += "\n" + (teller + 1) + "\n" + peker.data;
        }
        return str;
    }

    protected class Node {
        Node neste = null;
        Node forrige = null;
        E data;

        Node (E data, Node forrige) {
            this.data = data;
            this.forrige = forrige;
        }

        Node (E data) {
            this.data = data;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new ListeIterator();
    }
    class ListeIterator implements Iterator<E> {
        private Node node = start;
        public E next() {
            E verdi = node.data;
            node = node.neste;
            return verdi;
        }

        public boolean hasNext() {
            return node != null;
        }
    }
}
