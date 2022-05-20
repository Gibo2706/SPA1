public class Zad04 {
    private static Red<String> ucitajStringove(String fInput){
        if(!Svetovid.testIn(fInput))
            return null;
        Red<String> red = new Red<String>();
        while(Svetovid.in(fInput).hasMore() && !red.jePun()){
            red.naKraj(Svetovid.in(fInput).readLine());
        }
        Svetovid.in(fInput).close();
        return red;
    }

    private static Stek<Integer> ucitajBrojeve(String fInput){
        if(!Svetovid.testIn(fInput))
            return null;
        Stek<Integer> stek = new Stek<Integer>();
        while(Svetovid.in(fInput).hasMore() && !stek.jePun()){
            stek.stavi(Svetovid.in(fInput).readInt());
        }
        Svetovid.in(fInput).close();
        return stek;
    }
    
    public static void main(String[] args) {
        Red<String> redString = new Red<String>();
        redString = ucitajStringove("files\\input\\r1.txt");
        Stek<Integer> stekIntPrvi = new Stek<Integer>();
        stekIntPrvi = ucitajBrojeve("files\\input\\p1.txt");
        Stek<Integer> stekIntDrugi = new Stek<Integer>();
        stekIntDrugi = ucitajBrojeve("files\\input\\p2.txt");

        // 1.
        // 1.b
        if(!redString.jePrazan()){
            while(!redString.jePrazan()){
                if(redString.prvi().contains("e")){
                    redString.izbaciPrvi();
                }else{
                    break;
                }
            }
        }
        System.out.println(redString);
        // 1.c
        String fOut = "files\\output\\" + Svetovid.in.readLine("Unesite ime fajla za ispis(ime.txt): ");
        if(!redString.jePrazan()){
            while(!redString.jePrazan()){
                Svetovid.out(fOut).println(redString.izbaciPrvi());
            }
            Svetovid.out(fOut).close();
        }

        // 2.
        // 2.b
        System.out.println(stekIntPrvi);
        if(!stekIntPrvi.jePrazan()){
            while(!stekIntPrvi.jePrazan()){
                if(stekIntPrvi.vrh() <= 30 && stekIntPrvi.vrh() > 20)
                    stekIntPrvi.skiniVrh();
                else   
                    break;
            }
        }
        System.out.println(stekIntPrvi);
        // 2.c
        System.out.println(stekIntDrugi);
        if(!stekIntDrugi.jePrazan()){
            while(!stekIntDrugi.jePrazan()){
                int pom = stekIntDrugi.skiniVrh();
                if(pom % 2 == 0 && stekIntDrugi.vrh() % 2 == 0){
                    stekIntDrugi.stavi(pom);
                    break;
                }
            }
        }
        System.out.println(stekIntDrugi);


        System.out.println();
        System.out.println(stekIntPrvi);
        System.out.println(stekIntDrugi);
        // 2.d
        Stek<Integer> spojeni = new Stek<Integer>();
        if(!stekIntDrugi.jePrazan() && !stekIntPrvi.jePrazan()){
            while((!stekIntDrugi.jePrazan() || !stekIntPrvi.jePrazan()) && !spojeni.jePun()){
                spojeni.stavi(stekIntPrvi.skiniVrh());
                spojeni.stavi(stekIntDrugi.skiniVrh());
            }
        }
        System.out.println(spojeni);
        // 2.e
        fOut = "files\\output\\pp.txt";
        if(!spojeni.jePrazan()){
            while(!spojeni.jePrazan()){
                Svetovid.out(fOut).println(spojeni.skiniVrh());
            }
            Svetovid.out(fOut).close();
        }
    }
}
