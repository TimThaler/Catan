package spielbrettKomponenten;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Ecke 
implements interfaces.Ecke{
	private final Feld feld;
	private final int id;
	private Knoten knoten = null;
	
	private static int idZaehler = 0;
	
	public Ecke(Feld feld) {
		this.feld = feld;
		this.id = idZaehler;
		idZaehler++;	
	}
	
	@Override
	public int getId() {
		return this.id;
	}
	
	@Override
	public Feld getFeld() {
		return this.feld;
	}

	public Knoten getKnoten() {
		return knoten;
	}

	public void setKnoten(Knoten knoten) {
		this.knoten = knoten;
	}
	
	
	@Override
	public Set<Ecke> getNachbarEcken() { 
		
		Set<Ecke> nachbarn = new HashSet<Ecke>();

		Iterator<Kante> iterator = this.getFeld().getKanten().iterator();
	    while(iterator.hasNext()) {
	    	Kante k = iterator.next();
	    	
	    	if (k.hasEcke(this)) {
				nachbarn.add(k.getNachbarEcke(this));
			}
	    }
	    return nachbarn;
			
	}
}
