import javax.swing.*;
import java.awt.*;

public class UI {

    JFrame window = new JFrame("Solar System Sandbox!");

    public UI() {

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
        sidePanel.setPreferredSize(new Dimension(250, window.getHeight())); // make panel thin, and as tall as the window
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS)); // arrange elements vertically

        // box.createRigidArea adds an invisible componemnt (used as border) to stop components going off screen
        sidePanel.add(Box.createRigidArea(new Dimension(20, 20)));

        // create buttons with specified values
        JButton createPlanetBTN = ButtonCreator(null, 0, 0, 150, 100, "Create Planet");
        createPlanetBTN.addActionListener(e -> {
            System.out.println("Create Planet pressed");
        });

        JButton deletePlanetBTN = ButtonCreator(null, 0, 0, 150, 50, "Delete Planet");
        deletePlanetBTN.addActionListener(e -> {
            System.out.println("Delete planet pressed");
        });

        // add buttons to side panel with Button.add
        sidePanel.add(createPlanetBTN);
        sidePanel.add(Box.createVerticalStrut(20)); // add vertical spacing between buttons
        sidePanel.add(deletePlanetBTN);

        return sidePanel;
    }
}
