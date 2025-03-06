import javax.swing.*;
import java.awt.*;

// Main class
public class SolarGraphics extends JFrame  {
    private Planet earth; // Planet object to be taken from Planet file


        public SolarGraphics() {
            setTitle("Solar System");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//EFUEF
            // Get image from resource package "Images"
            ImageIcon icon = new ImageIcon(getClass().getResource("images/Earth.png"));
            // Scale image to appropriate size
            Image ScaledImage = icon.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
            // Planet object with scaled image (positions set as example)
            earth = new Planet(150, 150,150,5,0,64,ScaledImage, "earth");

            //Panel to draw planet
            SolarSystemPanel panel = new SolarSystemPanel(earth);
            add(panel);

            // Set window dimensions
            setSize(440,440);

            setLocationRelativeTo(null);
            setVisible(true);
        }
        public static void main(String[] args) {
            SwingUtilities.invokeLater(SolarGraphics::new);
        }
    }

    // Custom Jpanel to draw planet
class SolarSystemPanel extends JPanel{
    private Planet planet;
    public SolarSystemPanel(Planet planet) {
        this.planet = planet;
        setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the planet's image at its specified x and y coordinates
        g.drawImage(planet.getImage(), (int) planet.getXPosition(), (int) planet.getYPosition(), this);
    }
}



