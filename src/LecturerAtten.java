import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LecturerAtten {
    private JPanel MainPanel;
    private JLabel name;
    private JLabel picture;
    private JButton addLectureMaterialButton;
    private JButton attendanceButton;
    private JButton addMarksButton;
    private JButton viewStudentDetailsButton;
    private JButton viewStudentEligibilityButton;
    private JButton viewTimeTableButton;
    private JButton viewMedicalButton;
    private JPanel cardpanel;
    private JPanel leftpanel;
    private JPanel attendance;
    private JPanel title;
    private JPanel body;
    private JPanel table;
    private JPanel subcard;
    private JPanel viewatten;
    private JPanel viewmedical;
    private JScrollBar scrollBar1;
    private JComboBox selectcour;
    private JButton attendanceButton1;
    private JButton medicalButton;
    private JTable aviewtable;
    private JLabel stuname;
    private JButton selectbtn;
    private JButton presenButton;
    private JPanel table2;
    private JScrollBar scrollBar2;
    private JTable showtable;

    private String stuUser;

    public LecturerAtten(String stuUser, String stuName) {

        stuname.setText(stuName);
        this.stuUser = stuUser;

        JFrame frame = new JFrame("ViewCourse");
        frame.setContentPane(MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1400, 750);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Initialize the table
        attendanceViewTable();
        // Load courses for the student
        loadCoursesForStudent();

        // Add action listener to the select button
        selectbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // View attendance for the selected course
                attendanceView();
            }
        });

        presenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                show15WeekAttendance();
            }
        });
    }

    private void loadCoursesForStudent() {
        try {
            DbConnector db = new DbConnector();
            Connection conn = db.getConnection();

            String query = "SELECT DISTINCT ccode FROM attendance WHERE stuid = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, stuUser);  // Use stuUser here to refer to logged-in student's ID

            ResultSet rs = ps.executeQuery();
            selectcour.removeAllItems(); // Clear the ComboBox before adding new courses

            while (rs.next()) {
                selectcour.addItem(rs.getString("ccode"));
            }

            rs.close();
            ps.close();
            db.close();  // Close the connection
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error loading student courses: " + e.getMessage());
        }
    }

    private void attendanceView() {
        String[] columnNames = {"Student ID", "Course Code", "Attendance %"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        aviewtable.setModel(model);

        String selectedCourse = selectcour.getSelectedItem() != null ? selectcour.getSelectedItem().toString() : "";

        if (selectedCourse.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please select a course.");
            return;
        }

        try {
            DbConnector db = new DbConnector();
            Connection conn = db.getConnection();

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
            db.close();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading attendance percentage.");
        }
    }

    private void show15WeekAttendance() {
        String[] columns = {"Week", "Date", "Type", "Status", "Hours", "Medical Submitted"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        showtable.setModel(model);

        String selectedCourse = selectcour.getSelectedItem() != null ? selectcour.getSelectedItem().toString() : "";

        if (selectedCourse.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please select a course.");
            return;
        }

        try {
            DbConnector db = new DbConnector();
            Connection conn = db.getConnection();

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
            db.close();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error showing 15-week attendance.");
        }
    }




    private void attendanceViewTable() {
        // Initialize table with column names
        String[] latent = {"Student ID", "Course Code", "Date", "Type", "Present", "Hours", "Medical Submitted"};
        DefaultTableModel model = new DefaultTableModel(null, latent);
        aviewtable.setModel(model);
    }


    public static void main(String[] args) {
        new LecturerAtten("a0913", "John");
    }
}
