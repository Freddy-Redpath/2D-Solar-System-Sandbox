import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;
import java.awt.image.BufferedImage;

public class SolarPanel extends JPanel {
    private int offsetX = 0, offsetY = 0;
    private int prevMouseX, prevMouseY;
    private boolean planetFocussed = false;
    private int focussedplanetIndex = 0;
    private double zoomScale = 1.0;
    private ArrayList<Point> starPlacements = new ArrayList<>();
    private ArrayList<RandomPlanets> randomPlanetPlacements = new ArrayList<>();
    private boolean starsgenerated = false;
    private boolean randomPlanetsGenerated = false;
    private final int STAR_RANGE = 90000; // Simulated world-space size, in "pixels"
    private BufferedImage gasCloudTexture;
    private BufferedImage galaxyTexture; // galaxy texture variable

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
                        int x = (int) (((planet.getXPosition() * zoomScale) / 5e8) + offsetX);
                        int y = (int) (((planet.getYPosition() * zoomScale) / 5e8) + offsetY);
                        int size = (int) (64 * zoomScale);
                        int radius = size / 2;
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
                        int x = (int) (((planet.getXPosition() * zoomScale) / 5e8) + offsetX);
                        int y = (int) (((planet.getYPosition() * zoomScale) / 5e8) + offsetY);
                        int size = (int) (64 * zoomScale);
                        int radius = size / 2;
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
                double scaledRange = STAR_RANGE * zoomScale * 0.15;
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
        if (!randomPlanetsGenerated) {
            randomPlanetsGenerated = true;
            int numPlanets = 39999;
            Random rnd = new Random();
            for (int i = 0; i < numPlanets; i++) {
                int planetX = (int) (Math.random() * STAR_RANGE - STAR_RANGE / 2);
                int planetY = (int) (Math.random() * STAR_RANGE - STAR_RANGE / 2);
                int size = rnd.nextInt(8) + 1;
                int red = rnd.nextInt(75);
                int green = rnd.nextInt(44);
                int blue = rnd.nextInt(66);
                Color color = new Color(red, green, blue);
                randomPlanetPlacements.add(new RandomPlanets(new Point(planetX, planetY), size, color));
            }
        }
        int panelWidth = getWidth() > 0 ? getWidth() : 1000;
        int panelHeight = getHeight() > 0 ? getHeight() : 1000;
        galaxyTexture = generateGalaxyTexture(panelWidth, panelHeight, 0.5, offsetX, offsetY, zoomScale);
    }

    // Changed: Modified the signature to accept six parameters.
    public BufferedImage generateGalaxyTexture(int width, int height, double scale, int offX, int offY, double zoom) {
        if (width <= 0 || height <= 0) {
            width = getPreferredSize().width > 0 ? getPreferredSize().width : 800;
            height = getPreferredSize().height > 0 ? getPreferredSize().height : 600;
        }
        BufferedImage galaxyImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        PerlinNoise noiseGen = new PerlinNoise();
        double threshold = 0.75;
        int maxAlpha = 150;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                double nx = (((i - offX) / (double) width) / zoom) * scale;
                double ny = (((j - offY) / (double) height) / zoom) * scale;
                double noiseVal = noiseGen.noise(nx, ny);
                if (noiseVal < threshold) {
                    galaxyImage.setRGB(i, j, 0x00000000);
                } else {
                    double t = (noiseVal - threshold) / (1.0 - threshold);
                    int alpha = (int) (t * maxAlpha);
                    float hue = 0.65f + (float) t * 0.15f;
                    float saturation = 0.8f;
                    float brightness = 0.4f + (float) t * 0.2f;
                    Color c = Color.getHSBColor(hue, saturation, brightness);
                    int rgba = (alpha << 24) | (c.getRGB() & 0x00FFFFFF);
                    galaxyImage.setRGB(i, j, rgba);
                }
            }
        }
        return galaxyImage;
    }

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
        int w = getWidth();
        int h = getHeight();
        BufferedImage noiseBG = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        double perlinScale = 2.0;
        double threshold = 0.5;
        PerlinNoise noiseGen = new PerlinNoise();
        for (int sx = 0; sx < w; sx++) {
            for (int sy = 0; sy < h; sy++) {
                double worldX = (((sx - offsetX) * 0.02) / zoomScale);
                double worldY = (((sy - offsetY) * 0.02) / zoomScale);
                double nx = worldX / perlinScale;
                double ny = worldY / perlinScale;
                double noiseVal = noiseGen.noise(nx, ny);
                if (noiseVal < threshold) {
                    noiseBG.setRGB(sx, sy, 0x00000000);
                } else {
                    int alpha = (int)(noiseVal * 20);
                    double range = 1.0 - threshold;
                    double t = (noiseVal - threshold) / range;
                    float hue = 0.65f + (float)t * 0.15f;
                    float saturation = 0.8f;
                    float brightness = 0.5f + (float)t * 0.3f;
                    Color c = Color.getHSBColor(hue, saturation, brightness);
                    int rgba = (alpha << 24) | (c.getRGB() & 0x00FFFFFF);
                    noiseBG.setRGB(sx, sy, rgba);
                }
            }
        }
        g2d.drawImage(noiseBG, 0, 0, w, h, null);
        // Generate dynamic galaxy texture with scrolling/zoom offset
        BufferedImage dynamicGalaxy = generateGalaxyTexture(w, h, 0.5, offsetX, offsetY, zoomScale);
        g2d.drawImage(dynamicGalaxy, 0, 0, w, h, null);
        if (planetFocussed) {
            int panelWidth = getWidth();
            int panelHeight = getHeight();
            Planet body = Main.solarSystem.getPlanets().get(focussedplanetIndex);
            offsetX = (int)((panelWidth / 2) - ((body.getXPosition() * zoomScale) / 0.05) - (32 * zoomScale));
            offsetY = (int)((panelHeight / 2) - ((body.getYPosition() * zoomScale) / 0.05) - (32 * zoomScale));
        }
        g2d.setColor(new Color(238, 230,197));
        for (Point p : starPlacements) {
            double panParallaxFactor = 0.05;
            double parallaxFactor = 0.95;
            int drawX = (int)((p.x * Math.max(zoomScale, 0.05) * parallaxFactor) + (offsetX * panParallaxFactor));
            int drawY = (int)((p.y * Math.max(zoomScale, 0.05) * parallaxFactor) + (offsetY * panParallaxFactor));
            if (drawX >= -5 && drawX <= getWidth() + 5 && drawY >= -5 && drawY <= getHeight() + 5) {
                g2d.fillOval(drawX, drawY, 2, 2);
            }
        }
        for (RandomPlanets rp : randomPlanetPlacements) {
            double panParallaxFactor = 0.05;
            double parallaxFactor = 0.95;
            int drawX = (int)((rp.getPosition().x * Math.max(zoomScale, 0.05) * parallaxFactor) + (offsetX * panParallaxFactor));
            int drawY = (int)((rp.getPosition().y * Math.max(zoomScale, 0.05) * parallaxFactor) + (offsetY * panParallaxFactor));
            if (drawX >= -5 && drawX <= getWidth() + 5 && drawY >= -5 && drawY <= getHeight() + 5) {
                g2d.setColor(rp.getColor());
                g2d.fillOval(drawX, drawY, rp.getSize(), rp.getSize());
            }
        }
        for (Planet planet : Main.solarSystem.getPlanets()) {
            Image img = new ImageIcon(planet.getImage()).getImage();
            int x = (int)(((planet.getXPosition() * zoomScale) / 0.05) + offsetX);
            int y = (int)(((planet.getYPosition() * zoomScale) / 0.05) + offsetY);
            int size = (int)(64 * zoomScale);
            g2d.drawImage(img, x, y, size, size, this);
            Color semiOpaque = new Color(0.2f, 0.2f, 0.2f, .8f);
            if (planet.getShowInfoTile()) {
                int panelWidth = 130;
                int panelHeight = 75;
                int panelX = x + size + 10;
                int panelY = y;
                g2d.setColor(semiOpaque);
                g2d.fillRect(panelX, panelY, panelWidth, panelHeight);
                DecimalFormat df = new DecimalFormat("#.##");
                g2d.setColor(Color.WHITE);
                g2d.drawString("Mass: " + planet.getMass() + " kg", panelX + 2, panelY + 15);
                g2d.drawString("Speed: " + df.format(planet.getSpeed()) + " km/s", panelX + 2, panelY + 30);
                g2d.drawString("Direction: " + df.format(planet.getSpeedDirection()) + " radians", panelX + 2, panelY + 45);
                g2d.drawString("Radius: " + df.format(planet.getSize()) + "m", panelX + 2, panelY + 60);
            }
        }
        for (Star star : Main.solarSystem.getStars()) {
            Image img = new ImageIcon(star.getImage()).getImage();
            int x = (int)(star.getXPosition() * zoomScale) + offsetX;
            int y = (int)(star.getYPosition() * zoomScale) + offsetY;
            int size = (int)(64 * zoomScale);
            g2d.drawImage(img, x, y, size, size, this);
            g2d.drawImage(img, x, y, size, size, this);
            Color semiOpaque = new Color(0.2f, 0.2f, 0.2f, .8f);
            if (star.getShowInfoTile()) {
                int panelWidth = 130;
                int panelHeight = 75;
                int panelX = x + size + 10;
                int panelY = y - size / 2 + 20;
                g2d.setColor(semiOpaque);
                g2d.fillRect(panelX, panelY, panelWidth, panelHeight);
                DecimalFormat df = new DecimalFormat("#.##");
                g2d.setColor(Color.WHITE);
                g2d.drawString("Mass: " + star.getMass() + " kg", panelX + 2, panelY + 15);
                g2d.drawString("Speed: " + df.format(star.getSpeed()) + " km/s", panelX + 2, panelY + 30);
                g2d.drawString("Direction: " + df.format(star.getDirection()) + " radians", panelX + 2, panelY + 45);
                g2d.drawString("Radius: " + df.format(star.getSize()) + "m", panelX + 2, panelY + 60);
            }
        }
        ArrayList<Debris> toRemove = new ArrayList<>();
        for (Debris p : Main.debrisList) {
            p.update();
            if (!p.isAlive()) {
                toRemove.add(p);
                continue;
            }
            g2d.setColor(new Color(0.2f, 0.2f, 0.2f, (float)p.lifespan / 100));
            int drawX = (int)((p.x * zoomScale / 5e8) + offsetX);
            int drawY = (int)((p.y * zoomScale / 5e8) + offsetY);
            int pSize = (p.lifespan * p.size);
            g2d.fillOval(drawX, drawY, pSize, pSize);
            pSize = p.size;
            g2d.setColor(new Color(0.2f, 0.2f, 0.2f, (float)Math.random()));
            g2d.fillOval(drawX, drawY, pSize, pSize);
        }
        Main.debrisList.removeAll(toRemove);
    }
}
