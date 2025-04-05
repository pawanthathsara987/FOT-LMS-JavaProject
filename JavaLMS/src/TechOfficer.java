import javax.swing.*;

public class TechOfficer {
    private JPanel MainPanel;
    private JLabel name;
    private JLabel picture;
    private JButton addAttendanceButton;
    private JButton viewAttendanceButton;
    private JButton addMedicalButton;
    private JButton viewMedicalButton;
    private JButton timeTableButton;
    private JButton noticeButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JButton button7;
    private JButton button8;
    private JPanel cardpanel;
    private JPanel card2panel;
    private JPanel card1panel;
    private JPanel left;
    private JPanel right;
    private JLabel addlabel;
    private JPanel titlepanel;
    private JLabel viewlabel;
    private JComboBox comboBox3;
    private JButton deleteButton;
    private JButton editButton;
    private JScrollBar scrollBar1;
    private JTable table1;
    private JButton submitButton;
    private JButton clearButton;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JComboBox comboBox4;
    private JTextField textField8;
    private JTextArea textArea1;
    private JPanel jpanel;
    private JPanel card3panel;

    public TechOfficer() {
        JFrame frame = new JFrame("Add Attendance");
        frame.setContentPane(MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(1400, 750);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);







    }

    public static void main(String[] args) {
        new TechOfficer();

    }
}
