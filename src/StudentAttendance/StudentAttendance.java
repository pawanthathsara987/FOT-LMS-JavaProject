package StudentAttendance;

import Database.DbConnector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class StudentAttendance {
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
    private JButton presenButton;
    private JPanel table2;
    private JScrollBar scrollBar2;
    private JTable showtable;
    private JComboBox medselectcour;
    private JButton medselect;
    private JButton presentageButton;

    private String stuUser;

    public StudentAttendance(String stuUser) {

        this.stuUser = stuUser;

        JFrame frame = new JFrame("ViewCourse");
        frame.add(MainPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1100, 750);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);



        CardLayout cardlayout = new CardLayout();
        cardpanel.setLayout(cardlayout);

        subcard.setLayout(cardlayout);

        cardpanel.add(attendance,"card1");

        subcard.add(viewatten,"card2");
        subcard.add(viewmedical,"card3");

        attenButton.addActionListener(e -> cardlayout.show(subcard,"card2"));
        medicalButton.addActionListener(e -> cardlayout.show(subcard,"card3"));



        // Load courses for the student
        loadCoursesForStudentAtten();

        loadCoursesForStudentMedical();

        // Add action listener to the select button
        selectbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // View attendance for the selected course

                show15WeekAttendance();
            }
        });

        presenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                attendanceView();
            }
        });

        medselect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                show15WeekMedical();
            }
        });


        presentageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                medicalView();
            }
        });
    }

    //-----------------------------------------------------------------------------------------------------------------

    private void loadCoursesForStudentAtten() {

        DbConnector db = new DbConnector();
        Connection conn = db.getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(null, "Failed to connect to database!");
            return;
        }

        try {


            String query = "SELECT DISTINCT ccode FROM attendance WHERE stuid = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, stuUser);  // Use stuUser

            ResultSet rs = ps.executeQuery();
            selectcour.removeAllItems(); // Clear

            while (rs.next()) {
                selectcour.addItem(rs.getString("ccode"));
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error loading student courses: " + e.getMessage());
        }
    }

//------------------------------------------------------------------------------------------------------------------

    private void loadCoursesForStudentMedical() {

        DbConnector db = new DbConnector();
        Connection conn = db.getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(null, "Failed to connect to database!");
            return;
        }

        try {


            String query = "SELECT DISTINCT ccode FROM attendance WHERE stuid = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, stuUser);  // Use stuUser

            ResultSet rs = ps.executeQuery();
            medselectcour.removeAllItems(); // Clear the ComboBox

            while (rs.next()) {
                medselectcour.addItem(rs.getString("ccode"));
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error loading student courses: " + e.getMessage());
        }
    }


//----------------------------------------------------------------------------------------------------------------

    private void attendanceView() {
        String[] columnNames = {"Student ID", "Course Code", "Attendance %"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        showtable.setModel(model);

        JTableHeader header = showtable.getTableHeader();
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


            CallableStatement stmt = conn.prepareCall("{CALL ShowStudentCourseAttendanceByCourseWithout(?, ?)}");
            stmt.setString(1, stuUser);
            stmt.setString(2, selectedCourse);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getString("stuid"),
                        rs.getString("ccode"),
                        rs.getDouble("attendance_percentage")
                });
            }

            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading attendance percentage.");
        }
    }

    //------------------------------------------------------------------------------------------------------------------

    private void show15WeekAttendance() {
        String[] columns = {"Week", "Date", "Type", "Status", "Hours", "Medical Submitted"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
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


            CallableStatement stmt = conn.prepareCall("{CALL ShowStudent15WeekAttendance(?, ?)}");
            stmt.setString(1, stuUser);
            stmt.setString(2, selectedCourse);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("week_number"),
                        rs.getDate("date"),
                        rs.getString("ctype"),
                        rs.getString("status"),
                        rs.getInt("hours"),
                        rs.getString("medical_submit")
                });
            }

            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error showing 15-week attendance.");
        }
    }


//--------------------------------------------------------------------------------------------------------------------


    private void medicalView() {
        String[] columnNames = {"Student ID", "Course Code", "Attendance %"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        showtable.setModel(model);

        JTableHeader header = showtable.getTableHeader();
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

            CallableStatement stmt = conn.prepareCall("{CALL ShowStudentCourseAttendanceByCourse(?, ?)}");
            stmt.setString(1, stuUser);
            stmt.setString(2, selectedCourse);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getString("stuid"),
                        rs.getString("ccode"),
                        rs.getDouble("attendance_percentage")
                });
            }

            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading attendance percentage.");
        }
    }


    //-----------------------------------------------------------------------------------------------------------------

    private void show15WeekMedical() {
        String[] columns = {"Week", "Date", "Type", "Hours", "Status", "Medical Submitted"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        aviewtable.setModel(model);

        JTableHeader header = aviewtable.getTableHeader();
        header.setFont(new Font("SansSerif", Font.BOLD, 14));
        header.setBackground(new Color(204, 255, 204));
        header.setForeground(Color.BLACK);

        String selectedCourse = medselectcour.getSelectedItem() != null ? medselectcour.getSelectedItem().toString() : "";

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


            CallableStatement stmt = conn.prepareCall("{CALL ShowStudent15WeekMedical(?, ?)}");
            stmt.setString(1, stuUser);
            stmt.setString(2, selectedCourse);

            ResultSet rs = stmt.executeQuery();

            boolean foundMedicalSubmitted = false;

            while (rs.next()) {
                String medid = rs.getString("medid");


                System.out.println("medid: " + medid);


                if (medid != null) {
                    foundMedicalSubmitted = true;

                    String status = rs.getString("status");
                    boolean medicalSubmitted = true;

                    model.addRow(new Object[]{
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
}
