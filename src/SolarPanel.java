import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SolarPanel extends JPanel {
    private int offsetX = 0, offsetY = 0;
    private int prevMouseX, prevMouseY;
    private boolean planetFocussed = false;
    private int focussedplanetIndex = 0;
    private double zoomScale = 1.0;

    public SolarPanel() {
        setBackground(Color.black);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                planetFocussed = false;
                prevMouseX = e.getX();
                prevMouseY = e.getY();
            }
        });
        addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                planetFocussed = true;
                int rotation = e.getWheelRotation();
                double zoomStep = 0.1;
                System.out.println("rotation: " + rotation);
                if (rotation < 0) {
                    zoomScale *= (1 + zoomStep);
                    System.out.println("zoom in " + zoomScale);
                } else {
                    zoomScale /= (1 + zoomStep);
                    System.out.println("zoom out " + zoomScale);
                }

                repaint();
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

    // Method to focus on a specific planet
    public void focusOnPlanet(int index) {
        this.focussedplanetIndex = index;
        planetFocussed = true;
        zoomScale = 1.0;

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        if (planetFocussed) {
            int panelWidth = getWidth();
            int panelHeight = getHeight();
            Planet body = Main.solarSystem.getPlanets().get(focussedplanetIndex);
            // Center the view on the selected planet
            offsetX = (int) ((panelWidth / 2) - (body.getXPosition() * zoomScale) - (32 * zoomScale));
            offsetY = (int) ((panelHeight / 2) - (body.getYPosition() * zoomScale) - (32 * zoomScale));
        }
        for (Planet planet : Main.solarSystem.getPlanets()) {

            Image img = new ImageIcon(planet.getImage()).getImage();
            int x = (int) (((planet.getXPosition() * zoomScale)/1e8)+offsetX);
            int y = (int) (((planet.getYPosition() * zoomScale)/1e8)+offsetY);
            /*
            if (planet.getName().equals("mercury")) {
                System.out.println("Drawn x: " + x + ", y: " + y);
            }
            */
            int size = (int) (64 * zoomScale); // Scale planet size
            g2d.drawImage(img, x, y, size, size, this);
        }
        for (Star star : Main.solarSystem.getStars()) {
            Image img = new ImageIcon(star.getImage()).getImage();
            int x = (int) (star.getXPosition() * zoomScale) + offsetX;
            int y = (int) (star.getYPosition() * zoomScale) + offsetY;
            int size = (int) (64 * zoomScale); // Scale planet size
            g2d.drawImage(img, x, y, size, size, this);
        }
    }
}
