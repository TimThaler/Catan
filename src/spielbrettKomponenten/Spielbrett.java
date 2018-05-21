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

		ersteReiheLegen(4);
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
		
		this.ersteFeldZweiteReihe.getnordWestKante().setupSymmetricNeighbourRelation(nwNeighborField.getsuedOstKante());
		this.ersteFeldZweiteReihe.getnordOstKante().setupSymmetricNeighbourRelation(neNeighborField.getsuedWestKante());

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
		
		while(this.ersteFeldZweiteReihe.anzUnbesetzterEcken() > 0) {
			Ecke e = ersteFeldZweiteReihe.getUnbesetzteEcke();
			Knoten k = new Knoten(e);
			e.setKnoten(k); 
		}
		
		
		Feld vorgaengerLinks = this.ersteFeldZweiteReihe;

		for(int i = 0; i < anzahl -1; i++){
			System.out.println(i + " the for loop of the 2nd row ");
			rohstoff = Rohstoff.values()[(r.nextInt(5))];
			Point newPoint = new Point((int) vorgaengerLinks.getCenter().getX() + 87, (int) vorgaengerLinks.getCenter().getY());
			Feld aktuellesFeld = new Feld(rohstoff,(r.nextInt(11)+1),newPoint);			
			felder.addElement(aktuellesFeld);
			
			Feld vorgaengerObenLinks = vorgaengerLinks.getnordOstKante().getNachbarKante().getFeld();			
			Feld vorgaengerObenRechts = null;
			
			if (vorgaengerObenLinks.getOstKante().getNachbarKante() != null) {				                       
				vorgaengerObenRechts = vorgaengerObenLinks.getOstKante().getNachbarKante().getFeld();
			}
			
			/**
			 * Set first two edges as neighbours
			 * and the 3rd one if it exists
			 */
			aktuellesFeld.getWestKante().setupSymmetricNeighbourRelation(vorgaengerLinks.getOstKante());			
			aktuellesFeld.getnordWestKante().setupSymmetricNeighbourRelation(vorgaengerObenLinks.getsuedOstKante());		
			if(vorgaengerObenRechts != null) {				
				aktuellesFeld.getnordOstKante().setupSymmetricNeighbourRelation(vorgaengerObenRechts.getsuedWestKante());
			}	
			Knoten commonNode = null;
			Ecke cornerToConnect = null;
			/**
			 * Connect current field with left neighbour for southeast corner
			 * - find from the left neighbour the common node 
			 * - find corner from current field
			 * - connect them
			 */
			commonNode = vorgaengerLinks.
					getGemeinsameEcke(
							vorgaengerLinks.getOstKante(), 
							vorgaengerLinks.getsuedOstKante()).
					getKnoten();
			cornerToConnect = aktuellesFeld.getGemeinsameEcke(
					aktuellesFeld.getsuedWestKante(),
					aktuellesFeld.getWestKante()
					);	
		
			cornerToConnect.setKnoten(commonNode);
			commonNode.setFreieEcke(cornerToConnect);
			
			/**
			 * Connect current field with left  and top left neighbour for north east corner
			 * - find the common node for the tow fields 
			 * - find common node to connect
			 * - find corner to connect
			 */			
			commonNode = vorgaengerObenLinks.
					getGemeinsameEcke(
							vorgaengerObenLinks.getsuedOstKante(), 
							vorgaengerObenLinks.getsuedWestKante()).
					getKnoten();		
			cornerToConnect = aktuellesFeld.getWestKante().getNachbarEcke(cornerToConnect); 		
			cornerToConnect.setKnoten(commonNode);
			commonNode.setFreieEcke(cornerToConnect);
			
			/**
			 * Connect current field with top left and top right neighbour  
			 * - make edges neighbours
			 * - find common node to connect
			 * - find north corner of current field to connect
			 */
			commonNode = vorgaengerObenLinks.
					getGemeinsameEcke(
							vorgaengerObenLinks.getsuedOstKante(), 
							vorgaengerObenLinks.getOstKante()).
					getKnoten();
			cornerToConnect = aktuellesFeld.getnordWestKante().getNachbarEcke(cornerToConnect);	
			cornerToConnect.setKnoten(commonNode);
			commonNode.setFreieEcke(cornerToConnect);
			 
			/**
			 * if the neighbour field on the upper right edge is not present skip this here
			 */
			if (vorgaengerObenRechts != null) {				
				commonNode = vorgaengerObenRechts.
						getGemeinsameEcke(
								vorgaengerObenRechts.getsuedOstKante(), 
								vorgaengerObenRechts.getsuedWestKante()).
						getKnoten();
				cornerToConnect = aktuellesFeld.getnordOstKante().getNachbarEcke(cornerToConnect);
				cornerToConnect.setKnoten(commonNode);
				commonNode.setFreieEcke(cornerToConnect);
			}
			while (aktuellesFeld.anzUnbesetzterEcken() > 0) {
				Ecke e = aktuellesFeld.getUnbesetzteEcke();
				Knoten k = new Knoten(aktuellesFeld.getUnbesetzteEcke());
				e.setKnoten(k); 
			}
			vorgaengerLinks = aktuellesFeld;		
		}
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
			
			ersteFeld.getOstKante().setupSymmetricNeighbourRelation(zweiteFeld.getWestKante());
			
			Ecke e1 = ersteFeld.getGemeinsameEcke(
					ersteFeld.getnordOstKante(),
					ersteFeld.getOstKante()
					);
			Ecke e2 = zweiteFeld.getGemeinsameEcke(
					zweiteFeld.getnordWestKante(),
					zweiteFeld.getWestKante()
					);
			Ecke e11 = ersteFeld.getOstKante().getNachbarEcke(e1);
			Ecke e22 = zweiteFeld.getWestKante().getNachbarEcke(e2);
			
			Knoten k1 = new Knoten(e1,e2);
			Knoten k2 = new Knoten(e11,e22);
			e1.setKnoten(k1);
			e2.setKnoten(k1);
			e11.setKnoten(k2);
			e22.setKnoten(k2);
		}	
		//Set all unassigned corners with nodes
		for (Feld feld : felder) {
			while(feld.anzUnbesetzterEcken() > 0) {
				Ecke e = feld.getUnbesetzteEcke();
				Knoten k = new Knoten(feld.getUnbesetzteEcke());
				e.setKnoten(k);
			}
		}
	}

	public Vector<Feld> getFelder() {
		return felder;
	}
}
