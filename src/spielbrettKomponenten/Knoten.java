package spielbrettKomponenten;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

import interfaces.Konstanten;

public class Knoten
implements interfaces.Knoten{
	//private static Map<Feld, Vector<Knoten> > gridMap =  new ConcurrentHashMap<Feld, Vector<Knoten>>();
	
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
			Iterator<Knoten> iterator = knotenMap.iterator(); 
			Knoten knoten = knotenMap.firstElement();
			/*System.out.println("erster Knoten: " + knoten.getId());
			System.out.println(" is linked to  : " +  knoten.anzahlVerbundenerFelder());*/
			while(iterator.hasNext()) {
				Knoten vglKnoten = (Knoten) iterator.next();
				/*System.out.println("vgl  Knoten: " + vglKnoten.getId());
				System.out.println(" is linked to  : " +  vglKnoten.anzahlVerbundenerFelder());*/
				// find first node with most fields assigned
				if (vglKnoten.anzahlVerbundenerFelder() != 3 &&
						vglKnoten.anzahlVerbundenerFelder() > knoten.anzahlVerbundenerFelder()){
					knoten = vglKnoten;
				}			
			}
			Ecke verbindungsEcke1 = knoten.getEcke();
			
			System.out.println(" verbindungsecke : " +  verbindungsEcke1.getId());
			Ecke verbindungsEcke2 = null;
			Ecke[] nachbarecken = verbindungsEcke1.getNachbarEcken().toArray(new Ecke[verbindungsEcke1.getNachbarEcken().size()]);
			
			System.out.println(nachbarecken[0].getId() + " nachbar 0 id");
			System.out.println(nachbarecken[0].getKnoten().getId());
			//System.out.println(nachbarecken[0].getKnoten().getFreieEcke().getId() + " nachbar 1 id");
			System.out.println(nachbarecken[0].getKnoten().anzahlVerbundenerFelder());

			verbindungsEcke2 = nachbarecken[0];//.getKnoten().getFreieEcke();
			if(verbindungsEcke2 == null){
				verbindungsEcke2 = nachbarecken[1];//.getKnoten().getFreieEcke();
			}
			// add feld to register to this two nodes and create 4 new nodes
			verbindungsEcke1.getKnoten().setFreieEcke(feld.getUnbesetzteEcke());
			verbindungsEcke2.getKnoten().setFreieEcke(feld.getUnbesetzteEcke());
			for (int i = 0; i < Konstanten.ECKEN_PRO_FELD - 2; i++) {
				System.out.println(feld.getId() + " feld id");
				Knoten k = new Knoten(feld);
				knotenMap.addElement(k);	
			}
			
		}
		
		// now we have a node with most occupied places. Either 1 or 2.
		// check if neighbour corners of the connected corner or corners are free
		// if that is true 
		/*if (k.anzahlVerbundenerFelder() == 1 ) {
			Ecke ecke1 = k.
			
		}else if(k.anzahlVerbundenerFelder() == 2 ){

		}
			
		*/
	
		//map.put(null,knoten);
		// search for key field in map
		// if search is empty
		// search for values which occur less then three times.
		// pick first value, check if neighbour is free
		// assign new field to value and neighbour and create 4 new nodes and add them
		
		
		/*for(Knoten k : knotenMap) {
			
			
			if(k.anzahlVerbundenerFelder() == 2){
				// collect connected fields and also therefore the connected corners
				
				Ecke e1 = k.getEcke1();
				Ecke e2 = k.getEcke2();
			}
			
		}*/
		
	}

	@Override
	public boolean verbundenMitFeld(Feld feld) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int anzahlVerbundenerFelder() {
		int anz = 0;
		if (this.ecke1 != null) {
			anz++;
		}else if (this.ecke2 != null) {
			anz++;
		}else if (this.ecke3 != null) {
			anz++;
		}
		return anz;
	}

	public Ecke getEcke() {
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
}
