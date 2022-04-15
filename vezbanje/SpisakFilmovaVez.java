class SpisakFilmovaVez {
    public static void main(String[] args) {
        SpisakFilmova lista = new SpisakFilmova();
        lista.insertMovie("naslov");
        lista.insertMovie("naslov2");
        lista.insertMovie("naslov3");
        lista.printMovies();
        lista.addActor("imePrezime", "naslov2");
        Svetovid.out.println(lista);
        lista.printMoviesByActor("imePrezime");
        lista.deleteMoviesWhereActorIs("imePrezime");
        Svetovid.out.println(lista);
    }
}

class SpisakFilmova {

    class Film {
        String naslov;
        ListaGlumaca glumci = new ListaGlumaca();
        Film sledeci;

        public Film(String naslov, Film sledeci) {
            this.naslov = naslov;
            this.sledeci = sledeci;
        }

        public void dodajGlumac(String imePrezime) {
            glumci.inserActor(imePrezime);
        }

        public String filmovi(String imePrezime) {
            return glumci.isThereActor(imePrezime);
        }

        public String toString() {
            String rez = "Film ";
            rez += naslov + " " + glumci;
            return rez;
        }
    }

    class Glumac {
        String imePrezime;
        Glumac sledeci;

        public Glumac(String imePrezime, Glumac sledeci) {
            this.imePrezime = imePrezime;
            this.sledeci = sledeci;
        }

        public String toString() {
            return imePrezime;
        }
    }

    class ListaGlumaca {
        Glumac prvi;

        public ListaGlumaca() {
            this.prvi = null;
        }

        public void inserActor(String imePrezime) {
            Glumac cur = this.prvi;
            while (cur != null) {
                if (cur.imePrezime.equalsIgnoreCase(imePrezime)) {
                    Svetovid.out.println("Vec postoji glumac!");
                    return;
                }
                cur = cur.sledeci;
            }
            Glumac novi = new Glumac(imePrezime, null);
            novi.sledeci = this.prvi;
            this.prvi = novi;
        }

        public String isThereActor(String imePrezime) {
            Glumac cur = this.prvi;
            while (cur != null) {
                if (cur.imePrezime.equalsIgnoreCase(imePrezime)) {
                    return cur.imePrezime;
                }
            }
            return null;
        }

        public String toString() {
            String rez = "Glumci [";
            Glumac cur = this.prvi;
            while (cur != null) {
                rez += cur.imePrezime + " ";
                cur = cur.sledeci;
            }
            rez += "]";
            return rez;
        }
    }

    Film prvi;

    public SpisakFilmova() {
        this.prvi = null;
    }

    public void insertMovie(String naslov) {
        Film cur = this.prvi;
        while (cur != null) {
            if (cur.naslov.equalsIgnoreCase(naslov)) {
                Svetovid.out.println("Vec postoji film sa tim imenom.");
                return;
            }
            cur = cur.sledeci;
        }
        Film novi = new Film(naslov, null);
        novi.sledeci = this.prvi;
        this.prvi = novi;
    }

    public void addActor(String imePrezime, String movie) {
        Film cur = this.prvi;
        while (cur != null) {
            if (cur.naslov.equalsIgnoreCase(movie)) {
                cur.dodajGlumac(imePrezime);
            }
            cur = cur.sledeci;
        }
    }

    public void deleteMoviesWhereActorIs(String imePrezime) {
        Film cur = this.prvi, pred = null;
        while (cur != null) {
            if (cur.filmovi(imePrezime) != null) {
                if (cur.filmovi(imePrezime).equalsIgnoreCase(imePrezime)) {
                    pred.sledeci = cur.sledeci;
                }
            }
            pred = cur;
            cur = cur.sledeci;
        }
    }

    public void printMovies() {
        Film cur = this.prvi;
        while (cur != null) {
            System.out.println(cur.naslov);
            cur = cur.sledeci;
        }
    }

    public void printMoviesByActor(String imePrezime) {
        Film cur = this.prvi;
        while (cur != null) {
            if (cur.filmovi(imePrezime) != null)
                if (cur.filmovi(imePrezime).equalsIgnoreCase(imePrezime)) {
                    Svetovid.out.println(cur);
                }
            cur = cur.sledeci;
        }
    }

    public String toString() {
        String rez = "Filmovi [";
        Film cur = this.prvi;
        while (cur != null) {
            rez += cur.naslov + " " + cur.glumci + " ";
            cur = cur.sledeci;
        }
        rez += "]";
        return rez;
    }
}