package spielbrettKomponenten;

import java.util.Iterator;
import java.util.Vector;

import org.omg.PortableServer.ThreadPolicyOperations;

import interfaces.Konstanten;

public class Knoten
implements interfaces.Knoten{
	
	private static Vector<Knoten> nodeMap = new Vector<Knoten>();
	private static int idCounter = 0;

	private final Ecke corner1;
	private Ecke corner2;
	private Ecke corner3;
	private int id;
	
	public Knoten(Ecke ecke1, Ecke ecke2) {
		this.corner1 = ecke1;
		this.corner2 = ecke2;			
		this.id=idCounter;
		idCounter++;
		nodeMap.addElement(this);
	}
	
	public Knoten(Ecke e) {
		this.corner1 = e;	
		this.id=idCounter;
		idCounter++;
		nodeMap.addElement(this);
	}
	
	public int getId() {
		return this.id;
	}
		 
	public void setFreieEcke(Ecke ecke) {
		//call this method hier seems to be an awkward place
		//but i forgot myself often
		try {
			if(this.corner2 != null && this.corner3 != null) {
				String eString = "\n Node " + this.id + " has no free corners";
				eString += "\n Trying to add corner: " + ecke.getId() + " from field: " + ecke.getFeld().getId();
				throw new Exception(eString);
			}
			if(this.corner2 == null) {
				if(this.corner1.getId() == ecke.getId()) {
					throw new Exception("Ecke: "+ ecke.getId() + "already connected to node");
				}
				this.corner2 = ecke;
			}else if(this.corner3 == null) {
				if(this.corner1.getId() == ecke.getId() || this.corner2.getId() == ecke.getId() ) {
					throw new Exception("Ecke: "+ ecke.getId() + " already connected to node " + this.id);
				}
				this.corner3 = ecke;
			}
		}catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(-1);
		}
	}
	
	public static void printKnotenMap() {
		for(Knoten k : Knoten.nodeMap) {
			System.out.print(k.getId() + " kID |");
			if(k.corner1 != null) {
				System.out.print("Feld id "+ k.corner1.getFeld().getId() + " |" + k.corner1.getId() + "e1 |");
			}
			if(k.corner2 != null) {
				System.out.print("Feld id "+ k.corner2.getFeld().getId() + " |" + k.corner2.getId() + "e2 |");
			}
			if(k.corner3 != null) {
				System.out.print("Feld id "+ k.corner3.getFeld().getId() + " |" + k.corner3.getId() + "e3 |");
			}
			System.out.println();
		}
	}
}
