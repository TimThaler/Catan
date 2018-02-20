package interfaces;

import spielbrettKomponenten.Ecke;

public interface Kante {
	public Ecke getFirstCorner();
	public Ecke getSecondCorner();
	public Ecke getNachbarEcke(Ecke ecke);
}
