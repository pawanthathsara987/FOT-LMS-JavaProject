import Database.DbConnector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginForm{
    private JPanel Main_panel;
    private JPanel Loading_panel;
    private JPanel Form_panel;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton loginButton;
    private JProgressBar progressBar1;
    private JLabel loading_text;
    private JFrame frame;

    private CardLayout cardLayout;
    private Statement stmt = null;

    public String username;


    public LoginForm() {
        frame = new JFrame();
        frame.setTitle("Login Form");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(Main_panel);
        frame.setSize(500, 220);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);


        cardLayout = new CardLayout();
        Main_panel.setLayout(cardLayout);

        Main_panel.add(Loading_panel, "Loading");
        Main_panel.add(Form_panel, "Form");

        //default show card
        cardLayout.show(Main_panel, "Loading");

        startLoading();

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userLogin();
            }
        });
    }

    public void startLoading() {
        Timer timer = new Timer(200, null); // Slightly slower to make the randomness feel natural
        final int[] progress = {0};
        java.util.Random rand = new java.util.Random();

        timer.addActionListener(e -> {
            if (progress[0] < 100) {
                int step = rand.nextInt(10) + 1; // random increment between 1 and 10
                progress[0] = Math.min(100, progress[0] + step); // cap at 100

                progressBar1.setValue(progress[0]);
                loading_text.setText(progress[0] + "%");
            } else {
                timer.stop();
                cardLayout.show(Main_panel, "Form");
            }
        });

        timer.start();
    }

    private void userLogin() {
        username = textField1.getText().toUpperCase();
        String password = passwordField1.getText();

        if (username.equals("") && username.length() == 6) {
            JOptionPane.showMessageDialog(frame, "Please enter valid username!");
        }
        String id_user = username.substring(0, 2).toUpperCase();
        Connection conn = DbConnector.getConnection();
        if (conn == null) {return;}

        String table = null;

        switch (id_user) {
            case "TG":
                table = "student";
                break;
            case "LC":
                table = "lecturer";
                break;
            case "TO":
                table = "technician";
                break;
            case "AD":
                table = "admin";
                break;
            default:
                JOptionPane.showMessageDialog(frame, "Please enter valid username!");
        }

        String user_sql = "SELECT * FROM " + table + " WHERE username = ? ";
        try(PreparedStatement stmt = conn.prepareStatement(user_sql)){
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String storePassword = rs.getString("password");
                String name = rs.getString("fname") + " " + rs.getString("lname");
                if (storePassword == null) {
                    frame.dispose();
                    ChangePassword changePassword = new ChangePassword();
                    changePassword.passValue(username, table);
                } else if (storePassword.equals(password)) {
                    JOptionPane.showMessageDialog(frame, "Login Successful!");
                    switch (table) {
                        case "student":
                            frame.dispose();
//                            Student st = new Student(username);
                            break;
                        case "lecturer":
                            frame.dispose();
//                            lecturer st = new lecturer(username);
                            break;
                        case "technician":
                            frame.dispose();
//                            technician st = new technician(username);
                            break;
                        case "admin":
                            frame.dispose();
                            Admin ad = new Admin(username, name);
                            break;
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid Password!");
                }
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }


}
