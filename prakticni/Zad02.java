public class Zad02 {
    public static void main(String[] args) {
        int n;
        do {
            n = Svetovid.in.readInt("Unesite broj Stringova: ");
        } while (n <= 0);

        ListaStringova lista = new ListaStringova();
        for (int i = 0; i < n; i++) {
            lista.inserFirt(Svetovid.in.readLine("Unesite string: "));
        }
        lista.printFirstToLast();
        lista.printWithOddLength();
        lista.printFirstToLast();
        lista.deleteAllStartingWithNumber();
        lista.printFirstToLast();
        lista = lista.moveToNewObject(Svetovid.in.readLine("Unesite podstring: "));
        lista.printFirstToLast();
    }

}

class CvorString {
    String element;
    CvorString sledeci;

    public CvorString(String element, CvorString sledeci) {
        this.element = element;
        this.sledeci = sledeci;
    }
}

class ListaStringova {
    CvorString prvi;

    public ListaStringova() {
        this.prvi = null;
    }

    public void inserFirt(String element) {
        CvorString insert = new CvorString(element, null);
        insert.sledeci = this.prvi;
        this.prvi = insert;
    }

    public void printFirstToLast() {
        CvorString curr = this.prvi;
        while (curr != null) {
            System.out.println(curr.element);
            curr = curr.sledeci;
        }
    }

    public void printWithOddLength() {
        CvorString curr = this.prvi;
        while (curr != null) {
            if (curr.element.length() % 2 != 0)
                Svetovid.out.println(curr.element);
            curr = curr.sledeci;
        }
    }

    public void deleteAllStartingWithNumber() {
        if (prvi == null)
            return;
        CvorString curr, pred;
        curr = this.prvi;
        pred = null;
        while (curr != null) {
            if (Character.isDigit(curr.element.charAt(0))) {
                pred.sledeci = curr.sledeci;
            }
            pred = curr;
            curr = curr.sledeci;
        }
    }

    public ListaStringova moveToNewObject(String args) {
        CvorString curr = this.prvi, pred, izlazKraj = null;
        ListaStringova izlaz = new ListaStringova();
        while (curr != null && curr.element.contains(args)) {
            curr = prvi;
            prvi = prvi.sledeci;
            if (izlaz.prvi == null) {
                izlaz.prvi = curr;
                izlazKraj = curr;
                curr.sledeci = null;
            } else {
                izlazKraj.sledeci = curr;
                curr.sledeci = null;
                izlazKraj = curr;
            }

            curr = curr.sledeci;
        }
        if (prvi != null) {
            curr = prvi;
            while (curr.sledeci != null) {
                pred = curr;
                curr = curr.sledeci;
                if (curr.element.contains(args)) {
                    pred.sledeci = curr.sledeci;
                    if (izlaz.prvi == null) {
                        izlaz.prvi = curr;
                        curr.sledeci = null;
                        izlazKraj = curr;
                    } else {
                        izlazKraj.sledeci = curr;
                        curr.sledeci = null;
                        izlazKraj = curr;
                    }
                    curr = pred;
                }
            }
        }
        return izlaz;
    }
}