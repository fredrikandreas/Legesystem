class Prioritetskoe<E extends Comparable<E>> extends Lenkeliste<E> {
    @Override
    public void leggTil(E x) {
        Node nyNode = new Node(x);
        // Spesialtilfeller
        if (start == null) {start = nyNode; lengde++; return;}
        if (nyNode.data.compareTo(start.data) < 0) {
            Node nyNeste = start;
            start = nyNode;
            nyNode.neste = nyNeste;
            nyNeste.forrige = start;
            lengde++;
            return;
        }
        Node peker = start;
        if (peker.neste == null) {
            if (nyNode.data.compareTo(peker.data) > 0) {
                peker.neste = nyNode; 
                nyNode.forrige = peker; 
                lengde++; 
                return;
            } else {
                start = nyNode; 
                nyNode.neste = peker;
                peker.forrige = nyNode;
                lengde++;
                return;
            }
        }
        // "Vanlige" tilfeller, iterer fram til man finner et objekt som er stoerre,
        // eller men kommer til slutten av listen
        while (peker.neste != null && nyNode.data.compareTo(peker.neste.data) > 0) {
            peker = peker.neste;
        }
        // Sjekk hvilket tilfelle som har avsluttet loekken
        if (peker.neste == null) {
            peker.neste = nyNode; 
            nyNode.forrige = peker; 
            lengde++; 
            return;
        }
        Node nyNeste = peker.neste;
        peker.neste.forrige = nyNode;
        peker.neste = nyNode;
        nyNode.neste = nyNeste;
        nyNode.forrige = peker;
        lengde++;
    }

    public E hent(int pos) {
        if (pos < 0 || pos >= lengde) {
            throw new UgyldigListeindeks(pos, lengde);
        }
        Node peker = start;
        for (int i=0; i<pos; i++) {peker = peker.neste;}
        return peker.data;
        /*
        if (pos <= lengde/2) {
            Node peker = start;
            for (int i=0; i<pos; i++) {peker = peker.neste;}
            return peker.data;
        } else {
            Node peker = slutt;
            for (int i=lengde-1; i>pos; i--) {peker = peker.forrige;}
            return peker.data;
        }
        */
    }
}
