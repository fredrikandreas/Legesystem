class UgyldigListeindeks extends RuntimeException {
    UgyldigListeindeks(int indeks) {
        super("Ugyldig indeks: " + indeks);
    }

    UgyldigListeindeks(int indeks, int maks) {
        super ("Ugyldig indeks: " + indeks + ", er ikke mellom 0 og " + (maks - 1));
    }
}