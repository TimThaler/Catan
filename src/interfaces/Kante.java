package interfaces;

import spielbrettKomponenten.Ecke;

public interface Kante {
	public Ecke getErsteEcke();
	public Ecke getZweiteEcke();
	public Ecke getNachbarEcke(Ecke ecke);
}
