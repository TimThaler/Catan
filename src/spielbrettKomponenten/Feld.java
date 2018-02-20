package spielbrettKomponenten;

import java.util.Vector;

import enums.*;
import interfaces.Konstanten;

public class Feld 
implements interfaces.Feld{
	private final Rohstoff rohstoff;
	private final int wuerfelZahl;
	private final int id;
	private Vector<Ecke> ecken = new Vector<Ecke>();
	private Vector<Kante> kanten = new Vector<Kante>();

	private boolean istPlaziert = false;

	
	private static int idZaehler = 0;

	
	public Feld(Rohstoff rohstoff, int wuerfelZahl) {
		this.rohstoff = rohstoff;
		this.wuerfelZahl = wuerfelZahl;
		this.id = idZaehler;
		idZaehler++;		
		
		//Create corners for field
		for(int x =0; x < Konstanten.ECKEN_PRO_FELD; x++){
			Ecke ecke = new Ecke(this);
    		this.ecken.addElement(ecke);
      	}	
		
		//create edge-relation between corners for field
		int i = 0;
    	Kante kante = new Kante(this.ecken.firstElement(),this.ecken.lastElement());
    	kanten.addElement(kante);
    	while(i < Konstanten.ECKEN_PRO_FELD - 1){
    		Kante k = new Kante(this.ecken.elementAt(i),this.ecken.elementAt(i+1)); 
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

	public boolean istPlaziert() {
		return istPlaziert;
	}

	public void setIstPlaziert(boolean istGesetzt) {
		this.istPlaziert = istGesetzt;
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

}
