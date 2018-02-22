package spielbrettKomponenten;

import java.util.Iterator;
import java.util.Vector;
import interfaces.Konstanten;

public class Knoten
implements interfaces.Knoten{
	
	private static Vector<Knoten> knotenMap = new Vector<Knoten>();
	private static int idZaehler = 0;

	private final Ecke ecke1;
	private Ecke ecke2;
	private Ecke ecke3;
	private int id;
	
	
	private Knoten(Feld feld) {
		this.ecke1 = feld.getUnbesetzteEcke();
		this.ecke1.setKnoten(this);

		this.id=idZaehler;
		idZaehler++;
	}
	
	public int getId() {
		return this.id;
	}
	
	public static void feldRegistrieren(Feld feld) {
		if(knotenMap.isEmpty()) {
			for (int i = 0; i < Konstanten.ECKEN_PRO_FELD; i++) {
				Knoten k = new Knoten(feld);
				knotenMap.addElement(k);	
			}
		}else{
			Iterator<Knoten> knotenMapIteratror = knotenMap.iterator(); 
			Knoten verbindungsKnoten1 = knotenMap.firstElement();
			
			// find first node with most fields assigned
			// either one or two fields connects after first field is placed
			while(knotenMapIteratror.hasNext()) {
				Knoten vglKnoten = knotenMapIteratror.next();
 
				if (vglKnoten.anzahlBesetzterEckpunkte() < 3 &&
						vglKnoten.anzahlBesetzterEckpunkte() > verbindungsKnoten1.anzahlBesetzterEckpunkte()
						){
					verbindungsKnoten1 = vglKnoten;
				}			
			}
			//System.out.println("verb knoten: " + verbindungsKnoten1.anzahlBesetzterEckpunkte());
			//get nachbarknoten zu diesem Knoten
			Ecke verbindungsEckeNachbar1 = null;
			Ecke verbindungsEckeNachbar2 = null;
			
			if (verbindungsKnoten1.anzahlBesetzterEckpunkte() == 1) {
				verbindungsEckeNachbar1 = verbindungsKnoten1.getBesetzteEcke();
				Ecke[] nachbarecken = verbindungsEckeNachbar1.getNachbarEcken().
						toArray(new Ecke[verbindungsEckeNachbar1.getNachbarEcken().size()]);

				if (nachbarecken[0].getKnoten().anzahlBesetzterEckpunkte() < 3) {
					verbindungsEckeNachbar2 = nachbarecken[0] ;
				} else if (nachbarecken[1].getKnoten().anzahlBesetzterEckpunkte() < 3){
					verbindungsEckeNachbar2 = nachbarecken[1];
				} else {
					System.out.println("something s wrong");
					//System.exit(0);
				}
				Knoten tmp = verbindungsEckeNachbar2.getKnoten();
				Ecke e = feld.getUnbesetzteEcke();
				verbindungsKnoten1.setFreieEcke(e);
				e.setKnoten(verbindungsKnoten1);
				
				e = feld.getUnbesetzteEcke();
				tmp.setFreieEcke(feld.getUnbesetzteEcke());
				e.setKnoten(verbindungsKnoten1);
				
			} else if (verbindungsKnoten1.anzahlBesetzterEckpunkte() == 2){
				System.out.println("do something");
			}
			else {
				System.out.println("verbindungsknoten hat mehr als eine ecke");
			}


			//register two corners from field with two identified nodes
			// add feld to register to this two nodes and create 4 new nodes
			//verbindungsEcke1.getKnoten().setFreieEcke(feld.getUnbesetzteEcke());
			//verbindungsEcke2.getKnoten().setFreieEcke(feld.getUnbesetzteEcke());
			//connect rest of free corners to new nodes
			for (int i = 0; i < Konstanten.ECKEN_PRO_FELD - 2; i++) {
				System.out.println(feld.getId() + " feld id");
				Knoten k = new Knoten(feld);
				knotenMap.addElement(k);	
			}
			
		}
	//	System.out.println(knotenMap.size() + " size of knotenmap");
		
	}

	@Override
	public int anzahlBesetzterEckpunkte() {
		int anz = 0;
		if(this.ecke1 != null) {
			anz++;
		}
		if(this.ecke2 != null) {
			anz++;
		}
		if(this.ecke3 != null) {
			anz++;
		}
		return anz;
	}

	public Ecke getBesetzteEcke() {
		if (this.ecke1 != null ) {				
			return this.ecke1;
		} else if (this.ecke2 != null ){			
			return this.ecke2;
		}else {
			System.out.println("Error: This" + this.id + "has no free corners");
			return null;
		}
	}
	@Override
	public Ecke getFreieEcke() {
		System.out.println(this.ecke1 != null);
		System.out.println((this.ecke2 == null || this.ecke3 == null));
		/**Diese methode macht wenig bis kein sinn */
		
		if (this.ecke1 != null && (this.ecke2 == null || this.ecke3 == null)) {
			if (this.ecke2 == null) {
				return this.ecke2;
			} else {
				return this.ecke3;
			}
		}else {
			System.out.println("Error: This" + this.id + "has no free corners");
			return null;
		}
	}
	
	public void setFreieEcke(Ecke ecke) {

		if(this.ecke2 == null) {
			this.ecke2 = ecke;
		}else if(this.ecke3 == null) {
			this.ecke3 = ecke;
		}
	}
	
	public static void printKnotenMap() {
		for(Knoten k : Knoten.knotenMap) {
			if(k.ecke1 != null) System.out.print(k.ecke1.getId() + "e1 "
					+ k.ecke1.getFeld().getId() + "f | ");
			if(k.ecke2 != null) System.out.print(k.ecke2.getId() + "e2 "
					+ k.ecke2.getFeld().getId() + "f | ");
			if(k.ecke3 != null)System.out.print(k.ecke3.getId() + "e3 "
					+ k.ecke3.getFeld().getId() + "f | ");
			System.out.println(k.getId() + "kID");
		}
		
	}
		
	
}
