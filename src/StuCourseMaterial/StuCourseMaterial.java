package StuCourseMaterial;

import Database.DbConnector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.*;

public class StuCourseMaterial {
    private JPanel form;
    private JPanel rootPanel;
    private JPanel mainpanel;
    private JPanel top;
    private JLabel label1;
    private JPanel middle;
    private JComboBox courseName;
    private JButton checkEligibility;
    private JPanel bottom;
    private JTable materialInfo;
    private JButton downloadNote;
    private JPanel first_panel;
    private JComboBox materialList;
    private JButton searchMaterial;
    private JFrame frame;

    private String stuUsername;
    private String depid = null;
    private String level = null;
    private String fname = null;
    private String[] course_list = new String[10];

    public StuCourseMaterial(String stuUsername) {
        frame = new JFrame("Student Course Material");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(first_panel);
        frame.setSize(1100, 750);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        this.stuUsername = stuUsername;

        showStudentCourses();

        searchMaterial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int material = materialList.getSelectedIndex();
                int cname = courseName.getSelectedIndex();
                String ccode = course_list[cname];

                if (material == 0) {
                    showStuLecnotes(course_list[cname]);
                } else if (material == 1) {
                    showStuQuizes(course_list[cname]);
                } else if (material == 2) {
                    showStuAssignments(course_list[cname]);
                }


            }
        });
        materialInfo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                fname = materialInfo.getModel().getValueAt(materialInfo.getSelectedRow(), 2).toString();
                System.out.println(fname);
            }
        });
        materialList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (materialList.getSelectedIndex() == 0) {
                    downloadNote.setVisible(true);
                } else {
                    downloadNote.setVisible(false);
                }
            }
        });
        downloadNote.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                downloadNote();
            }
        });
    }

    public void showStudentCourses() {
        String getStuDepartment_sql = "SELECT depid, stulevel FROM student WHERE username = ?";
        String showStudentCourses_sql = "SELECT ccode, cname FROM course WHERE depid = ? AND clevel = ?";

        if (materialList.getSelectedIndex() == 0) {
            downloadNote.setVisible(true);
        }
        DbConnector db = new DbConnector();
        try (Connection conn = db.getConnection();
             PreparedStatement stmt1 = conn.prepareStatement(getStuDepartment_sql);
             PreparedStatement stmt2 = conn.prepareStatement(showStudentCourses_sql)) {

            if (conn == null) {
                JOptionPane.showMessageDialog(frame, "Failed to connect to database");
                return;
            }

            // Fetch department ID and level
            stmt1.setString(1, stuUsername);
            ResultSet rs1 = stmt1.executeQuery();
            if (rs1.next()) {
                depid = rs1.getString("depid");
                level = rs1.getString("stulevel");
            } else {
                JOptionPane.showMessageDialog(frame, "No department or level found for username: " + stuUsername);
                return;
            }

            // Fetch courses
            stmt2.setString(1, depid);
            stmt2.setString(2, level);
            ResultSet rs2 = stmt2.executeQuery();

            // Populate courseName JComboBox
            courseName.removeAllItems();
            int i = 0;
            while (rs2.next()) {
                String course = rs2.getString("cname");
                course_list[i] = rs2.getString("ccode");
                courseName.addItem(course);
                i++;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "Failed to load courses: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void showStuQuizes(String ccode) {
        String showStuQuiz_sql = "SELECT ccode, quiz_number, quiz_link FROM quiz WHERE ccode = ?";
        for (int i = 0; i < course_list.length; i++) {
            if (courseName.getSelectedItem().toString().equals(course_list[i])) {
                ccode = course_list[i];
            }
        }
        System.out.println(ccode);

        DbConnector db = new DbConnector();
        Connection conn = db.getConnection();

        if (conn == null) {
            return;
        }

        try(PreparedStatement stmt = conn.prepareStatement(showStuQuiz_sql)) {
            stmt.setString(1, ccode);
            ResultSet rs = stmt.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            DefaultTableModel model = (DefaultTableModel) materialInfo.getModel();

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

    public void showStuAssignments(String ccode) {
        String showStuAssignment_sql = "SELECT ccode, week, file_name FROM assessments WHERE ccode = ?";

        DbConnector db = new DbConnector();
        Connection conn = db.getConnection();

        if (conn == null) {
            return;
        }

        try(PreparedStatement stmt = conn.prepareStatement(showStuAssignment_sql)) {
            stmt.setString(1, ccode);
            ResultSet rs = stmt.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            DefaultTableModel model = (DefaultTableModel) materialInfo.getModel();

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

    public void showStuLecnotes(String ccode) {
        String showStuLecnotes_sql = "SELECT ccode, week, file_name FROM lecNotes WHERE ccode = ?";

        DbConnector db = new DbConnector();
        Connection conn = db.getConnection();

        if (conn == null) {
            return;
        }

        try(PreparedStatement stmt = conn.prepareStatement(showStuLecnotes_sql)) {
            stmt.setString(1, ccode);
            ResultSet rs = stmt.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            DefaultTableModel model = (DefaultTableModel) materialInfo.getModel();

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

    public void downloadNote() {
        if (fname == null || fname.trim().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "No file selected");
            return;
        }

        // Use the absolute path to the Resources directory
        Path currentDir = Paths.get(System.getProperty("user.dir"));
        Path basePath = currentDir.resolve(Paths.get("Resources", "Lecturer", "lectureNotes"));
        Path source = basePath.resolve(fname);

        // Debugging: Print the resolved source path
        System.out.println("Attempting to access source file: " + source.toAbsolutePath());
        System.out.println("File name from table: " + fname);

        // Sanitize file name to prevent path traversal
        if (!source.getFileName().toString().equals(fname)) {
            JOptionPane.showMessageDialog(frame, "Invalid file name");
            return;
        }

        try {
            // Check if source file exists
            if (!Files.exists(source)) {
                throw new FileNotFoundException("File not found: " + source.toAbsolutePath());
            }

            // Define destination path (e.g., user's home directory or a specific folder)
            Path downloadDir = Paths.get(System.getProperty("user.home"), "Downloads", "CourseMaterials");
            Path destination = downloadDir.resolve(fname);

            // Create parent directories if they don't exist
            Files.createDirectories(downloadDir);

            // Copy file with REPLACE_EXISTING option to overwrite if necessary
            Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
            JOptionPane.showMessageDialog(frame, "File downloaded successfully to: \n" + destination);

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(frame, e.getMessage());
        } catch (SecurityException e) {
            JOptionPane.showMessageDialog(frame, "Permission denied: " + e.getMessage());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Failed to download file: " + e.getMessage());
        }
    }
}
