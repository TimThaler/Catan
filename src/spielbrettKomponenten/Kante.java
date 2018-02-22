package spielbrettKomponenten;

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
