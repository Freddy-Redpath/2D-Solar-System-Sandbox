import javax.swing.*;
import java.awt.*;

public class UI {
    SolarSystem solarSystem;
    JFrame window = new JFrame("Solar System Sandbox!");

    public UI() {
        solarSystem = new SolarSystem();
        solarSystem.addOurSolarSystem();
        // this code executes once when an instance of UI is created
    }

    // create UI to appear at start of simulation (this will be replaced i imagine by main menu at some point)
    public void initialiseUI() {
        int width = 1280; //width of window
        int height = 720; //height of window

        // Set window properties
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit program when window closed
        window.setSize(width, height); // set size of window
        window.setLayout(new BorderLayout()); // add layout for side/top panels etc.
        window.add(sidePanel(), BorderLayout.WEST);// create (sidePanel()) and display side panel on left hand side of window
        window.add(topPanel(), BorderLayout.NORTH);
        window.setLocationRelativeTo(null); // make window centre of screen
        window.setVisible(true); // make the window visible
    }

    /**
     * Creates a button with the specified properties.
     *
     * @param colour Background color of the button (null for default)
     * @param xPos X position of button, given it isn't placed in a panel
     * @param yPos Y position of button, given it isn't placed in a panel
     * @param width width of button
     * @param height height of button
     * @param text text of button
     * @return JButton instance with specified properties
     */
    public JButton ButtonCreator(Color colour, int xPos, int yPos, int width, int height, String text) {
        JButton button = new JButton(text); // Create a new button with the given text

        // if colour given, set button to that colour
        if (colour != null) {
            button.setBackground(colour);
        }

        // set size to width and height, if either are empty or 0, default dimensions are used
        if (width != 0 && height != 0) {
            button.setPreferredSize(new Dimension(width, height)); // set preffered size (automatically changes if it doesnt fit on panel/frame)
            button.setMaximumSize(new Dimension(width, height)); // maximum size to prevent resizing
            button.setMinimumSize(new Dimension(width, height)); // minimum size to prevent shrinking
        }

        return button;
    }

    /**
     * creates and returns the side panel containing buttons.
     *
     * @return JPanel with buttons and spacing
     */
    public JPanel sidePanel() {
        JPanel sidePanel = new JPanel(); // create a new panel
        sidePanel.setPreferredSize(new Dimension(200, window.getHeight())); // make panel thin, and as tall as the window
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS)); // arrange elements vertically
        JSlider speedSlider = new JSlider(JSlider.HORIZONTAL,
                0, 1000, 500);
        // box.createRigidArea adds an invisible componemnt (used as border) to stop components going off screen
        sidePanel.add(Box.createRigidArea(new Dimension(20, 20)));

        // create buttons with specified values
        JButton createPlanetBTN = ButtonCreator(null, 0, 0, 150, 100, "Create Planet");
        createPlanetBTN.addActionListener(e -> {
            System.out.println("Create Planet pressed");
        });

        JButton deletePlanetBTN = ButtonCreator(null, 0, 0, 175, 50, "Delete Planet");
        deletePlanetBTN.addActionListener(e -> {
            System.out.println("Delete planet pressed");
        });

        // add buttons to side panel with Button.add
        sidePanel.add(createPlanetBTN);
        sidePanel.add(Box.createVerticalStrut(20));// add vertical spacing between buttons
        sidePanel.add(speedSlider);
        sidePanel.add(deletePlanetBTN);
        sidePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        return sidePanel;
    }
    public JPanel topPanel() {
        JPanel topPanel = new JPanel(); // create a new panel
        topPanel.setPreferredSize(new Dimension(window.getWidth(), 125));
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        JComboBox planetSelector = new JComboBox();
        for (Planet x : solarSystem.getPlanets()) {
            planetSelector.addItem(x.getName());

        }


        planetSelector.setPreferredSize(new Dimension(120, 30)); // Set preferred size
        planetSelector.setMaximumSize(new Dimension(120, 30));
        // add buttons to side panel with Button.add
        topPanel.add(Box.createHorizontalStrut(20));
        topPanel.add(planetSelector);

        topPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        return topPanel;
    }
}
