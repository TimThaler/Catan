package userInterface;

import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;

public class DrawingPanel extends JPanel{

	private static final long serialVersionUID = 1L;

	 private Hexagon hexagon;
	 private ArrayList<Hexagon> hexagons = new ArrayList<Hexagon>();
	 
     public DrawingPanel(Hexagon hexagon) {
    	 System.out.println("radus of hex: " +hexagon.getRadius());
         this.hexagon = hexagon;
         this.setPreferredSize(new Dimension(500, 500));
     }
     
     public DrawingPanel() {
    	 //this.hexagon = hexagon;
         this.setPreferredSize(new Dimension(500, 500));
     }
     
     public void addHexagon(Hexagon h) {
    	 hexagons.add(h);
     }


     @Override
     protected void paintComponent(Graphics g) {
         super.paintComponent(g);

         g.setColor(Color.RED);
         for (int i = 0; i <  hexagons.size(); i++) {
        	 System.out.println("dsfsdfsfdsdfsdf" + hexagons.get(i).getCenter());
        	 g.drawPolygon(hexagons.get(i).getHexagon());
		}
       // g.drawPolygon(hexagon.getHexagon());
     }
  //   p.addPoint((int) (100 + 50 * Math.cos(i * 2 * Math.PI / 6)),
  //           (int) (100 + 50 * Math.sin(i * 2 * Math.PI / 6)));           
    /* protected void paintComponent(Graphics g) {
    	 System.out.println("sdfsdfsfd");
    	 super.paintComponent(g);            
         Polygon p = new Polygon();
         for (int i = 0; i < 6; i++)
             p.addPoint((int) (50 + 50 * Math.sin(i * 2 * Math.PI / 6)),
               (int) (100 + 50 * Math.cos(i * 2 * Math.PI / 6)));        
         g.drawPolygon(p);    
         
         p = new Polygon();
         for (int i = 0; i < 6; i++)
             p.addPoint((int) (137.5 + 50 * Math.sin(i * 2 * Math.PI / 6)),
               (int) (100 + 50 * Math.cos(i * 2 * Math.PI / 6)));        
         g.drawPolygon(p);    
     }*/
}