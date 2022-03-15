public class Zad01{
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
            for(int i = 0 ; i<Knjiga.lastIndex(spisak); i++){
                Svetovid.out(file).println(spisak[i]);
            }
            Svetovid.out(file).close();
            Svetovid.out.println("Uspesno upisano u fajl: " + file);
        }else{
            Svetovid.out.println("Spisak je prazan.");
        }
    }
    static void brojiKnjigeDoGodine(Knjiga[] spisak, int godina){
        int brojac = 0;
        if(spisak == null) return;
        for(int i = 0; i<Knjiga.lastIndex(spisak);i++){
            if(spisak[i].godina <= godina)
                brojac++;
        }
        Svetovid.out.println("Pre date godine ima tacno: " + brojac + " knjiga");
    }
    public static void main(String[] args) {
        String file = "files/" + Svetovid.in.readLine("Unesite ime fajla: ");
        ucitavanje(file);
        Knjiga[] spisak = Knjiga.ucitajKnjige(file);
        int lastIndex = Knjiga.lastIndex(spisak);
        String naslov = Svetovid.in.readLine("Unesite ime knjige: ");
        String ime = Svetovid.in.readLine("Unesite ime pisca: ");
        int godina = Svetovid.in.readInt("Unesite godinu kad je knjiga objavljena: ");
        spisak[lastIndex] = new Knjiga(naslov, ime, godina);
        saveArrayToFile("output.txt", spisak);
        int traziPoGodini = Svetovid.in.readInt("Do koje godine trazite: ");
        brojiKnjigeDoGodine(spisak, traziPoGodini);
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

    static Knjiga[] ucitajKnjige(String file){
        int brojac = 0;
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

    static int lastIndex(Knjiga[] spisak){
        int brj = 0;
        for(int i = 0; i <= MAX_KNJIGA; i++){
            if(spisak[i]==null){
                brj = i;
                break;
            }
        }
        return brj;
    }

    public String toString(){
        return naslov + " " + ime + " " + godina;
    }
}