public class MilResept extends HvitResept{
    
    public MilResept(Legemiddel legemiddel, Lege lege, Pasient pasient) {
        super(legemiddel, lege, pasient, 3);
    }

    public double prisAaBetale() {
        return 0;
    }

    @Override
    public String toString() {
        String s1 = "Militaerresept\n";
        String s2 = super.toString();
        return s1+s2;
    }
}
