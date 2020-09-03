import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Defines a rectangle object
 *
 * @author sharmavins23
 */
public class Rectangle extends BoundedShapes {
    /**
     * Default constructor for rectangle object
     *
     * @param startPoint: Starting point of current drawn object
     * @param endPoint:   Ending point of current drawn object
     * @param paint:      Paint color and settings provided
     * @param stroke:     Brush stroke settings provided
     * @param filled:     Whether the shape is filled in
     */
    public Rectangle(Point startPoint, Point endPoint, Paint paint, Stroke stroke, boolean filled) {
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

        // Draw either a filled or empty rectangle
        if (isFilled()) {
            g2d.fill(new Rectangle2D.Double(getTopLeftX(), getTopLeftY(), getWidth(), getHeight()));
        } else {
            g2d.draw(new Rectangle2D.Double(getTopLeftX(), getTopLeftY(), getWidth(), getHeight()));
        }

    }
}
