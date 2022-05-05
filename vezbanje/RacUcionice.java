public class RacUcionice {
	public static void main(String args[]){
		RacunarskeUcionice igr = new RacunarskeUcionice();
		igr.dodajUcionicu("plava");
		igr.dodajUcionicu("zelena");
		igr.dodajUcionicu("crvena");
		igr.dodajUcionicu("zuta");
		igr.dodajUcionicu("zelena");
		igr.dodajUcionicu("zelena");
		
		if(igr.dodajOpremu(2, "monitor", "Dell")){
			System.out.println("Uspesno dodato");
		}
		if(igr.dodajOpremu(3, "mis", "MS")){
			System.out.println("Uspesno dodato");
		}
		if(igr.dodajOpremu(5, "3slusalice", "Whiteshark")){
			System.out.println("Uspesno dodato");
		}
		
		igr.dodajOpremu(2,"zelena","1tastatura","red Dragon");
		igr.dodajOpremu(2,"zelena","mis","red Dragon");
		igr.dodajOpremu(2,"zelena","1tastatura","red Dragon");
		igr.dodajOpremu(2,"zelena","monitor","red Dragon");
		igr.dodajOpremu(3,"zelena","podloga za mis","linus tech tips");
		System.out.println(igr);
		igr.ispisiOpremuZadateBoje("zelena");
		System.out.println("Opreme tipa \"mis\" ima: " + igr.prebrojatiSvuOpremu("mis"));
		igr.dodajOpremuUNajprazniju("mis", "Razer");
		igr.dodajOpremuUNajprazniju("tastatura", "Razer");
		igr.dodajOpremuUNajprazniju("slusalice", "Razer");
		System.out.println("---------------------------------------");
		System.out.println(igr);
		System.out.println("---------------------------------------");
		//igr.izbaciOpremuIzUcionice(5,"1tastatura");
		//System.out.println(igr);
		System.out.println("---------------------------------------");
		igr.izbaciOpremuIzSvih("1tastatura", "red Dragon");
		System.out.println(igr);
	}
}

class RacunarskeUcionice {
	class Ucionica {
		String boja;
		Oprema prva;
		Ucionica sledeca;
		
		public Ucionica(String boja){
			this.boja = boja;
			this.prva = null;
			this.sledeca = null;
		}
		
		public String toString(){
			String rez = boja + " Oprema: [ ";
			if(prva != null){
				Oprema tmp = prva;
				while(tmp.sledeca != null){
					rez += tmp + ", ";
					tmp = tmp.sledeca;
				}
				rez += tmp + " ]";
			}else{
				rez += "Empty! ]";
			}
			return rez;
		}
	}
	class Oprema {
		String tip, opis;
		Oprema sledeca;
		
		public Oprema(String tip, String opis){
			this.tip = tip;
			this.opis = opis;
			this.sledeca = null;
		}
		
		public String toString(){
			return "Tip: " + tip + " Opis: " + opis;
		}
	}
	
	Ucionica prva;
	private int brojUcionica = 0;
	
	public RacunarskeUcionice(){
		this.prva = null;
	}
	
	public void dodajUcionicu(String boja){
		if(prva == null){
			Ucionica nova = new Ucionica(boja);
			nova.sledeca = prva;
			prva = nova;
			brojUcionica++;
		}else {
			Ucionica tmp = prva;
			while(tmp.sledeca != null){
				tmp = tmp.sledeca;
			}
			Ucionica nova = new Ucionica(boja);
			nova.sledeca = tmp.sledeca;
			tmp.sledeca = nova;
			brojUcionica++;
		}
	}
	
	public Boolean dodajOpremu(int brUcionice, String tip, String opis){
		if(prva == null)
			return false;
		else{
			if(brUcionice <= brojUcionica){
				Ucionica cur = prva;
				int br = 1;
				while(cur.sledeca != null){	
					if((cur.prva == null || cur.prva.tip.compareTo(tip) > 0) && brUcionice == 1){
						Oprema nova = new Oprema(tip, opis);
						nova.sledeca = cur.prva;
						cur.prva = nova;
						return true;
					}else{
						if(br == brUcionice){		
							Oprema tmp = prva.prva;
							if(tmp == null){
								Oprema nova = new Oprema(tip, opis);
								nova.sledeca = cur.prva;
								cur.prva = nova;
								return true;
							}else{
								while(tmp.sledeca != null && tmp.sledeca.tip.compareTo(tip) < 0){
									tmp = tmp.sledeca;
								}
								Oprema nova = new Oprema(tip, opis);
								nova.sledeca = tmp.sledeca;
								tmp.sledeca = nova;
								return true;
							}
						}
					}
					cur = cur.sledeca;
					br++;
				}
			}else
				return false;
		}
		return false;
	}
	
