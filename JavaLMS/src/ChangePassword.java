import Database.DbConnector;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ChangePassword {
    private JPanel Main_panel;
    private JTextField usernameField;
    private JButton button1;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JTextField new_password;
    private JTextField confirm_password;
    public String table;
    public String username;

    ChangePassword(){
        JFrame cframe = new JFrame("Change Password");
        cframe.add(Main_panel);
        cframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cframe.setSize(500, 260);
        cframe.setLocationRelativeTo(null);
        cframe.setVisible(true);
        cframe.setResizable(false);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changedPassword();
            }
        });
    }

    public void passValue(String username, String table){
        usernameField.setText(username);
        this.table = table;
        this.username = username;
    }

    public void changedPassword(){
        String new_pass = passwordField1.getText();
        String confirm_pass = passwordField2.getText();
        String regx = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,16}$";

        Connection conn = DbConnector.getConnection();
        if (conn == null) {return;}

        if (new_pass.equals("") && confirm_pass.equals("")) {
            JOptionPane.showMessageDialog(null, "Please enter valid password!");
            return;
        } else if (new_pass.matches(regx) && confirm_pass.equals(new_pass)) {
            String updatePass = "UPDATE " + table + " SET password = ? WHERE username = ?";

            try(PreparedStatement stmt = conn.prepareStatement(updatePass)){
                stmt.setString(1, new_pass);
                stmt.setString(2, username);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Password changed successfully!");
                SwingUtilities.getWindowAncestor(Main_panel).dispose();
                LoginForm loginForm = new LoginForm();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
