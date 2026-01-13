import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Panel extends JPanel {
    private final Grid grid;
    private final int sideLength;
    private GridCell selectedCell;

    public Panel(Grid grid1, int sideLength) {
        this.grid = grid1;
        this.sideLength = sideLength;

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                boardMouseListener(e);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g2);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // Background
        g2.setColor(Color.blue);//LightBrown
        g2.fillRect(0, 0, getWidth(), getHeight());

        drawGrid(g2);
    }

    /**
     * Generates a Hexagon-Polygon based with the center (cx, cy).
     *
     * @param cx x Coordinate des Mittelpunkts
     * @param cy y Coordinate des Mittelpunkts
     * @return the Hexagon-Polygon
     */
    private Polygon createHexagon(int cx, int cy) {
        Polygon hex = new Polygon();
        for (int i = 0; i < 6; i++) {
            double angle = Math.toRadians(60 * i);
            int x = cx + (int) Math.round(sideLength * Math.cos(angle));
            int y = cy + (int) Math.round(sideLength * Math.sin(angle));
            hex.addPoint(x, y);
        }
        return hex;
    }

    /**
     * Draws the Hexagons and the pieces on the grid
     *
     * @param g2 the Graphics2D object
     */
    private void drawGrid(Graphics2D g2) {
        for (GridCell cell : grid.getCells()) {
            int col = cell.getYCord();
            int row = cell.getXCord();
            //kp wie ich darauf komme, aber so passt es
            double x = Math.round(sideLength * 50f) + ((col - row) * 1.5) * sideLength;
            double y = Math.round(((row + col) * Math.sqrt(3) / 2) * sideLength);

            Polygon hex = createHexagon((int) x, (int) y);
            g2.setColor(Color.BLACK);
            g2.draw(hex);

            if(cell == selectedCell){
                g2.setColor(Color.yellow);
            }else {
                g2.setColor(Color.CYAN);
            }
            g2.fill(hex);
        }
    }

    /**
     * The board mouse listener.
     *
     * @param e the mouse event.
     */
    private void boardMouseListener(MouseEvent e) {
        double temp1 = (double) (e.getX() - Math.round(sideLength * 50f)) / (3 * sideLength);
        double temp2 = (double) (e.getY()) / (Math.sqrt(3) * sideLength);
        int gx = (int) Math.round(temp2 - temp1);
        int gy = (int) Math.round(temp2 + temp1);
        if (grid.getCell(gx, gy) != null) {
            selectedCell = grid.getCell(gx, gy);
        }
        repaint();
    }
}