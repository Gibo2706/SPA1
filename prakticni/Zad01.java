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
        if(spisak != null){
            for(int i = 0 ; i<=Knjiga.lastIndex(); i++){
                Svetovid.out(file).println(spisak[i]);
            }
            Svetovid.out(file).close();
            Svetovid.out.println("Uspesno upisano u fajl: " + file);
        }else{
            Svetovid.out.println("Spisak je prazan.");
        }
    }
    public static void main(String[] args) {
        String file = Svetovid.in.readLine("Unesite ime fajla: ");
        ucitavanje(file);
        Knjiga[] spisak = Knjiga.ucitajKnjige(file);
        int lastIndex = Knjiga.lastIndex();
        String naslov = Svetovid.in.readLine("Unesite ime knjige: ");
        String ime = Svetovid.in.readLine("Unesite ime pisca: ");
        int godina = Svetovid.in.readInt("Unesite godinu kad je knjiga objavljena: ");
        spisak[lastIndex] = new Knjiga(naslov, ime, godina);
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
                String naslov = Svetovid.in(file).readToken();
                String ime = Svetovid.in(file).readToken();
                int godina = Svetovid.in(file).readInt();
                spisak[brojac] = new Knjiga(naslov, ime, godina);
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