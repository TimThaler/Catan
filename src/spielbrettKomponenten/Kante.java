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

	private void setupNeighbourEdge(Kante k) {
		try {
			if(this.nachbarKante != null) {
				throw new Exception("Edge with id: " + this.id + "already has a neighbour");
			}
			this.nachbarKante = k;
		}catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(-1);
		}
	}
	
	public void setupSymmetricNeighbourRelation(Kante k) {
		try {
			if (this.nachbarKante != null) {
				throw new Exception("Edge with id: " + this.id + "already has a neighbour");
			}	
			this.nachbarKante = k;
			k.setupNeighbourEdge(this);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(-1);
		}
	}
	
	public Feld getFeld() {
		return this.feld;
	}
}
