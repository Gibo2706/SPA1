/*
	Kolokvijum 06.05.2022
	Zadatak:
		Napraviti klasu Vrtic koja sadrzi listu Vaspitaca(Svaki ima unikatno ime, godine radnog staza i listu dece/sticenika)
		Svako dete ima svoje ime, pol i broj godina (Pri unosu sortirani po godinama)
		1. Napraviti jednu toString metodu za ispis liste Vrtica - Pozeljno u svakoj klasi napisati toString metodu
		2. Napraviti metodu za unos vaspitaca, paziti na ime moze samo jedno isto da bude - preporuka equalsIgnoreCase
			2.1 Napraviti metodu za unos dece/sticenika - sortirati po broju prilikom unosa, deca mogu imati ista imena.
		3. Napraviti metodu koja uklanja vaspitaca i svu njegovu decu prebacuje sledecem u listi (ako ga ima, u drugom slucaju samo obrisati svu decu)
			bez zauzimanja nove memorije
*/
public class KolokvijumDrugi {
	public static void main(String args[]){
		Vrtic vrt = new Vrtic();
		// Testing cases
		vrt.dodajVaspitaca("Milana Mijkovic", 23);
		vrt.dodajVaspitaca("Milana Mijkovic", 23);
		vrt.dodajVaspitaca("Milana Mijkovic", 23);
		vrt.dodajVaspitaca("Jelena Markovic", 23);
		vrt.dodajVaspitaca("Dragana", 23);
		vrt.dodajVaspitaca("Jovana", 23);
		vrt.dodajVaspitaca("Natasa", 23);
		vrt.dodajDete("Milan", "Musko", 4, "Milana Mijkovic");
		vrt.dodajDete("Jovan", "Musko", 2, "Milana Mijkovic");
		vrt.dodajDete("Petar", "Musko", 6, "Milana Mijkovic");
		vrt.dodajDete("Ivan", "Musko", 1, "Milana Mijkovic");
		vrt.dodajDete("Milan", "Musko", 4, "Jelena Markovic");
		vrt.dodajDete("Jovan", "Musko", 2, "Jelena Markovic");
		vrt.dodajDete("Petar", "Musko", 6, "Jelena Markovic");
		vrt.dodajDete("Ivan", "Musko", 1, "Jelena Markovic");
		Svetovid.out.println(vrt);
		Svetovid.out.println("---------------------------");
		vrt.dodajDete("Marko", "Musko", 2, "Jovana");
		vrt.dodajDete("Ivana", "Zensko", 2, "Jovana");
		vrt.dodajDete("Milica", "Zensko", 2, "Jovana");
		Svetovid.out.println(vrt);
		Svetovid.out.println("---------------------------");
		vrt.ukloniVasp("Jovana");
		Svetovid.out.println(vrt);
		
		
	}
}
class Vrtic {
	class Vaspitac {
		String ime;
		int godRad;
		Sticenik prvi;
		Vaspitac sledeci;
		
		public Vaspitac(String ime, int godRad){
			this.ime = ime;
			this.godRad = godRad;
			this.prvi = null;
			this.sledeci = null;
		}
		
		public String toString(){
			String rez = "Vaspitac: " + ime + " , Godine rada: " + godRad + " , Deca: [ ";
			if(prvi != null){
				Sticenik pom = prvi;
				while(pom.sledeci != null){
					rez += pom + ", ";
					pom = pom.sledeci;
				}
				rez += pom + " ]";
				
			}else{
				rez += "Empty! ]";
			}
			return rez;
		}
	}
	
	class Sticenik {
		String ime, pol;
		int god;
		Sticenik sledeci;
		
		public Sticenik(String ime, String pol, int god){
			this.ime = ime;
			this.pol = pol;
			this.god = god;
			this.sledeci = null;
		}
		
		public String toString(){
			return "Dete: " + ime + " Pol: " + pol + " Godine: "+ god;
		}
	}
	
	Vaspitac prvi; 
	
	public Vrtic(){
		this.prvi = null;
	}
	
	public void dodajVaspitaca(String ime, int godRad){
		if(prvi == null){
			Vaspitac novi = new Vaspitac(ime, godRad);
			novi.sledeci = prvi;
			prvi = novi;
		}else{
			if(!prvi.ime.equalsIgnoreCase(ime)){
				Vaspitac tmp = prvi;
				while(tmp.sledeci != null && !tmp.sledeci.ime.equalsIgnoreCase(ime)){
					tmp = tmp.sledeci;
				}
				if(tmp.sledeci == null){
					Vaspitac novi = new Vaspitac(ime, godRad);
					novi.sledeci = tmp.sledeci;
					tmp.sledeci = novi;
				}
			}
		}
	}
	
	public void dodajDete(String ime, String pol, int god, String imeVasp){
		if(prvi == null)
			return;
		Vaspitac cur = prvi;
		while(cur != null){
			if(cur.ime.equalsIgnoreCase(imeVasp)){
				if(cur.prvi == null || cur.prvi.god > god){
					Sticenik novi = new Sticenik(ime, pol, god);
					novi.sledeci = cur.prvi;
					cur.prvi = novi;
				}else{
					Sticenik pom = cur.prvi;
					while(pom.sledeci != null && pom.sledeci.god < god){
						pom = pom.sledeci;
					}
					Sticenik novi = new Sticenik(ime, pol, god);
					novi.sledeci = pom.sledeci;
					pom.sledeci = novi;
				}
				break;
			}
			cur = cur.sledeci;
		}
	}
	// One mistake, this methode doesn't delete Teacher just move/delete his children, still not fixed
	public void ukloniVasp(String ime){
		if(prvi == null)
			return;
		if(prvi.ime.equalsIgnoreCase(ime)){
			if(prvi.sledeci != null){
				Vaspitac pom = prvi.sledeci;
				if(pom.prvi == null){
					pom.prvi = prvi.prvi;
					prvi.prvi = null;
					prvi = prvi.sledeci;
				}else {
					Sticenik tmp = pom.prvi;
					while(tmp.sledeci != null){
						tmp = tmp.sledeci;
					}
					tmp.sledeci = prvi.prvi;
					prvi.prvi = null;
					prvi = prvi.sledeci;
				}
			}else{
				prvi.prvi = null;
				prvi = null;
			}
		}else {
			Vaspitac cur = prvi, pret = null;
			while(cur.sledeci != null){
				pret = cur;
				cur = cur.sledeci;
				if(cur.ime.equalsIgnoreCase(ime)){
					if(cur.sledeci != null){
						Vaspitac pom = cur.sledeci;
						if(pom.prvi == null){
							pom.prvi = cur.prvi;
							cur.prvi = null;
							pret = cur.sledeci;
							cur = null;
							cur = pret;
						}else {
							Sticenik tmp = pom.prvi;
							while(tmp.sledeci != null){
								tmp = tmp.sledeci;
							}
							tmp.sledeci = cur.prvi;
							cur.prvi = null;
							pret = cur.sledeci;
							cur = null;
							cur = pret;
						}
					}else{
						cur.prvi = null;
						cur = null;
					}
					break;
				}
			}
		}
	}
	
	public String toString(){
		String rez = "Vrtic [ ";
		if(prvi != null){
			Vaspitac pom = prvi;
			while(pom.sledeci != null){
				rez += pom + ", ";
				pom = pom.sledeci;
			}
			rez += pom + " ]";
		}else{
			rez += "Empty! ]";
		}
		return rez;
	}
}


