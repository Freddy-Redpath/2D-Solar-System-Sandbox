
import javax.swing.*;
import java.awt.*;

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

   }
   public JButton ButtonCreator( Color colour, int xPos, int yPos, int width, int height, String text){
      JButton button = new JButton(text);
      button.setBackground(colour);
      //changeColour.setForeground( new Color(153,153,252));
      button.setBounds(xPos, yPos, width, height);
      button.addActionListener(e -> {

      });
      return button;
   }
   }
