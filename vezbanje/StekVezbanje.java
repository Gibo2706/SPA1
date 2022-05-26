public class StekVezbanje {
    public static void main(String[] args) {
        System.out.println("Unesite broj elemenata steka: ");
        int n = Svetovid.in.readInt();
        Stek<Integer> stek = new Stek<Integer>(n);
        for (int i = 0; i < n; i++) {
            System.out.println("Unesite " + (i + 1) + ". element steka: ");
            stek.stavi(Svetovid.in.readInt());
        }
        System.out.println(stek);
        
    }
}
