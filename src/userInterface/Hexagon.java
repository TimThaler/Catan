package userInterface;

import java.awt.Point;
import java.awt.Polygon;

public class Hexagon {
	private final int radius;
    private final Point center;
    private final Polygon hexagon;

    public Hexagon(Point center, int radius) {
        this.center = center;
        this.radius = radius;
        this.hexagon = createHexagon();
    }

    private Polygon createHexagon() {
        Polygon p = new Polygon();

      /*  for (int i = 0; i < 6; i++) {
            int xval = (int) (center.x + radius
                    * Math.cos(i * 2 * Math.PI / 6D));
            int yval = (int) (center.y + radius
                    * Math.sin(i * 2 * Math.PI / 6D));
            polygon.addPoint(xval, yval);
        }*/
      /*  for (int i = 0; i < 6; i++) {
            p.addPoint((int) (50 + 50 * Math.sin(i * 2 * Math.PI / 6)),
              (int) (100 + 50 * Math.cos(i * 2 * Math.PI / 6)));  
        }*/
        for (int i = 0; i < 6; i++) {
            p.addPoint((int) (this.center.getX() + 50 * Math.sin(i * 2 * Math.PI / 6)),
              (int) (100 + 50 * Math.cos(i * 2 * Math.PI / 6)));  
        }
        return p;
    }

    public int getRadius() {
        return radius;
    }

    public Point getCenter() {
        return center;
    }

    public Polygon getHexagon() {
        return hexagon;
    }
}
