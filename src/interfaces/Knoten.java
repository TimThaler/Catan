package interfaces;

public interface Knoten {
	public boolean verbundenMitFeld(spielbrettKomponenten.Feld feld);
	//public static void feldRegistrieren(spielbrettKomponenten.Feld feld);
	public int anzahlBesetzterEckpunkte();
	public spielbrettKomponenten.Ecke getFreieEcke();
}
