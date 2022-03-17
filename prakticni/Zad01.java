public class Zad01{
    public static void main(String[] args) {
        String file = "files/input/" + Svetovid.in.readLine("Unesite ime fajla: ");
        String fileO = "files/output/SpisakKnjiga.txt";
        SpisakKnjiga spisak = new SpisakKnjiga();
        spisak.ucitajKnjige(file);
        spisak.ispisNaEkran();
        Svetovid.out.println("-----Unos Knjige----");
        spisak.dodajKnjigu(Svetovid.in.readLine("Unesite naslov knjige: "), 
                Svetovid.in.readLine("Unesite prezime pisca knjige: "), 
                Svetovid.in.readInt("Unesite godinu izdavanja: "));
        spisak.ispisUFile(fileO);
        spisak.ispisPoPiscu(Svetovid.in.readLine("Unesite prezime pisca: "));
        spisak.traziPoGodini(Svetovid.in.readInt("Unesite godinu: "));
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

    public static Knjiga dodajKnjigu(String nsl, String pr, int gd) {
        return new Knjiga(nsl, pr, gd);
    }

    public String toString(){
        return naslov + " " + ime + " " + godina;
    }
}

class SpisakKnjiga {
    Knjiga[] spisak;
    public static final int MAX_KNJIGA = 1000;
    static int brojac;

    public SpisakKnjiga() {
        spisak = new Knjiga[MAX_KNJIGA];
        brojac = 0;
    }

    public void ucitajKnjige(String file) {
        if (!Svetovid.testIn(file)) {
            Svetovid.out.println("Greska prilikom ucitavanja spiska.");
            return;
        }
        while (Svetovid.in(file).hasMore()) {
            if (brojac > MAX_KNJIGA)
                break;
            spisak[brojac] = Knjiga.dodajKnjigu(Svetovid.in(file).readToken(), Svetovid.in(file).readToken(),
                    Svetovid.in(file).readInt());
            brojac++;
        }
    }

    public void ispisNaEkran() {
        for (int i = 0; i < brojac; i++) {
            Svetovid.out.println(spisak[i]);
        }
    }

    public void ispisUFile(String fileO) {
        for (int i = 0; i < brojac; i++) {
            Svetovid.out(fileO).println(spisak[i].naslov + " " + spisak[i].ime + " " + spisak[i].godina);
        }
        Svetovid.out(fileO).close();
    }

    public void dodajKnjigu(String nsl, String pr, int gd) {
        if (!(brojac < MAX_KNJIGA)) {
            Svetovid.out.println("Maksimalan broj studenata je dostignut.");
            return;
        }
        spisak[brojac] = Knjiga.dodajKnjigu(nsl, pr, gd);
        brojac++;
    }

    public void ispisPoPiscu(String pisac) {
        for (int i = 0; i < brojac; i++) {
            if (spisak[i].ime.equalsIgnoreCase(pisac)) {
                Svetovid.out.println(spisak[i]);
            }
        }
    }

    public void traziPoGodini(int god){
        int prbr = 0;
        for (int i = 0; i < brojac; i++) {
            if(spisak[i].godina < god){
                prbr++;
            }
        }
        if(prbr != 0)
            Svetovid.out.println("Pre zadate godine ima: " + prbr + " knjiga");
        else    
            Svetovid.out.println("Nema knjiga izdatih pre te godine");
    }

    public String toString() {
        String izl = "Spisak [ ";
        if (brojac > 0)
            izl += spisak[0];
        for (int i = 1; i < spisak.length; i++) {
            izl += ", " + spisak[i];
        }
        izl += " ]";
        return izl;
    }
}