package interfaces;

public interface Knoten {
	public boolean verbundenMitFeld(spielbrettKomponenten.Feld feld);
	//public static void feldRegistrieren(spielbrettKomponenten.Feld feld);
	public int anzahlVerbundenerFelder();
	public spielbrettKomponenten.Ecke getFreieEcke();
}
