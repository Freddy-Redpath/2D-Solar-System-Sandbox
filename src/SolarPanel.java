import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SolarPanel extends JPanel {
    private int offsetX = 0, offsetY = 0;
    private int prevMouseX, prevMouseY;

    public SolarPanel() {
        setBackground(Color.black);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                prevMouseX = e.getX();
                prevMouseY = e.getY();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int dx = e.getX() - prevMouseX;
                int dy = e.getY() - prevMouseY;

                offsetX += dx;
                offsetY += dy;

                prevMouseX = e.getX();
                prevMouseY = e.getY();

                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        for (Planet planet : Main.solarSystem.getPlanets()) {
            Image img = new ImageIcon(planet.getImage()).getImage();
            int x = (int) planet.getXPosition() + offsetX;
            int y = (int) planet.getYPosition() + offsetY;
            g2d.drawImage(img, x, y, 64, 64, this);
        }

        for (Star star : Main.solarSystem.getStars()) {
            Image img = new ImageIcon(star.getImage()).getImage();
            int x = (int) star.getXPosition() + offsetX;
            int y = (int) star.getYPosition() + offsetY;
            g2d.drawImage(img, x, y, 64, 64, this);
        }
    }
}
