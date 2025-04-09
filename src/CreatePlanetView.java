import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CreatePlanetView extends JPanel {
    private Color selectedColor;

    // This class now contains both the preview area and the side panel controls
    public CreatePlanetView() {
        // Use BorderLayout so we can place the preview area and side panel separately
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(500, 300));

        // Create the preview area (a black panel with a white circle and stars)
        PreviewPanel preview = new PreviewPanel();
        add(preview, BorderLayout.CENTER);

        // Create the side panel with labels and controls
        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        sidePanel.setPreferredSize(new Dimension(200, 300));
        sidePanel.setBackground(Color.LIGHT_GRAY);
        selectedColor = Color.WHITE;
        JButton colorButton = new JButton("Select Color");

        colorButton.addActionListener(e -> {
            Color newColor = JColorChooser.showDialog(this, "Choose Planet Color", selectedColor);
            if (newColor != null) {
                selectedColor = newColor;
                colorButton.setBackground(selectedColor);
                preview.setPlanetColour(selectedColor);
            }
        });
        // Create labels and controls
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(15);
        nameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, nameField.getPreferredSize().height));

        // Create a slider for Mass (range 1-1000, initial 100)
        JLabel massLabel = new JLabel("Mass:");
        JSlider massSlider = new JSlider(JSlider.HORIZONTAL, 1, 1000, 100);
        massSlider.setMajorTickSpacing(100);
        massSlider.setMinorTickSpacing(10);
        massSlider.setPaintTicks(true);
        massSlider.setPaintLabels(false);
        massSlider.addChangeListener(e -> {
            int massValue = massSlider.getValue();

        });

        // Create a slider for Speed (range 1-1000, initial 100)
        JLabel speedLabel = new JLabel("Speed:");
        JSlider speedSlider = new JSlider(JSlider.HORIZONTAL, 1, 1000, 100);
        speedSlider.setMajorTickSpacing(100);
        speedSlider.setMinorTickSpacing(10);
        speedSlider.setPaintTicks(true);
        speedSlider.setPaintLabels(false);
        speedSlider.addChangeListener(e -> {
            int speedValue = speedSlider.getValue();
        });

        // Create a slider for Size (range 1-100, initial 10)
        JLabel sizeLabel = new JLabel("Size:");
        JSlider sizeSlider = new JSlider(JSlider.HORIZONTAL, 1, 100, 10);
        sizeSlider.setMajorTickSpacing(10);
        sizeSlider.setMinorTickSpacing(1);
        sizeSlider.setPaintTicks(true);
        sizeSlider.setPaintLabels(false);
        sizeSlider.addChangeListener(e -> {
            int value = sizeSlider.getValue();
            preview.setPlanetSize(value);
        });

        // Add components to the side panel with spacing
        sidePanel.add(Box.createRigidArea(new Dimension(20, 20)));
        sidePanel.add(nameLabel);
        sidePanel.add(nameField);
        sidePanel.add(colorButton);
        sidePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        sidePanel.add(massLabel);
        sidePanel.add(massSlider);
        sidePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        sidePanel.add(speedLabel);
        sidePanel.add(speedSlider);
        sidePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        sidePanel.add(sizeLabel);
        sidePanel.add(sizeSlider);
        sidePanel.add(Box.createVerticalGlue());

        // Add the side panel to the EAST of this panel
        add(sidePanel, BorderLayout.EAST);
    }

    // Inner class for the preview area that draws a white circle on a black background with stars
    private class PreviewPanel extends JPanel {
        private double planetSize = 10;
        private Color planetColour = Color.WHITE;
        // Area to store star positions
        private java.util.List<Point> starPlacements = new java.util.ArrayList<>();
        private boolean starsgenerated = false;

        public PreviewPanel() {
            setPreferredSize(new Dimension(300, 300));
            setBackground(Color.BLACK);
        }

        // Generate star positions only once using actual dimensions (or fallback to preferred size)
        @Override
        public void addNotify() {
            super.addNotify();
            if (!starsgenerated) {
                starsgenerated = true;
                int w = getWidth() > 0 ? getWidth() : getPreferredSize().width;
                int h = getHeight() > 0 ? getHeight() : getPreferredSize().height;
                int numStars = 65;
                for (int i = 0; i < numStars; i++) {
                    int starX = (int)(Math.random() * w);
                    int starY = (int)(Math.random() * h);
                    starPlacements.add(new Point(starX, starY));
                }
            }
        }

        public void setPlanetSize(double newSize) {
            this.planetSize = newSize;
            repaint();
        }

        public void setPlanetColour(Color color) {
            this.planetColour = color;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // Convert to Graphics2D for smoother drawing
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Draw background stars using stored positions
            g2d.setColor(Color.WHITE);
            for (Point p : starPlacements) {
                g2d.fillOval(p.x, p.y, 2, 2);
            }

            // Draw the planet (a filled circle) in the center
            g2d.setColor(planetColour);
            int centerX = getWidth() / 2;
            int centerY = getHeight() / 2;
            int d = (int) (planetSize * 2);
            g2d.fillOval(centerX - (int)planetSize, centerY - (int)planetSize, d, d);
        }
    }
}