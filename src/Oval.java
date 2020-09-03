import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Defines an oval object
 *
 * @author sharmavins23
 */
public class Oval extends BoundedShapes {
    /**
     * Default constructor for oval object
     *
     * @param startPoint: Starting point of current drawn object
     * @param endPoint:   Ending point of current drawn object
     * @param paint:      Paint color and settings provided
     * @param stroke:     Brush stroke settings provided
     * @param filled:     Whether the shape is filled in
     */
    public Oval(Point startPoint, Point endPoint, Paint paint, Stroke stroke, boolean filled) {
        // Pass parent class values
        super(startPoint, endPoint, paint, stroke, filled);
    }

    /**
     * Draw the current object onto the canvas
     *
     * @param g2d: Graphics2D object
     */
    @Override
    public void draw(Graphics2D g2d) {
        // Set the graphics variables
        g2d.setPaint(getPaint());
        g2d.setStroke(getStroke());

        // Draw either a filled or empty oval
        if (isFilled()) {
            g2d.fill(new Ellipse2D.Double(getTopLeftX(), getTopLeftY(), getWidth(), getHeight()));
        } else {
            g2d.draw(new Ellipse2D.Double(getTopLeftX(), getTopLeftY(), getWidth(), getHeight()));
        }

    }
}
