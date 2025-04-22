

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

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
    private JTable showtable;

    public LecturerAtten() {
        JFrame frame = new JFrame("ViewCourse");
        frame.setContentPane(MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1400, 750);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        createUIComponents();
    }





    private void createUIComponents() {
        // TODO: place custom component creation code here
        String[] columns = {"Course ID", "Course Name", "Credit", "Type", "Level"};
        Object[][] data = {}; // or populate from DB later

        DefaultTableModel model = new DefaultTableModel(data, columns);
        showtable.setModel(model);
    }

    public static void main(String[] args) {
        new LecturerAtten();
    }
}


