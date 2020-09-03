import javax.swing.*;

/**
 * Paint application entry point
 *
 * @author sharmavins23
 */
public class JPaint {
    /**
     * Entry point function for command line
     *
     * @param args: Command line arguments
     */
    public static void main(String[] args) {
        // Creating our frame object
        DrawingFrame frame = new DrawingFrame();

        // Frame display settings
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650, 500);
        frame.setVisible(true);
    }
}
