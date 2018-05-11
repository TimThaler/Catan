package spielbrettKomponenten;

import java.awt.Point;
import java.util.Iterator;
import java.util.Vector;

import enums.*;
import interfaces.Konstanten;

public class Feld 
extends userInterface.Hexagon
implements interfaces.Feld{
	private final Rohstoff rohstoff;
	private final int wuerfelZahl;
	private final int id;
	private Vector<Ecke> ecken = new Vector<Ecke>();
	private Vector<Kante> kanten = new Vector<Kante>();

	private boolean istPlaziert = false;

	
	private static int idZaehler = 0;

	
	public Feld(Rohstoff rohstoff, int wuerfelZahl, Point point) {
		//super(Point)
		super(point);
		this.rohstoff = rohstoff;
		this.wuerfelZahl = wuerfelZahl;
		this.id = idZaehler;
		idZaehler++;		
		
		//Create corners for field
		for(int x = 0; x < Konstanten.ECKEN_PRO_FELD; x++){
			Ecke ecke = new Ecke(this);
    		this.ecken.addElement(ecke);
      	}	
		
		//create edge-relation between corners for field
		int i = 0;
		Ausrichtung[] ausrichtung = Ausrichtung.values();
    	Kante kante = new Kante(this.ecken.firstElement(),this.ecken.lastElement(),ausrichtung[0],this);
    	kanten.addElement(kante);
    	while(i < Konstanten.ECKEN_PRO_FELD - 1){
    		Kante k = new Kante(this.ecken.elementAt(i),this.ecken.elementAt(i+1),ausrichtung[i+1],this); 
        	kanten.add(k);
    		i++;
    	}
	}
	
	public Rohstoff getRohstoff() {
		return rohstoff;
	}
	
	public int getWuerfelZahl() {
		return wuerfelZahl;
	}
	
	public int getId() {
		return id;
	}

	public void setIstPlaziert(boolean istGesetzt) {
		this.istPlaziert = istGesetzt;
	}
	
	public void printEckenId() {
		System.out.println("eckenids or feld");
		for (Iterator iterator = ecken.iterator(); iterator.hasNext();) {
			Ecke ecke = (Ecke) iterator.next();
			System.out.println(ecke.getId());
		}
	}
	
	@Override
	public Ecke getUnbesetzteEcke() {
		for(Ecke e : ecken){
			if(e.getKnoten() == null) {
				return e;
			}
		}
		return null;
	}

	public Vector<Kante> getKanten() {
		return kanten;
	}
	
	public Ecke getGemeinsameEcke(Kante k1, Kante k2) {
		Ecke e = null;
		if (k1.getErsteEcke().getId() == k2.getErsteEcke().getId()){
			e = k1.getErsteEcke();
		} else if (k1.getZweiteEcke().getId() == k2.getErsteEcke().getId()) {
			e = k1.getZweiteEcke();
		} else if (k1.getErsteEcke().getId() == k2.getZweiteEcke().getId()){
			e = k1.getErsteEcke();
		} else if (k1.getZweiteEcke().getId() == k2.getZweiteEcke().getId()){
			e = k1.getZweiteEcke();
		}
		
		if(e == null) {
			//throw exception
		}
		return e;
	}

	public Kante getOstKante() {
		for(Kante k : kanten) {
			if(k.getAusrichtung() == Ausrichtung.ost) {
				return k;
			}
		}
		return null;
	}
	
	public Kante getnordOstKante() {
		for(Kante k : kanten) {
			if(k.getAusrichtung() == Ausrichtung.nordOst) {
				return k;
			}
		}
		return null;
	}
	
	public Kante getnordWestKante() {
		for(Kante k : kanten) {
			if(k.getAusrichtung() == Ausrichtung.nordWest) {
				return k;
			}
		}
		return null;
	}
	
	public Kante getsuedWestKante() {
		for(Kante k : kanten) {
			if(k.getAusrichtung() == Ausrichtung.suedWest) {
				return k;
			}
		}
		return null;
	}
	
	public Kante getsuedOstKante() {
		for(Kante k : kanten) {
			if(k.getAusrichtung() == Ausrichtung.suedOst) {
				return k;
			}
		}
		return null;
	}
	public Kante getWestKante() {
		for(Kante k : kanten) {
			if(k.getAusrichtung() == Ausrichtung.west) {
				return k;
			}
		}
		return null;
	}
	
	public void printFeldInfo() {
		for (Iterator<Kante> iterator = kanten.iterator(); iterator.hasNext();) {
			Kante k = (Kante) iterator.next();
			Ecke e = k.getNachbarEcke(k.getErsteEcke());
			System.out.println("Feld id: " + this.id + " |  Kanten id: " + k.getId() +
					" | ID of kante " + k.getErsteEcke().getId() + "  " + 
					e.getId() +		
					" | ausrichung: " + k.getAusrichtung());		
		}		
	}
}
