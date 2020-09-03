import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Main application frame definition
 *
 * @author sharmavins23
 */
public class DrawingFrame extends JFrame {
    // Top widget panel - contains two lines of widgets
    private final JPanel topPanel;

    // 'First line' panel and widgets
    private final JPanel firstLine;

    private final JButton undo;
    private final JButton clear;
    private final JLabel shapeLabel;
    private final JComboBox<String> shapeSelect;
    private final String[] shapeChoices = {"Line", "Oval", "Rectangle"};
    private final JCheckBox filled;

    // 'Second line' panel and widgets
    private final JPanel secondLine;

    private final JCheckBox useGradient;
    private final JButton firstColor;
    private final JButton secondColor;
    private final JLabel lineWidth;
    private final JTextField lineWidthInput;
    private final JLabel dashLength;
    private final JTextField dashLengthInput;
    private final JCheckBox dashed;
    // Initialize primary and secondary colors to black and white

    // Initialize objects in drawing panel
    private Paint paint;
    private Stroke stroke;
    private boolean isFilled;
    private Point mouseLocation;
    private ArrayList<Shapes> shapes = new ArrayList<>();
    private Shapes currentShape;
    // Initial values for drawing

    // Initialize values for drawing
    private Color color1 = Color.BLACK;
    private Color color2 = Color.WHITE;
    private int lineWidthValue = 5;
    private float[] dashLengthValue = {5};

    // Bottom status label
    private final JLabel statusLabel;

    /**
     * Constructor for drawing frame
     */
    public DrawingFrame() {
        // Title header
        super("Java Paint");
        // Set frame layout
        setLayout(new BorderLayout());

        // Construct first line panel
        firstLine = new JPanel();
        firstLine.setLayout(new FlowLayout());

        undo = new JButton("Undo");
        clear = new JButton("Clear");
        shapeLabel = new JLabel("Shape:");
        shapeSelect = new JComboBox<>(shapeChoices);
        filled = new JCheckBox("Filled");

        firstLine.add(undo);
        firstLine.add(clear);
        firstLine.add(shapeLabel);
        firstLine.add(shapeSelect);
        firstLine.add(filled);

        // Construct second line panel
        secondLine = new JPanel();
        secondLine.setLayout(new FlowLayout());

        useGradient = new JCheckBox("Use Gradient");
        firstColor = new JButton("1st Color...");
        secondColor = new JButton("2nd Color...");
        lineWidth = new JLabel("Line Width:");
        lineWidthInput = new JTextField("5", 2);
        dashLength = new JLabel("Dash Length:");
        dashLengthInput = new JTextField("5", 2);
        dashed = new JCheckBox("Dashed");

        secondLine.add(useGradient);
        secondLine.add(firstColor);
        secondLine.add(secondColor);
        secondLine.add(lineWidth);
        secondLine.add(lineWidthInput);
        secondLine.add(dashLength);
        secondLine.add(dashLengthInput);
        secondLine.add(dashed);

        // Construct top panel
        topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());

        topPanel.add(firstLine, BorderLayout.NORTH);
        topPanel.add(secondLine, BorderLayout.CENTER);

        // Construct main JFrame
        add(topPanel, BorderLayout.NORTH);
        DrawPanel drawPanel = new DrawPanel();
        drawPanel.setBackground(Color.WHITE);
        add(drawPanel, BorderLayout.CENTER);
        statusLabel = new JLabel("(0, 0)");
        add(statusLabel, BorderLayout.SOUTH);

