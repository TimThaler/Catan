package spielbrettKomponenten;

import java.awt.Point;
import java.util.Random;
import java.util.Vector;

import enums.*;
import interfaces.*;

public class Spielbrett
implements interfaces.Spielbrett{
	private static Spielbrett instance = null;
	private Vector<Feld> felder = null;

	private Random r = new Random();
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
	  
		/**
		 * Create fields with corners and edges
		 */
		  
		ersteReiheLegen(6);
		//zweiteReiheLegen(2);
		
		Knoten.printKnotenMap();
		System.out.println(felder.size());
	}
	
	public void zweiteReiheLegen(int anzahl) {
		Rohstoff rohstoff = Rohstoff.values()[(r.nextInt(5))];	
		
		Point relPoint = ersteFeldErsteReihe.getCenter();
		
		this.ersteFeldZweiteReihe = null; //new Feld(rohstoff,(r.nextInt(11)+1));			
		felder.addElement(this.ersteFeldZweiteReihe);
		
		/**
		 * get information about first two fields from the first row 
		 * with their two edges we are able to place the first field
		 */
		 
		Feld nwNeighborField = this.ersteFeldErsteReihe;
		Feld neNeighborField = nwNeighborField.getOstKante().getNachbarKante().getFeld();

		/*find common node and connect it to north corner of current field*/
		Knoten gemeinsamerKonten = null;

		Ecke nwNeighborSECorner = nwNeighborField.getGemeinsameEcke(
				nwNeighborField.getsuedOstKante(),
				nwNeighborField.getOstKante());
		
		Ecke neNeighborSWCorner = neNeighborField.getGemeinsameEcke(
				neNeighborField.getsuedWestKante(),
				neNeighborField.getWestKante());
		
		if (nwNeighborSECorner.getKnoten().getId() != neNeighborSWCorner.getKnoten().getId()) {
			System.out.println("Error nodes do not match");
		} else {
			gemeinsamerKonten = nwNeighborSECorner.getKnoten();
		}
		
		Ecke currentFieldNCorner = this.ersteFeldZweiteReihe.getGemeinsameEcke(
				this.ersteFeldZweiteReihe.getnordOstKante(),
				this.ersteFeldZweiteReihe.getnordWestKante());
		
		gemeinsamerKonten.setFreieEcke(currentFieldNCorner);
		currentFieldNCorner.setKnoten(gemeinsamerKonten);
		/*DONE*/
		
		/*connect left and right corners of current field to nodes left and right of the common node*/
		Ecke currentFieldNWCorner = this.ersteFeldZweiteReihe.getnordWestKante().getNachbarEcke(currentFieldNCorner);
		Ecke currentFieldNOCorner = this.ersteFeldZweiteReihe.getnordOstKante().getNachbarEcke(currentFieldNCorner);
		
		
		Knoten nwKnoten = nwNeighborField.getsuedOstKante().getNachbarEcke(nwNeighborSECorner).getKnoten();
		Knoten noKnoten = neNeighborField.getsuedWestKante().getNachbarEcke(neNeighborSWCorner).getKnoten();
		
		
		/**
		 * corners gets hidden in set free node function assigned to node -.-
		 */
		//gemeinsameEcke.setKnoten(gemeinsamerKonten);		
		noKnoten.setFreieEcke(currentFieldNOCorner);		
		nwKnoten.setFreieEcke(currentFieldNWCorner);

		this.ersteFeldZweiteReihe.getnordWestKante().setNachbarKante(nwNeighborField.getsuedOstKante());
		this.ersteFeldZweiteReihe.getnordOstKante().setNachbarKante(neNeighborField.getsuedWestKante());
		
		while(this.ersteFeldZweiteReihe.getUnbesetzteEcke() != null) {
			new Knoten(this.ersteFeldZweiteReihe.getUnbesetzteEcke());
		}
		
		
		Feld vorgaengerLinks = this.ersteFeldZweiteReihe;

		for(int i = 0; i < anzahl -1; i++){
			
			rohstoff = Rohstoff.values()[(r.nextInt(5))];			
			Feld aktuellesFeld = null;//new Feld(rohstoff,(r.nextInt(11)+1));			
			felder.addElement(aktuellesFeld);
			
			/*wenn der nordost nachbar nicht da ist leg die kante nicht an und auch die knoten nicht
			besetzen danach dann mit neuen knoten auffüllen*/
			
			Feld vorgaengerObenLinks = vorgaengerLinks.getnordOstKante().getNachbarKante().getFeld();
			Kante vorgaengerOstKante = vorgaengerLinks.getOstKante();
			Kante vorgaengerSuedOstKante = vorgaengerObenLinks.getsuedOstKante();
			aktuellesFeld.getWestKante().setNachbarKante(vorgaengerOstKante);
			aktuellesFeld.getnordWestKante().setNachbarKante(vorgaengerSuedOstKante);
			
			vorgaengerOstKante.setNachbarKante(aktuellesFeld.getWestKante());
			
			
			/**
			 * Corners from current field to connect
			 */
			Ecke e1 = aktuellesFeld.getGemeinsameEcke(
					aktuellesFeld.getsuedWestKante(),
					aktuellesFeld.getWestKante()
					);	
			Ecke e2 = aktuellesFeld.getWestKante().getNachbarEcke(e1);
			Ecke e3 = aktuellesFeld.getnordWestKante().getNachbarEcke(e2);
					
			Knoten k1 = vorgaengerLinks.
					getGemeinsameEcke(vorgaengerOstKante, 
							vorgaengerLinks.getsuedOstKante()).
					getKnoten();
			
			Knoten k2 = vorgaengerObenLinks.
					getGemeinsameEcke(vorgaengerObenLinks.getsuedOstKante(), 
							vorgaengerObenLinks.getsuedWestKante()).
					getKnoten();

			Knoten k3 = vorgaengerObenLinks.
					getGemeinsameEcke(vorgaengerObenLinks.getsuedOstKante(), 
							vorgaengerObenLinks.getOstKante()).
					getKnoten();
				
			e1.setKnoten(k1);
			e2.setKnoten(k2);
			e3.setKnoten(k3);
			
			k1.setFreieEcke(e1);
			k2.setFreieEcke(e2);
			k3.setFreieEcke(e3);
			
			/**
			 * if the neighbour field on the upper right edge is not present skip this here
			 */
			if (vorgaengerObenLinks.getOstKante().getNachbarKante() != null) {
				Feld vorgaengerObenRechts = vorgaengerObenLinks.getOstKante().getNachbarKante().getFeld();
				Kante vorgaengerSuedWestKante = vorgaengerObenRechts.getsuedWestKante();
				aktuellesFeld.getnordOstKante().setNachbarKante(vorgaengerSuedWestKante);
				
				Ecke e4 = aktuellesFeld.getnordOstKante().getNachbarEcke(e3);
				Knoten k4 = vorgaengerObenRechts.
						getGemeinsameEcke(vorgaengerObenLinks.getsuedOstKante(), 
								vorgaengerObenLinks.getOstKante()).
						getKnoten();
				
				e4.setKnoten(k4);
				k4.setFreieEcke(e4);
			}
			
			vorgaengerLinks = aktuellesFeld;		
		}
		for (Feld feld : felder) {
			while(feld.getUnbesetzteEcke() != null) {
				new Knoten(feld.getUnbesetzteEcke());
			}
		}	
		//}
	}
	
	public void ersteReiheLegen(int anzahl) {
		Rohstoff rohstoff = Rohstoff.values()[(r.nextInt(5))];
		Feld ersteFeld = new Feld(rohstoff,(r.nextInt(11)+1),new Point(100, 250));		
		felder.addElement(ersteFeld);
		this.ersteFeldErsteReihe = ersteFeld;
		
		for(int i = 0; i < anzahl -1; i++) {
			ersteFeld = felder.get(i);
			rohstoff = Rohstoff.values()[(r.nextInt(5))];
			
			Feld zweiteFeld = new Feld(rohstoff,(r.nextInt(11)+1),new Point((int) ersteFeld.getCenter().getX() + 87, 250));
			felder.addElement(zweiteFeld);
			
			Kante kOstFeldEins = ersteFeld.getOstKante();
			Kante kWestFeldZwei = zweiteFeld.getWestKante();
			
			kOstFeldEins.setNachbarKante(kWestFeldZwei);
			kWestFeldZwei.setNachbarKante(kOstFeldEins);
			
			Ecke e1 = kOstFeldEins.getErsteEcke();
			Ecke e2 = kWestFeldZwei.getErsteEcke();		
			Ecke e11 = kOstFeldEins.getNachbarEcke(e1);
			Ecke e22 = kWestFeldZwei.getNachbarEcke(e2);
			
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

	public Vector<Feld> getFelder() {
		return felder;
	}
}
