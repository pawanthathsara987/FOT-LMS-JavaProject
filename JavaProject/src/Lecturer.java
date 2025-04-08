import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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
    private JTable stuMarksTable;
    private JPanel tablePanel;
    private JTextField enteredMarksField;
    private JPanel marksTypePanel;
    private JPanel subjectDisplayPanel;
    private JPanel addMarksTitlePanel;
    private JPanel marksEnterPanel;
    private JPanel marksPanel;
    private JButton addMarksButton1;
    private JLabel studentIDLabel;
    private JPanel studentIDLabelPanel;
    private JPanel studentIDTextFieldPanel;
    private JPanel addMarksButtonPanel;
    private JPanel timeTablePanel;
    private JPanel studentEligibilityPanel;
    private JTable studentDetailsTable;
    private JPanel stuDetailsTablePanel;

    public Lecturer() {

        JFrame frame = new JFrame("Lecturer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(MainPanel);
        frame.setSize(1400,750);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        marksTable();
        studentDetailsTable();

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

    public void studentDetailsTable(){
        Object[] [] data = {};

        studentDetailsTable.setModel(new DefaultTableModel(
                data,
                new String [] {"Username","First Name","Last Name","Email","Phone Number","Date of Birth"}
        ));
    }
    public void marksTable() {
        Object[] [] data = {
                {"TG/2022/1365","KPGS SANDARUWAN",8.75,null,38.50,18.00,55.55},
                {"TG/2022/1366","SDP LAKSHAN",9.00,null,35.55,20.00,60.45},
                {"TG/2022/1367","SAPT SAMARATHUNGA",7.50,null,36.75,19.00,57.00},
        };
        stuMarksTable.setModel(new DefaultTableModel(
                data,
                new String [] {"Stu_ID","Stu_Name","Quiz Marks","Assignments Marks","Mid Marks","End Practical","End Theory"}
        ));

//        TableColumnModel columns = stuMarksTable.getColumnModel();
//        columns.getColumn(0).setMinWidth(100);
//        columns.getColumn(0).setMaxWidth(100);
//        columns.getColumn(1).setMinWidth(200);
//        columns.getColumn(1).setMaxWidth(200);
//        columns.getColumn(2).setMinWidth(150);
//        columns.getColumn(2).setMaxWidth(150);
//        columns.getColumn(3).setMinWidth(150);
//        columns.getColumn(3).setMaxWidth(150);
//        columns.getColumn(4).setMinWidth(150);
//        columns.getColumn(4).setMaxWidth(150);
//        columns.getColumn(5).setMinWidth(150);
//        columns.getColumn(5).setMaxWidth(150);
//        columns.getColumn(6).setMinWidth(150);
//        columns.getColumn(6).setMaxWidth(150);
    }
}
