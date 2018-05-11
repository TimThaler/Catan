package spiel;

import java.awt.Point;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import spielbrettKomponenten.*;

public class Spiel extends JFrame{
	 private static final long serialVersionUID = 3775690273871048733L;
	 private  userInterface.DrawingPanel drawingPanel;
	
	 public Spiel() {
		 /*hexagons two is not printed .....*/
	        super("CATAN");
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        userInterface.Hexagon hexagon = new userInterface.Hexagon(new Point(10, 300), 30);
	        userInterface.Hexagon hexagon2 = new userInterface.Hexagon(new Point(97, 300), 30);

	        Spielbrett spielbrett = Spielbrett.getInstance();
	        drawingPanel = new userInterface.DrawingPanel(hexagon);
	        drawingPanel = new userInterface.DrawingPanel();
	       // drawingPanel.addHexagon(hexagon);
	        //drawingPanel.addHexagon(hexagon2);

	        add(drawingPanel);

	        Vector<Feld> felder = spielbrett.getFelder();
	        
	        for(int i = 0; i< felder.size(); i++) {
	        	drawingPanel.addHexagon(felder.elementAt(i));
	        }
	        pack();
	        setLocationByPlatform(true);
	        setVisible(true);
	 }
	 
	 public static void main(String[] args) {    
		
		
		/*JFrame frame = new JFrame("Catan");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new userInterface.DrawingPanel());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
	 	*/
		
		 
		 SwingUtilities.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	                new Spiel();
	            }
	        });
	}
}


/**
 * a) how to put singleton getInstance in interface class?!?
 * b) getNachbarEcke wenn ecke nicht passt soll eine exception geschmissen werden
 */
/**
 * Phasen
 * Spiel initialisieren(Spielbrett, Spieler, Startposition)
 * Wuerfeln
 * Rohstoffe einsammeln
 * Optionen anzeigen
 * Bauen 
 * punkte berechnen wenn gesamtpunkte erreicht -> zug vorbei
 * wieder wuerfeln
 */
