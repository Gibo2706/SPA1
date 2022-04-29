/*
Napisati klasu Pijaca koja predstavlja listu tezgi na nekoj pijaci.
Svaka tezga ima identifikacioni broj, naziv hale u kojoj se nalazi (tipa String) i listu namirnica
Lista tezgi je sortirana po broju. Svaka namirnica ima ime i jedinstvenu šifru.
Napraviti i testirati metode koji:

Dodaje novu tezgu sa datim identifikacionim brojem i nazivom hale.
Dodaje namirnicu na tezgu sa datim brojem. Ukoliko tezga sa zadatim brojem ne postoji, dodati prvo tezgu sa tim brojem, a zatim ubaciti namirnicu na nju.
Ispisuje brojeve tezgi koje imaju više od N namirnica.
Spaja poslednje dve tezge u datoj hali u jednu tako što prebacuje namirnice iz poslednje u pretposlednju i briše poslednju tezgu.
Ako u datoj hali nema bar 2 tezge, odustaje se od spajanja.
Ne zauzimati novu memoriju za namirnice.
*/
public class Zadatak3 {
	
	public static void main (String[] args) {
		Pijaca pijaca = new Pijaca();
		pijaca.dodajTezgu(1, "Prva hala");
		pijaca.dodajNamirnicu(1, "Hleb", 2);

		pijaca.dodajTezgu(2, "Prva hala");
		pijaca.dodajNamirnicu(2, "Riba", 4);

		Svetovid.out.println(pijaca);
	}
}

class Pijaca {
	
	private Tezga prvaTezga;
	private Tezga poslednjaTezga;
	
	class Tezga {
		int ID;
		String nazivHale;
		Tezga veza;
		Namirnica sadrzaj;
		
		public Tezga(int ID, String nazivHale) {
			this.ID = ID;
			this.nazivHale = nazivHale;
			this.veza = null;
		}

		public String toString(){
			String rez = "Tezga sa ID-om " + this.ID + " u hali " + this.nazivHale + " sadrzi: ";
			Namirnica cur = this.sadrzaj;
			while (cur != null) {
				rez += cur.toString() + " ";
				cur = cur.veza;
			}
			return rez;
		}
	}
	
	class Namirnica {
		String ime;
		int sifra;
		Namirnica veza;
		
		public Namirnica(String ime, int sifra) {
			this.ime = ime;
			this.sifra = sifra;
			this.veza = null;
		}

		public String toString() {
			return "Ime: " + ime + ", Sifra: " + sifra;
		}
	}
	
	// toString 
	public String toString() {
		String rez = "Pijaca: ";
		Tezga tek = prvaTezga;
		while (tek != null) {
			rez += tek.toString() + "\n";
			tek = tek.veza;
		}
		return rez;
	}
	
	
	public void dodajTezgu(int ID, String nazivHale) {
		if (!postojiTezga(ID)) {
			Tezga novi = new Tezga(ID, nazivHale);
			novi.veza = prvaTezga;
			prvaTezga = novi;
			if(poslednjaTezga == null)
				poslednjaTezga = novi;
		}
	}
	
	public boolean postojiTezga(int ID) {
		Tezga tekuci = prvaTezga;
		while (tekuci != null) {
			if (tekuci.ID == ID) {
				return true;
			}
			tekuci = tekuci.veza;
		}
		return false;
	}
	
	private Tezga nadjiTezgu(int ID) {
		Tezga tekuci = prvaTezga;
		while (tekuci != null) {
			if (tekuci.ID == ID) {
				return tekuci;
			}
			tekuci = tekuci.veza;
		}
		return null;
	}
	
	public void dodajNamirnicu(int ID, String ime, int sifra) {
		Tezga cilj = nadjiTezgu(ID);
		if (cilj == null){
			String nazivHale = Svetovid.in.readLine("Dodajte ime hale: ");
			dodajTezgu(ID, nazivHale);
			cilj = nadjiTezgu(ID);
		}
		Namirnica nova = new Namirnica(ime, sifra);
		nova.veza = cilj.sadrzaj;
		cilj.sadrzaj = nova;
	}
	
	public void brojeviTezgiKojeImajuViseOdN(int N) {
		Tezga tekuci = prvaTezga;
		while (tekuci != null) {
			int brojac = 0;
			while(tekuci.sadrzaj != null){
				brojac++;
				tekuci.sadrzaj = tekuci.sadrzaj.veza;
			}
			if(brojac > N)
				Svetovid.out.println("Broj tezge je: " + tekuci.ID);
			tekuci = tekuci.veza;
		}
	}
	
	public void spajajTezge(int ID1, int ID2) {
		Tezga tek1 = nadjiTezgu(ID1);
		Tezga tek2 = nadjiTezgu(ID2);
		if (tek1 != null && tek2 != null) {
			tek1.sadrzaj = tek2.sadrzaj;
			tek2.sadrzaj = null;
			if (tek1.veza == null)
				poslednjaTezga = tek1;
			if (tek2.veza == null)
				poslednjaTezga = tek2;
		}
	}

}
