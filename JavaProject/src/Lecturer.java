import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.TableView;
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
    private JLabel studentIDPanel;

    public Lecturer() {

        JFrame frame = new JFrame("Lecturer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(MainPanel);
        frame.setSize(1400,750);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        createTable();

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

    public void createTable() {
        stuMarksTable.setModel(new DefaultTableModel(
                null,
                new String [] {"Stu_ID","Stu_Name","Quiz Marks","Assignments Marks","Mid Marks","End Practical","End Theory"}
        ));

        TableColumnModel columns = stuMarksTable.getColumnModel();
        columns.getColumn(0).setMinWidth(100);
        columns.getColumn(0).setMaxWidth(100);
        columns.getColumn(1).setMinWidth(200);
        columns.getColumn(1).setMaxWidth(200);
        columns.getColumn(2).setMinWidth(150);
        columns.getColumn(2).setMaxWidth(150);
        columns.getColumn(3).setMinWidth(150);
        columns.getColumn(3).setMaxWidth(150);
        columns.getColumn(4).setMinWidth(150);
        columns.getColumn(4).setMaxWidth(150);
        columns.getColumn(5).setMinWidth(150);
        columns.getColumn(5).setMaxWidth(150);
        columns.getColumn(6).setMinWidth(150);
        columns.getColumn(6).setMaxWidth(150);
    }
}
