package spielbrettKomponenten;

import java.util.Random;
import java.util.Vector;

import enums.*;
import interfaces.*;

public class Spielbrett 
implements interfaces.Spielbrett{
	private static Spielbrett instance = null;
	private Vector<Feld> felder = null;

	private Feld ersteFeldErsteReihe = null;
	private Feld ersteFeldZweiteReihe = null;
	public static Spielbrett getInstance() {
		if(instance == null) {
			instance = new Spielbrett();
		}
		return instance;
	}

	public Spielbrett() {
		this.felder = new Vector<Feld>();
		Random r = new Random();
		
		
	  
		/**
		 * Create fields with corners and edges
		 */
		for(int i = 0; i < Konstanten.FELDER_AUF_SPIELBRETT; i++){	
			Rohstoff rohstoff = Rohstoff.values()[(r.nextInt(5))];			
			//Feld feld = new Feld(rohstoff,(r.nextInt(11)+1));			
			//felder.addElement(feld);
		}   
		 
		//System.out.println("hello world");
		/**
		 * link fields
		 * get first field link it with 6 nodes
		 * get 2nd field link it with 2 or nodes and create 4 new nodes
		 * get 3rd field link it with 2 or 3 existing nodes (therefore fields) create 4 or 3 new nodes
		 * get 4th field link it with 2 or 3 existing nodes (therefore fields) and create 4 or 3 new nodes
		 */
	/*	Feld ersteFeld = felder.firstElement();	
		Knoten.feldRegistrieren(ersteFeld);
    	ersteFeld.setIstPlaziert(true);
		Feld zweiteFeld = felder.get(1);
		Knoten.feldRegistrieren(zweiteFeld);
		zweiteFeld.setIstPlaziert(true);
		
		Knoten.feldRegistrieren(felder.get(2));
		felder.get(2).setIstPlaziert(true);
		int x = 0;
		x++;*/
		ersteReiheLegen(2);
		zweiteReiheLegen(1);
		Knoten.printKnotenMap();
	 
    	
	}
	
	public void zweiteReiheLegen(int anzahl) {
		int offset = felder.size();
		Random r = new Random();		
		Rohstoff rohstoff = Rohstoff.values()[(r.nextInt(5))];			
		this.ersteFeldZweiteReihe = new Feld(rohstoff,(r.nextInt(11)+1));			
		felder.addElement(ersteFeldZweiteReihe);
		
		//get information about first two fields from the first row 
		Kante suedOstKante = this.ersteFeldErsteReihe.getsuedOstKante();
		Kante suedwestKante = this.ersteFeldErsteReihe.getOstKante().getNachbarKante().
				getErsteEcke().getFeld().getsuedWestKante();
		
		Knoten suedOstKnoten1 = suedOstKante.getErsteEcke().getKnoten();
		Knoten suedOstKnoten2 = suedOstKante.getZweiteEcke().getKnoten();
		Knoten suedWestKnoten1 = suedwestKante.getErsteEcke().getKnoten();
		Knoten suedWestKnoten2 = suedwestKante.getZweiteEcke().getKnoten();
		
		suedOstKnoten1.setFreieEcke(ersteFeldZweiteReihe.getnordWestKante().getErsteEcke());
		suedOstKnoten2.setFreieEcke(ersteFeldZweiteReihe.getnordWestKante().getZweiteEcke());
		
		suedWestKnoten1.setFreieEcke(ersteFeldZweiteReihe.getnordOstKante().getErsteEcke());
		suedWestKnoten2.setFreieEcke(ersteFeldZweiteReihe.getnordOstKante().getZweiteEcke());
		
		//set neighbour edges
		ersteFeldZweiteReihe.getnordWestKante().setNachbarKante(suedOstKante);
		ersteFeldZweiteReihe.getnordOstKante().setNachbarKante(suedwestKante);
		//at the end move field forward
		
		while(ersteFeldZweiteReihe.getUnbesetzteEcke() != null) {
			new Knoten(ersteFeldZweiteReihe.getUnbesetzteEcke());
		}
		
		Feld vorgaenger = ersteFeldZweiteReihe;
		
		
		for(int i = 0; i < anzahl - 1 ; i++){
			//init new field and add it to fieldvector
			rohstoff = Rohstoff.values()[(r.nextInt(5))];			
			Feld aktuellesFeld = new Feld(rohstoff,(r.nextInt(11)+1));			
			felder.addElement(aktuellesFeld);
			
			vorgaenger.getOstKante().setNachbarKante(aktuellesFeld.getWestKante());
			//get information about successor
			suedOstKante = vorgaenger.getsuedOstKante();
			suedwestKante = vorgaenger.getOstKante().getNachbarKante().
					getErsteEcke().getFeld().getsuedWestKante();
			
			suedOstKnoten1 = suedOstKante.getErsteEcke().getKnoten();
			suedOstKnoten2 = suedOstKante.getZweiteEcke().getKnoten();
			suedWestKnoten1 = suedwestKante.getErsteEcke().getKnoten();
			suedWestKnoten2 = suedwestKante.getZweiteEcke().getKnoten();
			
			suedOstKnoten1.setFreieEcke(ersteFeldZweiteReihe.getnordWestKante().getErsteEcke());
			suedOstKnoten2.setFreieEcke(ersteFeldZweiteReihe.getnordWestKante().getZweiteEcke());
			
			suedWestKnoten1.setFreieEcke(ersteFeldZweiteReihe.getnordOstKante().getErsteEcke());
			suedWestKnoten2.setFreieEcke(ersteFeldZweiteReihe.getnordOstKante().getZweiteEcke());
			
			//set neighbour edges
			ersteFeldZweiteReihe.getnordWestKante().setNachbarKante(suedOstKante);
			ersteFeldZweiteReihe.getnordOstKante().setNachbarKante(suedwestKante);
			//at the end move field forward
			vorgaenger = aktuellesFeld;
		} 	
	}
	
	public void ersteReiheLegen(int anzahl) {

		Random r = new Random();
		Rohstoff rohstoff = Rohstoff.values()[(r.nextInt(5))];			
		this.ersteFeldErsteReihe = new Feld(rohstoff,(r.nextInt(11)+1));			
		felder.addElement(this.ersteFeldErsteReihe);
		 
		
		for(int i = 1; i < anzahl; i++){	
			rohstoff = Rohstoff.values()[(r.nextInt(5))];			
			Feld feld = new Feld(rohstoff,(r.nextInt(11)+1));			
			felder.addElement(feld);
		} 
		
		Feld ersteFeld;
		Feld zweiteFeld;
				
		for(int i = 0; i < anzahl -1; i++) {
			ersteFeld = felder.get(i);
			zweiteFeld = felder.get(i+1);

			Kante kOst = ersteFeld.getOstKante();
			Kante kWest = zweiteFeld.getWestKante();
			
			kOst.setNachbarKante(kWest);
			kWest.setNachbarKante(kOst);
			
			Ecke e1 = kOst.getErsteEcke();
			Ecke e2 = kWest.getErsteEcke();		
			Ecke e11 = kOst.getNachbarEcke(e1);
			Ecke e22 = kWest.getNachbarEcke(e2);
			
			new Knoten(e1,e2);
			new Knoten(e11,e22);

			ersteFeld.setIstPlaziert(true);
			zweiteFeld.setIstPlaziert(true);
		}	
		//Set all unassigned corners with nodes
		for (Feld feld : felder) {
			while(feld.getUnbesetzteEcke() != null) {
				new Knoten(feld.getUnbesetzteEcke());
			}
			
		}
	}
}
