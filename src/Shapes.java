import java.awt.*;

/**
 * Abstract class defining a drawn shape
 *
 * @author sharmavins23
 */
public abstract class Shapes {
    private Point startPoint = new Point();
    private Point endPoint = new Point();
    private Paint paint;
    private Stroke stroke;

    /**
     * Default constructor for shape object
     */
    public Shapes() {
        // Initializing our drawing configuration
        stroke = new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
        paint = Color.BLACK;
    }

    /**
     * Overloaded constructor for shape object
     *
     * @param startPoint: Starting point of current drawn object
     * @param endPoint:   Ending point of current drawn object
     * @param paint:      Paint color and settings provided
     * @param stroke:     Brush stroke settings provided
     */
    public Shapes(Point startPoint, Point endPoint, Paint paint, Stroke stroke) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.paint = paint;
        this.stroke = stroke;
    }

    /**
     * Abstract function to draw the current object
     *
     * @param g2d: Graphics2D object
     */
    public abstract void draw(Graphics2D g2d);

    /**
     * @return startPoint: Starting point of current drawn object
     */
    public Point getStartPoint() {
        return startPoint;
    }

    /**
     * @param startPoint: Starting point of current drawn object
     */
    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }

    /**
     * @return endPoint: Ending point of current drawn object
     */
    public Point getEndPoint() {
        return endPoint;
    }

    /**
     * @param endPoint: Ending point of current drawn object
     */
    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }

    /**
     * @return paint: Paint color and settings provided
     */
    public Paint getPaint() {
        return paint;
    }

    /**
     * @param paint: Paint color and settings provided
     */
    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    /**
     * @return stroke: Brush stroke settings provided
     */
    public Stroke getStroke() {
        return stroke;
    }

    /**
     * @param stroke: Brush stroke settings provided
     */
    public void setStroke(Stroke stroke) {
        this.stroke = stroke;
    }
}
