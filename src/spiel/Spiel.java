package spiel;

import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import spielbrettKomponenten.*;

public class Spiel extends JFrame{
	 private static final long serialVersionUID = 3775690273871048733L;
	 private  userInterface.DrawingPanel drawingPanel;
	
	 public Spiel() {
	        super("Lines Drawing Demo");
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        userInterface.Hexagon hexagon = new userInterface.Hexagon(new Point(250, 250), 200);

	        drawingPanel = new userInterface.DrawingPanel(hexagon);
	        add(drawingPanel);

	        pack();
	        setLocationByPlatform(true);
	        setVisible(true);
	 }
	 
	 public static void main(String[] args) {
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
 
		

		    
		Spielbrett spielbrett = Spielbrett.getInstance();
		
	/*	JFrame frame = new JFrame("Catan");
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