        // Widget event handlers
        undo.addActionListener(
                event -> {
                    if (shapes.size() > 0) shapes.remove(shapes.size() - 1); // If some value exists, remove it.
                    repaint();
                }
        );
        clear.addActionListener(
                event -> {
                    shapes.clear();
                    repaint();
                }
        );
        firstColor.addActionListener(
                event -> {
                    color1 = JColorChooser.showDialog(DrawingFrame.this, "Choose a color", color1);

                    if (color1 == null) color1 = Color.BLACK;
                }
        );
        secondColor.addActionListener(
                event -> {
                    color2 = JColorChooser.showDialog(DrawingFrame.this, "Choose a color", color2);

                    if (color2 == null) color2 = Color.WHITE;
                }
        );
    }

    /**
     * Private inner class for drawing panel
     */
    private class DrawPanel extends JPanel {
        public DrawPanel() {
            MouseHandler mouseHandler = new MouseHandler();
            addMouseListener(mouseHandler);
            addMouseMotionListener(mouseHandler);
        }

        /**
         * Redraws all components on screen
         *
         * @param g: Graphics object
         */
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            // Loop through and draw each shape in the shapes ArrayList
            for (Shapes shape : shapes) {
                shape.draw(g2d);
            }
        }

        /**
         * Private inner class for handling mouse events
         */
        private class MouseHandler extends MouseAdapter implements MouseMotionListener {

            /**
             * Called when the mouse is first pressed down
             *
             * @param event: Event where the mouse is pressed
             */
            @Override
            public void mousePressed(MouseEvent event) {
                // Create a paint variable, setting its value based on the colors/gradients
                if (useGradient.isSelected()) {
                    paint = new GradientPaint(0, 0, color1, 50, 50, color2, true);
                } else {
                    paint = color1;
                }

                // Stroke variable to set width and dashedness
                try { // Sanitizing lineWidthInput
                    lineWidthValue = Integer.parseInt(lineWidthInput.getText());
                } catch (IllegalArgumentException e) { // invalid value from the textArea
                    lineWidthValue = 5; // sets back to simple defaults
                    lineWidthInput.setText("5");
                }
                try { // Sanitizing dashLengthInput
                    dashLengthValue[0] = Integer.parseInt(dashLengthInput.getText());
                } catch (IllegalArgumentException e) { // invalid value from the textArea
                    dashLengthValue[0] = 5; // sets back to simple defaults
                    dashLengthInput.setText("5");
                }

                if (dashed.isSelected()) {
                    stroke = new BasicStroke(lineWidthValue, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 10, dashLengthValue, 0);
                } else {
                    stroke = new BasicStroke(lineWidthValue, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
                }

                // Filled variable on whether or not it's filled
                isFilled = filled.isSelected();
                // Mouse location for start and end point
                mouseLocation = event.getPoint();

                // Create the drawn object either line, rectangle, oval
                if (Objects.requireNonNull(shapeSelect.getSelectedItem()).toString().equals("Rectangle")) {
                    currentShape = new Rectangle(mouseLocation, mouseLocation, paint, stroke, isFilled);
                } else if (shapeSelect.getSelectedItem().toString().equals("Oval")) {
                    currentShape = new Oval(mouseLocation, mouseLocation, paint, stroke, isFilled);
                } else { // Line
                    currentShape = new Line(mouseLocation, mouseLocation, paint, stroke);
                }


                // Add to ArrayList
                shapes.add(currentShape);
            }

            /**
             * Called when the mouse is released
             *
             * @param event: Event where the mouse is released
             */
            @Override
            public void mouseReleased(MouseEvent event) {
                // setEndpoint() of current shape being drawn
                mouseLocation = event.getPoint();
                currentShape.setEndPoint(mouseLocation);

                // Update the bottom status label
                statusLabel.setText(String.format("(%d, %d)", mouseLocation.x, mouseLocation.y));

                // Update ArrayList with the current shape setup
                shapes.set(shapes.size() - 1, currentShape);
                repaint();

                // Remove currentShape value
                currentShape = null;
            }

            /**
             * Called when the mouse is dragged
             *
             * @param event: Event where the mouse is dragged
             */
            @Override
            public void mouseDragged(MouseEvent event) {
                // setEndpoint() of current shape being drawn
                mouseLocation = event.getPoint();
                currentShape.setEndPoint(mouseLocation);

                // Update the bottom status label
                statusLabel.setText(String.format("(%d, %d)", mouseLocation.x, mouseLocation.y));

                // Update ArrayList with the current shape setup
                shapes.set(shapes.size() - 1, currentShape);
                repaint();
            }

            /**
             * Called when the mouse is moved, but not necessarily clicked in
             *
             * @param event: Event where the mouse is moved
             */
            @Override
            public void mouseMoved(MouseEvent event) {
                // Update status label and mouse position
                mouseLocation = event.getPoint();
                statusLabel.setText(String.format("(%d, %d)", mouseLocation.x, mouseLocation.y));
            }
        }
    }
}
