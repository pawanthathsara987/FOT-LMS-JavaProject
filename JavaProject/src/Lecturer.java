import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Lecturer {
    private JPanel MainPanel;
    private JButton addLectureMaterialButton;
    private JButton viewMedicalButton;
    private JButton viewTimeTableButton;
    private JButton addMarksButton;
    private JButton viewStudentDetailsButton;
    private JButton viewStudentEligibilityButton;
    private JComboBox comboBox1;
    private JButton uploadFileButton;
    private JTextField textField1;
    private JButton uploadButton;
    private JComboBox comboBox2;
    private JPanel addLecMaterialPanel;
    private JPanel addMarksPanel;
    private JPanel viewStuDetailsPanel;
    private JPanel viewStuEligibilityPanel;
    private JPanel viewTimeTablePanel;
    private JPanel viewMedicalPanel;
    private JPanel parentPanel;
    private JPanel sidePanel;
    private JPanel lecturerLabel;
    private JPanel actionButtons;

    public Lecturer() {

        JFrame frame = new JFrame("Lecturer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(MainPanel);
        frame.setSize(1400,750);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        addLectureMaterialButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentPanel.removeAll();
                parentPanel.add(addLecMaterialPanel);
                parentPanel.repaint();
                parentPanel.revalidate();
            }
        });
        addMarksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentPanel.removeAll();
                parentPanel.add(addMarksPanel);
                parentPanel.repaint();
                parentPanel.revalidate();
            }
        });
        viewStudentDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentPanel.removeAll();
                parentPanel.add(viewStuDetailsPanel);
                parentPanel.repaint();
                parentPanel.revalidate();
            }
        });
        viewStudentEligibilityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentPanel.removeAll();
                parentPanel.add(viewStuEligibilityPanel);
                parentPanel.repaint();
                parentPanel.revalidate();
            }
        });
        viewTimeTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentPanel.removeAll();
                parentPanel.add(viewTimeTablePanel);
                parentPanel.repaint();
                parentPanel.revalidate();
            }
        });
        viewMedicalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentPanel.removeAll();
                parentPanel.add(viewMedicalPanel);
                parentPanel.repaint();
                parentPanel.revalidate();
            }
        });
    }
}
