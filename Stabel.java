class Stabel <E> extends Lenkeliste <E> {
    @Override
    public void leggTil(E x) {
        Node peker = start;
        start = new Node(x);
        start.neste = peker;
        lengde++;
    }
}
