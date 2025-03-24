import javax.swing.*;
import java.awt.*;
import java.text.AttributedString;
import java.util.ArrayList;


public class SolarGraphics {
    private double zoomLevel = 1.0;

    public JPanel SolarPanelCreator(SolarSystem solarSystem) {
        // Establishes the JPanel, and establishes the background + layout
        JPanel SolarPanel = new JPanel();
        SolarPanel.setLayout(null);
        SolarPanel.setBackground(Color.black);
        // Loops through each planet in the Solar System, setting the image




        // return the panel
        return SolarPanel;
    }

    public JPanel updateBodies(SolarSystem solarSystem, JPanel SolarPanel) {
        ImageIcon icon = null;
        for (Planet planet : solarSystem.getPlanets()) {
            icon = new ImageIcon(planet.getImage());
            Image ScaledImage = icon.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(ScaledImage);
            //create a label for each star
            JLabel planetLabel = new JLabel(scaledIcon);
            //set size and position of label to match size of image (to stop stretching or shrinking of image)
            planetLabel.setBounds((int) planet.getXPosition(), (int) planet.getYPosition(), 64, 64);
            //add star labels to Panel
            SolarPanel.add(planetLabel);

        }
        for (Star star : solarSystem.getStars()) {
            icon = new ImageIcon(star.getImage());
            Image ScaledImage = icon.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(ScaledImage);
            //create a label for each star
            JLabel starLabel = new JLabel(scaledIcon);
            //set size and position of label to match size of image (to stop stretching or shrinking of image)
            starLabel.setBounds((int) star.getXPosition(), (int) star.getYPosition(), 64, 64);
            //add star labels to Panel
            SolarPanel.add(starLabel);
        }
        return SolarPanel;
    }
}





