interface Liste <E> extends Iterable <E>{
    public int stoerrelse();
    public void leggTil(E x);
    public E hent();
    public E fjern();
}
