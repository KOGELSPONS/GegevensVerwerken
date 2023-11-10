import javax.swing.*;
import java.awt.*;
class gui{
    public static void main(String[] args){
        JFrame frame = new JFrame("Solar");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,300);
        JButton button1 = new JButton("Enter");
        frame.getContentPane().add(button1);
        frame.setVisible(true);
        frame.setBackground(new Color(255, 255, 255));
        frame.setForeground(new Color(173, 216, 230));
        Font font1 = new Font("SansSerif", Font.BOLD, 16);


        JFrame loGin= new JFrame("Login");
        loGin.setVisible(true);
        loGin.setBounds(100,50,300,300);
        Container c=loGin.getContentPane();
        c.setLayout(null);
        loGin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPasswordField pwd=new JPasswordField();
        pwd.setBounds(100,50,100,30);


        // ^ Adjust code above for it to only work if password correct

    }
}