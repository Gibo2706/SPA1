/**
 * Demonstracija upotrebe steka i reda opsluzivanja znakova.
 * 
 */
public class StekRed {
	public static void main(String[] args) {

		//System.out.println("Unesite string: ");
		String str = "0123456789";// Svetovid.in.readLine();

		System.out.println();

		Stek<Character> stek = new Stek<>(str.length());

		for (int i = 0; i < str.length(); i++) {
			stek.stavi(str.charAt(i));
			System.out.println(stek);
		}

		Red<Character> red = new Red<>(str.length());

		for (int i = 0; i < str.length(); i++) {
			red.naKraj(str.charAt(i));
			System.out.println(red);
		}

		char c;

		System.out.println("Sadrzaj steka");
		while (!stek.jePrazan()) {
			c = stek.skiniVrh();
			System.out.print(c);
		}
		System.out.println();

		System.out.println("Sadrzaj reda opsl.");
		while (!red.jePrazan()) {
			c = red.izbaciPrvi();
			System.out.print(c);
		}
		System.out.println();
	}
}