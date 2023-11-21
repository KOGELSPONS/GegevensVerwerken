import javax.swing.*;
import java.awt.*;
import java.time.Duration;
import java.time.Instant;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        // Open the login frame
        SwingUtilities.invokeLater(Login::new);
    }
}

class Login extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private int loginAttempts = 0;
    private Instant lastLoginAttemptTime = Instant.now();
    private final int MAX_LOGIN_ATTEMPTS = 3;
    private final int COOLDOWN_DURATION_SECONDS = 60;

    public Login() {
        super("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 300);
        setLocationRelativeTo(null);

        // Set icon
        ImageIcon icon = new ImageIcon("src/img/Icon.png");
        setIconImage(icon.getImage());

        LoginPanel();

        setVisible(true);
    }

    private void LoginPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));
        panel.setBackground(new Color(248, 248, 248));

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        JButton loginButton = new JButton("Login");

        loginButton.addActionListener(e -> validateLogin());

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel()); // Empty label for spacing
        panel.add(loginButton);

        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        add(panel);
    }

    private void validateLogin() {
        String username = usernameField.getText();
        char[] password = passwordField.getPassword();
        Instant currentTime = Instant.now();

        if (!username.isEmpty() && new String(password).equals("admin")) {
            // Open the MainApplication frame
            SwingUtilities.invokeLater(Import::new);
            // Close the login frame
            dispose();
        } else {
            loginAttempts++;
            lastLoginAttemptTime = Instant.now();

            if (loginAttempts > MAX_LOGIN_ATTEMPTS) {
                if (Duration.between(lastLoginAttemptTime, currentTime).getSeconds() < COOLDOWN_DURATION_SECONDS) {
                    JOptionPane.showMessageDialog(this, "Too many unsuccessful login attempts. Please try again later.");
                    System.exit(0); // Close the program
                } else {
                    loginAttempts = 0;
                    lastLoginAttemptTime = currentTime;
                }
            }
            JOptionPane.showMessageDialog(this, "(" + loginAttempts + ")" + " Invalid username or password. Please try again.");
            // You can add more sophisticated validation logic here.
        }
    }
}

class Import {
    public static String filepath;
    public Import() {
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jfc.setDialogTitle("Select an CSV");
        jfc.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV Files", "csv");
        jfc.addChoosableFileFilter(filter);

        int returnValue = jfc.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            filepath = jfc.getSelectedFile().getPath();
            SwingUtilities.invokeLater(Info::new);
        }
    }
}


class Info extends JFrame {
    final String filepath = Import.filepath;
    final boolean doneloading = true;
    public Info() {
        super("Main Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);
        setLocationRelativeTo(null);

        // Set icon
        ImageIcon icon = new ImageIcon("src/img/Icon.png");
        setIconImage(icon.getImage());

        ProcessData();
        //InfoPanel();

        setVisible(true);
    }

    private void ProcessData(){
        JPanel panel = new JPanel();
        panel.setBackground(new Color(248, 248, 248));

        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        ImageIcon loading = new ImageIcon("src/img/loading.gif");
        panel.add(new JLabel(loading, JLabel.CENTER));

        add(panel);

        File file= new File(filepath);

        // this gives you a 2-dimensional array of strings
        List<List<String>> lines = new ArrayList<>();
        Scanner inputStream;

        try{
            inputStream = new Scanner(file);

            while(inputStream.hasNext()){
                String line= inputStream.next();
                String[] values = line.split(";");
                // this adds the currently parsed line to the 2-dimensional string array
                lines.add(Arrays.asList(values));
            }

            inputStream.close();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println(lines);


    }

    private void InfoPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));
        panel.setBackground(new Color(248, 248, 248));

        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(panel);
    }
}