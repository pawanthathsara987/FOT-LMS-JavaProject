import org.jdatepicker.JDatePicker;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.time.*;

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
    private JTextField cdcoursecode;
    private JPanel timeformcard;
    private JPanel tcreate_p;
    private JPanel tdelete_p;
    private JButton tdelete_btn;
    private JButton tcreate_btn;
    private JComboBox tdlevel;
    private JComboBox tddepartment;
    private JTextArea ndescription;
    private JComboBox ndtitle;
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
    private JComboBox ccourselevel;
    private JTextField ccoursecode;
    private JComboBox ccoursetype;
    private JTextField ccredit;
    private JComboBox cdepartment;
    private JTextField ccoursename;
    private JComboBox clecturer;
    private JButton csubmit;
    private JButton cdelete;
    private JTable courseInfo;
    private JTextField ntitle;
    private JTable noticeInfo;
    private JButton nsubmit;
    private JButton nddelete;
    private JLabel adminname;
    private JComboBox tlevel;
    private JComboBox tstime;
    private JComboBox tdepartment;
    private JComboBox tday;
    private JComboBox tcourse;
    private JComboBox tcoursetype;
    private JButton tsubmit;
    private JButton tdelete;
    private JComboBox tetime;
    private JComboBox thall;
    private JTable timetableInfo;
    private JFrame frame;

    public String adminusername;
    public String username;
    public String nextId;
    public String depid;

    public Admin(String username, String name) {
        this.adminusername = username;
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

        //set admin username
        adminname.setText(name);

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



        //------------------------------------------------------------USER----------------------------------------------------------------------------------------//

        ucreate_btn.setBackground(Color.GREEN);  //set default panel button to green color

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

        tdlevel.addActionListener(listener);
        tddepartment.addActionListener(listener);
        userinfo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectRow();
            }
        });

        //------------------------------------------------------------COURSE----------------------------------------------------------------------------------------//
        ccreate_btn.setBackground(Color.GREEN);  //set default panel button to green color
        showAvailableLecturers();
        showCourseDetails();

        ActionListener listener1 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == ccreate_btn) {
                    ccreate_btn.setBackground(Color.GREEN);
                    cdelete_btn.setBackground(Color.WHITE);
                    showAvailableLecturers();
                    showCourseDetails();
                } else if (e.getSource() == cdelete_btn) {
                    ccreate_btn.setBackground(Color.WHITE);
                    cdelete_btn.setBackground(Color.GREEN);
                    showCourseDetails();
                }
            }
        };
        ccreate_btn.addActionListener(listener1);
        cdelete_btn.addActionListener(listener1);

        cdepartment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAvailableLecturers();
            }
        });
        csubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createCourse();
            }
        });
        cdelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteCourse();
            }
        });

        ActionListener listener5 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showCourseDetails();
            }
        };
        ccourselevel.addActionListener(listener5);
        cdepartment.addActionListener(listener5);

        courseInfo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectRow();
            }
        });

        //------------------------------------------------------------Time Table----------------------------------------------------------------------------------------//


        tcreate_btn.setBackground(Color.GREEN);
        showAvailableCourse();
        showTimeTableDetails();

        ActionListener listener3 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == tcreate_btn) {
                    tcreate_btn.setBackground(Color.GREEN);
                    tdelete_btn.setBackground(Color.WHITE);
                    showAvailableCourse();
                    showTimeTableDetails();
                } else if (e.getSource() == tdelete_btn) {
                    tcreate_btn.setBackground(Color.WHITE);
                    tdelete_btn.setBackground(Color.GREEN);
                    showTimeTableDetails();

                }
            }
        };
        tdelete_btn.addActionListener(listener3);
        tcreate_btn.addActionListener(listener3);

        ActionListener listener4 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAvailableCourse();
            }
        };
        tlevel.addActionListener(listener4);
        tdepartment.addActionListener(listener4);

        tsubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createTimeTable();
            }
        });

        ActionListener listener6 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showTimeTableDetails();
            }
        };
        tlevel.addActionListener(listener6);
        tdepartment.addActionListener(listener6);


        tdelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteTimeTable();
            }
        });

        //------------------------------------------------------------Notice----------------------------------------------------------------------------------------//

        ncreate_btn.setBackground(Color.GREEN);
        showAvailableNoticeTitle();
        showNoticeDetails();
        showNoticeDetails();


        ActionListener listener2 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == ncreate_btn) {
                    ncreate_btn.setBackground(Color.GREEN);
                    ndelete_btn.setBackground(Color.WHITE);
                    showNoticeDetails();
                } else if (e.getSource() == ndelete_btn) {
                    ndelete_btn.setBackground(Color.GREEN);
                    ncreate_btn.setBackground(Color.WHITE);
                    showAvailableNoticeTitle();
                    showNoticeDetails();
                }
            }
        };
        ndelete_btn.addActionListener(listener2);
        ncreate_btn.addActionListener(listener2);


        nsubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createNotice();
            }
        });
        nddelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteNotice();
            }
        });
        ActionListener listener7 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showTimeTableDetails();
            }
        };
    }

    //------------------------------------------------------------USER----------------------------------------------------------------------------------------//

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
                e.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Database connection error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
                showAvailableLecturers();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Database connection error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
                e.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Database connection error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
                e.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Database connection error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
                e.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Database connection error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
                e.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Database connection error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
                e.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Database connection error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
                e.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Database connection error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Invalid user type!");
            return;
        }
    }

    //delete user when click delete button by admin-------------------------------------------------------------------------
    public void deleteUser() {
        String uname = dltuname.getText().toUpperCase();
        String usertype = dltusertype.getSelectedItem().toString().toLowerCase();
        if (usertype.equals("technical officer")) {
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
                System.out.println("Invalid username!");
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

    //show user details when load panel--------------------------------------------------------------------------------------------
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

    //------------------------------------------------------------COURSE----------------------------------------------------------------------------------------//

    //show available lecture according to department when panel load and update lecturer--------------------------------------------------------
    public void showAvailableLecturers() {
        Connection conn = Database.DbConnector.getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(frame, "Failed to connect to database!");
            return;
        }

        String dep = cdepartment.getSelectedItem().toString();

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

        String showAvailableLecturers_sql = "SELECT username FROM lecturer WHERE depid = ?";

        try(PreparedStatement stmt = conn.prepareStatement(showAvailableLecturers_sql)) {
            stmt.setString(1, depid);
            ResultSet rs = stmt.executeQuery();
            clecturer.removeAllItems();
            while (rs.next()) {
                String lecturer = rs.getString("username");
                clecturer.addItem(lecturer);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error showing available lecturers: " + e.getMessage(), e);
        }
    }

    //create course and assign lecturer------------------------------------------------------------------------------------
    public void createCourse() {
        String ccode = ccoursecode.getText().toUpperCase();
        String ctype = ccoursetype.getSelectedItem().toString();
        String cnoofcredit = ccredit.getText();
        String cname = ccoursename.getText();
        String clevel = ccourselevel.getSelectedItem().toString();
        String cdep = cdepartment.getSelectedItem().toString();
        String lecname = clecturer.getSelectedItem().toString();

        switch (cdep) {
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

        Connection conn = Database.DbConnector.getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(frame, "Failed to connect to database!");
            return;
        }

        String createCourse_sql = "INSERT INTO course (ccode, cname, ccredit, ctype, clevel, depid, lecuname) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try(PreparedStatement stmt = conn.prepareStatement(createCourse_sql)) {
            stmt.setString(1, ccode);
            stmt.setString(2, cname);
            stmt.setString(3, cnoofcredit);
            stmt.setString(4, ctype);
            stmt.setString(5, clevel);
            stmt.setString(6, depid);
            stmt.setString(7, lecname);

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(frame, "Course added successfully!");
            showCourseDetails();
            setCourseFieldToNull();
        } catch (SQLException e) {
            throw new RuntimeException("Error creating course: " + e.getMessage(), e);
        }
    }

    // delete course-------------------------------------------------------------------------------------
    public void deleteCourse() {
        String ccode = cdcoursecode.getText().toUpperCase();

        Connection conn = Database.DbConnector.getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(frame, "Failed to connect to database!");
            return;
        }

        String deleteCourse_sql = "DELETE FROM course WHERE ccode = ?";

        try(PreparedStatement stmt = conn.prepareStatement(deleteCourse_sql)) {
            stmt.setString(1, ccode);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(frame, "Course deleted successfully!");
            showCourseDetails();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting course: " + e.getMessage(), e);
        }
    }

    //show course details------------------------------------------------------------------------------------
    public void showCourseDetails() {
        String cLevel = null;
        String cdep = null;

        Connection conn = Database.DbConnector.getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(frame, "Failed to connect to database!");
            return;
        }

        if (ccreate_btn.getBackground() == Color.GREEN) {
            cLevel = ccourselevel.getSelectedItem().toString().toLowerCase();
            cdep = cdepartment.getSelectedItem().toString();
        } else if (cdelete_btn.getBackground() == Color.GREEN) {
            cLevel = ccourselevel.getSelectedItem().toString().toLowerCase();
            cdep = cdepartment.getSelectedItem().toString();
        } else {
            JOptionPane.showMessageDialog(frame, "Please select a user to view details!");
            return;
        }

        switch (cdep) {
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
                return;
        }

        String showCourseDetails_sql = "SELECT ccode, cname, ccredit, ctype, clevel, depid, lecuname FROM course WHERE clevel = ? AND depid = ?";

        if (cdelete_btn.getBackground() == Color.GREEN) {
            showCourseDetails_sql = "SELECT ccode, cname, ccredit, ctype, clevel, depid, lecuname FROM course ORDER BY clevel";
        }



        try(PreparedStatement stmt = conn.prepareStatement(showCourseDetails_sql)) {
            if (ccreate_btn.getBackground() == Color.GREEN) {
                stmt.setString(1, cLevel);
                stmt.setString(2, depid);
            }

            ResultSet rs = stmt.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            DefaultTableModel model = (DefaultTableModel) courseInfo.getModel();

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
            throw new RuntimeException("Error showing course details: " + e.getMessage(), e);
        }
    }

    //clear all field after submit in course
    public void setCourseFieldToNull() {
        ccoursecode.setText(null);
        ccredit.setText(null);
        ccoursename.setText(null);
        ccoursetype.setSelectedIndex(0);
    }


    //------------------------------------------------------------Time Table----------------------------------------------------------------------------------------//

    // show available courses according to select level and department--------------------------------------------------
    public void showAvailableCourse() {
        String level = tlevel.getSelectedItem().toString();
        String dep = tdepartment.getSelectedItem().toString().toUpperCase();

        Connection conn = Database.DbConnector.getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(frame, "Failed to connect to database!");
            return;
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
                return;
        }

        String showAvailableCourse_sql = "SELECT ccode, cname FROM course WHERE clevel = ? AND depid = ?";

        try(PreparedStatement stmt = conn.prepareStatement(showAvailableCourse_sql)) {
            stmt.setString(1, level);
            stmt.setString(2, depid);
            ResultSet rs = stmt.executeQuery();

            tcourse.removeAllItems();

            while(rs.next()) {
                String coursename = rs.getString("ccode") + " " + rs.getString("cname");
                tcourse.addItem(coursename);
            }
        } catch (SQLException e) {
            System.err.println("Course showing error" + e.getMessage());
        }

    }

    // creaet time table when user click submit nutton
    public void createTimeTable() {
        String level = tlevel.getSelectedItem().toString();
        String department = tdepartment.getSelectedItem().toString();
        String day = tday.getSelectedItem().toString();
        String startTime = tstime.getSelectedItem().toString();
        String endTime = tetime.getSelectedItem().toString();
        String hall = thall.getSelectedItem().toString();
        String course = tcourse.getSelectedItem() != null ? tcourse.getSelectedItem().toString() : null;
        String courseType = tcoursetype.getSelectedItem().toString();
        String lecname = null;

        switch (department) {
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
                return;
        }

        // Input validation
        if (course == null) {
            JOptionPane.showMessageDialog(frame, "Please fill course to create a timetable.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String coursecode = course.split(" ")[0];

        Connection conn = Database.DbConnector.getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(frame, "Failed to connect to database!");
            return;
        }

        String getlecname = "SELECT lecuname FROM course WHERE ccode = ?";

        String createTimeTable_sql = "INSERT INTO timetable (level, depid, day, start_time, end_time, hall, cname, ctype, lec_name) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";


        // Database operation
        try (PreparedStatement stmt1 = conn.prepareStatement(getlecname); PreparedStatement stmt2 = conn.prepareStatement(createTimeTable_sql)) {

            stmt1.setString(1, coursecode);
            ResultSet rs = stmt1.executeQuery();

            if (rs.next()) {
                lecname = rs.getString("lecuname");
            }

            String dep_id = depid;

            // Set parameters for the prepared statement
            stmt2.setString(1, level);
            stmt2.setString(2, dep_id);
            stmt2.setString(3, day);
            stmt2.setString(4, startTime);
            stmt2.setString(5, endTime);
            stmt2.setString(6, hall);
            stmt2.setString(7, course);
            stmt2.setString(8, courseType);
            stmt2.setString(9, lecname);

            // Execute the update
            int rowsInserted = stmt2.executeUpdate();

            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(frame, "Timetable created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                showTimeTableDetails();
            } else {
                JOptionPane.showMessageDialog(frame, "Failed to create timetable.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Database connection error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    // delete time table when click delete button---------------------------------------------------------------------=-
    public void deleteTimeTable() {
        String level = tlevel.getSelectedItem().toString();
        String dep = tdepartment.getSelectedItem().toString();
        String dep_id = null;
        switch (dep) {
            case "ICT":
                dep_id = "D001";
                break;
            case "ET":
                dep_id = "D002";
                break;
            case "BST":
                dep_id = "D003";
                break;
            default:
                JOptionPane.showMessageDialog(frame, "Invalid department!");
                return;
        }

        Connection conn = Database.DbConnector.getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(frame, "Failed to connect to database!");
            return;
        }

        String deleteTimeTable_sql = "DELETE FROM timetable WHERE level = ? AND depid = ?";

        try(PreparedStatement stmt = conn.prepareStatement(deleteTimeTable_sql)) {
            stmt.setString(1, level);
            stmt.setString(2, dep_id);

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(frame, "Time Table deleted successfully!");
            showTimeTableDetails();
        } catch (SQLException e) {
            System.err.println("Timetable deleting error" + e.getMessage());
        }
    }


    //show time table details when load panel and click button which change between panel--------------------------------
    public void showTimeTableDetails() {
        String dep = null;
        String dep_id = null;
        String level = null;
        if (tcreate_btn.getBackground() == Color.GREEN) {
            level = tlevel.getSelectedItem().toString();
            dep = tdepartment.getSelectedItem().toString().toUpperCase();
        } else if (tdelete_btn.getBackground() == Color.GREEN) {
            level = tdlevel.getSelectedItem().toString();
            dep = tddepartment.getSelectedItem().toString().toUpperCase();
        }


        switch (dep) {
            case "ICT":
                dep_id = "D001";
                break;
            case "ET":
                dep_id = "D002";
                break;
            case "BST":
                dep_id = "D003";
                break;
            default:
                JOptionPane.showMessageDialog(frame, "Invalid department!");
                return;
        }

        String showNoticeDetails_sql = "SELECT * FROM timetable WHERE level = ? AND depid = ?";

        Connection conn = Database.DbConnector.getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(frame, "Failed to connect to database!");
            return;
        }

        try(PreparedStatement stmt = conn.prepareStatement(showNoticeDetails_sql)) {
            stmt.setString(1, level);
            stmt.setString(2, dep_id);
            ResultSet rs = stmt.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            DefaultTableModel model = (DefaultTableModel) timetableInfo.getModel();

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
            throw new RuntimeException("Error showing course details: " + e.getMessage(), e);
        }
    }


    //------------------------------------------------------------Notice----------------------------------------------------------------------------------------//

    //create notice when admin click submit button---------------------------------------------------------------------
    public void createNotice() {
        String title = ntitle.getText();
        String content = ndescription.getText();

        if (title.isEmpty() || content.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please fill all the fields!");
            return;
        }

        Connection conn = Database.DbConnector.getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(frame, "Failed to connect to database!");
            return;
        }

        String createNotice_sql = "INSERT INTO notice (title, content, posteddate) VALUES (?, ?, ?)";

        LocalDateTime now = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(now);

        try(PreparedStatement stmt = conn.prepareStatement(createNotice_sql)) {
            stmt.setString(1, title);
            stmt.setString(2, content);
            stmt.setTimestamp(3, timestamp);

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(frame, "Notice added successfully!");
            showNoticeDetails();
            setNoticeFieldToNull();
        } catch (SQLException e) {
            throw new RuntimeException("Error creating notice: " + e.getMessage(), e);
        }
    }

    //delete notice when admin click delete button--------------------------------------------------------------------
    public void deleteNotice() {
        String title = ndtitle.getSelectedItem().toString();

        Connection conn = Database.DbConnector.getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(frame, "Failed to connect to database!");
            return;
        }

        String deleteNotice_sql = "DELETE FROM notice WHERE title = ?";

        try(PreparedStatement stmt = conn.prepareStatement(deleteNotice_sql)) {
            stmt.setString(1, title);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(frame, "Notice deleted successfully!");
            showNoticeDetails();
            showAvailableNoticeTitle();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting notice: " + e.getMessage(), e);
        }
    }

    //show available notice title in ndtitle combobox
    public void showAvailableNoticeTitle() {

        Connection conn = Database.DbConnector.getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(frame, "Failed to connect to database!");
            return;
        }

        String showAvailableNoticeTitle_sql = "SELECT title FROM notice";

        try(PreparedStatement stmt = conn.prepareStatement(showAvailableNoticeTitle_sql)) {
            ResultSet rs = stmt.executeQuery();

            ndtitle.removeAllItems();

            while(rs.next()) {
                String ntitle = rs.getString("title");
                ndtitle.addItem(ntitle);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error showing available notice title: " + e.getMessage(), e);
        }
    }

    //show notices in jtable------------------------------------------------------------------------------------------
    public void showNoticeDetails() {

        Connection conn = Database.DbConnector.getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(frame, "Failed to connect to database!");
            return;
        }

        String showNoticeDetails_sql = "SELECT title, content, posteddate FROM notice";

        try(PreparedStatement stmt = conn.prepareStatement(showNoticeDetails_sql)) {
            ResultSet rs = stmt.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            DefaultTableModel model = (DefaultTableModel) noticeInfo.getModel();

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
            throw new RuntimeException("Error showing notice details: " + e.getMessage(), e);
        }
    }

    //clear field after create notice
    public void setNoticeFieldToNull() {
        ntitle.setText(null);
        ndescription.setText(null);
    }
    
    //
    public void selectRow() {
        if (uedit_btn.getBackground() == Color.GREEN) {
            unamebox1.setText(userinfo.getValueAt(userinfo.getSelectedRow(), 1).toString());
            fnamebox1.setText(userinfo.getValueAt((userinfo.getSelectedRow()), 2).toString());
            lnamebox1.setText(userinfo.getValueAt((userinfo.getSelectedRow()), 3).toString());
            emailbox1.setText(userinfo.getValueAt((userinfo.getSelectedRow()), 4).toString());
            dobbox1.getModel().setSelected(userinfo.getValueAt((userinfo.getSelectedRow()), 5).toString().equals("Yes"));
            pnobox1.setText(userinfo.getValueAt((userinfo.getSelectedRow()), 6).toString());
        } else if (udelete_btn.getBackground() == Color.GREEN) {
            dltuname.setText(userinfo.getValueAt(userinfo.getSelectedRow(), 1).toString());
        } else if (cdelete_btn.getBackground() == Color.GREEN) {
            cdcoursecode.setText(courseInfo.getValueAt(courseInfo.getSelectedRow(), 0).toString());
        } else return;
    }

}