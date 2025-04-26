import Database.DbConnector;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class student {
    private JPanel mainPanal;
    private JPanel L_student;
    private JButton updateProfileButton;
    private JButton viewGradeButton1;
    private JButton viewAttendansButton;
    private JButton viewMarksButton;
    private JButton signOutButton;
    private JButton updateProfilePictureButton;
    private JTextField emailEnterFielf;
    private JTextField mnoEnterField;
    private JButton saveChangesButton;
    private JButton viewNoticeButton1;
    private JLabel nameField;
    private JLabel emailField;
    private JLabel mnoField;
    private JLabel fullnameLabel;
    private JFrame frame;

    private String stuUsername;
    private String fullname;
    private String profileP;

    public student() {
        frame = new JFrame("Student");
        frame.add(mainPanal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1400, 750);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public student(String  stuUsername, String fullname, String profileP) {
        this();
        this.stuUsername = stuUsername;
        this.fullname = fullname;

        //set uasr name
        fullnameLabel.setText(fullname);


        showProfileDetails();

        saveChangesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateProfileDetails();
            }
        });

    }

    public void showProfileDetails() {

        DbConnector db = new DbConnector();
        Connection conn = db.getConnection();
        if (conn == null) {
            return;
        }

        String showPrpfileDetails_sql = "select * from student where username = ?";

        try(PreparedStatement stmt = conn.prepareStatement(showPrpfileDetails_sql)) {
            stmt.setString(1, stuUsername);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                nameField.setText(fullname);
                emailField.setText(rs.getString("email"));
                mnoField.setText(rs.getString("pnumber"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void updateProfileDetails() {
        String email = null;
        String pnumber = null;

        DbConnector db = new DbConnector();
        Connection conn = db.getConnection();
        if (conn == null) {
            return;
        }

        email = emailEnterFielf.getText();
        pnumber = mnoEnterField.getText();

        String updateStudentDetails_sql = "UPDATE student SET email = ?, pnumber = ? where username = ?";

        try(PreparedStatement stmt = conn.prepareStatement(updateStudentDetails_sql)) {
            stmt.setString(1, email);
            stmt.setString(2, pnumber);
            stmt.setString(3, stuUsername);
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(frame, "Successfully updated student profile picture");
            showProfileDetails();
            fieldToNull();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void updateProfilePicture() {
        DbConnector db = new DbConnector();
        Connection conn = db.getConnection();
    }

    public void fieldToNull() {
        emailEnterFielf.setText("");
        mnoEnterField.setText("");
    }
}
