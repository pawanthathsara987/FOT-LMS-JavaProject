package StuCourses;

import Database.DbConnector;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StuCourses {
    private JPanel rootpanel;
    private JPanel mainPanel;
    private JPanel toppanel;
    private JPanel bottompanel;
    private JPanel coursepanel; // Make sure you set this field in Form Designer

    public StuCourses() {
        JFrame frame = new JFrame("Student Courses");
        frame.setContentPane(rootpanel); // root panel from the form
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1100, 750);
        frame.setLocationRelativeTo(null);

        // 👉 Create a scroll pane with vertical scroll
        JScrollPane scrollPane = new JScrollPane(coursepanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // 👉 Replace coursepanel inside the mainPanel with scrollPane
        coursepanel.setLayout(new BoxLayout(coursepanel, BoxLayout.Y_AXIS));
        mainPanel.add(scrollPane, BorderLayout.CENTER); // assuming mainPanel uses BorderLayout

        frame.setVisible(true);

        loadCourses(); // Call the function to load buttons
    }


    private void loadCourses() {
        DbConnector dbc = new DbConnector();
        Connection conn = dbc.getConnection();

        String sql = "SELECT cname FROM course"; // Replace with your real table name

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            coursepanel.removeAll(); // Clear existing buttons before adding new

            coursepanel.setLayout(new BoxLayout(coursepanel, BoxLayout.Y_AXIS));

            while (rs.next()) {
                String courseName = rs.getString("cname");

                JButton btn = new JButton(courseName);
                btn.setAlignmentX(Component.CENTER_ALIGNMENT); // align to center
                btn.setMaximumSize(new Dimension(300, 60)); // button width/height
                btn.setPreferredSize(new Dimension(300, 60));

                btn.addActionListener(e ->
                        JOptionPane.showMessageDialog(null, "You clicked: " + courseName)
                );

                coursepanel.add(Box.createVerticalStrut(10)); // spacing
                coursepanel.add(btn);
            }


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Failed to load courses: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StuCourses());
    }
}
