package spielbrettKomponenten;

import java.util.Random;
import java.util.Vector;

import enums.*;
import interfaces.*;

public class Spielbrett 
implements interfaces.Spielbrett{
	private static Spielbrett instance = null;
	private Vector<Feld> felder = null;

	public static Spielbrett getInstance() {
		if(instance == null) {
			instance = new Spielbrett();
		}
		return instance;
	}

	public Spielbrett() {
		this.felder = new Vector<Feld>();
		Random r = new Random();
		Ecke e = new Ecke(new Feld(Rohstoff.Erz,5));
		e.getFeld();
		
	  
		/**
		 * Create fields with corners and edges
		 */
		for(int i = 0; i < Konstanten.FELDER_AUF_SPIELBRETT; i++){	
			Rohstoff rohstoff = Rohstoff.values()[(r.nextInt(5))];			
			Feld feld = new Feld(rohstoff,(r.nextInt(11)+1));			
			felder.addElement(feld);
		}   
		 
		/**
		 * link fields
		 * get first field link it with 6 nodes
		 * get 2nd field link it with 2 or nodes and create 4 new nodes
		 * get 3rd field link it with 2 or 3 existing nodes (therefore fields) create 4 or 3 new nodes
		 * get 4th field link it with 2 or 3 existing nodes (therefore fields) and create 4 or 3 new nodes
		 */
		Feld ersteFeld = felder.firstElement();	
		Knoten.feldRegistrieren(ersteFeld);
    	ersteFeld.setIstPlaziert(true);
		Feld zweiteFeld = felder.get(1);
		Knoten.feldRegistrieren(zweiteFeld);
		zweiteFeld.setIstPlaziert(true);
		
		Knoten.feldRegistrieren(felder.get(2));
		felder.get(2).setIstPlaziert(true);
		int x = 0;
		x++;
		Knoten.printKnotenMap();
    	
	}
}
