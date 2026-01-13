import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        //TODO: Do a version where mines on diagonals count.
        //TODO: Make online version.

        int sideLength = 10;//Größe einer Zelle (skaliert das ganze Panel).
        int size = 5;//size of the grid (number of cells along one edge)
        //1 -> 1
        //2 -> 7
        //3 -> 19
        //4 -> 37
        //5 -> 61
        //x -> 3x^2 - 3x + 1
        int mines = 50; //number of mines in the grid
        Grid grid = new Grid(size, mines);

        // Setup the frame (kp was hier genau passiert funktioniert aber)
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Hexagonal Chess");
            Panel panel = new Panel(grid, sideLength);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(sideLength * 100, sideLength * 100);
            frame.add(panel);
            frame.setVisible(true);
        });
    }
}