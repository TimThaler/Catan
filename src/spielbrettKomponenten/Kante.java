package spielbrettKomponenten;

import java.util.HashSet;
import java.util.Set;

public class Kante 
implements interfaces.Kante{
	private final Ecke ersteEcke;
	private final Ecke zweiteEcke;
	private final int id;
	
	private static int idZaehler = 0;
		
	public Kante(Ecke ersteEcke, Ecke zweiteEcke) {
		this.ersteEcke = ersteEcke;
		this.zweiteEcke = zweiteEcke;
		id = idZaehler;
		idZaehler++;
 
	

		/*
		System.out.println("Kante " + id + " mit Ecke " + ersteEcke.getId() + " und " + zweiteEcke.getId());
		System.out.println(" Ecke " + ersteEcke.getId() + " gehoert zu Feld " + ersteEcke.getFeld().getId());

		System.out.println(" Ecke " + zweiteEcke.getId() + " gehoert zu Feld " + zweiteEcke.getFeld().getId());
		*/
	}
	
	public boolean hasEcke(Ecke ecke) {
		if (this.ersteEcke.getId() == ecke.getId() || this.zweiteEcke.getId() == ecke.getId() ) {
			return true;
		} else {
			return false;
		}
	}
	public Ecke getFirstCorner() {
		return this.ersteEcke;
	}

	public Ecke getSecondCorner() {
		return this.zweiteEcke;
	}
	
	/**
	 * return always the other corner
	 */
	public Ecke getNachbarEcke(Ecke ecke){
		if(ersteEcke.getId() == ecke.getId()) {
			return zweiteEcke;
		}else if (zweiteEcke.getId() == ecke.getId()) {
			return ersteEcke;
		}else {
			return null;
		}
	}
	
	public int getId() {
		return this.id;
	}

}
