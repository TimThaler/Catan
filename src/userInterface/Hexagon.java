package userInterface;

import java.awt.Point;
import java.awt.Polygon;

public class Hexagon {
	private int radius = 50;
	private double height = radius / 2;
    private final Point center;
    private final Polygon hexagon;

    public Hexagon(Point center) {
        this.center = center;
        this.hexagon = createHexagon();
    }
    
    public Hexagon(Point center, int radius) {
        this.center = center;
        this.radius = radius;
        this.hexagon = createHexagon();
    }

    private Polygon createHexagon() {
        Polygon p = new Polygon();

        for (int i = 0; i < 6; i++) {
            int xval = (int) (center.x + radius
                    * Math.sin(i * 2 * Math.PI / 6D));
            int yval = (int) (center.y + radius
                    * Math.cos(i * 2 * Math.PI / 6D));
            p.addPoint(xval, yval);
        }
   
       /* for (int i = 0; i < 6; i++) {
            p.addPoint((int) (this.center.getX() + 50 * Math.sin(i * 2 * Math.PI / 6)),
              (int) (100 + 50 * Math.cos(i * 2 * Math.PI / 6)));  
        }*/
        return p;
    }

    public int getRadius() {
        return radius;
    }

    public Point getCenter() {
        return center;
    }
    
    public double getHeight() {
        return height;
    }

    public Polygon getHexagon() {
        return hexagon;
    }
}


/*
 *   for (int i = 0; i < 6; i++) {
            int xval = (int) (center.x + radius
                    * Math.cos(i * 2 * Math.PI / 6D));
            int yval = (int) (center.y + radius
                    * Math.sin(i * 2 * Math.PI / 6D));
            p.addPoint(xval, yval);
        }
 * 
 * */

/*  for (int i = 0; i < 6; i++) {
p.addPoint((int) (50 + 50 * Math.sin(i * 2 * Math.PI / 6)),
  (int) (100 + 50 * Math.cos(i * 2 * Math.PI / 6)));  
}*/
