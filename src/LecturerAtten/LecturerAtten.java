package LecturerAtten;

import Database.DbConnector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LecturerAtten {
    private JPanel MainPanel;
    private JPanel cardpanel;
    private JPanel attendance;
    private JPanel title;
    private JPanel body;
    private JPanel table;
    private JPanel subcard;
    private JPanel viewatten;
    private JPanel viewmedical;
    private JScrollBar scrollBar1;
    private JComboBox selectcour;
    private JButton attenButton;
    private JButton medicalButton;
    private JTable aviewtable;
    private JButton selectbtn;
    private JButton graatten;
    private JButton lessatten;
    private JComboBox medcourse;
    private JButton medselect;
    private JButton medpercen;
    private JButton medgrater;
    private JButton medless;
    private JButton medTP;
    private JTextField selectstuid;

    private String lecUser;

    public LecturerAtten(String lecUser) {
        this.lecUser = lecUser;

        JFrame frame = new JFrame("ViewCourse");
        frame.setContentPane(MainPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1100, 750);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        CardLayout cardlayout = new CardLayout();
        cardpanel.setLayout(cardlayout);

        subcard.setLayout(cardlayout);

        cardpanel.add(attendance,"card1");

        subcard.add(viewatten,"card2");
        subcard.add(viewmedical,"card3");

        attenButton.addActionListener(e -> cardlayout.show(subcard,"card2"));
        medicalButton.addActionListener(e -> cardlayout.show(subcard,"card3"));


        // Initialize the table

        // Load courses for the student
        loadCoursesForLecturer();

        loadCoursesForLecturerMed();

        // Add action listener to the select button
        selectbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // View attendance for the selected course
                attendanceView();
            }
        });

        //Grater than 80
        graatten.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                attendanceViewGrater80();
            }
        });

//less than 80
        lessatten.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                attendanceViewLess80();
            }
        });



        medselect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showMedicalRecords();
            }
        });


        medpercen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                attendanceViewWithMedical();
            }
        });


        medgrater.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                attendanceViewMedicalGrater80();
            }
        });


        medless.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                attendanceViewMedicalLess80();
            }
        });


        medTP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                attendanceViewTheoryPractical();
            }
        });
    }



    private void loadCoursesForLecturer() {

        DbConnector db = new DbConnector();
        Connection conn = db.getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(null, "Failed to connect to database!");
            return;
        }

        try {


            String query = "SELECT * FROM course WHERE lecusername = ?";

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, lecUser);  // Use lecUser to refer to the logged-in lecturer's username

            ResultSet rs = ps.executeQuery();
            selectcour.removeAllItems(); // Clear the ComboBox before adding new courses

            while (rs.next()) {
                selectcour.addItem(rs.getString("ccode"));
            }

            rs.close();
            ps.close();
            conn.close();  // Close the connection
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error loading lecturer courses: " + e.getMessage());
        }
    }


//-----------------------------------------------------------------------------------

    private void loadCoursesForLecturerMed() {

        DbConnector db = new DbConnector();
        Connection conn = db.getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(null, "Failed to connect to database!");
            return;
        }

        try {


            String query = "SELECT * FROM course WHERE lecusername = ?";

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, lecUser);  // Use lecUser to refer to the logged-in lecturer's username

            ResultSet rs = ps.executeQuery();
            medcourse.removeAllItems(); // Clear the ComboBox before adding new courses

            while (rs.next()) {
                medcourse.addItem(rs.getString("ccode"));
            }

            rs.close();
            ps.close();
            conn.close();  // Close the connection
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error loading lecturer courses: " + e.getMessage());
        }
    }


//--------------------------------------------------------------------------------------------------------------

    private void attendanceView() {
        // Define the column names for the table
        String[] columnNames = {"Student ID", "First Name", "Last Name", "Course Code", "Attendance %"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        aviewtable.setModel(model);

        // Set the table header style
        JTableHeader header = aviewtable.getTableHeader();
        header.setFont(new Font("SansSerif", Font.BOLD, 14));
        header.setBackground(new Color(204, 255, 204));
        header.setForeground(Color.BLACK);

        // Get the selected course code from the ComboBox
        String selectedCourse = selectcour.getSelectedItem() != null ? selectcour.getSelectedItem().toString() : "";

        // Get the student ID from the text field
        String selectedStudentID = selectstuid.getText().trim();

        // course is selected
        if (selectedCourse.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please select a course.");
            return;
        }


        DbConnector db = new DbConnector();
        Connection conn = db.getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(null, "Failed to connect to database!");
            return;
        }

        try {
            CallableStatement stmt;

            // If a student id is entered, on both course code and student id
            if (!selectedStudentID.isEmpty()) {
                stmt = conn.prepareCall("{CALL ShowStudentCourseAttendance(?, ?)}");
                stmt.setString(1, selectedCourse);
                stmt.setString(2, selectedStudentID);
            } else {
                // Otherwise, show attendance for all students in the selected course
                stmt = conn.prepareCall("{CALL ShowCourseAttendancePerStudent(?)}");
                stmt.setString(1, selectedCourse);
            }


            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getString("stuid"),
                        rs.getString("fname"),
                        rs.getString("lname"),
                        rs.getString("ccode"),
                        rs.getDouble("attendance_percentage")
                });
            }

            // Close the result set and statement
            rs.close();
            stmt.close();
            conn.close();

            selectstuid.setText("");

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading attendance: " + e.getMessage());
        }
    }


