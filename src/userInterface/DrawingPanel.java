package userInterface;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.*;
import javax.swing.*;

public class DrawingPanel extends JPanel{

	private static final long serialVersionUID = 1L;

	 private Hexagon hexagon;

     public DrawingPanel(Hexagon hexagon) {
         this.hexagon = hexagon;
         this.setPreferredSize(new Dimension(500, 500));
     }

     public DrawingPanel(){
         setPreferredSize(new Dimension(200, 200));
     }

  //   p.addPoint((int) (100 + 50 * Math.cos(i * 2 * Math.PI / 6)),
  //           (int) (100 + 50 * Math.sin(i * 2 * Math.PI / 6)));           
     protected void paintComponent(Graphics g) {
    	 super.paintComponent(g);            
         Polygon p = new Polygon();
         for (int i = 0; i < 6; i++)
             p.addPoint((int) (50 + 50 * Math.sin(i * 2 * Math.PI / 6)),
               (int) (100 + 50 * Math.cos(i * 2 * Math.PI / 6)));        
         g.drawPolygon(p);    
     }
}
