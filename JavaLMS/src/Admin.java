import javax.swing.*;
import java.awt.*;

public class Admin {

    private JPanel Main_panel;
    private JButton createUserButton;
    private JButton createCourseButton;
    private JButton createTimeTableButton;
    private JButton createNoticeButton;
    private JButton signOutButton;
    private JPanel crtcpanel;     // Create Course panel
    private JPanel crttpanel;     // Create Time Table panel
    private JPanel crtupanel;     // Create User panel
    private JPanel crtnpanel;     // Create Notice panel
    private JPanel cardContainer;
    private JComboBox comboBox1;
    private JComboBox comboBox7;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JButton ucreate_btn;
    private JButton uedit_btn;
    private JButton udelete_btn;
    private JPanel ucreate_panel;
    private JPanel edit_panel;
    private JPanel delete_panel;
    private JPanel userformcard;
    private JButton submitButton;
    private JButton editButton1;
    private JButton deleteButton1;
    private JTable userinfo;
    private JPanel courseformcard;
    private JButton ccreate_btn;
    private JButton cdelete_btn;
    private JPanel ccreate_panel;
    private JPanel cdelete_panel;
    private JTextField textField7;
    private JPanel timeformcard;
    private JPanel tcreate_p;
    private JPanel tdelete_p;
    private JButton tdelete_btn;
    private JButton tcreate_btn;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JTextArea textArea1;
    private JComboBox comboBox4;
    private JPanel ndelete_panel;
    private JPanel noticeformcard;
    private JPanel ncreate_panel;
    private JButton ndelete_btn;
    private JButton ncreate_btn;
    private JFrame frame;

    public Admin() {
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

        //user form card layout
        userformcard.setLayout(cardLayout);

        //course panel card layout
        courseformcard.setLayout(cardLayout);

        //timetable panel card layout
        timeformcard.setLayout(cardLayout);

        //notice panel card layout
        noticeformcard.setLayout(cardLayout);

        // Add panels to card container with correct names from .form file
        cardContainer.add(crtupanel, "Card1");  // Create User
        cardContainer.add(crtcpanel, "Card2");  // Create Course
        cardContainer.add(crttpanel, "Card3");  // Create Time Table
        cardContainer.add(crtnpanel, "Card4");  // Create Notice

        //Add panel to the card container for userformcard
        userformcard.add(ucreate_panel, "uCard1");
        userformcard.add(edit_panel, "uCard2");
        userformcard.add(delete_panel, "uCard3");

        //Add panel to the card container for courseformcard
        courseformcard.add(ccreate_panel, "cCard1");
        courseformcard.add(cdelete_panel, "cCard2");

        //Add panel to the card container for timeformcard
        timeformcard.add(tcreate_p, "tCard1");
        timeformcard.add(tdelete_p, "tCard2");

        //Add panel to the card container for noticeformcard
        noticeformcard.add(ncreate_panel, "nCard1");
        noticeformcard.add(ndelete_panel, "nCard2");

        // Button listeners with correct card names
        createUserButton.addActionListener(e -> cardLayout.show(cardContainer, "Card1"));
        createCourseButton.addActionListener(e -> cardLayout.show(cardContainer, "Card2"));
        createTimeTableButton.addActionListener(e -> cardLayout.show(cardContainer, "Card3"));
        createNoticeButton.addActionListener(e -> cardLayout.show(cardContainer, "Card4"));

        //Button function for create, delete and edit for userformcard
        ucreate_btn.addActionListener(e -> cardLayout.show(userformcard, "uCard1"));
        uedit_btn.addActionListener(e -> cardLayout.show(userformcard, "uCard2"));
        udelete_btn.addActionListener(e -> cardLayout.show(userformcard, "uCard3"));

        //Button function for create, delete and edit for courseformcard
        ccreate_btn.addActionListener(e -> cardLayout.show(courseformcard, "cCard1"));
        cdelete_btn.addActionListener(e -> cardLayout.show(courseformcard, "cCard2"));

        //Button function for create, delete and edit for timeformcard
        tcreate_btn.addActionListener(e -> cardLayout.show(timeformcard, "tCard1"));
        tdelete_btn.addActionListener(e -> cardLayout.show(timeformcard, "tCard2"));

        //Button function for create, delete and edit for noticeformcard
        ncreate_btn.addActionListener(e -> cardLayout.show(noticeformcard, "nCard1"));
        ndelete_btn.addActionListener(e -> cardLayout.show(noticeformcard, "nCard2"));

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
        cardLayout.show(userformcard, "uCard1");
    }
}