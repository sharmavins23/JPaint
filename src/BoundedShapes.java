import java.awt.*;

/**
 * Abstract class defining a bounded drawn shape
 *
 * @author sharmavins23
 */
public abstract class BoundedShapes extends Shapes {
    private boolean filled;

    /**
     * Default constructor for bounded shape object
     *
     * @param startPoint: Starting point of current drawn object
     * @param endPoint:   Ending point of current drawn object
     * @param paint:      Paint color and settings provided
     * @param stroke:     Brush stroke settings provided
     * @param filled:     Whether the shape is filled in
     */
    public BoundedShapes(Point startPoint, Point endPoint, Paint paint, Stroke stroke, boolean filled) {
        // Pass parent class values
        super(startPoint, endPoint, paint, stroke);

        this.filled = filled;
    }

    /**
     * @return filled: Whether the shape is filled in
     */
    public boolean isFilled() {
        return filled;
    }

    /**
     * @param filled: Whether the shape is filled in
     */
    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    /**
     * @return topLeftX: Top-left-most point's X value
     */
    public int getTopLeftX() {
        return Math.min((int) (getStartPoint().getX()), (int) (getEndPoint().getX()));
    }

    /**
     * @return topLeftY: Top-left-most point's Y value
     */
    public int getTopLeftY() {
        return Math.min((int) (getStartPoint().getY()), (int) (getEndPoint().getY()));
    }

    /**
     * @return width: Width of the object
     */
    public int getWidth() {
        return Math.abs((int) getStartPoint().getX() - (int) getEndPoint().getX());
    }

    /**
     * @return height: Height of the object
     */
    public int getHeight() {
        return Math.abs((int) getStartPoint().getY() - (int) getEndPoint().getY());
    }
}
