import java.awt.*;
import java.awt.geom.Line2D;

/**
 * Defines a line object
 *
 * @author sharmavins23
 */
public class Line extends Shapes {
    /**
     * Default constructor for line object
     *
     * @param startPoint: Starting point of current drawn object
     * @param endPoint:   Ending point of current drawn object
     * @param paint:      Paint color and settings provided
     * @param stroke:     Brush stroke settings provided
     */
    public Line(Point startPoint, Point endPoint, Paint paint, Stroke stroke) {
        // Pass parent class values
        super(startPoint, endPoint, paint, stroke);
    }

    /**
     * Draw the current object onto the canvas
     *
     * @param g2d: Graphics2D object
     */
    @Override
    public void draw(Graphics2D g2d) {
        g2d.setPaint(getPaint());
        g2d.setStroke(getStroke());
        g2d.draw(new Line2D.Double(
                (int) (getStartPoint().getX()),
                (int) (getStartPoint().getY()),
                (int) (getEndPoint().getX()),
                (int) (getEndPoint().getY()))
        );
    }
}
