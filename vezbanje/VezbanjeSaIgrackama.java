public class VezbanjeSaIgrackama {
    public static void main(String[] args) {
        String fInput = "files/input/" + Svetovid.in.readLine("Unesite ime fajla: ");
        String fOutput = "files/output/SpisakIgracaka.txt";
        SpisakIgracaka sIgracaka = new SpisakIgracaka();
        sIgracaka.ucitajIgrackeIzFile(fInput);
        sIgracaka.ispisNaEkran();
        sIgracaka.dodajIgrackuUSpisak();
        sIgracaka.ispisUFile(fOutput);
        sIgracaka.ispisPoBoji(Svetovid.in.readLine("Unesite boju po kojoj zelite da trazite: "));
        sIgracaka.prebrojSkupljeOdCene(Svetovid.in.readDouble("Unesite cenu po kojoj zelite da trazite: "));
    }
}

class Igracka {
    String tip, boja;
    double cena;

    public Igracka(String t, String b, double c) {
        this.tip = t;
        this.boja = b;
        this.cena = c;
    }

    public static Igracka dodajIgracku(String tip, String boja, double cena) {
        return new Igracka(tip, boja, cena);
    }

    public String toString() {
        return tip + " " + boja + " " + cena;
    }
}

class SpisakIgracaka {
    Igracka[] spisak;
    int brojac;
    static final int MAX_IGRACAKA = 1000;

    public SpisakIgracaka() {
        spisak = new Igracka[MAX_IGRACAKA];
        brojac = 0;
    }

    public void ucitajIgrackeIzFile(String fInput) {
        if (!Svetovid.testIn(fInput)) {
            Svetovid.out.println("Greska prilikom citanja fajla!");
            return;
        }
        while (Svetovid.in(fInput).hasMore()) {
            if (brojac >= MAX_IGRACAKA) {
                Svetovid.out.println("Dostignut je maksimalan broj igracaka.");
                break;
            }
            spisak[brojac] = Igracka.dodajIgracku(
                    Svetovid.in(fInput).readToken(),
                    Svetovid.in(fInput).readToken(),
                    Svetovid.in(fInput).readDouble());
            brojac++;
        }
        Svetovid.in(fInput).close();
    }

    public void ispisUFile(String fOutput) {
        for (int i = 0; i < brojac; i++) {
            Svetovid.out(fOutput).println(spisak[i].tip + " " + spisak[i].boja + " " + spisak[i].cena);
        }
        Svetovid.out(fOutput).close();
    }

    public void ispisNaEkran(){
        for (int i = 0; i < brojac; i++) {
            Svetovid.out.println(spisak[i]);
        }
    }

    public void dodajIgrackuUSpisak(){
        if(brojac >= MAX_IGRACAKA){
            Svetovid.out.println("Maksimalan broj knjiga je dostignut!");
            return;
        }
        Svetovid.out.println("----Unos igracke----");
        spisak[brojac] = Igracka.dodajIgracku(
            Svetovid.in.readLine("Unesite tip igracke: "),
            Svetovid.in.readLine("Unesite boju igracke: "), 
            Svetovid.in.readDouble("Unesite cenu igracke: ")
            );
        brojac++;
    }

    public void ispisPoBoji(String boja){
        for (int i = 0; i < brojac; i++) {
            if(spisak[i].boja.equalsIgnoreCase(boja))
                Svetovid.out.println(spisak[i]);
        }
    }

    public void prebrojSkupljeOdCene(double cena){
        int br = 0;
        for (int i = 0; i < brojac; i++) {
            if(spisak[i].cena > cena)
                br++;
        }
        if(br>0)
            Svetovid.out.println("Od zadate cene ima ukupno " + br + " skupljih igracaka");
        else
            Svetovid.out.println("Od zadate cene nema skupljih igracaka");
        
    }

    public String toString() {
        String izl = "Spisak [ ";
        if (brojac > 0)
            izl += spisak[0];
        for (int i = 0; i < brojac; i++) {
            izl += ", " + spisak[i];
        }
        izl += " ]";
        return izl;
    }
}