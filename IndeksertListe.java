import java.util.Iterator;
class IndeksertListe <E> extends Lenkeliste <E> {
    public void leggTil(int pos, E x) {
        if (pos < 0 || pos > lengde) throw new UgyldigListeindeks(pos, lengde);
    
        // Spesialtilfeller
        if (start == null) {start = new Node(x); slutt = start; lengde++; return;}
        if (pos == 0) {
            Node nyNode = new Node(x); 
            start.forrige = nyNode; 
            nyNode.neste = start; 
            start = nyNode; 
            lengde++; 
            return;}
        if (lengde == 1) {
            start.neste = new Node(x, start);
            slutt = start.neste;
            lengde++; 
            return;
        }
        if (pos == lengde) {
            Node nyNode = new Node(x, slutt); 
            slutt.neste = nyNode; 
            slutt = nyNode; 
            lengde++; 
            return;}

        // Hvis 0 < pos < lengde-1

        if (pos <= lengde/2) {
            // Iterer forlengs
            Node peker = start;
            for (int i=0; i<pos-1; i++) {
                peker = peker.neste;
            }
            Node nyNode = new Node(x, peker);
            peker.neste.forrige = nyNode;
            nyNode.neste = peker.neste;
            peker.neste = nyNode;
            lengde++;
        } else {
            // Iterer baklengs
            Node peker = slutt;
            for (int i=lengde-1; i>pos+1; i--) {
                peker = peker.forrige;
            }
            Node nyNode = new Node(x, peker.forrige);
            nyNode.neste = peker;
            peker.forrige.neste = nyNode;
            peker.forrige = nyNode;
            lengde++;
        }
    }

    public void sett(int pos, E x) {
        if (pos < 0 || pos > lengde-1) {
            throw new UgyldigListeindeks(pos, lengde);
        }
        if (start == null) {start = new Node(x); slutt = start; lengde++; return;}
        
        if (pos <= lengde/2) {
            Node peker = start;
            for (int i=0; i<pos; i++) {peker = peker.neste;}
            peker.data = x;
        } else {
            Node peker = slutt;
            for (int i=lengde-1; i>pos; i--) {peker = peker.forrige;}
            peker.data = x;
        }
    }
    

    public E hent(int pos) {
        if (pos < 0 || pos >= lengde) {
            throw new UgyldigListeindeks(pos, lengde);
        }
        if (pos <= lengde/2) {
            Node peker = start;
            for (int i=0; i<pos; i++) {peker = peker.neste;}
            return peker.data;
        } else {
            Node peker = slutt;
            for (int i=lengde-1; i>pos; i--) {peker = peker.forrige;}
            return peker.data;
        }
    }

    public E fjern(int pos) {
        if (pos < 0 || pos >= lengde) {
            throw new UgyldigListeindeks(pos, lengde);
        }
        if (start == null) {throw new UgyldigListeindeks(0, 0);}
        if (pos == lengde-1) {
            E res = slutt.data;
            Node peker = slutt.forrige;
            slutt = peker;
            peker.neste = null;
            lengde--;
            return res;
            
        }
        if (pos <= lengde/2) {
            Node peker = start;
            for (int i =0; i<pos-1; i++) {
                peker = peker.neste;
            }
            E res = peker.neste.data;
            peker.neste = peker.neste.neste;
            lengde--;
            return res;
        } else {
            Node peker = slutt;
            for (int i=lengde-1; i>pos+1; i--) {peker = peker.forrige;}
            E res = peker.forrige.data;
            peker.forrige = peker.forrige.forrige;
            peker.forrige.neste = peker;
            lengde--;
            return res;
        }
    }

    @Override
        public Iterator<E> iterator() {
            return new ListeIterator();
        }
    
    class ListeIterator implements Iterator<E> {
        private int pos = 0;
        public E next() {
            pos++;
            return hent(pos-1);
        }

        public boolean hasNext() {
            return pos < lengde;
        }
    }
}
