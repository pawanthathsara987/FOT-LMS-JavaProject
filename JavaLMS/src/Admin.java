import org.jdatepicker.JDatePicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

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
    private JComboBox crtusertype;
    private JComboBox crtleveltype;
    private JTextField emailbox2;
    private JTextField pnobox2;
    private JTextField lnamebox2;
    private JTextField fnamebox2;
    private JTextField unamebox2;
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
    private JLabel crtlevellabel;
    private JComboBox crtdeptype;
    private JLabel crtdeplabel;
    private JTextField unamebox1;
    private JTextField emailbox1;
    private JTextField fnamebox1;
    private JTextField lnamebox1;
    private JDatePicker dobbox1;
    private JTextField pnobox1;
    private JComboBox depbox1;
    private JComboBox edtusertype;
    private JComboBox edtleveltype;
    private JLabel edtlevellabel;
    private JLabel deplabel1;
    private JDatePicker dobbox2;
    private JFrame frame;

    public String username;

    public Admin(String username) {
        this.username = username;
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
                LoginForm lf = new LoginForm();
            }
        });


        // Show Create User panel by default
        cardLayout.show(cardContainer, "Card1");
        cardLayout.show(userformcard, "uCard1");

        crtusertype.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showCreateUserNecassarry();
            }
        });

        editButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createUser();
            }
        });
    }

    public void showCreateUserNecassarry() {
        String userType = crtusertype.getSelectedItem().toString();
        if (userType.equals("Lecturer") || userType.equals("Technical Officer") || userType.equals("Admin")) {
            crtleveltype.setVisible(false);
            crtlevellabel.setVisible(false);
        } else {
            crtleveltype.setVisible(true);
            crtlevellabel.setVisible(true);
        }

        if (userType.equals("Admin")) {
            crtdeptype.setVisible(false);
            crtdeplabel.setVisible(false);
        } else {
            crtdeptype.setVisible(true);
            crtdeplabel.setVisible(true);
        }
    }

    public void showEditUserNecassarry() {
        String userType = edtusertype.getSelectedItem().toString();
        if (userType.equals("Lecturer") || userType.equals("Technical Officer") || userType.equals("Admin")) {
            edtleveltype.setVisible(false);
            edtlevellabel.setVisible(false);
        } else {
            edtleveltype.setVisible(true);
            edtlevellabel.setVisible(true);
        }

        if (userType.equals("Admin")) {
            depbox1.setVisible(false);
            depbox1.setVisible(false);
        } else {
            depbox1.setVisible(true);
            depbox1.setVisible(true);
        }
    }

    public void createUser() {
        String uname = unamebox2.getText();
        String fname = fnamebox2.getText();
        String lname = lnamebox2.getText();
        String email = emailbox2.getText();
        String pno = pnobox2.getText();
        String regx = "^[A-Za-z0-9+_.-]+@(.+)$";
        String userType = crtusertype.getSelectedItem().toString();
        String level = edtleveltype.getSelectedItem().toString();
        String dept = crtdeptype.getSelectedItem().toString();
        Date dob = (Date) dobbox2.getModel().getValue();

        if (dob == null || uname.isEmpty() || fname.isEmpty() || lname.isEmpty() || email.isEmpty() || pno.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please fill all the fields!");
            return;
        }

        if (uname.length() != 6) {
            JOptionPane.showMessageDialog(frame, "Please enter valid username!");
            return;
        }

        if (!email.matches(regx)) {
            JOptionPane.showMessageDialog(frame, "Please enter valid email!");
            return;
        }

        if (pno.length() != 10) {
            JOptionPane.showMessageDialog(frame, "Please enter valid phone number!");
            return;
        }
        LocalDate date = dob.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();


        if (date.isAfter((LocalDate.now().minusYears(18)))) {
            JOptionPane.showMessageDialog(frame, "Please enter valid date of birth!");
            return;
        }

        if (userType.equals("Admin")) {
            Connection conn = Database.DbConnector.getConnection();
            if (conn == null) {return;}

            String nextId = null;
            String crt_adminiD_sql = "SELECT adminid FROM admin ORDER BY adminid DESC LIMIT 1";
            try (PreparedStatement stmt = conn.prepareStatement(crt_adminiD_sql)) {
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    String lastId = rs.getString("adminid"); // e.g., "A001"
                    // Extract numeric part and increment
                    int num = Integer.parseInt(lastId.substring(3)); // "001" -> 1
                    num++;
                    nextId = String.format("A%03d", num); // e.g., "A002"
                } else {
                    nextId = "a001";
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            String admin_sql = "INSERT INTO admin (adminid, username, fname, lname, email, dob, pnumber) VALUES ( nextId, uname, fname, lname, email, dob , pno)";
            try(PreparedStatement stmt = conn.prepareStatement(admin_sql)) {
                ResultSet rs = stmt.executeQuery();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }



    }
}