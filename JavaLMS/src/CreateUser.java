import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CreateUser {

    private JPanel Main_panel;
    private JButton createUserButton;
    private JButton createCourseButton;
    private JButton createTimeTableButton;
    private JButton createNoticeButton;
    private JTextField textField1; // Username
    private JTextField textField2; // Date of Birth
    private JTextField textField3; // Phone Number
    private JTextField textField4; // First Name
    private JTextField textField5; // Email
    private JTextField textField6; // Last Name
    private JComboBox comboBox1;  // User Type
    private JButton button1;      // Submit Button
    private JButton signOutButton;
    private JPanel crtcpanel;     // Create Course panel
    private JPanel crttpanel;     // Create Time Table panel
    private JPanel crtupanel;     // Create User panel
    private JPanel crtnpanel;     // Create Notice panel
    private JPanel cardContainer;
    private JTextField textField7;
    private JTextField textField8;
    private JTextField textField9;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JButton button2;
    private JComboBox comboBox4;
    private JComboBox comboBox5;
    private JTable table1;
    private JComboBox comboBox6;
    private JFrame frame;

    public CreateUser() {
        frame = new JFrame("Create User");
        frame.setContentPane(Main_panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1400, 750);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);

        // Initialize card container with panels
        CardLayout cardLayout = new CardLayout();
        cardContainer.setLayout(cardLayout);

        // Add panels to card container with correct names from .form file
        cardContainer.add(crtupanel, "Card1");  // Create User
        cardContainer.add(crtcpanel, "Card2");  // Create Course
        cardContainer.add(crttpanel, "Card3");  // Create Time Table
        cardContainer.add(crtnpanel, "Card4");  // Create Notice

        // Button listeners with correct card names
        createUserButton.addActionListener(e -> cardLayout.show(cardContainer, "Card1"));
        createCourseButton.addActionListener(e -> cardLayout.show(cardContainer, "Card2"));
        createTimeTableButton.addActionListener(e -> cardLayout.show(cardContainer, "Card3"));
        createNoticeButton.addActionListener(e -> cardLayout.show(cardContainer, "Card4"));

        // Sign Out button
        signOutButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(frame,
                    "Are you sure you want to sign out?",
                    "Confirm Sign Out",
                    JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                frame.dispose();
            }
        });


        // Show Create User panel by default
        cardLayout.show(cardContainer, "Card1");
    }
}