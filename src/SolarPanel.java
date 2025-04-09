import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

public class SolarPanel extends JPanel {
    private int offsetX = 0, offsetY = 0;
    private int prevMouseX, prevMouseY;
    private boolean planetFocussed = false;
    private int focussedplanetIndex = 0;
    private double zoomScale = 1.0;
    private java.util.List<Point> starPlacements = new java.util.ArrayList<>();

    private java.util.List<Color> starColours = new java.util.ArrayList<>();
    private boolean starsgenerated = false;
    // In class
    private final int STAR_RANGE = 90000; // Simulated world-space size, in "pixels"


    public SolarPanel() {
        setBackground(Color.black);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    planetFocussed = false;
                    prevMouseX = e.getX();
                    prevMouseY = e.getY();
                    for (Planet planet : Main.solarSystem.getPlanets()) {
                        // Calculate the on-screen position
                        int x = (int) (((planet.getXPosition() * zoomScale) / 5e8) + offsetX);
                        int y = (int) (((planet.getYPosition() * zoomScale) / 5e8) + offsetY);
                        int size = (int) (64 * zoomScale);
                        int radius = size / 2;

                        // Center of the planet image
                        int centerX = x + radius;
                        int centerY = y + radius;

                        double dx = e.getX() - centerX;
                        double dy = e.getY() - centerY;
                        double distance = Math.sqrt(dx * dx + dy * dy);

                        if (distance <= radius) {
                            planet.setShowInfoTile(!planet.getShowInfoTile());

                            break;
                        }
                    }

                    for (Star planet : Main.solarSystem.getStars()) {
                        // Calculate the on-screen position
                        int x = (int) (((planet.getXPosition() * zoomScale) / 5e8) + offsetX);
                        int y = (int) (((planet.getYPosition() * zoomScale) / 5e8) + offsetY);
                        int size = (int) (64 * zoomScale);
                        int radius = size / 2;

                        // Center of the planet image
                        int centerX = x + radius;
                        int centerY = y + radius;

                        double dx = e.getX() - centerX;
                        double dy = e.getY() - centerY;
                        double distance = Math.sqrt(dx * dx + dy * dy);

                        if (distance <= radius) {
                            planet.setShowInfoTile(!planet.getShowInfoTile());

                            break;
                        }
                    }

                }
            }
        });
        addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                int rotation = e.getWheelRotation();
                double zoomStep = 0.1;
                if (rotation < 0) {
                    zoomScale *= (1 + zoomStep);
                } else {
                    zoomScale /= (1 + zoomStep);
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

                // Clamp based on zoom and world boundaries
                double scaledRange = STAR_RANGE * zoomScale * 0.15; // use parallax factor if needed
                int minOffsetX = (int) (-scaledRange - getWidth());
                int maxOffsetX = (int) (scaledRange);
                int minOffsetY = (int) (-scaledRange - getHeight());
                int maxOffsetY = (int) (scaledRange);

                offsetX = Math.max(minOffsetX, Math.min(offsetX, maxOffsetX));
                offsetY = Math.max(minOffsetY, Math.min(offsetY, maxOffsetY));

                prevMouseX = e.getX();
                prevMouseY = e.getY();

                repaint();
            }

        });
    }
    @Override
    public void addNotify() {
        super.addNotify();
        if (!starsgenerated) {
            starsgenerated = true;
            int numStars = 99999;
            for (int i = 0; i < numStars; i++) {
                int starX = (int) (Math.random() * STAR_RANGE - STAR_RANGE / 2);
                int starY = (int) (Math.random() * STAR_RANGE - STAR_RANGE / 2);
                starPlacements.add(new Point(starX, starY));

            }
        }
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
            offsetX = (int) ((panelWidth / 2) - ((body.getXPosition() * zoomScale)/ 5e8) - (32 * zoomScale));
            offsetY = (int) ((panelHeight / 2) - ((body.getYPosition() * zoomScale)/5e8) - (32 * zoomScale));
        }


        g2d.setColor(new Color(238, 230,197));
        for (Point p : starPlacements) {



                    // Parallax effect (stars move slower relative to planets)
            double panParallaxFactor = 0.05;
            double parallaxFactor = 0.95;
            int drawX = (int) ((p.x * (Math.max(zoomScale,0.05)) * parallaxFactor) + (offsetX * panParallaxFactor));
            int drawY = (int) ((p.y * (Math.max(zoomScale,0.05)) * parallaxFactor) + (offsetY * panParallaxFactor));

            // Only draw stars that are within view
            if (drawX >= -5 && drawX <= getWidth() + 5 && drawY >= -5 && drawY <= getHeight() + 5) {
                g2d.fillOval(drawX, drawY, 2, 2);
            }
        }




        for (Planet planet : Main.solarSystem.getPlanets()) {

            Image img = new ImageIcon(planet.getImage()).getImage();

            int x = (int) (((planet.getXPosition() * zoomScale)/5e8)+offsetX);
            int y = (int) (((planet.getYPosition() * zoomScale)/5e8)+offsetY);





            int size = (int) (64 * zoomScale); // Scale planet size
            g2d.drawImage(img, x, y, size, size, this);
            Color semiOpaque=new Color(0.2f,0.2f,0.2f,.8f );
            if(planet.getShowInfoTile()){
                int panelWidth = 130;
                int panelHeight = 75;
                int panelX = x + size+10;
                int panelY = y ;
                g2d.setColor(semiOpaque);
                g2d.fillRect(panelX, panelY, panelWidth, panelHeight);
                DecimalFormat df = new DecimalFormat("#.##");

                g2d.setColor(Color.WHITE);
                g2d.drawString("Mass: " + planet.getMass()+" kg", panelX+2, panelY+ 15);
                g2d.drawString("Speed: " +df.format(planet.getSpeed())+" km/s", panelX+2, panelY+ 30);
                g2d.drawString("Direction: " + df.format(planet.getSpeedDirection())+ " radians", panelX+2, panelY+ 45);
                g2d.drawString("Radius: " + df.format(planet.getSize()) + "m", panelX+2, panelY + 60);

            }




        }
        for (Star star : Main.solarSystem.getStars()) {
            Image img = new ImageIcon(star.getImage()).getImage();
            int x = (int) (star.getXPosition() * zoomScale) + offsetX;
            int y = (int) (star.getYPosition() * zoomScale) + offsetY;
            int size = (int) (64 * zoomScale);
            g2d.drawImage(img, x, y, size, size, this);






            g2d.drawImage(img, x, y, size, size, this);
            Color semiOpaque=new Color(0.2f,0.2f,0.2f,.8f );
            if(star.getShowInfoTile()){
                int panelWidth = 130;
                int panelHeight = 75;
                int panelX = x + size+10;
                int panelY = y - size/2+20;
                g2d.setColor(semiOpaque);
                g2d.fillRect(panelX, panelY, panelWidth, panelHeight);
                DecimalFormat df = new DecimalFormat("#.##");

                g2d.setColor(Color.WHITE);
                g2d.drawString("Mass: " + star.getMass()+" kg", panelX+2, panelY+ 15);
                g2d.drawString("Speed: " +df.format(star.getSpeed())+" km/s", panelX+2, panelY+ 30);
                g2d.drawString("Direction: " + df.format(star.getDirection())+ " radians", panelX+2, panelY+ 45);
                g2d.drawString("Radius: " + df.format(star.getSize()) + "m", panelX+2, panelY + 60);

            }
        }
        ArrayList<Debris> toRemove = new ArrayList<>();
        for (Debris p : Main.debrisList) {

            p.update();
            if (!p.isAlive()) {
                toRemove.add(p);
                continue;
            }
            g2d.setColor(new Color(0.2f,0.2f,0.2f, (float) p.lifespan /100));

            int drawX = (int) ((p.x * zoomScale / 5e8) + offsetX);
            int drawY = (int) ((p.y * zoomScale / 5e8) + offsetY);
            int size = (p.lifespan* p.size);
            //g2d.setColor(p.color);
            g2d.fillOval(drawX, drawY, size, size); // tiny dot for particle
            size = (p.size);
            g2d.setColor(new Color(0.2f,0.2f,0.2f, (float) Math.random()));

            g2d.fillOval(drawX, drawY, size, size); // tiny dot for particle

        }
        Main.debrisList.removeAll(toRemove);

    }
}
