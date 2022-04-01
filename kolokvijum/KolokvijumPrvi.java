public class KolokvijumPrvi {
    static RecnaMreza mreza = null;

    public static void main(String[] args) {
        String fInput = "files/input/" + Svetovid.in.readLine("Unesite ime fajla: ");
        String fOutput = "files/output/rekeOutput.txt";
        mreza = new RecnaMreza();
        mreza.ucitajRekeIzFile(fInput);
        mreza.dodajRekuUMrezu();
        Svetovid.out.println("----Provera dva sliva----");
        mreza.proveraDvaSliva(Svetovid.in.readLine("Unesite naziv prvog sliva: "),
                Svetovid.in.readLine("Unesite naziv drugog sliva: "));
        mreza.ispisNaEkran();
        mreza.ispisUFile(fOutput);

    }
}

class Reka {
    String ime, sliv;
    double km;
    int drzave;

    public Reka(String ime, double km, String sliv, int drzave) {
        this.ime = ime;
        this.km = km;
        this.sliv = sliv;
        this.drzave = drzave;
    }

    public static Reka dodajReku(String ime, double km, String sliv, int drzave) {
        return new Reka(ime, km, sliv, drzave);
    }

    public String toString() {
        return ime + " " + km + " " + sliv + " " + drzave;
    }
}

class RecnaMreza {
    Reka[] mreza;
    int brojac;
    static final int MAX_REKA = 30;

    public RecnaMreza() {
        mreza = new Reka[MAX_REKA];
        brojac = 0;
    }

    public void ucitajRekeIzFile(String fInput) {
        if (!Svetovid.testIn(fInput)) {
            Svetovid.out.println("Greska prilikom ucitavanja fajla!");
            return;
        }
        while (Svetovid.in(fInput).hasMore()) {
            if (brojac >= MAX_REKA) {
                Svetovid.out.println("Dostignut je maksimalan broj u mrezi.");
                break;
            }
            mreza[brojac] = Reka.dodajReku(
                    Svetovid.in(fInput).readToken(),
                    Svetovid.in(fInput).readDouble(),
                    Svetovid.in(fInput).readToken(),
                    Svetovid.in(fInput).readInt());
            brojac++;
        }
        Svetovid.in(fInput).close();
    }

    public void ispisNaEkran() {
        for (int i = 0; i < brojac; i++) {
            Svetovid.out.println(mreza[i]);
        }
    }

    public void ispisUFile(String fOutput) {
        for (int i = 0; i < brojac; i++) {
            Svetovid.out(fOutput)
                    .println(mreza[i].ime + " " + mreza[i].km + " " + mreza[i].sliv + " " + mreza[i].drzave);
        }
        Svetovid.out(fOutput).close();
        Svetovid.out.println("----Ispis u file gotov.----");
    }

    public void dodajRekuUMrezu() {
        if (brojac >= MAX_REKA) {
            Svetovid.out.println("Maksimalan broj reka u mrezi je dostignut!");
            return;
        }
        Svetovid.out.println("----Unos reke u mrezu----");
        mreza[brojac] = Reka.dodajReku(
                Svetovid.in.readLine("Unesite ime reke: "),
                Svetovid.in.readDouble("Unesite duzinu reke: "),
                Svetovid.in.readLine("Unesite sliv u kojem pripada: "),
                Svetovid.in.readInt("Unesite broj drzava kroz koje prolazi: "));
        Svetovid.out.println("-------------------");
        brojac++;
    }

    public void proveraDvaSliva(String sliv1, String sliv2) {
        int br1 = 0, br2 = 0;
        for (int i = 0; i < brojac; i++) {
            if (mreza[i].sliv.equalsIgnoreCase(sliv1))
                br1++;
            if (mreza[i].sliv.equalsIgnoreCase(sliv2))
                br2++;
        }
        if (br1 == 0 && br2 == 0) {
            Svetovid.out.println("U izabranim slivovima nema reka.");
            return;
        }
        if (br1 > br2)
            Svetovid.out.println("U " + sliv1 + " slivu ima vise reka nego u " + sliv2 + " slivu");
        else if (br1 < br2)
            Svetovid.out.println("U " + sliv2 + " slivu ima vise reka nego u " + sliv1 + " slivu");
        else if (br1 == br2)
            Svetovid.out.println("U oba sliva ima podjednako isto reka");
        Svetovid.out.println("------------------");

    }

    public String toString() {
        String izl = "Mreza [";
        if (brojac > 0)
            izl += mreza[0];
        for (int i = 1; i < brojac; i++) {
            izl += ", " + mreza[i];
        }
        izl += " ]";
        return izl;
    }
}