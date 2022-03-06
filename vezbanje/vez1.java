class vez1 {

    private static void prviZad(String file){
        Svetovid.out(file).println("Prvi red");
        Svetovid.out(file).println("Drugi red");
        Svetovid.out(file).println("\nCetvrti red");
        Svetovid.out(file).close();
        
    }
    private static void drugiZad(String file){
        if(Svetovid.testIn(file)){
            while(Svetovid.in(file).hasMore()){
                String red = Svetovid.in(file).readLine();
                Svetovid.out.println(red);
            }
            Svetovid.in(file).close();
        }
    }
    private static void treciZad(String file){
        double zbir = 0;
        int counter = 0;
        if(Svetovid.testIn(file)){
            while(Svetovid.in(file).hasMore()){
                zbir += Svetovid.in(file).readDouble();
                counter++;
            }
            Svetovid.out.println("Suma je: " + zbir);
            if(counter != 0)
                Svetovid.out.println("Prosek je: " + zbir/counter);
            else
                Svetovid.out.println("Greska!");
            Svetovid.in(file).close();
        }
    }

    private static void cetvrtiZad(String file){
        String unos = Svetovid.in.readLine("Unesite ime: ");
        if(Svetovid.testIn(file)){
            String red = new String();
            while(Svetovid.in(file).hasMore()){
                red = Svetovid.in(file).readLine();
                
            }
            if(red.equals(unos)){
                Svetovid.out.println("Ime se nalazi = " + red);
            }else{
                Svetovid.out.println("Nema tog imena. Ime je upisano u fajl");
                Svetovid.out(file).println(unos);
            }
            Svetovid.in(file).close();
        }
    }

    public static void main(String[] args) {
       // prviZad("fajl01.txt");
       // drugiZad("fajl01.txt");
       // treciZad("brojevi.txt");
       // cetvrtiZad("imena.txt");
    }
}