import javax.swing.*;

public class TechOfficer {
    private JPanel MainPanel;
    private JLabel name;
    private JLabel picture;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JButton button7;
    private JButton button8;

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
