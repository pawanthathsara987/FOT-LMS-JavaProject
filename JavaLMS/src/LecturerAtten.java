

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
    private JPanel attendance;
    private JPanel title;
    private JPanel body;
    private JPanel table;
    private JPanel subcard;
    private JPanel viewatten;
    private JPanel viewmedical;
    private JScrollBar scrollBar1;
    private JComboBox comboBox1;
    private JButton attendanceButton1;
    private JButton medicalButton;
    private JTable aviewtable;
    private JTable showtable;

    public LecturerAtten() {
        JFrame frame = new JFrame("ViewCourse");
        frame.setContentPane(MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1400, 750);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        attendanceViewTable();
    }

    private void attendanceViewTable() {
        String[] latent = {"Student ID", "Course Code", "Date", "Type", "Present", "Hours", "Medical Submitted"};
        DefaultTableModel model = new DefaultTableModel(null, latent);
        aviewtable.setModel(model);
    }







    public static void main(String[] args) {
        new LecturerAtten();
    }
}


