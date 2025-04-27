package Student;

import Database.DbConnector;
import StuNotice.StuNotice;
import StuViewMarks.StuViewMarks;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Student {
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
    private JLabel profileImage;
    private JFrame frame;

    private String stuUsername;
    private String fullname;
    private String profileP;

    public Student() {
        frame = new JFrame("Student");
        frame.add(mainPanal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1400, 750);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == updateProfileButton) {
//                    new Student(stuUsername, fullname, profileP);
                } else if (e.getSource() == viewGradeButton1) {

                } else if (e.getSource() == viewAttendansButton) {

                } else if (e.getSource() == viewMarksButton) {
                    new StuViewMarks();
                } else if (e.getSource() == viewNoticeButton1) {
                    new StuNotice();
                }
            }
        };
        updateProfileButton.addActionListener(listener);
        viewGradeButton1.addActionListener(listener);
        viewAttendansButton.addActionListener(listener);
        viewMarksButton.addActionListener(listener);
        viewNoticeButton1.addActionListener(listener);
    }

    public Student(String  stuUsername, String fullname, String profileP) {
        this();
        this.stuUsername = stuUsername;
        this.fullname = fullname;

        //set uasr name
        fullnameLabel.setText(fullname);
        showProfileDetails();
        String basepath = String.format("%s%s%s%s%s", "Resources", "\\", "ProfileImage", "\\", "Student\\");

        // Resize and set the profile picture
        try {
            File imageFile = new File(basepath + profileP);
            if (!imageFile.exists()) {
                throw new IOException("Image file not found: " + imageFile.getAbsolutePath());
            }

            // Load and resize the image
            BufferedImage originalImage = ImageIO.read(imageFile);
            BufferedImage resizedImage = resizeImage(originalImage, 100, 100);

            // Clip to circular shape
            BufferedImage roundImage = makeRoundImage(resizedImage);
            profileImage.setIcon(new ImageIcon(roundImage));

            // Your original result check
            String result = profileP != null ? profileP : "nothing";
            System.out.println("Profile Picture: " + result);

        } catch (IOException e) {
            System.err.println("Error loading image: " + e.getMessage());
            profileImage.setText("No Image");
        }

        saveChangesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateProfileDetails();
            }
        });

        // Sign Out button
        signOutButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(frame,
                    "Are you sure you want to sign out?",
                    "Confirm Sign Out",
                    JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                frame.dispose();
//                new LoginForm();
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

    //---------------------------------------------------------------Profile picture------------------------------------------------------------------------------
    static class RoundImageLabel extends JLabel {
        private BufferedImage image;
        private final int width;
        private final int height;

        public RoundImageLabel(int width, int height) {
            this.width = width;
            this.height = height;
            setPreferredSize(new Dimension(width, height));
        }

        public void setImage(BufferedImage image) {
            this.image = image;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (image != null) {
                Graphics2D g2d = (Graphics2D) g.create();
                // Enable anti-aliasing for smooth edges
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Clip to a circle
                g2d.setClip(new Ellipse2D.Float(0, 0, width, height));

                // Draw the image
                g2d.drawImage(image, 0, 0, width, height, this);

                // Optional: Draw a border
                g2d.setClip(null); // Reset clip for border
                g2d.setColor(Color.GRAY);
                g2d.setStroke(new BasicStroke(2));
                g2d.drawOval(0, 0, width - 1, height - 1);

                g2d.dispose();
            }
        }
    }

    // Resize image to target dimensions
    private static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resizedImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        g2d.dispose();
        return resizedImage;
    }

    // Clip image to circular shape
    private static BufferedImage makeRoundImage(BufferedImage image) {
        int size = Math.min(image.getWidth(), image.getHeight());
        BufferedImage roundImage = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = roundImage.createGraphics();

        // Enable anti-aliasing
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Clip to a circle
        g2d.setClip(new Ellipse2D.Float(0, 0, size, size));

        // Draw the image
        g2d.drawImage(image, 0, 0, size, size, null);
        g2d.dispose();

        return roundImage;
    }
}
