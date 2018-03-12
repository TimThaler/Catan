package spielbrettKomponenten;

import enums.Ausrichtung;

public class Kante 
implements interfaces.Kante{
	private final Ecke ersteEcke;
	private final Ecke zweiteEcke;
	private final int id;
	private final Ausrichtung ausrichtung;
	private Kante nachbarKante = null;
	
	private static int idZaehler = 0;
		
	public Kante(Ecke ersteEcke, Ecke zweiteEcke, Ausrichtung ausrichtung) {
		this.ersteEcke = ersteEcke;
		this.zweiteEcke = zweiteEcke;
		this.ausrichtung = ausrichtung;
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
	public Ecke getErsteEcke() {
		return this.ersteEcke;
	}

	public Ecke getZweiteEcke() {
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

	public Ausrichtung getAusrichtung() {
		return this.ausrichtung;
	}

	public Kante getNachbarKante() {
		return nachbarKante;
	}

	public void setNachbarKante(Kante nachbarKante) {
		this.nachbarKante = nachbarKante;
	}
}
