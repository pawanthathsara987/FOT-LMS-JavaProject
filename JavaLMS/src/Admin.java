import org.jdatepicker.JDatePicker;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

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
    private JTextField dltuname;
    private JComboBox dltusertype;
    private JFrame frame;

    public String username;
    public String nextId;
    public String depid;

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
        ucreate_btn.setBackground(Color.GREEN);

        //when open admin form
        showNextUsername();
        showUserDetails();

        //hide some field when user select
        crtusertype.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showCreateUserNecassarry();
                showUserDetails();
            }
        });

        //hide some field when user select
        edtusertype.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showEditUserNecassarry();
                showUserDetails();
            }
        });

        dltusertype.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showUserDetails();
            }
        });

        //create user when user click submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createUser();
            }
        });

        editButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editUser();;
            }
        });

        deleteButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteUser();
            }
        });

        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == ucreate_btn) {
                    ucreate_btn.setBackground(Color.GREEN);
                    uedit_btn.setBackground(Color.WHITE);
                    udelete_btn.setBackground(Color.WHITE);
                    showUserDetails(); // Already present
                } else if (e.getSource() == uedit_btn) {
                    ucreate_btn.setBackground(Color.WHITE);
                    uedit_btn.setBackground(Color.GREEN);
                    udelete_btn.setBackground(Color.WHITE);
                    showUserDetails(); // Already present
                } else if (e.getSource() == udelete_btn) {
                    ucreate_btn.setBackground(Color.WHITE);
                    uedit_btn.setBackground(Color.WHITE);
                    udelete_btn.setBackground(Color.GREEN);
                    showUserDetails(); // Already present
                }
            }
        };

        ucreate_btn.addActionListener(listener);
        uedit_btn.addActionListener(listener);
        udelete_btn.addActionListener(listener);
    }

    //hide some field when user select------------------------------------------------------------------------
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

        showNextUsername();
    }

    //hide some field when user select--------------------------------------------------------------------
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
            deplabel1.setVisible(false);
        } else {
            depbox1.setVisible(true);
            deplabel1.setVisible(true);
        }
    }


    //generate next username and id for user----------------------------------------------------------
    public void showNextUsername() {
        String userType = crtusertype.getSelectedItem().toString();

        //check database connection
        Connection conn = Database.DbConnector.getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(frame, "Failed to connect to database!");
            return;
        }

        if (userType.equals("Admin")) {
            String crt_adminId_sql = "SELECT adminid FROM admin ORDER BY adminid DESC LIMIT 1";
            try (PreparedStatement stmt = conn.prepareStatement(crt_adminId_sql)) {
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    String lastId = rs.getString("adminid"); // e.g., "A001"
                    int num = Integer.parseInt(lastId.substring(1)); // Extract numeric part
                    num++;
                    nextId = String.format("A%04d", num); // Create new ID
                    unamebox2.setText(String.format("AD%04d", num));
                } else {
                    nextId = "A0001"; // First ID if database is empty
                    unamebox2.setText("AD0001");
                }
            } catch (SQLException e) {
                throw new RuntimeException("Error fetching admin ID: " + e.getMessage(), e);
            }
        } else if (userType.equals("Lecturer")) {
            String crt_lecid_sql = "SELECT lecid FROM lecturer ORDER BY lecid DESC LIMIT 1";
            try (PreparedStatement stmt = conn.prepareStatement(crt_lecid_sql)) {
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    String lastId = rs.getString("lecid");
                    int num = Integer.parseInt(lastId.substring(1));
                    num++;
                    nextId = String.format("L%04d", num); // Create new ID
                    unamebox2.setText(String.format("LC%04d", num));
                } else {
                    nextId = "L0001"; // First ID if database is empty
                    unamebox2.setText("LC0001");
                }
            } catch (SQLException e) {
                throw new RuntimeException("Error fetching lecturer ID: " + e.getMessage(), e);
            }

        } else if (userType.equals("Student")) {
            String crt_stuid_sql = "SELECT stuid FROM student ORDER BY stuid DESC LIMIT 1";
            try (PreparedStatement stmt = conn.prepareStatement(crt_stuid_sql)) {
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    String lastId = rs.getString("stuid");
                    int num = Integer.parseInt(lastId.substring(1));
                    num++;
                    nextId = String.format("S%04d", num); // Create new ID
                    unamebox2.setText(String.format("TG%04d", num));
                } else {
                    nextId = "S0001"; // First ID if database is empty
                    unamebox2.setText("TG0001");
                }
            } catch (SQLException e) {
                throw new RuntimeException("Error fetching student ID: " + e.getMessage(), e);
            }

        } else if (userType.equals("Technical Officer")) {
            String crt_techId_sql = "SELECT techid FROM technician ORDER BY techid DESC LIMIT 1";
            try (PreparedStatement stmt = conn.prepareStatement(crt_techId_sql)) {
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    String lastId = rs.getString("techid");
                    int num = Integer.parseInt(lastId.substring(1));
                    num++;
                    nextId = String.format("T%04d", num); // Create new ID
                    unamebox2.setText(String.format("TO%04d", num));
                } else {
                    nextId = "T0001"; // First ID if database is empty
                    unamebox2.setText("TO0001");
                }
            } catch (SQLException e) {
                throw new RuntimeException("Error fetching Technical Officer ID: " + e.getMessage(), e);
            }

        } else {
            JOptionPane.showMessageDialog(frame, "Invalid user type!");
            return;
        }
    }

    //set all field to null---------------------------------------------------------------------------------------
    public void setFieldToNull() {
        if (ucreate_panel.isVisible()) {
            fnamebox2.setText(null);
            lnamebox2.setText(null);
            emailbox2.setText(null);
            pnobox2.setText(null);
            dobbox2.getModel().setSelected(false);
        } else if (edit_panel.isVisible()) {
            unamebox1.setText(null);
            fnamebox1.setText(null);
            lnamebox1.setText(null);
            emailbox1.setText(null);
            pnobox1.setText(null);
            dobbox1.getModel().setSelected(false);
        } else if (delete_panel.isVisible()) {
            dltuname.setText(null);
        }
    }

    //select department id----------------------------------------------------------------------------------------
    public void selectDepartment() {
        String dep = null;
        if (ucreate_panel.isVisible()) {
            dep = crtdeptype.getSelectedItem().toString();
        } else if (edit_panel.isVisible()) {
            dep = depbox1.getSelectedItem().toString();
        }
        switch (dep) {
            case "ICT":
                depid = "D001";
                break;
            case "ET":
                depid = "D002";
                break;
            case "BST":
                depid = "D003";
                break;
            default:
                JOptionPane.showMessageDialog(frame, "Invalid department!");

        }
    }

    //user create method---------------------------------------------------------------------------
    public void createUser() {
        String uname = unamebox2.getText();
        String fname = fnamebox2.getText();
        String lname = lnamebox2.getText();
        String email = emailbox2.getText();
        String pno = pnobox2.getText();
        String regx = "^[A-Za-z0-9+_.-]+@(.+)$";
        String userType = crtusertype.getSelectedItem().toString();

        // Ensure dobbox2 is not null and fetch the selected date
        java.util.Date selectedDate = null;
        if (dobbox2 != null && dobbox2.getModel().getValue() != null) {
            selectedDate = ((java.util.GregorianCalendar) dobbox2.getModel().getValue()).getTime();
        }
        String dob = null;

        // Convert selectedDate to SQL-compatible format if not null
        if (selectedDate != null) {
            java.time.LocalDate localDate = selectedDate.toInstant()
                    .atZone(java.time.ZoneId.systemDefault())
                    .toLocalDate();
            dob = localDate.toString(); // Format as yyyy-MM-dd
        }

        // Validate required fields
        if (dob == null || uname.isEmpty() || fname.isEmpty() || lname.isEmpty() || email.isEmpty() || pno.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please fill all the fields!");
            return;
        }

        // Validate username, email, and phone number
        if (uname.length() != 6) {
            JOptionPane.showMessageDialog(frame, "Please enter a valid username!");
            return;
        }
        if (!email.matches(regx)) {
            JOptionPane.showMessageDialog(frame, "Please enter a valid email!");
            return;
        }
        if (pno.length() != 10) {
            JOptionPane.showMessageDialog(frame, "Please enter a valid phone number!");
            return;
        }
        //check database connection
        Connection conn = Database.DbConnector.getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(frame, "Failed to connect to database!");
            return;
        }

        if (userType.equals("Admin")) {
            // Insert the admin details
            String admin_sql = "INSERT INTO admin (adminid, username, fname, lname, email, dob, pnumber) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(admin_sql)) {
                stmt.setString(1, nextId);
                stmt.setString(2, uname);
                stmt.setString(3, fname);
                stmt.setString(4, lname);
                stmt.setString(5, email);
                stmt.setString(6, dob); // Properly formatted date
                stmt.setString(7, pno);

                stmt.executeUpdate(); // Execute the INSERT query
                JOptionPane.showMessageDialog(frame, "Admin created successfully!");
                setFieldToNull();
                showNextUsername();
                showUserDetails();
            } catch (SQLException e) {
                throw new RuntimeException("Error inserting admin: " + e.getMessage(), e);
            }
        } else if (userType.equals("Lecturer")) {

            selectDepartment();
            // Insert the lecturer details
            String lecturer_sql = "INSERT INTO lecturer (lecid, username, fname, lname, email, dob, pnumber, depid) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(lecturer_sql)) {
                stmt.setString(1, nextId);
                stmt.setString(2, uname);
                stmt.setString(3, fname);
                stmt.setString(4, lname);
                stmt.setString(5, email);
                stmt.setString(6, dob); // Properly formatted date
                stmt.setString(7, pno);
                stmt.setString(8, depid);

                stmt.executeUpdate(); // Execute the INSERT query
                JOptionPane.showMessageDialog(frame, "Lecturer created successfully!");
                setFieldToNull();
                showNextUsername();
                showUserDetails();
            } catch (SQLException e) {
                throw new RuntimeException("Error inserting Lecturer: " + e.getMessage(), e);
            }

        } else if (userType.equals("Student")) {

            selectDepartment();
            // Insert the admin details
            String student_sql = "INSERT INTO student (stuid, username, fname, lname, email, dob, pnumber, stulevel, depid) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(student_sql)) {
                stmt.setString(1, nextId);
                stmt.setString(2, uname);
                stmt.setString(3, fname);
                stmt.setString(4, lname);
                stmt.setString(5, email);
                stmt.setString(6, dob); // Properly formatted date
                stmt.setString(7, pno);
                stmt.setString(8, crtleveltype.getSelectedItem().toString());
                stmt.setString(9, depid);

                stmt.executeUpdate(); // Execute the INSERT query
                JOptionPane.showMessageDialog(frame, "Student created successfully!");
                setFieldToNull();
                showNextUsername();
                showUserDetails();
            } catch (SQLException e) {
                throw new RuntimeException("Error inserting Student: " + e.getMessage(), e);
            }

        }else if (userType.equals("Technical Officer")) {

            selectDepartment();
            // Insert the admin details
            String technician_sql = "INSERT INTO technician (techid, username, fname, lname, email, dob, pnumber, depid) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(technician_sql)) {
                stmt.setString(1, nextId);
                stmt.setString(2, uname);
                stmt.setString(3, fname);
                stmt.setString(4, lname);
                stmt.setString(5, email);
                stmt.setString(6, dob); // Properly formatted date
                stmt.setString(7, pno);
                stmt.setString(8, depid);

                stmt.executeUpdate(); // Execute the INSERT query
                JOptionPane.showMessageDialog(frame, "Technical Officer created successfully!");
                setFieldToNull();
                showNextUsername();
                showUserDetails();
            } catch (SQLException e) {
                throw new RuntimeException("Error inserting Technical Officer: " + e.getMessage(), e);
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Invalid user type!");
            return;
        }
    }

    //edit user when click edit button----------------------------------------------------------------------------------
    public void editUser() {
        String uname = unamebox1.getText().toUpperCase();
        String fname = fnamebox1.getText();
        String lname = lnamebox1.getText();
        String email = emailbox1.getText();
        String pno = pnobox1.getText();
        String regx = "^[A-Za-z0-9+_.-]+@(.+)$";
        String userType = edtusertype.getSelectedItem().toString();

        java.util.Date selectedDate = null;
        if (dobbox1 != null && dobbox1.getModel().getValue() != null) {
            selectedDate = ((java.util.GregorianCalendar) dobbox1.getModel().getValue()).getTime();
        }
        String dob = null;

        // Convert selectedDate to SQL-compatible format if not null
        if (selectedDate != null) {
            java.time.LocalDate localDate = selectedDate.toInstant()
                    .atZone(java.time.ZoneId.systemDefault())
                    .toLocalDate();
            dob = localDate.toString(); // Format as yyyy-MM-dd
        }

        // Validate required fields
        if (dob == null || uname.isEmpty() || fname.isEmpty() || lname.isEmpty() || email.isEmpty() || pno.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please fill all the fields!");
            return;
        }

        // Validate username, email, and phone number
        if (uname.length() != 6) {
            JOptionPane.showMessageDialog(frame, "Please enter a valid username!");
            return;
        }
        if (!email.matches(regx)) {
            JOptionPane.showMessageDialog(frame, "Please enter a valid email!");
            return;
        }
        if (pno.length() != 10) {
            JOptionPane.showMessageDialog(frame, "Please enter a valid phone number!");
            return;
        }
        //check database connection
        Connection conn = Database.DbConnector.getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(frame, "Failed to connect to database!");
            return;
        }

        if (userType.equals("Admin")) {
            // Insert the admin details
            String admin_sql = "UPDATE admin SET fname = ?, lname = ?, email = ?, dob = ?, pnumber = ? WHERE username = ?";
            try (PreparedStatement stmt = conn.prepareStatement(admin_sql)) {
                stmt.setString(1, fname);
                stmt.setString(2, lname);
                stmt.setString(3, email);
                stmt.setString(4, dob);
                stmt.setString(5, pno);
                stmt.setString(6, uname);

                stmt.executeUpdate(); // Execute the INSERT query
                JOptionPane.showMessageDialog(frame, "Admin updated successfully!");
                setFieldToNull();
                showUserDetails();
            } catch (SQLException e) {
                throw new RuntimeException("Error updating admin: " + e.getMessage(), e);
            }
        } else if (userType.equals("Lecturer")) {

            selectDepartment();
            // Insert the lecturer details
            String lecturer_sql = "UPDATE lecturer SET fname = ?, lname = ?, email = ?, dob = ?, pnumber = ?, depid = ? WHERE username = ?";
            try (PreparedStatement stmt = conn.prepareStatement(lecturer_sql)) {
                stmt.setString(1, fname);
                stmt.setString(2, lname);
                stmt.setString(3, email);
                stmt.setString(4, dob);
                stmt.setString(5, pno);
                stmt.setString(6, depid);
                stmt.setString(7, uname);


                stmt.executeUpdate(); // Execute the INSERT query
                JOptionPane.showMessageDialog(frame, "Lecturer updated successfully!");
                setFieldToNull();
                showUserDetails();
            } catch (SQLException e) {
                throw new RuntimeException("Error updating Lecturer: " + e.getMessage(), e);
            }

        } else if (userType.equals("Student")) {

            selectDepartment();
            // Insert the admin details
            String student_sql = "UPDATE student SET fname = ?, lname = ?, email = ?, dob = ?, pnumber = ?, stulevel = ?, depid = ? WHERE username = ?";
            try (PreparedStatement stmt = conn.prepareStatement(student_sql)) {
                stmt.setString(1, fname);
                stmt.setString(2, lname);
                stmt.setString(3, email);
                stmt.setString(4, dob);
                stmt.setString(5, pno);
                stmt.setString(6, edtleveltype.getSelectedItem().toString());
                stmt.setString(7, depid);
                stmt.setString(8, uname);


                stmt.executeUpdate(); // Execute the INSERT query
                JOptionPane.showMessageDialog(frame, "Student updated successfully!");
                setFieldToNull();
                showUserDetails();
            } catch (SQLException e) {
                throw new RuntimeException("Error updating Student: " + e.getMessage(), e);
            }

        }else if (userType.equals("Technical Officer")) {

            selectDepartment();
            // Insert the admin details
            String technician_sql = "UPDATE technician SET fname = ?, lname = ?, email = ?, dob = ?, pnumber = ?, depid = ? WHERE username = ?";
            try (PreparedStatement stmt = conn.prepareStatement(technician_sql)) {
                stmt.setString(1, fname);
                stmt.setString(2, lname);
                stmt.setString(3, email);
                stmt.setString(4, dob);
                stmt.setString(5, pno);
                stmt.setString(6, depid);
                stmt.setString(7, uname);

                stmt.executeUpdate(); // Execute the INSERT query
                JOptionPane.showMessageDialog(frame, "Technical Officer updated successfully!");
                setFieldToNull();
                showUserDetails();
            } catch (SQLException e) {
                throw new RuntimeException("Error updating Technical Officer: " + e.getMessage(), e);
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Invalid user type!");
            return;
        }
    }

    public void deleteUser() {
        String uname = dltuname.getText().toUpperCase();
        String usertype = dltusertype.getSelectedItem().toString().toLowerCase();
        System.out.println(uname + " " + usertype);
        if (usertype.equals("Technical Officer")) {
            usertype = "technician";
        }

        Connection conn = Database.DbConnector.getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(frame, "Failed to connect to database!");
            return;
        }
        String table = null;
        String id_user = uname.substring(0, 2);

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
                return;
        }

        if (usertype.equals(table)) {
            String delete_sql = "DELETE FROM " + table + " WHERE username = ?";

            try(PreparedStatement stmt = conn.prepareStatement(delete_sql)) {
                stmt.setString(1, uname);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(frame, "User deleted successfully!");
                setFieldToNull();
                showUserDetails();
            } catch (SQLException e) {
                throw new RuntimeException("Error deleting user: " + e.getMessage(), e);
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Please enter valid username!");
            return;
        }

    }

    public void showUserDetails() {
        String usertype = null;
        Connection conn = Database.DbConnector.getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(frame, "Failed to connect to database!");
            return;
        }

        if (ucreate_btn.getBackground() == Color.GREEN) {
            usertype = crtusertype.getSelectedItem().toString().toLowerCase();
        } else if (uedit_btn.getBackground() == Color.GREEN) {
            usertype = edtusertype.getSelectedItem().toString().toLowerCase();
        }else if (udelete_btn.getBackground() == Color.GREEN) {
            usertype = dltusertype.getSelectedItem().toString().toLowerCase();
        } else {
            JOptionPane.showMessageDialog(frame, "Please select a user to view details!");
            return;
        }

        if (usertype.equals("technical officer")) {
            usertype = "technician";
        }

        String showUser_sql;
        switch (usertype) {
            case "admin":
                showUser_sql = "SELECT adminid, username, fname, lname, email, dob, pnumber FROM admin";
                break;
            case "lecturer":
                showUser_sql = "SELECT lecid, username, fname, lname, email, dob, pnumber, depid FROM lecturer";
                break;
            case "student":
                showUser_sql = "SELECT stuid, username, fname, lname, email, dob, pnumber, stulevel, depid FROM student";
                break;
            case "technician":
                showUser_sql = "SELECT techid, username, fname, lname, email, dob, pnumber, depid FROM technician";
                break;
            default:
                JOptionPane.showMessageDialog(frame, "Invalid user type!");
                return;
        }

        try(PreparedStatement stmt = conn.prepareStatement(showUser_sql)) {
            ResultSet rs = stmt.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            DefaultTableModel model = (DefaultTableModel) userinfo.getModel();

            model.setRowCount(0);

            int columnsNumber = rsmd.getColumnCount();
            String[] columnNames = new String[columnsNumber];
            for (int i = 0; i < columnsNumber; i++) {
                columnNames[i] = rsmd.getColumnName(i + 1);
            }
            model.setColumnIdentifiers(columnNames);

            while (rs.next()) {
                Object[] rowData = new Object[columnsNumber];
                for (int i = 0; i < columnsNumber; i++) {
                    rowData[i] = rs.getObject(i + 1);
                }
                model.addRow(rowData);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error showing user details: " + e.getMessage(), e);
        }
    }
}