	public void dodajOpremu(int n, String boja, String tip, String opis){
		int brNte = 0;
		if(prva == null)
			return;
		else{
			Ucionica cur = prva;
			while(cur != null){
				if(cur.boja.equalsIgnoreCase(boja)){
					brNte++;
					if(brNte == n){
						if((cur.prva == null || cur.prva.tip.compareTo(tip) > 0)){
							Oprema nova = new Oprema(tip, opis);
							nova.sledeca = cur.prva;
							cur.prva = nova;
						}else {
							Oprema tmp = cur.prva;
							if(tmp != null){
								while(tmp.sledeca != null && tmp.sledeca.tip.compareTo(tip) < 0){
									tmp = tmp.sledeca;
								}
								Oprema nova = new Oprema(tip, opis);
								nova.sledeca = tmp.sledeca;
								tmp.sledeca = nova;
							}else{ 
								Oprema nova = new Oprema(tip, opis);
								nova.sledeca = cur.prva;
								cur.prva = nova;
							}
						}
					}
				}
				cur = cur.sledeca;
			}
		}
	}
	
	public void ispisiOpremuZadateBoje(String boja){
		if(prva != null){
			System.out.println("Oprema iz ucionice zadate boje je: ");
			Ucionica tmp = prva;
			while(tmp != null){
				if(tmp.boja.equalsIgnoreCase(boja)){
					Oprema pom = tmp.prva;
					if(pom != null){
						while(pom != null){
							System.out.println(pom);
							pom = pom.sledeca;
						}
					}
				}
				tmp = tmp.sledeca;
			}
		}	
	}
	
	public int prebrojatiSvuOpremu(String tip){
		if(prva == null)
			return -1;
		Ucionica cur = prva;
		int brTipa = 0;
		while(cur != null){
			if(cur.prva != null){
				Oprema tmp = cur.prva;
				while(tmp != null){
					if(tmp.tip.equalsIgnoreCase(tip)){
						brTipa++;
					}
					tmp = tmp.sledeca;
				}
			}
			cur = cur.sledeca;
		}
		return brTipa;
	}
	
	public void dodajOpremuUNajprazniju(String tip, String opis){
		if(prva == null)
			return;
		Ucionica cur = prva;
		int min = Integer.MAX_VALUE;
		Ucionica pom = null;
		while(cur != null){
			int brOpreme = 0;
			if(cur.prva != null){
				Oprema opr = cur.prva;
				while(opr != null){
					brOpreme++;
					opr = opr.sledeca;
				}
				if(brOpreme < min){
					min = brOpreme;
					pom = cur;
				}
			}else{
				if(brOpreme < min){
					min = brOpreme;
					pom = cur;
				}
			}
			cur = cur.sledeca;
		}
		cur = prva;
		while(cur != null){
			if(cur.equals(pom)){
				if(cur.prva == null || cur.prva.tip.compareTo(tip) > 0){
					Oprema nova = new Oprema(tip, opis);
					nova.sledeca = cur.prva;
					cur.prva = nova;
				}else{
					Oprema tmp = cur.prva;
					while(tmp.sledeca != null && tmp.sledeca.tip.compareTo(tip) < 0){
						tmp = tmp.sledeca;
					}
					Oprema nova = new Oprema(tip, opis);
					nova.sledeca = tmp.sledeca;
					tmp.sledeca = nova;
				}
			}
			cur = cur.sledeca;
		}
	}
	
	public void izbaciOpremuIzUcionice(int brUcionice, String tip){
		if(prva == null)
			return;
		if(brUcionice > brojUcionica)
			return;
		int br = 1;
		Ucionica cur = prva;
		while(cur != null){
			if(br == brUcionice){
				if(cur.prva != null){
					Oprema pred = cur.prva;
					if(cur.prva.tip.equalsIgnoreCase(tip)){
						cur.prva = cur.prva.sledeca;
					}else{
						while(cur.prva != null){
							pred = cur.prva;
							if(cur.prva.tip.equalsIgnoreCase(tip)){
								pred = cur.prva.sledeca;
								cur.prva = pred;
							}
							cur.prva = cur.prva.sledeca;
						}
					}
				}
			}
			br++;
			cur = cur.sledeca;
		}
	}
	
	public void izbaciOpremuIzSvih(String tip, String opis){
		if(prva == null)
			return;
		Ucionica cur = prva;
		while(cur != null){
			if(cur.prva != null){
				Oprema tek = cur.prva, pred = null;
				if(tek.tip.equals(tip) && tek.opis.equals(opis)){
					cur.prva = cur.prva.sledeca;
				}else{
					while(tek != null){
						pred = cur.prva;
						if(tek.tip.equals(tip) && tek.opis.equals(opis)){
							tek = tek.sledeca;
							pred.sledeca = tek.sledeca;
						}
						tek = tek.sledeca;
					}
				}
			}
			cur = cur.sledeca;
		}
	}
	
	public String toString(){
		String rez = "Rac ucionica [ ";
		if(prva != null){
			Ucionica tmp = prva;
			while(tmp.sledeca != null){
				rez += tmp + ", ";
				tmp = tmp.sledeca;
			}
			rez += tmp + " ]";			
		}else{
			rez += "Empty! ]";
		}
		return rez;
	}
}
