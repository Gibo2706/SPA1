public class Zad01{
    final static int MAX_KNJIGA = 1000;
    
    static void ucitavanje(String file){
        if(Svetovid.testIn(file)){
            while(Svetovid.in(file).hasMore()){
                String red = Svetovid.in(file).readLine();
                Svetovid.out.println(red);
            }
            Svetovid.in(file).close();
        }
    }
    static void saveArrayToFile(String file, Knjiga[] spisak){
        for(int i = 0 ; i<=spisak.length; i++){
            Svetovid.out(file).println(spisak[i]);
        }
        Svetovid.out(file).close();
    }
    public static void main(String[] args) {
        String file = Svetovid.in.readLine("Unesite ime fajla: ");
        ucitavanje(file);
        Knjiga[] spisak = Knjiga.ucitajKnjige(file);
        int lastIndex = Knjiga.lastIndex();
        spisak[lastIndex+1].naslov = Svetovid.in.readLine("Unesite ime knjige: ");
        spisak[lastIndex+1].ime = Svetovid.in.readLine("Unesite ime pisca: ");
        spisak[lastIndex+1].godina = Svetovid.in.readInt("Unesite godinu kad je knjiga objavljena: ");
        saveArrayToFile("output.txt", spisak);
    }
}

class Knjiga{
    String naslov, ime;
    int godina;

    final static int MAX_KNJIGA = 1000;
    public Knjiga(String nsl, String im, int god){
        this.godina = god;
        this.naslov = nsl;
        this.ime = im;
    }
    static int brojac = 0;
    static Knjiga[] ucitajKnjige(String file){
        brojac = 0;
        Knjiga[] spisak = new Knjiga[MAX_KNJIGA];
        if(Svetovid.testIn(file)){
            while(Svetovid.in(file).hasMore()){
                spisak[brojac].naslov = Svetovid.in(file).readToken();
                spisak[brojac].ime = Svetovid.in(file).readToken();
                spisak[brojac].godina = Svetovid.in(file).readInt();
                brojac++;
            }
            Svetovid.in(file).close();
        }
        return spisak;
    }
    static int lastIndex(){
        return brojac;
    }
    public String toString(){
        String red = new String();
        red = naslov + ime + godina;
        return red;
    }
}