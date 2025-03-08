import javax.swing.*;
import java.awt.*;

public class SolarGraphics {


    public JPanel SolarPanelCreator(SolarSystem solarSystem) {
        // Establishes the JPanel, and establishes the background + layout
        JPanel SolarPanel = new JPanel();
        SolarPanel.setLayout(null);
        SolarPanel.setBackground(Color.black);
        // Loops through each planet in the Solar System, setting the image
        for (Planet planet : solarSystem.getPlanets()) {
            ImageIcon icon = new ImageIcon(planet.getImage());
            Image ScaledImage = icon.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(ScaledImage);
            //create a label for each planet
            JLabel planetLabel = new JLabel(scaledIcon);
            //set size and position of label to match size of image (to stop stretching or shrinking of image)
            planetLabel.setBounds((int) planet.getXPosition(), (int) planet.getYPosition(), 64, 64);
            //add planet labels to Panel
            SolarPanel.add(planetLabel);
        }
        for (Star star : solarSystem.getStars()) {
            ImageIcon icon = new ImageIcon(star.getImage());
            Image ScaledImage = icon.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(ScaledImage);
            //create a label for each star
            JLabel starLabel = new JLabel(scaledIcon);
            //set size and position of label to match size of image (to stop stretching or shrinking of image)
            starLabel.setBounds(150, 150, 64, 64);
            //add star labels to Panel
            SolarPanel.add(starLabel);
        }

        // return the panel
        return SolarPanel;
    }
}

