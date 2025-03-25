/*

import javax.swing.*;
import java.awt.*;
import java.text.AttributedString;
import java.util.ArrayList;


public class SolarGraphics {
    private double zoomLevel = 1.0;



    public JPanel updateBodies(ArrayList<?> bodyArray, JPanel SolarPanel) {

        for (Object o : bodyArray) {
            ImageIcon icon = null;
            if (o instanceof Planet body) {
                icon = new ImageIcon(body.getImage());
                Image ScaledImage = icon.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(ScaledImage);
                //create a label for each star
                JLabel planetLabel = new JLabel(scaledIcon);
                //set size and position of label to match size of image (to stop stretching or shrinking of image)
                planetLabel.setBounds((int) body.getXPosition(), (int) body.getYPosition(), 64, 64);
                //add star labels to Panel
                SolarPanel.add(planetLabel);

            } else if (o instanceof Star body) {
                icon = new ImageIcon(body.getImage());
                Image ScaledImage = icon.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(ScaledImage);
                //create a label for each star
                JLabel starLabel = new JLabel(scaledIcon);
                //set size and position of label to match size of image (to stop stretching or shrinking of image)
                starLabel.setBounds((int) body.getXPosition(), (int) body.getYPosition(), 64, 64);
                //add star labels to Panel
                SolarPanel.add(starLabel);


            } else if (o instanceof Asteroid) {
                Asteroid body = (Asteroid) o;
                // icon = new ImageIcon(body.getImage());

            } else if (o instanceof BlackHole) {
                BlackHole body = (BlackHole) o;
                // icon = new ImageIcon(body.getImage());

            }


        }
        SolarPanel.revalidate(); // Revalidate the panel
        SolarPanel.repaint(); // Repaint the panel
        return SolarPanel;

        }
    }

*/



