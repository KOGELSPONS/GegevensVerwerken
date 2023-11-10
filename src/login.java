import javax.swing.*;
import java.awt.*;
public class login extends JFrame{

    public static void main(String[] args) {
        JFrame frame = new login();
        frame.setTitle("Gegevens verwerkenn: Login");
        frame.setSize(300, 300);
        frame.setBounds(100, 50, 300, 300);
        frame.setBackground(new Color(255, 255, 255));
        frame.setForeground(new Color(173, 216, 230));
        Font font1 = new Font("SansSerif", Font.BOLD, 16);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // ^ Adjust code above for it to only work if password correct

    }

    public login(){
        ImageIcon usIcon = new ImageIcon("image/Icon.png");
        JButton jbt1 = new JButton(usIcon);
        JButton jbt2 = new JButton(usIcon);
        JButton jbt3 = new JButton(usIcon);

        JPanel p1 = new JPanel();
        p1.add(jbt1);

        JPanel p2 = new JPanel();
        p2.add(jbt2);

        JPanel p3 = new JPanel();
        p3.add(jbt3);

        add(p1, BorderLayout.NORTH);
        add(p2, BorderLayout.SOUTH);
        add(p3, BorderLayout.CENTER);
    }
}