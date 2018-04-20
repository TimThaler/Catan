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
