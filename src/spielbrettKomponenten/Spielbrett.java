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
		zweiteReiheLegen(3);
		
		Knoten.printKnotenMap();
		System.out.println(felder.size());
	}
	
	public void zweiteReiheLegen(int anzahl) {
		Rohstoff rohstoff = Rohstoff.values()[(r.nextInt(5))];	
		
		Point relPoint = ersteFeldErsteReihe.getCenter();
		//relPoint.y = 325;
		relPoint.y = (int) (relPoint.y * 1.3);
		relPoint.x = 143;
		this.ersteFeldZweiteReihe = new Feld(rohstoff,(r.nextInt(11)+1),relPoint);			
		felder.addElement(this.ersteFeldZweiteReihe);
		
		/**
		 * get information about first two fields from the first row 
		 * with their two edges (southEast and southWest) we are able to place the first field
		 */
		 
		Feld nwNeighborField = this.ersteFeldErsteReihe;
		Feld neNeighborField = nwNeighborField.getOstKante().getNachbarKante().getFeld();

		

		Ecke nwNeighborSECorner = nwNeighborField.getGemeinsameEcke(
				nwNeighborField.getsuedOstKante(),
				nwNeighborField.getOstKante());
		
		Ecke neNeighborSWCorner = neNeighborField.getGemeinsameEcke(
				neNeighborField.getsuedWestKante(),
				neNeighborField.getWestKante());
		
		try {
			if (nwNeighborSECorner.getKnoten().getId() != neNeighborSWCorner.getKnoten().getId()) {
				throw new Exception("Error nodes do not match");
			}	
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(-1);
		}
		/*find common node and connect it to north corner of current field*/
		
		Knoten gemeinsamerKonten = nwNeighborSECorner.getKnoten();
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
		
		noKnoten.setFreieEcke(currentFieldNOCorner);		
		nwKnoten.setFreieEcke(currentFieldNWCorner);
		currentFieldNOCorner.setKnoten(noKnoten);
		currentFieldNWCorner.setKnoten(nwKnoten);
		
		this.ersteFeldZweiteReihe.getnordWestKante().setNachbarKante(nwNeighborField.getsuedOstKante());
		this.ersteFeldZweiteReihe.getnordOstKante().setNachbarKante(neNeighborField.getsuedWestKante());
		
		while(this.ersteFeldZweiteReihe.getUnbesetzteEcke() != null) {
			new Knoten(this.ersteFeldZweiteReihe.getUnbesetzteEcke());
		}
		
		
		Feld vorgaengerLinks = this.ersteFeldZweiteReihe;

		for(int i = 0; i < anzahl -1; i++){
			
			rohstoff = Rohstoff.values()[(r.nextInt(5))];
			Point newPoint = new Point((int) vorgaengerLinks.getCenter().getX() + 87, (int) vorgaengerLinks.getCenter().getY());
			Feld aktuellesFeld = new Feld(rohstoff,(r.nextInt(11)+1),newPoint);			
			felder.addElement(aktuellesFeld);
			
			/*wenn der nordost nachbar nicht da ist leg die kante nicht an und auch die knoten nicht
			besetzen danach dann mit neuen knoten auffüllen*/
			
			Feld vorgaengerObenLinks = vorgaengerLinks.getnordOstKante().getNachbarKante().getFeld();
			Kante vorgaengerOstKante = vorgaengerLinks.getOstKante();
			Kante vorgaengerSuedOstKante = vorgaengerObenLinks.getsuedOstKante();
			
			aktuellesFeld.getWestKante().setNachbarKante(vorgaengerOstKante);
			aktuellesFeld.getnordWestKante().setNachbarKante(vorgaengerSuedOstKante);		
			vorgaengerOstKante.setNachbarKante(aktuellesFeld.getWestKante());
			vorgaengerSuedOstKante.setNachbarKante(aktuellesFeld.getnordWestKante());
			
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
			//ERROR k1 has no free corner?!?!
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
						getGemeinsameEcke(vorgaengerObenRechts.getsuedOstKante(), 
								vorgaengerObenRechts.getsuedWestKante()).
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
