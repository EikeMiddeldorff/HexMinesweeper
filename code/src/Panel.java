import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Panel extends JPanel {
    private Grid grid;
    private final int sideLength;

    public Panel(Grid grid1, int sideLength) {
        this.grid = grid1;
        this.sideLength = sideLength;

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getButton() == MouseEvent.BUTTON1){ //Left click
                   System.out.println("test");
                }
                if(e.getButton() == MouseEvent.BUTTON3){ //Right click
                    System.out.println("test1");
                }


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
    }
}