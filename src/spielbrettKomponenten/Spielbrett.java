package spielbrettKomponenten;

import java.util.Iterator;
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
		for(int i = 0; i < Konstanten.FELDER_AUF_SPIELBRETT; i++){	
			Rohstoff rohstoff = Rohstoff.values()[(r.nextInt(5))];			
			//Feld feld = new Feld(rohstoff,(r.nextInt(11)+1));			
			//felder.addElement(feld);
		}   
		ersteReiheLegen(2);
		zweiteReiheLegen(1);
		
		Knoten.printKnotenMap();
	}
	
	public void zweiteReiheLegen(int anzahl) {
		Rohstoff rohstoff = Rohstoff.values()[(r.nextInt(5))];			
		this.ersteFeldZweiteReihe = new Feld(rohstoff,(r.nextInt(11)+1));			
		felder.addElement(this.ersteFeldZweiteReihe);
		
		/**
		 * get information about first two fields from the first row 
		 * with their two edges we are able to place the first field
		 */
		 
		Kante soKanteReihe1  = this.ersteFeldErsteReihe.getsuedOstKante();
		Kante swKanteReihe1  = this.ersteFeldErsteReihe.getOstKante().getNachbarKante().
				getErsteEcke().getFeld().getsuedWestKante();
		
		Knoten gemeinsamerKonten = soKanteReihe1.getGemeinsamenKnoten(swKanteReihe1);
		System.out.println("gemeinsamer Knoten: " + gemeinsamerKonten.getId());
		Knoten soKnoten = null;
		Knoten swKnoten = null;
		if(soKanteReihe1.getErsteEcke().getKnoten().getId() == gemeinsamerKonten.getId()) {
			soKnoten = soKanteReihe1.getZweiteEcke().getKnoten();
		} else {
			soKnoten = soKanteReihe1.getErsteEcke().getKnoten();
		}
		
		if(swKanteReihe1.getErsteEcke().getKnoten().getId() == gemeinsamerKonten.getId()) {
			swKnoten = swKanteReihe1.getZweiteEcke().getKnoten();
		} else {
			swKnoten = swKanteReihe1.getErsteEcke().getKnoten();
		}
		
		
		Ecke gemeinsameEcke = this.ersteFeldZweiteReihe.getGemeinsameEcke(
				ersteFeldZweiteReihe.getnordOstKante(),
				ersteFeldZweiteReihe.getnordWestKante());
		
		gemeinsamerKonten.setFreieEcke(gemeinsameEcke);
				
		soKnoten.setFreieEcke(this.ersteFeldZweiteReihe.getnordOstKante().getNachbarEcke(gemeinsameEcke));		
		swKnoten.setFreieEcke(this.ersteFeldZweiteReihe.getnordWestKante().getNachbarEcke(gemeinsameEcke));

		this.ersteFeldZweiteReihe.getnordWestKante().setNachbarKante(soKanteReihe1);
		this.ersteFeldZweiteReihe.getnordOstKante().setNachbarKante(swKanteReihe1);
		
		while(this.ersteFeldZweiteReihe.getUnbesetzteEcke() != null) {
			new Knoten(this.ersteFeldZweiteReihe.getUnbesetzteEcke());
		}
		
		Feld vorgaenger = this.ersteFeldZweiteReihe;
		
		for(int i = 0; i < anzahl - 1 ; i++){
			rohstoff = Rohstoff.values()[(r.nextInt(5))];			
			Feld aktuellesFeld = new Feld(rohstoff,(r.nextInt(11)+1));			
			felder.addElement(aktuellesFeld);
			
			vorgaenger.getOstKante().setNachbarKante(aktuellesFeld.getWestKante());
			
			/*
			 * get vorgaenger ost kante
			 * get suedost kante verbunden mit ost kante
			 * get suedwest kante verbunden mit suedostkante
			 */
			
			Feld tmpf1 = vorgaenger.getnordOstKante().getNachbarKante().getFeld();
			Feld tmpf2 = tmpf1.getOstKante().getNachbarKante().getFeld();
			
			Kante vorgaengerOstKante = vorgaenger.getOstKante();
			Kante vorgaengerSuedOstKante = tmpf1.getsuedOstKante();
			Kante vorgaengerSuedWestKante = tmpf2.getsuedWestKante();
			
			aktuellesFeld.getWestKante().setNachbarKante(vorgaengerOstKante);
			aktuellesFeld.getnordWestKante().setNachbarKante(vorgaengerSuedOstKante);
			aktuellesFeld.getnordOstKante().setNachbarKante(vorgaengerSuedWestKante);

			/**
			 * Corners to connect
			 */
			Ecke e1 = aktuellesFeld.getGemeinsameEcke(
					aktuellesFeld.getnordOstKante(),
					aktuellesFeld.getnordWestKante()
					);	
			Ecke e2 = aktuellesFeld.getnordOstKante().getNachbarEcke(e1);
			Ecke e3 = aktuellesFeld.getGemeinsameEcke(
					aktuellesFeld.getnordWestKante(),
					aktuellesFeld.getWestKante()
					);
			Ecke e4 = aktuellesFeld.getWestKante().getNachbarEcke(e3);
			
			Knoten k1 = vorgaengerOstKante.getErsteEcke().getKnoten();
			Knoten k2 = vorgaengerOstKante.getZweiteEcke().getKnoten();
			
			
			//connect nodes 
			
			/*
			suedOstKante = vorgaenger.getsuedOstKante();
			suedwestKante = vorgaenger.getOstKante().getNachbarKante().
					getErsteEcke().getFeld().getsuedWestKante();
			
			suedOstKnoten1 = suedOstKante.getErsteEcke().getKnoten();
			suedOstKnoten2 = suedOstKante.getZweiteEcke().getKnoten();
			suedWestKnoten1 = suedwestKante.getErsteEcke().getKnoten();
			suedWestKnoten2 = suedwestKante.getZweiteEcke().getKnoten();
			
			suedOstKnoten1.setFreieEcke(this.ersteFeldZweiteReihe.getnordWestKante().getErsteEcke());
			suedOstKnoten2.setFreieEcke(this.ersteFeldZweiteReihe.getnordWestKante().getZweiteEcke());
			
			suedWestKnoten1.setFreieEcke(this.ersteFeldZweiteReihe.getnordOstKante().getErsteEcke());
			suedWestKnoten2.setFreieEcke(this.ersteFeldZweiteReihe.getnordOstKante().getZweiteEcke());
			
			//set neighbour edges
			this.ersteFeldZweiteReihe.getnordWestKante().setNachbarKante(suedOstKante);
			this.ersteFeldZweiteReihe.getnordOstKante().setNachbarKante(suedwestKante);
			//at the end move field forward
			 * */
			vorgaenger = aktuellesFeld;
		}
	}
	
	public void ersteReiheLegen(int anzahl) {
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
}