//---------------------------------------------------------------------------------------------------------------------

    private void attendanceViewGrater80() {
        String[] columnNames = {"Student ID", "First Name", "Last Name", "Course Code", "Attendance %"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        aviewtable.setModel(model);

        JTableHeader header = aviewtable.getTableHeader();
        header.setFont(new Font("SansSerif", Font.BOLD, 14));
        header.setBackground(new Color(204, 255, 204));
        header.setForeground(Color.BLACK);

        String selectedCourse = selectcour.getSelectedItem() != null ? selectcour.getSelectedItem().toString() : "";

        if (selectedCourse.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please select a course.");
            return;
        }

        DbConnector db = new DbConnector();
        Connection conn = db.getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(null, "Failed to connect to database!");
            return;
        }

        try {


            // Prepare the stored procedure call
            CallableStatement stmt = conn.prepareCall("{CALL ShowCourseAttendancePerStudentGrater( ?)}");
            stmt.setString(1, selectedCourse);  // Pass the selected course code

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getString("stuid"),
                        rs.getString("fname"),
                        rs.getString("lname"),
                        rs.getString("ccode"),
                        rs.getDouble("attendance_percentage")
                });
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading attendance percentage: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "An unexpected error occurred: " + e.getMessage());
        }
    }

    //------------------------------------------------------------------------------------------------------------------

    private void attendanceViewLess80() {
        String[] columnNames = {"Student ID", "First Name", "Last Name", "Course Code", "Attendance %"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        aviewtable.setModel(model);

        JTableHeader header = aviewtable.getTableHeader();
        header.setFont(new Font("SansSerif", Font.BOLD, 14));
        header.setBackground(new Color(204, 255, 204));
        header.setForeground(Color.BLACK);

        String selectedCourse = selectcour.getSelectedItem() != null ? selectcour.getSelectedItem().toString() : "";

        if (selectedCourse.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please select a course.");
            return;
        }

        DbConnector db = new DbConnector();
        Connection conn = db.getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(null, "Failed to connect to database!");
            return;
        }

        try {

            CallableStatement stmt = conn.prepareCall("{CALL ShowCourseAttendancePerStudentLess( ?)}");
            stmt.setString(1, selectedCourse);  // Pass the selected course code to the procedure

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getString("stuid"),
                        rs.getString("fname"),
                        rs.getString("lname"),
                        rs.getString("ccode"),
                        rs.getDouble("attendance_percentage")
                });
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading attendance percentage: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "An unexpected error occurred: " + e.getMessage());
        }
    }


    //------------------------------------------------------------------------------

    private void showMedicalRecords() {
        String[] columns = {"Sutdent ID","fname","lname","Week", "Date", "Type", "Hours", "Status", "Medical Submitted"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        aviewtable.setModel(model);



        JTableHeader header = aviewtable.getTableHeader();
        header.setFont(new Font("SansSerif", Font.BOLD, 14));
        header.setBackground(new Color(204, 255, 204));
        header.setForeground(Color.BLACK);

        String selectedCourse = medcourse.getSelectedItem() != null ? medcourse.getSelectedItem().toString() : "";

        if (selectedCourse.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please select a course.");
            return;
        }

        DbConnector db = new DbConnector();
        Connection conn = db.getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(null, "Failed to connect to database!");
            return;
        }

        try {

            CallableStatement stmt = conn.prepareCall("{CALL ShowAllStudentsMedicalByCourse(?)}");

            stmt.setString(1, selectedCourse);

            ResultSet rs = stmt.executeQuery();

            boolean foundMedicalSubmitted = false;

            while (rs.next()) {
                String medid = rs.getString("medid");


                System.out.println("medid: " + medid);


                if (medid != null) {
                    foundMedicalSubmitted = true;

                    String status = rs.getString("status");
                    boolean medicalSubmitted = true;  // Since medid is not null, it's a valid submission

                    model.addRow(new Object[]{
                            rs.getString("stuid"),
                            rs.getString("fname"),
                            rs.getString("lname"),
                            rs.getInt("week"),
                            rs.getDate("date"),
                            rs.getString("ctype"),
                            rs.getInt("hours"),
                            status,  // "Absent" or "Present"
                            medicalSubmitted  // TRUE
                    });
                }
            }

            // If no medical records were found
            if (!foundMedicalSubmitted) {
                JOptionPane.showMessageDialog(null, "No medical records found for this student.");
            }

            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error showing 15-week medical attendance.");
        }
    }


