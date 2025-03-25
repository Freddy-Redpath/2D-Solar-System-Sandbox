import javax.swing.*;
import java.awt.*;
import java.util.Random;


public class SolarPanel extends JPanel {

    public JPanel SolarPanelCreator() {

        SolarPanel panel = new SolarPanel();
        panel.setBackground(Color.black);


        return panel;
    }


    @Override
    protected void paintComponent(Graphics g) {


        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.white);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawRect(0, 0, 64,64);

        for (Planet planet : Main.solarSystem.getPlanets()) {
            Image img = null;
            img = new ImageIcon(planet.getImage()).getImage();
            int x = (int)planet.getXPosition();
            int y = (int)planet.getYPosition();


            g2d.drawImage(img,x,y,64, 64, this);
        }
        for (Star star : Main.solarSystem.getStars()) {
            Image img = null;
            img = new ImageIcon(star.getImage()).getImage();
            int x = (int)star.getXPosition();
            int y = (int)star.getYPosition();


            g2d.drawImage(img,x,y,64,64, this);
        }

    }
}
