package interfaces;

import java.util.Set;

public interface Ecke {
	 
	public int getId();
	public spielbrettKomponenten.Feld getFeld();
	public Set<spielbrettKomponenten.Ecke> getNachbarEcken();
}
