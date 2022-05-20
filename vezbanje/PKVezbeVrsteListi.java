import java.util.*;

public class PKVezbeVrsteListi {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<String>();
        String pathIn = "C:\\Faks\\SEM2\\SPA1\\SPA1\\files\\input\\imena.txt";
        int inList = 0;
        if(Svetovid.testIn(pathIn)){
            while(Svetovid.in(pathIn).hasMore()){
                list.add(Svetovid.in(pathIn).readLine());
            }
        }
        inList = list.size();
        Svetovid.out.println(list);
        System.out.println("-------------------------------------");
        list.add(3, "ImeKojeJeDodatoNa2.Mesto");
        Svetovid.out.println(list);
        System.out.println("-------------------------------------");
        System.out.println(list.set(4, "elementNa4Poziciji")); 
        System.out.println(list.get(4));
        System.out.println("-------------------------------------");
        for (int i = 0; i < inList; i++) {
            if(i == 0 || i%2 == 0){
                System.out.print(list.get(i) + " ");
            }
            Svetovid.in(pathIn).close();
        }
        System.out.println("-------------------------------------");
        for (int i = 0; i < inList; i++) {
            if(list.get(i).charAt(0) == 'S'){
                System.out.print(list.get(i) + " ");
            }
        }
        list.remove(3);
        System.out.println();
        String imeZaIzbacivanje = Svetovid.in.readLine("Unesite ime koje zelite da izbacite: ");
        list.remove(imeZaIzbacivanje);
        System.out.println("-------------------------------------");
        Svetovid.out.println(list);
        inList = list.size();
        // String pathout
        String pathOut = "C:\\Faks\\SEM2\\SPA1\\SPA1\\files\\output\\imenaListaVezb.txt";
        for (int i = 0; i < list.size(); i++) {
            Svetovid.out(pathOut).println(list.get(i));
        }
        Svetovid.out(pathOut).close();

        List<String> list2 = new LinkedList<String>();
        list2.addAll(list);
        pathIn = "files\\input\\Stud10.txt";
        pathOut = "files\\output\\Stud10List.txt";
        if(Svetovid.testIn(pathIn)){
            while(Svetovid.in(pathIn).hasMore()){
                list2.add(Svetovid.in(pathIn).readLine());
            }
            Svetovid.in(pathIn).close();
        }
        for (int i = 0; i < list2.size(); i++) {
            Svetovid.out(pathOut).println(list2.get(i));
        }
    }
}