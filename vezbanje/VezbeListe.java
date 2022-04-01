public class VezbeListe {
    public static void main(String[] args) {
        Lista lista = new Lista();
        int n = Svetovid.in.readInt("Unesite broj korisnika: ");
        for (int i = 0; i < n; i++) {
            lista.inserFirt(Svetovid.in.readLine("Unesite "+ (i+1) + " korisnika"));
        }
        lista.printFirstToLast();
        lista.printLongerThan3();
        lista.findString(Svetovid.in.readLine("Unesite string: "));
        lista.printOnlyLast();
        // String file = "files/input/imena.txt";
        // String fileO = "file/output/liste.txt";
        // lista.insertFromFile(file);
        // lista.findString(Svetovid.in.readLine("Unesite ime: "));
        // lista.saveToFile(fileO);
    }
}

class Cvor {
    String element;
    Cvor succ;

    public Cvor(String elem, Cvor succ) {
        this.element = elem;
        this.succ = succ;
    }
}

class Lista {
    Cvor prvi;

    public Lista() {
        this.prvi = null;
    }

    public void inserFirt(String elem) {
        Cvor insert = new Cvor(elem, null);
        insert.succ = this.prvi;
        this.prvi = insert;
    }

    public void insert(String elem, Cvor pred) {
        Cvor ins = new Cvor(elem, null);
        if (pred == null) {
            ins.succ = this.prvi;
            this.prvi = ins;
        } else {
            ins.succ = pred.succ;
            pred.succ = ins;
        }
    }

    public void insertFromFile(String file){
        if(Svetovid.testIn(file)){
            while(Svetovid.in(file).hasMore()){
                inserFirt(Svetovid.in(file).readToken());
            }
            Svetovid.in(file).close();
        }
    }

    public void printFirstToLast() {
        Cvor curr = this.prvi;
        while (curr != null) {
            System.out.println(curr.element);
            curr = curr.succ;
        }
    }

    public void printLongerThan3() {
        Cvor curr = this.prvi;
        while (curr != null) {
            if (curr.element.length() > 3) {
                Svetovid.out.println(curr.element);
            }
            curr = curr.succ;
        }
    }

    public void findString(String find) {
        Cvor curr = this.prvi;
        int counter = 0;
        while (curr != null) {
            if (curr.element.equals(find)) {
                counter++;
            }
            curr = curr.succ;
        }
        if(counter != 0)
            Svetovid.out.println("Ima ukupno " + counter + " takvih objekata.");
        else{
            Svetovid.out.println("Nema takvih objekata u listi. Dodat je u listu");
            inserFirt(find);
        }
    }

    public void saveToFile(String fileO){
        Cvor curr = this.prvi;
        while (curr != null) {
            if (curr.succ == null)
                Svetovid.out(fileO).println("Poslednji element liste je " + curr.element);
        }
        Svetovid.out(fileO).close();
    }

    public void printOnlyLast(){
        Cvor curr = this.prvi;
        while(curr != null){
            if(curr.succ == null)
                Svetovid.out.println("Poslednji element liste je " + curr.element);
        }
    }

    public String toString() {
        String rez = "Lista [ ";
        Cvor tek = prvi;
        while (tek != null) {
            rez += tek.element + " ";
            tek = tek.succ;
        }
        rez += " ]";
        return rez;
    }
}