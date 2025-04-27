package TimeTable;

import Database.DbConnector;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TimeTable {
    private JPanel MainPanel;
    private JButton downloadButton;
    private JTextField tableName;
    private JComboBox levelTable;
    private JFrame frame;

    private String department;

    public TimeTable(String depid){
        frame = new JFrame("Time Table");
        frame.add(MainPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);

        showTimetable(depid);
        downloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                downloadTimetable();
            }
        });
    }

    public void showTimetable(String depid) {
        DbConnector db = new DbConnector();
        Connection conn = db.getConnection();
        if (conn == null) {
            return;
        }

        if (depid != null || !depid.trim().isEmpty()) {
            switch (depid) {
                case "D001":
                    department = "ICT";
                    break;
                case "D002":
                    department = "ET";
                    break;
                case "D003":
                    department = "BST";
                    break;
                default:
                    department = "ICT";
                    break;
            }
        }

        String sql = "SELECT * FROM timetable WHERE depid = ?";

        try(PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, depid);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                tableName.setText(rs.getString("table_name"));
            }
            else {
                JOptionPane.showMessageDialog(null, "No timetable found for this department.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error showing timetable: " + e.getMessage(), e);
        }
    }

    public void downloadTimetable() {
        String filename = tableName.getText();
        System.out.println(filename);

        // Use the absolute path to the Resources directory
        Path currentDir = Paths.get(System.getProperty("user.dir"));
        Path basePath = currentDir.resolve(Paths.get("Resources", "TimeTable", department));
        Path source = basePath.resolve(filename);

        // Debugging: Print the resolved source path
        System.out.println("Attempting to access source file: " + source.toAbsolutePath());
        System.out.println("File name from table: " + filename);

        // Sanitize file name to prevent path traversal
        if (!source.getFileName().toString().equals(filename)) {
            JOptionPane.showMessageDialog(frame, "Invalid file name");
            return;
        }

        try {
            // Check if source file exists
            if (!Files.exists(source)) {
                throw new FileNotFoundException("File not found: " + source.toAbsolutePath());
            }

            // Define destination path (e.g., user's home directory or a specific folder)
            Path downloadDir = Paths.get(System.getProperty("user.home"), "Downloads", "LMS", "TimeTable");
            Path destination = downloadDir.resolve(filename);

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


    public static void main(String[] args) {
        new TimeTable("D001");
    }
}
