
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;

public class UI extends JPanel {


   public void Initialisewindow() {

      JFrame window = new JFrame("UI Dev Test");
      window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      window.setSize(1280, 720);

      window.setLayout(null);
      JButton changeColour =  ButtonCreator(Color.GREEN, 300, 300, 200, 50, "HELLO woRLD");
      window.add(changeColour);
      window.setLocationRelativeTo(null);
      window.setVisible(true);

      JSlider slider = SliderCreator(0, 300, 300, 100, 300, 50);
      window.add(slider);
      window.setLocationRelativeTo(null);
      window.setVisible(true);


   }
   public JButton ButtonCreator( Color colour, int xPos, int yPos, int width, int height, String text){
      JButton button = new JButton(text);
      button.setBackground(colour);
      //changeColour.setForeground( new Color(153,153,252));
      button.setBounds(xPos, yPos, width, height);

      Color Og_color = colour;
      Color new_color = colour.darker();

      button.addMouseListener(new MouseAdapter() {

         public void mouseEntered(MouseEvent e) {
            button.setBackground(new_color);
         }
         public void mouseExited(MouseEvent e) {
            button.setBackground(Og_color);
         }

      });

      button.addActionListener(e -> {

      });
      return button;
   }
   }
   public JSlider SliderCreator(int min, int max, int xPos, int yPos, int width, int height){
      JSlider slider = new JSlider(JSlider.HORIZONTAL, min, max, (min + max) / 2);
      slider.setBounds(xPos, yPos, width, height);
      slider.setMajorTickSpacing(50);
      slider.setMinorTickSpacing(10);
      slider.setPaintTicks(true);
      slider.setPaintLabels(true);

   }