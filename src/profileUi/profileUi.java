package profileUi;

import Database.DbConnector;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class profileUi {
    private JPanel main;
    private JButton updateProfilePictureButton;
    private JTextField emailEnterFielf;
    private JButton saveChangesButton;
    private JTextField mnoEnterField;
    private JLabel nameField;
    private JLabel emailField;
    private JLabel mnoField;
    private JFrame frame;

    private String Username;
    private String fullname;

    public profileUi(String stuUsername, String fullname) {
        this.Username = stuUsername;
        this.fullname = fullname;
        System.out.println(stuUsername + " " + fullname);
        showProfileDetails();
        frame = new JFrame("Profile");
        frame.add(main);
        frame.setSize(1100, 750);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        saveChangesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateProfileDetails();
            }
        });
    }

    private String determineTableName(String username) {
        if (username != null && username.length() >= 2) {
            String prefix = username.substring(0, 2).toLowerCase(); // Convert to lowercase for case-insensitive comparison
            if (prefix.equals("lc")) {
                return "lecturer";
            } else if (prefix.equals("to")) {
                return "technician";
            }
        }
        return null; // Return null if no valid prefix is found
    }

    public void showProfileDetails() {
        DbConnector db = new DbConnector();
        Connection conn = db.getConnection();
        if (conn == null) {
            return;
        }

        String tablename = determineTableName(Username); // Use the common method for table name determination
        if (tablename == null) {
            JOptionPane.showMessageDialog(frame, "Invalid username prefix. Unable to determine table.", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Exit if the table name is invalid
        }

        String showProfileDetails_sql = "SELECT * FROM " + tablename + " WHERE username = ?";

        try (PreparedStatement stmt = conn.prepareStatement(showProfileDetails_sql)) {
            stmt.setString(1, Username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                nameField.setText(fullname);
                emailField.setText(rs.getString("email"));
                mnoField.setText(rs.getString("pnumber"));
            } else {
                JOptionPane.showMessageDialog(frame, "No records found for username: " + Username, "Warning", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "An error occurred while fetching profile details: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e);
        }
    }

    public void updateProfileDetails() {
        DbConnector db = new DbConnector();
        Connection conn = db.getConnection();
        if (conn == null) {
            return;
        }

        String tablename = determineTableName(Username); // Use the common method for table name determination
        if (tablename == null) {
            JOptionPane.showMessageDialog(frame, "Invalid username prefix. Unable to determine table.", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Exit if the table name is invalid
        }

        String email = emailEnterFielf.getText();
        String pnumber = mnoEnterField.getText();

        String updateStudentDetails_sql = "UPDATE " + tablename + " SET email = ?, pnumber = ? WHERE username = ?";

        try (PreparedStatement stmt = conn.prepareStatement(updateStudentDetails_sql)) {
            stmt.setString(1, email);
            stmt.setString(2, pnumber);
            stmt.setString(3, Username);
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(frame, "Successfully updated student profile.");
            showProfileDetails();
            fieldToNull();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "An error occurred while updating profile details: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e);
        }
    }

    public void fieldToNull() {
        emailEnterFielf.setText("");
        mnoEnterField.setText("");
    }
}