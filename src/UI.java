import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
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



    public void refreshUI() {
        planetSelector.removeAllItems();
        ArrayList<ImageIcon> planetIcons = new ArrayList<>();

        for (Planet planet : solarSystem.getPlanets()) {
            planetSelector.addItem(planet.getName());

            ImageIcon icon = new ImageIcon(planet.getImage());
            Image scaledImage = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            planetIcons.add(new ImageIcon(scaledImage));
        }
        for (Star star : solarSystem.getStars()) {
            planetSelector.addItem(star.getName());

            ImageIcon icon = new ImageIcon(star.getImage());
            Image scaledImage = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            planetIcons.add(new ImageIcon(scaledImage));
        }

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

        planetSelector.revalidate();
        planetSelector.repaint();
        window.revalidate();
        window.repaint();
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
    public JButton ButtonCreator(Color colour,String imagePath, int xPos, int yPos, int width, int height, String text) {
        JButton button = new JButton();
        if(text != null) {
            button.setText("<html><center>" + text.replace("\n", "<br>") + "</center></html>");
        }
        if (colour != null) {
            button.setBackground(colour);
        }

        if (imagePath != null) {
            ImageIcon icon = new ImageIcon(imagePath);
            Image img = icon.getImage();
            Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            button.setIcon(new ImageIcon(scaledImg));

            button.setBorderPainted(false);
            button.setFocusPainted(false);
            button.setContentAreaFilled(false);
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
        JPanel sidePanel = new JPanel(new BorderLayout());
        sidePanel.setPreferredSize(new Dimension(200, window.getHeight()));

        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));

        JPanel speedPanel = new JPanel();
        speedPanel.setLayout(new BoxLayout(speedPanel, BoxLayout.X_AXIS));
        speedPanel.add(Box.createHorizontalGlue());
        JButton normalSpeedButton = ButtonCreator(null, "src/images/playBTN.png", 0, 0, 30, 30, null);
        normalSpeedButton.addActionListener(e -> {

            Main.deltaT = 10000;
            Main.simPaused = false;

        });

        JButton speedUpBTN = ButtonCreator(null, "src/images/speedUpBTN.png", 0, 0, 30, 30, null);
        speedUpBTN.addActionListener(e -> {
            if (Main.deltaT + 10000 <= 1000000) {
                Main.deltaT += 10000;
            }
            Main.simPaused = false;
        });

        JButton slowDownBTN = ButtonCreator(null, "src/images/slowDownBTN.png", 0, 0, 30, 30, null);
        slowDownBTN.addActionListener(e -> {
            if (Main.deltaT - 1000 >= 1) {
                Main.deltaT -= 1000;
            }
            Main.simPaused = false;
        });

        JButton pauseBTN = ButtonCreator(null, "src/images/pauseBTN.png", 0, 0, 30, 30, null);
        pauseBTN.addActionListener(e -> {
            Main.simPaused = true;
        });

        speedPanel.add(slowDownBTN);
        speedPanel.add(Box.createVerticalStrut(5));
        speedPanel.add(pauseBTN);
        speedPanel.add(normalSpeedButton);
        speedPanel.add(Box.createVerticalStrut(5));

        speedPanel.add(normalSpeedButton);

        speedPanel.add(Box.createVerticalStrut(5));
        speedPanel.add(speedUpBTN);
        speedPanel.add(Box.createHorizontalGlue()); // To push buttons to the left

        // Create the speed slider
        JSlider speedSlider = new JSlider(JSlider.HORIZONTAL, 100, 1000000, 100000);
        speedSlider.addChangeListener(e -> {
            Main.deltaT = speedSlider.getValue();
        });

        // Create the "Create Planet" button
        JButton createPlanetBTN = ButtonCreator(null, null, 0, 0, 150, 50, "Create Planet");
        createPlanetBTN.addActionListener(e -> {
            OpenPlanetCreator();
            refreshUI();
        });
        JButton deletePlanetBTN = ButtonCreator(null, null, 0, 0, 150, 50, "Delete focussed Planet");
        deletePlanetBTN.addActionListener(e -> {
            if (planetSelector.getSelectedIndex() <= Main.solarSystem.getPlanets().size()) {
               Main.solarSystem.removePlanet( Main.solarSystem.getPlanets().get(planetSelector.getSelectedIndex()));;
               refreshUI();




            }else{
                Main.solarSystem.removeStar( Main.solarSystem.getStars().get(0));;
                refreshUI();
            }
        });
        JPanel sidePlanetPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        sidePlanetPanel.add(createPlanetBTN);
        sidePlanetPanel.add(deletePlanetBTN);

        // Add components to the side panel
        sidePanel.add(Box.createVerticalStrut(20));
        sidePanel.add(speedPanel);
        sidePanel.add(Box.createVerticalStrut(20));
        sidePanel.add(speedSlider);
        sidePanel.add(Box.createVerticalGlue());
        sidePanel.add(sidePlanetPanel);




        // Set the border
        sidePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        return sidePanel;
    }

    public JComboBox createPlanetCombo() {
        ArrayList<ImageIcon> planetIcons = new ArrayList<>();
        for (Planet x : solarSystem.getPlanets()) {
            planetSelector.addItem(x.getName());
            ImageIcon icon = new ImageIcon(x.getImage());
            Image ScaledImage = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);

            planetIcons.add(new ImageIcon(ScaledImage));

        }
        for (Star x : solarSystem.getStars()) {
            planetSelector.addItem(x.getName());
            ImageIcon icon = new ImageIcon(x.getImage());
            Image ScaledImage = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);

            planetIcons.add(new ImageIcon(ScaledImage));

        }


        planetSelector.setPreferredSize(new Dimension(120, 30)); // Set preferred size
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

        JButton focusPlanetBTN = ButtonCreator(null, null,0, 0, 110, 50, "Focus on \n selected Planet");
        focusPlanetBTN.addActionListener(e -> {
            solarPanelClass.focusOnPlanet(planetSelector.getSelectedIndex());
        });

        JButton pauseBTN = ButtonCreator(null, null,0, 0, 100, 100, "Pause");
        pauseBTN.addActionListener(e -> {
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



    public void mousePlacement(Planet planet) {
        Main.simPaused = true;
        JPanel panel = solarPanel;

        MouseMotionListener moveListener = new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                double simX = ((e.getX() + 510 - panel.getWidth() / 2.0) - solarPanelClass.getOffsetX()) * 5e8 / solarPanelClass.getZoomScale();
                double simY = ((e.getY() + 250 - panel.getHeight() / 2.0) - solarPanelClass.getOffsetY()) * 5e8 / solarPanelClass.getZoomScale();

                planet.setXPosition(simX);
                planet.setYPosition(simY);
                solarPanelClass.setPreviewPlanet(planet);
                panel.repaint();
            }
        };

        MouseAdapter clickListener = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    panel.removeMouseMotionListener(this);
                    panel.removeMouseListener(this);
                    panel.removeMouseMotionListener(moveListener);

                    solarPanelClass.clearPreviewPlanet();
                    Main.solarSystem.addPlanet(planet);
                    panel.repaint();
                }
            }
        };

        panel.addMouseMotionListener(moveListener);
        panel.addMouseListener(clickListener);
    }


}

