import Database.DbConnector;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class student {
    private JPanel mainPanal;
    private JPanel L_student;
    private JButton updateProfileButton;
    private JButton courseDetailsButton;
    private JButton viewAttendanceButton;
    private JButton timeTableButton;
    private JButton signOutButton;
    private JButton updateProfilePictureBtn;
    private JTextField usemail;
    private JTextField uscnum;
    private JButton saveChangesButton;
    private JButton noticeButton;
    private JPanel sideBar;
    private JLabel userTitle;
    private JButton viewGradeBtn;
    private JButton addMedicalBtn;
    private JLabel nameField;
    private JLabel mailFeaild;
    private JLabel contactField;
    private JLabel dobField;
    private JLabel usernameField;
    private JLabel propic;

    private String stuUsername;
    private String name;



    public student(String stuUsername, String name) {
        this.stuUsername = stuUsername;
        this.name = name;

        JFrame frame = new JFrame("Student");
        frame.add(mainPanal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1400,750);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        //call the showProfileDetails
        showProfileDetails();


        saveChangesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == updateProfileButton) {
                    student stu = new student("tg1365","Supun Sandaruwan");
                } else if (e.getSource() == viewAttendanceButton) {
//                    viewAttendance vt = new viewAttendance();
                } else if (e.getSource() == timeTableButton) {
//                    TimeTable tb = new TimeTable();
                } else if (e.getSource() == signOutButton) {

                } else if (e.getSource() == noticeButton) {
//                    viewNotice vn = new viewNotice();
                } else if (e.getSource() == addMedicalBtn) {
//                    addMedical am = new addMedical();
                }
            }
        };
        signOutButton.addActionListener(listener);
        courseDetailsButton.addActionListener(listener);
        viewAttendanceButton.addActionListener(listener);
        timeTableButton.addActionListener(listener);
        noticeButton.addActionListener(listener);
        viewGradeBtn.addActionListener(listener);
        addMedicalBtn.addActionListener(listener);
        saveChangesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setUpdateProfile();
            }
        });
        updateProfilePictureBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateProfilePicture();
            }
        });
    }

    public void showProfileDetails() {
        DbConnector dbc = new DbConnector();
        Connection conn = dbc.getConnection();

        String sql = "select * from student  where username = ?";

        try(PreparedStatement stmt = conn.prepareStatement(sql)){
           stmt.setString(1, stuUsername);
           ResultSet rs = stmt.executeQuery();

           nameField.setText(name);
           if (rs.next()){

               dobField.setText(rs.getString("dob"));
               contactField.setText(rs.getString("pnumber"));
               mailFeaild.setText(rs.getString("email"));

               String imagePath = rs.getString("ppicture");
               if (imagePath != null && !imagePath.isEmpty()) {
                   ImageIcon icon = new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH));
                   propic.setIcon(icon);
               } else {
                   propic.setIcon(null); // Optional: set a default avatar
               }


           }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setUpdateProfile(){
        String email = null;
        String pno = null;

        email = usemail.getText();
        pno = uscnum.getText();

        String update_student = "update student set email = ?, pnumber = ? where username = ?";

        DbConnector dbc = new DbConnector();
        Connection conn = dbc.getConnection();

        try(PreparedStatement stmt1 = conn.prepareStatement(update_student)){
            stmt1.setString(1, email);
            stmt1.setString(2, pno);
            stmt1.setString(3, stuUsername);


            int rowsUpdated = stmt1.executeUpdate();


            if (rowsUpdated > 0) {
                showProfileDetails();
                JOptionPane.showMessageDialog(null, "Profile updated successfully!");

                usemail.setText("");
                uscnum.setText("");





            } else {
                JOptionPane.showMessageDialog(null, "No rows updated. Please check your input.");
            }


        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }


    public void updateProfilePicture() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select Profile Picture");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String imagePath = selectedFile.getAbsolutePath();

            // 1. Save to database
            try {
                DbConnector dbc = new DbConnector();
                Connection conn = dbc.getConnection();

                String updatePic = "UPDATE student SET ppicture = ? WHERE username = ?";
                
                try (PreparedStatement stmt = conn.prepareStatement(updatePic)) {
                    stmt.setString(1, imagePath);
                    stmt.setString(2, stuUsername);
                    stmt.executeUpdate();
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error saving image path: " + e.getMessage());
            }

            // 2. Show in UI
//            ImageIcon icon = new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
//            propic.setIcon(icon);
        }
    }
}