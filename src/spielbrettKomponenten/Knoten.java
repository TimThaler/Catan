package spielbrettKomponenten;

import java.util.Iterator;
import java.util.Vector;

import org.omg.PortableServer.ThreadPolicyOperations;

import interfaces.Konstanten;

public class Knoten
implements interfaces.Knoten{
	
	private static Vector<Knoten> knotenMap = new Vector<Knoten>();
	private static int idZaehler = 0;

	private final Ecke ecke1;
	private Ecke ecke2;
	private Ecke ecke3;
	private int id;
	
	public Knoten(Ecke ecke1, Ecke ecke2) {
		this.ecke1 = ecke1;
		this.ecke2 = ecke2;
		// hidden in the constructor the corner sets the edge's node .... 
		this.ecke1.setKnoten(this);
		this.ecke2.setKnoten(this);
		knotenMap.addElement(this);	

		
		this.id=idZaehler;
		idZaehler++;
	}
	
	public Knoten(Ecke e) {
		this.ecke1 = e;
		// hidden in the constructor the corner sets the edge's node .... 
		this.ecke1.setKnoten(this);
		knotenMap.addElement(this);	
		
		this.id=idZaehler;
		idZaehler++;

	}
	
	//shitty constructor
	private Knoten(Feld feld) {
		this.ecke1 = feld.getUnbesetzteEcke();
		// hidden in the constructor the corner sets the edge's node .... 
		this.ecke1.setKnoten(this);

		knotenMap.addElement(this);	

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
			//get nachbarknoten zu diesem Knoten
			
			if (verbindungsKnoten1.anzahlBesetzterEckpunkte() == 1) {
				Ecke verbindungsEckeNachbar1 = null;
				Ecke verbindungsEckeNachbar2 = null;
				
				//verbindungsEckeNachbar1 = verbindungsKnoten1.getErstebesetzteEcke();
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
				Ecke verbindungsEckeNachbar1; //= verbindungsKnoten1.getErstebesetzteEcke();
				Ecke verbindungsEckeNachbar2;// = verbindungsKnoten1.getZweitebesetzteEcke();
				Ecke verbindungsEckeNachbar3 = null;
				
				//Feld feld1 = verbindungsEckeNachbar1.getFeld();
				//Feld feld2 = verbindungsEckeNachbar2.getFeld();
				
				
				//find a corner which node shares the other field feld2, if that is true we found a location of the nodes 
				// and we can use the other neighbours to connect the new field to the three existing nodes
				//Ecke[] nachbarn = verbindungsEckeNachbar1.getNachbarEcken().toArray(new Ecke[verbindungsEckeNachbar1.getNachbarEcken().size()]);

				
			}
			else {
				System.out.println("verbindungsknoten hat mehr als eine ecke");
			}

			//register two corners from field with two identified nodes
			// add feld to register to this two nodes and create 4 new nodes
			//verbindungsEcke1.getKnoten().setFreieEcke(feld.getUnbesetzteEcke());
			//verbindungsEcke2.getKnoten().setFreieEcke(feld.getUnbesetzteEcke());
			//connect rest of free corners to new nodes
			int limit = feld.getAnzahlUnbesetzterEcken();
			for (int i = 0; i < limit ; i++) {
				Knoten k = new Knoten(feld);
				knotenMap.addElement(k);	
			}
		}		
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

		 
	public void setFreieEcke(Ecke ecke) {
		//call this method hier seems to be an awkward place
		//but i forgot myself often
		System.out.println("Knoten " + this.getId());
		if(this.ecke2 == null) {
			this.ecke2 = ecke;
			this.ecke2.setKnoten(this);
			System.out.println("sdfe");
		}else if(this.ecke3 == null) {
			this.ecke3 = ecke;
			this.ecke3.setKnoten(this);
			System.out.println("sdf");
		}else {
			//throw Exception e
			System.out.println("ERROR setfreieecke to knoten");
		}
	}
	
	public static void printKnotenMap() {
		for(Knoten k : Knoten.knotenMap) {
			System.out.print(k.getId() + " kID |");
			if(k.ecke1 != null) {
				System.out.print("Feld id "+ k.ecke1.getFeld().getId() + " |" + k.ecke1.getId() + "e1 |");
			}
			if(k.ecke2 != null) {
				System.out.print("Feld id "+ k.ecke2.getFeld().getId() + " |" + k.ecke2.getId() + "e2 |");
			}
			if(k.ecke3 != null) {
				System.out.print("Feld id "+ k.ecke3.getFeld().getId() + " |" + k.ecke3.getId() + "e3 |");
			}
			System.out.println();
		}
	}

	public Ecke getEcke2() {
		return ecke2;
	}

	public Ecke getEcke3() {
		return ecke3;
	}

	public Ecke getEcke1() {
		return ecke1;
	}
}
