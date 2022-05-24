public class StekVezb {

    private static Stek<Integer> ucitajIzFajla(String fInput){
        Stek<Integer> stek = new Stek<Integer>();
        try {
            if(Svetovid.testIn(fInput)){
                while(Svetovid.in(fInput).hasMore()){
                    stek.stavi(Svetovid.in(fInput).readInt());
                }
                Svetovid.in(fInput).close();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return stek;
    }

    private static void ukloniJednocifrene(Stek<Integer> stek){
        if(stek.jePrazan()) return;
        while(!stek.jePrazan()){
            if(stek.vrh() > 0 && stek.vrh() < 10){
                stek.skiniVrh();
            }else{
                break;
            }
        }
    }

    private static void ukloniNeparne(Stek<Integer> stek){
        if(stek.jePrazan()) return;
        while(!stek.jePrazan()){
            if(stek.vrh() % 2 == 1){
                stek.skiniVrh();
            }else{
                break;
            }
        }
    }

    private static Stek<Integer> spoji(Stek<Integer> s1, Stek<Integer> s2){
        Stek<Integer> stek = new Stek<Integer>();
        if(s1.jePrazan() && s2.jePrazan()) return stek;
        if(!s1.jePrazan() || !s2.jePrazan()){
            try {
                while (!s1.jePrazan() || !s2.jePrazan()) {
                    if (!s1.jePrazan()) {
                        stek.stavi(s1.skiniVrh());
                    }
                    if (!s2.jePrazan()) {
                        stek.stavi(s2.skiniVrh());
                    }
                }
            } catch (Exception e) {
                System.err.println(e);
            }
        }
        return stek;
    }

    private static void ispisiUFajl(String fInput, Stek<Integer> stek){
        try {
            if(Svetovid.testOut(fInput)){
                while(!stek.jePrazan()){
                    Svetovid.out(fInput).println(stek.skiniVrh());
                }
                Svetovid.out(fInput).close();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        Stek<Integer> p1 = new Stek<Integer>();
        p1 = ucitajIzFajla("files\\input\\p1.txt");
        Stek<Integer> p2 = new Stek<Integer>();
        p2 = ucitajIzFajla("files\\input\\p2.txt");
        System.out.println(p1);
        System.out.println(p2);
        p2.stavi(2);
        System.out.println(p2);
        ukloniJednocifrene(p2);
        System.out.println(p2);
        ukloniNeparne(p1);
        System.out.println(p1);
        Stek<Integer> pp = spoji(p1, p2);
        System.out.println(pp);
        ispisiUFajl("files\\output\\ppVezb.txt", pp);
    }
}
