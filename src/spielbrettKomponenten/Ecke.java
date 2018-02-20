package spielbrettKomponenten;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Ecke 
implements interfaces.Ecke{
	private final Feld feld;
	private final int id;
	private Knoten knoten = null;
	private Kante kante;

	
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
	
	public void setKante(Kante kante) {
		this.kante = kante;
	}
	
	@Override
	public Set<Ecke> getNachbarEcken() {
		Set<Ecke> nachbarn = new HashSet<Ecke>();
		//System.out.println(this.getId() + " id der ecke zu suchen ");
	    Iterator<Kante> iterator = Kante.kantenSet.iterator();
	    while(iterator.hasNext()) {
	    	Kante k = iterator.next();
	    //	System.out.println(k.getId() + " kanten id");
	    	
	    	if (k.hasEcke(this)) {
	    	//	System.out.println(this.getId() + " ecken id");
	    	//	System.out.println(k.getNachbarEcke(this).getId() + " nachbarecken id");
				nachbarn.add(k.getNachbarEcke(this));
			}
	    }
	    return nachbarn;
			
	}
}
