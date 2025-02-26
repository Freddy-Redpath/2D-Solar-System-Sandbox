
import javax.swing.*;
import java.awt.*;

public class UI extends JPanel {
   JButton changeColour = new JButton("Change Colour");

   public void Initialisewindow() {

      this.setBackground(Color.BLACK);
      JFrame window = new JFrame("UI Dev Test");
      window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      window.setSize(1280, 720);
      window.setLayout(null);
      ButtonCreator();
      window.add(changeColour);
      window.setLocationRelativeTo(null);
      window.setVisible(true);

   }
   public void ButtonCreator(){

      changeColour.setBackground(Color.GREEN);
      changeColour.setForeground( new Color(153,153,252));
      changeColour.setBounds(300, 300, 200, 50);
      changeColour.addActionListener(e -> {
         System.out.println("Change Colour");
      });

   }
   }
