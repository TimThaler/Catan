package spielbrettKomponenten;

import enums.Ausrichtung;

public class Kante 
implements interfaces.Kante{
	private final Ecke ersteEcke;
	private final Ecke zweiteEcke;
	private final int id;
	private final Ausrichtung ausrichtung;
	private Kante nachbarKante = null;
	private final Feld feld;
	
	private static int idZaehler = 0;
		
	public Kante(Ecke ersteEcke, Ecke zweiteEcke, Ausrichtung ausrichtung, Feld feld) {
		this.feld = feld;
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

	/**
	 * Nachbarkante ist die Kante die direkt an (this) dieser Kante anliegt.
	 * Gehört zu einem andern Feld
	 * @return
	 */
	public Kante getNachbarKante() {
		return nachbarKante;
	}

	public void setNachbarKante(Kante nachbarKante) {
		this.nachbarKante = nachbarKante;
	}
	
	public Feld getFeld() {
		return this.feld;
	}
	
	public Knoten getGemeinsamenKnoten(Kante k) {
		Knoten knoten = null;
		Knoten k1 = this.getErsteEcke().getKnoten();
		Knoten k2 = this.getZweiteEcke().getKnoten();
		Knoten k1Nachbar = k.getErsteEcke().getKnoten();
		Knoten k2Nachbar = k.getZweiteEcke().getKnoten();

		if (k1.getId() == k1Nachbar.getId()) {
			knoten =  k1;
		} else if (k1.getId() == k2Nachbar.getId()) {
			knoten = k1;
		} else if (k2.getId() == k2Nachbar.getId()) {
			knoten = k2;
		} else if (k2.getId() == k2Nachbar.getId()) {
			knoten = k2;
		}
		if(knoten == null) {
			System.out.println("Error getGemeinsamenKnoten");;
//			throw Exception e 
		}
		return knoten;
	}
}
