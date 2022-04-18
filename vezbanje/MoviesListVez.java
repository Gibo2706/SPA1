class MoviesListVez {
    public static void main(String[] args) {
        MoviesList lista = new MoviesList();
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

class MoviesList {

    class Movie {
        String title;
        ActorsList actors = new ActorsList();
        Movie next;

        public Movie(String title, Movie next) {
            this.title = title;
            this.next = next;
        }

        public void addActor(String fullName) {
            actors.insertActor(fullName);
        }

        public String moviesOfActor(String fullName) {
            return actors.isThereActor(fullName);
        }

        public String toString() {
            String rez = "Film ";
            rez += title + " " + actors;
            return rez;
        }
    }

    class Actor {
        String fullName;
        Actor next;

        public Actor(String fullName, Actor next) {
            this.fullName = fullName;
            this.next = next;
        }

        public String toString() {
            return fullName;
        }
    }

    class ActorsList {
        Actor first;

        public ActorsList() {
            this.first = null;
        }

        public void insertActor(String fullName) {
            Actor cur = this.first;
            while (cur != null) {
                if (cur.fullName.equalsIgnoreCase(fullName)) {
                    Svetovid.out.println("Vec postoji glumac!");
                    return;
                }
                cur = cur.next;
            }
            Actor novi = new Actor(fullName, null);
            novi.next = this.first;
            this.first = novi;
        }

        public String isThereActor(String fullName) {
            Actor cur = this.first;
            while (cur != null) {
                if (cur.fullName.equalsIgnoreCase(fullName)) {
                    return cur.fullName;
                }
            }
            return null;
        }

        public String toString() {
            String rez = "Glumci [";
            Actor cur = this.first;
            while (cur != null) {
                rez += cur.fullName + " ";
                cur = cur.next;
            }
            rez += "]";
            return rez;
        }
    }

    Movie first;

    public MoviesList() {
        this.first = null;
    }

    public void insertMovie(String title) {
        Movie cur = this.first;
        while (cur != null) {
            if (cur.title.equalsIgnoreCase(title)) {
                Svetovid.out.println("Vec postoji film sa tim imenom.");
                return;
            }
            cur = cur.next;
        }
        Movie newMovie = new Movie(title, null);
        newMovie.next = this.first;
        this.first = newMovie;
    }

    public void addActor(String fullName, String movie) {
        Movie cur = this.first;
        while (cur != null) {
            if (cur.title.equalsIgnoreCase(movie)) {
                cur.addActor(fullName);
            }
            cur = cur.next;
        }
    }

    public void deleteMoviesWhereActorIs(String fullName) {
        Movie cur = this.first, pred = null;
        while (cur != null) {
            if (cur.moviesOfActor(fullName) != null) {
                if (cur.moviesOfActor(fullName).equalsIgnoreCase(fullName)) {
                    pred.next = cur.next;
                }
            }
            pred = cur;
            cur = cur.next;
        }
    }

    public void printMovies() {
        Movie cur = this.first;
        while (cur != null) {
            System.out.println(cur.title);
            cur = cur.next;
        }
    }

    public void printMoviesByActor(String fullName) {
        Movie cur = this.first;
        while (cur != null) {
            if (cur.moviesOfActor(fullName) != null) {
                if (cur.moviesOfActor(fullName).equalsIgnoreCase(fullName)) {
                    Svetovid.out.println(cur);
                }
            }
            cur = cur.next;
        }
    }


    public String toString() {
        String rez = "Filmovi [";
        Movie cur = this.first;
        while (cur != null) {
            rez += cur.title + " " + cur.actors + " ";
            cur = cur.next;
        }
        rez += "]";
        return rez;
    }
}