public class VezbanjeZaKol01 {
    public static void main(String[] args) {
        String file = Svetovid.in.readLine("Unesite ime fajla: ");
        SpisakStudenata spisak = new SpisakStudenata();
        spisak.ucitajStudente(file);
        spisak.dodajStudenta(Svetovid.in.readLine("Unesite prezime: "), Svetovid.in.readLine("Unesite ime: "),
                Svetovid.in.readInt("Unesite godiste: "));
        spisak.ispisiStudenteConsole();
        int god = Svetovid.in.readInt("Unesite godinu na osnovu koje zelite izlistavanje: ");
        spisak.ispisiStudSaGodinom(god);
        Svetovid.out.println("Broj studenata pre date godine je: " + spisak.prebrojPreGod(god));
        spisak.ispisiStudenteFile("vezbanje/VezbanjeSaStudentima.txt");
    }
}

class Student {
    String ime, prezime;
    int godiste;

    public Student(String prezime, String ime, int godiste) {
        this.prezime = prezime;
        this.ime = ime;
        this.godiste = godiste;
    }

    static Student upisiStudenta(String prezime, String ime, int godiste) {
        return new Student(prezime, ime, godiste);
    }

    public String toString() {
        return prezime + " " + ime + " " + godiste;
    }
}

class SpisakStudenata {
    static final int MAX_STUDENATA = 1000;
    static Student[] spisak;
    static int brStud;

    public SpisakStudenata() {
        spisak = new Student[MAX_STUDENATA];
        brStud = 0;
    }

    public void ucitajStudente(String file) {
        if (Svetovid.testIn(file)) {
            while (Svetovid.in(file).hasMore()) {
                if (brStud > MAX_STUDENATA - 1) {
                    Svetovid.out.println("Previse studenata ste pokusali da upisete. Ostatak nije upisan.");
                    break;
                }
                spisak[brStud] = Student.upisiStudenta(Svetovid.in(file).readToken(), Svetovid.in(file).readToken(),
                        Svetovid.in(file).readInt());
                brStud++;
            }
            Svetovid.in(file).close();
        } else
            Svetovid.out.println("Nema studenata u fajlu.");
    }

    static int lenghtOfArray() {
        int brojac = 0;
        for (int i = 0; i <= MAX_STUDENATA; i++) {
            if (spisak[i] == null) {
                brojac = i - 1;
                break;
            }
        }
        return brojac;
    }

    public void ispisiStudenteConsole() {
        for (int i = 0; i < brStud; i++) {
            Svetovid.out.println(spisak[i]);
        }
    }

    public void dodajStudenta(String prezime, String ime, int godiste) {
        if (brStud == MAX_STUDENATA) {
            Svetovid.out.println("Maximum broj studenata je dostignut. Student nije dodat!");
            return;
        }
        Svetovid.out.println("----Unos studenta----");
        spisak[brStud] = Student.upisiStudenta(prezime, ime, godiste);
        Svetovid.out.println("Student je uspesno dodat.");
        brStud++;
    }

    public void ispisiStudenteFile(String file) {
        for (int i = 0; i < brStud; i++) {
            Svetovid.out(file).println(spisak[i]);
        }
        Svetovid.in(file).close();
    }

    public void ispisiStudSaGodinom(int god) {
        for (int i = 0; i < brStud; i++) {
            if (spisak[i].godiste == god)
                Svetovid.out.println(spisak[i]);
        }
    }

    public int prebrojPreGod(int god) {
        int brSaGodStud = 0;
        for (int i = 0; i < brStud; i++) {
            if (spisak[i].godiste <= god)
                brSaGodStud++;
        }
        return brSaGodStud;
    }

    public String toString() {
        String st = "Studenti [";
        if (brStud > 0)
            st += spisak[0];
        for (int i = 0; i < brStud; i++) {
            st += ", " + spisak[i];
        }
        st += "]";
        return st;
    }

}