//----------------------------------------------------------------------------------------------------------------------

    private void attendanceViewWithMedical() {
        String[] columnNames = {"Student ID", "First Name", "Last Name", "Course Code", "Attendance %"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        aviewtable.setModel(model);

        JTableHeader header = aviewtable.getTableHeader();
        header.setFont(new Font("SansSerif", Font.BOLD, 14));
        header.setBackground(new Color(204, 255, 204));
        header.setForeground(Color.BLACK);

        String selectedCourse = medcourse.getSelectedItem() != null ? medcourse.getSelectedItem().toString() : "";

        if (selectedCourse.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please select a course.");
            return;
        }

        DbConnector db = new DbConnector();
        Connection conn = db.getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(null, "Failed to connect to database!");
            return;
        }

        try {

            // Prepare the stored procedure call
            CallableStatement stmt = conn.prepareCall("{CALL ShowCourseAttendancePerStudentWith( ?)}");
            stmt.setString(1, selectedCourse);  // Pass the selected course code

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getString("stuid"),
                        rs.getString("fname"),
                        rs.getString("lname"),
                        rs.getString("ccode"),
                        rs.getDouble("attendance_percentage")
                });
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading attendance percentage: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "An unexpected error occurred: " + e.getMessage());
        }
    }


    //----------------------------------------------------------------------------------------------------------------------

    private void attendanceViewMedicalGrater80() {
        String[] columnNames = {"Student ID", "First Name", "Last Name", "Course Code", "Attendance %"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        aviewtable.setModel(model);

        JTableHeader header = aviewtable.getTableHeader();
        header.setFont(new Font("SansSerif", Font.BOLD, 14));
        header.setBackground(new Color(204, 255, 204));
        header.setForeground(Color.BLACK);

        String selectedCourse = medcourse.getSelectedItem() != null ? medcourse.getSelectedItem().toString() : "";

        if (selectedCourse.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please select a course.");
            return;
        }

        DbConnector db = new DbConnector();
        Connection conn = db.getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(null, "Failed to connect to database!");
            return;
        }

        try {


            // Prepare the stored procedure call
            CallableStatement stmt = conn.prepareCall("{CALL ShowCourseAttendancePerStudentWithGrater( ?)}");
            stmt.setString(1, selectedCourse);  // Pass the selected course code

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getString("stuid"),
                        rs.getString("fname"),
                        rs.getString("lname"),
                        rs.getString("ccode"),
                        rs.getDouble("attendance_percentage")
                });
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();  // Print stack trace to console for debugging
            JOptionPane.showMessageDialog(null, "Error loading attendance percentage: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();  // General exception handling
            JOptionPane.showMessageDialog(null, "An unexpected error occurred: " + e.getMessage());
        }
    }

    //--------------------------------------------------------------------------------------------------------------------

    private void attendanceViewMedicalLess80() {
        String[] columnNames = {"Student ID", "First Name", "Last Name", "Course Code", "Attendance %"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        aviewtable.setModel(model);

        JTableHeader header = aviewtable.getTableHeader();
        header.setFont(new Font("SansSerif", Font.BOLD, 14));
        header.setBackground(new Color(204, 255, 204));
        header.setForeground(Color.BLACK);

        String selectedCourse = medcourse.getSelectedItem() != null ? medcourse.getSelectedItem().toString() : "";

        if (selectedCourse.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please select a course.");
            return;
        }

        DbConnector db = new DbConnector();
        Connection conn = db.getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(null, "Failed to connect to database!");
            return;
        }

        try {


            // Prepare the stored procedure call
            CallableStatement stmt = conn.prepareCall("{CALL ShowCourseAttendancePerStudentWithLess( ?)}");
            stmt.setString(1, selectedCourse);  // Pass the selected course code

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getString("stuid"),
                        rs.getString("fname"),
                        rs.getString("lname"),
                        rs.getString("ccode"),
                        rs.getDouble("attendance_percentage")
                });
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading attendance percentage: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "An unexpected error occurred: " + e.getMessage());
        }
    }


    //----------------------------------------------------------------------------------------------------
    //Theory practical separate

    private void attendanceViewTheoryPractical() {
        String[] columnNames = {"Student ID", "First Name", "Last Name", "Course Code", "Type", "Attendance %"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        aviewtable.setModel(model);

        JTableHeader header = aviewtable.getTableHeader();
        header.setFont(new Font("SansSerif", Font.BOLD, 14));
        header.setBackground(new Color(204, 255, 204));
        header.setForeground(Color.BLACK);

        String selectedCourse = selectcour.getSelectedItem() != null ? selectcour.getSelectedItem().toString() : "";

        if (selectedCourse.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please select a course.");
            return;
        }

        DbConnector db = new DbConnector();
        Connection conn = db.getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(null, "Failed to connect to database!");
            return;
        }

        try {
            CallableStatement stmt = conn.prepareCall("{CALL ShowCourseAttendancePerStudentTP(?)}");
            stmt.setString(1, selectedCourse);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getString("stuid"),
                        rs.getString("fname"),
                        rs.getString("lname"),
                        rs.getString("ccode"),
                        rs.getString("ctype"),
                        rs.getDouble("attendance_percentage")
                });
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading attendance percentage: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "An unexpected error occurred: " + e.getMessage());
        }
    }
}
