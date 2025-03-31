import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class UI {


    SolarSystem solarSystem = Main.solarSystem;
    JFrame window = new JFrame("Solar System Sandbox!");
    JFrame createPlanetFrame = new JFrame("Planet Creator!");
    // SolarGraphics solareGraphics;
    JPanel solarPanel = new JPanel();
    //SolarGraphics solarGraphics = new SolarGraphics();
    SolarPanel solarPanelClass = new SolarPanel();
    JComboBox planetSelector = new JComboBox();

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
        window.add(topPanel(), BorderLayout.NORTH);
        solarPanel = solarPanelClass;

        //adding space listener for pause
        window.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    //System.out.println("space");
                    Main.simPaused = !Main.simPaused;
                }
            }
        });

        window.add(solarPanel);
        window.setLocationRelativeTo(null); // make window centre of screen
        window.setVisible(true); // make the window visible

        createPlanetFrame.setSize((int) (width / 1.25), (int) (height / 1.25)); // set size of window
        createPlanetFrame.setLayout(new BorderLayout()); // add layout for side/top panels etc.
        createPlanetFrame.setLocationRelativeTo(null); // make window centre of screen
        createPlanetFrame.setVisible(false); // make the window visible
    }

    /**
     * Creates a button with the specified properties.
     *
     * @param colour Background color of the button (null for default)
     * @param xPos   X position of button, given it isn't placed in a panel
     * @param yPos   Y position of button, given it isn't placed in a panel
     * @param width  width of button
     * @param height height of button
     * @param text   text of button
     * @return JButton instance with specified properties
     */
    public JButton ButtonCreator(Color colour, int xPos, int yPos, int width, int height, String text) {
        JButton button = new JButton("<html><center>" + text.replace("\n", "<br>") + "</center></html>");

        if (colour != null) {
            button.setBackground(colour);
        }

        if (width != 0 && height != 0) {
            button.setPreferredSize(new Dimension(width, height));
            button.setMaximumSize(new Dimension(width, height));
            button.setMinimumSize(new Dimension(width, height));
        }

        return button;
    }


    /**
     * creates and returns the side panel containing buttons.
     *
     * @return JPanel with buttons and spacing
     */
    public JPanel sidePanel() {
        JPanel sidePanel = new JPanel(new BorderLayout());// create a new panel
        sidePanel.setPreferredSize(new Dimension(200, window.getHeight())); // make panel thin, and as tall as the window
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS)); // arrange elements vertically
        JSlider speedSlider = new JSlider(JSlider.HORIZONTAL,
                0, 1000, 500);
        // box.createRigidArea adds an invisible componemnt (used as border) to stop components going off screen
        sidePanel.add(Box.createRigidArea(new Dimension(20, 20)));

        // create buttons with specified values
        JButton createPlanetBTN = ButtonCreator(null, 0, 0, 150, 50, "Create Planet");
        createPlanetBTN.addActionListener(e -> {
            System.out.println("Create Planet pressed");
            OpenPlanetCreator();
        });

        JButton deletePlanetBTN = ButtonCreator(null, 0, 0, 175, 50, "Delete Planet");
        deletePlanetBTN.addActionListener(e -> {
            System.out.println("Delete planet pressed");
        });

        // add buttons to side panel with Button.add
        sidePanel.add(Box.createVerticalStrut(20));// add vertical spacing between buttons
        sidePanel.add(speedSlider);
        sidePanel.add(deletePlanetBTN);
        sidePanel.add(Box.createVerticalGlue());


        sidePanel.add(createPlanetBTN);
        sidePanel.add(Box.createVerticalStrut(20));
        sidePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        return sidePanel;
    }

    public JComboBox createPlanetCombo() {
        ArrayList<ImageIcon> planetIcons = new ArrayList<>();
        for (CelestialBody x : solarSystem.getCelestialBodies()) {
            planetSelector.addItem(x.getName());
            ImageIcon icon = new ImageIcon(x.getImage());
            Image ScaledImage = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);

            planetIcons.add(new ImageIcon(ScaledImage));

        }


        planetSelector.setPreferredSize(new Dimension(120, 30));
        planetSelector.setMaximumSize(new Dimension(150, 30));

        planetSelector.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                JLabel planetLabel = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                planetLabel.setHorizontalAlignment(SwingConstants.LEFT);
                if (index >= 0 && index < planetIcons.size()) {
                    planetLabel.setIcon(planetIcons.get(index));
                }

                return planetLabel;
            }
        });
        return planetSelector;
    }


    public JPanel topPanel() {
        JPanel topPanel = new JPanel(); // create a new panel
        topPanel.setPreferredSize(new Dimension(window.getWidth(), 125));
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        JLabel dropDownLabel = new JLabel("Select Planet:");
        JComboBox planetSelector = createPlanetCombo();

        JButton focusPlanetBTN = ButtonCreator(null, 0, 0, 110, 50, "Focus on \n selected Planet");
        focusPlanetBTN.addActionListener(e -> {
            System.out.println("focusPlanetBTN pressed");
            solarPanelClass.focusOnPlanet(planetSelector.getSelectedIndex());
            System.out.println("focus on: " + planetSelector.getSelectedItem());
        });

        JButton pauseBTN = ButtonCreator(null, 0, 0, 100, 100, "Pause");
        pauseBTN.addActionListener(e -> {
            System.out.println("Pause pressed");
            Main.simPaused = !Main.simPaused;
        });

        topPanel.add(Box.createRigidArea(new Dimension(20, 20)));

        topPanel.add(dropDownLabel);
        topPanel.add(Box.createHorizontalStrut(10));
        topPanel.add(planetSelector);
        topPanel.add(Box.createHorizontalStrut(10));

        topPanel.add(focusPlanetBTN);
        topPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        topPanel.add(pauseBTN);

        return topPanel;
    }

    public void OpenPlanetCreator() {
        createPlanetFrame.getContentPane().removeAll();
        createPlanetFrame.add(new CreatePlanetView(), BorderLayout.CENTER);
        createPlanetFrame.pack();
        createPlanetFrame.setLocationRelativeTo(null);
        createPlanetFrame.setVisible(true);
    }


}
