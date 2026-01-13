import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        //TODO: Do a version where mines on diagonals count.
        //TODO: Make online version.

        Grid grid = new Grid();
        int sideLength = 40;//Größe einer Zelle (skaliert das ganze Panel).

        // Setup the frame (kp was hier genau passiert funktioniert aber)
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Hexagonal Chess");
            Panel panel = new Panel(grid, sideLength);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(sideLength * 25, sideLength * 25);
            frame.add(panel);
            frame.setVisible(true);
        });

    }
}