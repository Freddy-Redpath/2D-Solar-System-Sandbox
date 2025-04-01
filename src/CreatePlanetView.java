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

        // Create the preview area (a black panel with a white circle)
        PreviewPanel preview = new PreviewPanel();
        add(preview, BorderLayout.CENTER);

        // Create the side panel with labels and text fields
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
        // Create labels and text fields
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(15);
        nameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, nameField.getPreferredSize().height));

        JLabel massLabel = new JLabel("Mass:");
        JTextField massField = new JTextField(15);
        massField.setMaximumSize(new Dimension(Integer.MAX_VALUE, massField.getPreferredSize().height));

        JLabel densityLabel = new JLabel("Density:");
        JTextField densityField = new JTextField(15);
        densityField.setMaximumSize(new Dimension(Integer.MAX_VALUE, densityField.getPreferredSize().height));

        JLabel sizeLabel = new JLabel("Size:");
        JTextField sizeField = new JTextField("10", 15);
        sizeField.setMaximumSize(new Dimension(Integer.MAX_VALUE, sizeField.getPreferredSize().height));

        // When the user enters a value, update the preview panel's planet size
        sizeField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double newSize = Double.parseDouble(sizeField.getText());
                    preview.setPlanetSize(newSize);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(CreatePlanetView.this, "Invalid size value.");
                }
            }
        });

        // Add components to the side panel with spacing
        sidePanel.add(Box.createRigidArea(new Dimension(20, 20)));
        sidePanel.add(nameLabel);
        sidePanel.add(nameField);
        sidePanel.add(colorButton);
        sidePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        sidePanel.add(massLabel);
        sidePanel.add(massField);
        sidePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        sidePanel.add(densityLabel);
        sidePanel.add(densityField);
        sidePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        sidePanel.add(sizeLabel);
        sidePanel.add(sizeField);
        sidePanel.add(Box.createVerticalGlue());

        // Add the side panel to the EAST of this panel
        add(sidePanel, BorderLayout.EAST);
    }

    // Inner class for the preview area that draws a white circle on a black background
    private class PreviewPanel extends JPanel {
        private double planetSize = 10;
        private Color planetColour = Color.WHITE;

        public PreviewPanel() {
            setPreferredSize(new Dimension(300, 300));
            setBackground(Color.BLACK);
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
            // Convert to Graphics2D for better drawing
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            // Set color to white
            g2d.setColor(planetColour);
            // Calculate center of the panel
            int centerX = getWidth() / 2;
            int centerY = getHeight() / 2;
            int d = (int) (planetSize * 2);
            // Draw a filled circle with center at (centerX, centerY)
            g2d.fillOval(centerX - (int) planetSize, centerY - (int) planetSize, d, d);
        }
    }
}
