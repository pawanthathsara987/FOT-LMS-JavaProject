package StuViewGrade;

import Database.DbConnector;
import ViewNotice.ViewNotice;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StuViewGrade {
    private JPanel rootPanel;
    private JPanel MainPanal;
    private JPanel title;
    private JPanel Table;
    private JPanel middle;
    private JTable table1;
    private JLabel usernameLabel;
    private JLabel nameLabel;
    private JLabel gpa1;
    private JLabel gpa2;
    private JLabel gpa1Label;
    private JLabel gpa2Label;
    private JFrame frame;

    private Connection conn;
    private String level;
    private String depid;

    public StuViewGrade(String stuUsername, String sid, String fullname, String depid, String level) {
        this.usernameLabel.setText(stuUsername);
        this.nameLabel.setText(fullname);
        this.level = level;
        this.depid = depid;

        frame = new JFrame("View Grade");
        frame.add(rootPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1100, 750);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        createGradeTable(sid);
    }

    public void createGradeTable(String sid) {
        List<String> courseCodes = new ArrayList<>();
        DbConnector db = new DbConnector();
        conn = db.getConnection();

        if (conn == null) {
            JOptionPane.showMessageDialog(null, "Database Connection Error!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String sql1 = "SELECT ccode FROM course WHERE clevel = ? AND depid = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql1)) {
            stmt.setString(1, level);
            stmt.setString(2, depid);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                courseCodes.add(rs.getString("ccode"));
                System.out.println("Course Code: " + rs.getString("ccode"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error fetching courses: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            try {
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                System.out.println("Error closing connection: " + ex.getMessage());
            }
            return;
        }

        DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Course Code", "Grade"}, 0);
        table1.setModel(tableModel);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table1.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table1.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);

        table1.getColumnModel().getColumn(0).setPreferredWidth(150);
        table1.getColumnModel().getColumn(1).setPreferredWidth(100);

        // Variables for GPA calculation
        double totalGradePoints = 0.0;
        int totalCredits = 0;

        String sql2 = "CALL calculate_grades_level2_ict_course_all(?, ?)";
        for (String courseCode : courseCodes) {
            try (PreparedStatement stmt = conn.prepareStatement(sql2)) {
                stmt.setString(1, courseCode);
                stmt.setString(2, sid);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    String grade = rs.getString("grade") != null ? rs.getString("grade") : "N/A";
                    tableModel.addRow(new Object[]{courseCode, grade});
                    System.out.println("Course: " + courseCode + ", Grade: " + grade);

                    // Calculate credits (last digit of course code)
                    int credit = Character.getNumericValue(courseCode.charAt(courseCode.length() - 1));
                    if (credit < 0 || credit > 9) {
                        System.out.println("Invalid credit for course: " + courseCode);
                        continue;
                    }

                    // Map grade to grade points
                    double gradePoint = getGradePoint(grade);
                    if (gradePoint >= 0) { // Valid grade
                        totalGradePoints += gradePoint * credit;
                        totalCredits += credit;
                    }
                } else {
                    tableModel.addRow(new Object[]{courseCode, "N/A"});
                    System.out.println("Course: " + courseCode + ", Grade: N/A");
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error fetching grade for " + courseCode + ": " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        // Calculate and display GPA
        if (totalCredits > 0) {
            double gpa = totalGradePoints / totalCredits;
            System.out.println("Total Grade Points: " + totalGradePoints);
            System.out.println("Total Credits: " + totalCredits);
            System.out.printf("GPA: %.2f\n", gpa);
            gpa1Label.setText(String.format("%.2f", gpa));
            gpa2Label.setText(String.format("%.2f", gpa));
        } else {
            JOptionPane.showMessageDialog(null, "No valid grades to calculate GPA.", "GPA", JOptionPane.WARNING_MESSAGE);
        }

        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Error closing connection: " + e.getMessage());
        }
    }


    private double getGradePoint(String grade) {
        switch (grade) {
            case "A":
                return 4.0;
            case "B+":
                return 3.3;
            case "B":
                return 3.0;
            case "B-":
                return 2.7;
            case "C":
                return 2.0;
            case "D+":
                return 1.3;
            case "E":
                return 0.0;
            default:
                return -1; // Invalid grade (e.g., "N/A")
        }
    }


    public static void main(String[] args) {
        new StuViewGrade("TG0042", "S0041", "sandeepa lakshan", "D001", "Level 2");
    }
}