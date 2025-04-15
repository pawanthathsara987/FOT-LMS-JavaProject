import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class Lecturer {
    private JPanel MainPanel;
    private JButton addLectureMaterialButton;
    private JButton viewStudentDetailsButton;
    private JButton viewTimeTableButton;
    private JButton UpdateMarksButton;
    private JButton addMarksButton;
    private JButton viewStudentEligibilityButton;
    private JComboBox comboBox1;
    private JButton uploadFileButton;
    private JTextField textField1;
    private JButton uploadButton;
    private JComboBox updateMarkTypeComboBox;
    private JPanel addLecMaterialPanel;
    private JPanel updateMarksPanel;
    private JPanel addMarksPanel;
    private JPanel viewStuEligibilityPanel;
    private JPanel viewTimeTablePanel;
    private JPanel viewStudentDetailsPanel;
    private JPanel parentPanel;
    private JPanel sidePanel;
    private JPanel lecturerLabel;
    private JPanel actionButtons;
    private JTable stuMarksTable;
    private JPanel tablePanel;
    private JTextField enteredMarksField;
    private JPanel marksTypePanel;
    private JPanel subjectDisplayPanel;
    private JPanel updateMarksTitlePanel;
    private JPanel marksEnterPanel;
    private JPanel marksPanel;
    private JButton updateMarksButton1;
    private JLabel studentIDLabel;
    private JPanel studentIDLabelPanel;
    private JPanel studentIDTextFieldPanel;
    private JPanel updateMarksButtonPanel;
    private JPanel timeTablePanel;
    private JPanel studentEligibilityPanel;
    private JTable studentDetailsTable;
    private JTextField stuIDField;
    private JPanel stuDetailsTablePanel;
    private JPanel addMarksLabelPanel;
    private JPanel subjectPanel;
    private JPanel stuIDPanel;
    private JTextField stuIDTextField;
    private JTextField stuCourseTextField;
    private JComboBox addMarkTypeComboBox;
    private JPanel markTypePanel;
    private JPanel courseIDPanel;
    private JPanel enterMarksPanel;
    private JPanel addMarksButtonPanel;
    private JPanel addStuMarksTable;
    private JButton addMarksButton1;
    private JTextField stuMarksField;
    private JTable addStudentMarksTable;
    private JComboBox studentLevelComboBox;

    private String stuid;
    private String marksValue;
    private String markType;
    private String courseID;
    private String stuid2;
    private String marksValue2;
    private String markType2;

    private int stuLevel;

    private Connection conn = null;
    private Statement stmt = null;

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

        UpdateMarksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentPanel.removeAll();
                parentPanel.add(updateMarksPanel);
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

        viewStudentDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentPanel.removeAll();
                parentPanel.add(viewStudentDetailsPanel);
                parentPanel.repaint();
                parentPanel.revalidate();
            }
        });

        updateMarksButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stuid = stuIDField.getText().trim();
                marksValue = enteredMarksField.getText();
                markType = updateMarkTypeComboBox.getSelectedItem().toString();

                if (stuid.isEmpty() || marksValue.isEmpty() || markType.isEmpty()) {
                    JOptionPane.showMessageDialog(parentPanel, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try{
                    double marks = Double.parseDouble(marksValue);
                    updateStuMarks(stuid,marks,markType);

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid number for marks.");
                }
            }

            public void updateStuMarks(String stuid, double marks, String markType){
                DbConnector db = new DbConnector();
                conn = db.getConnection();

                Map<String,String> columns = new HashMap<>();
                columns.put("Quiz 1","quiz_1");
                columns.put("Quiz 2","quiz_2");
                columns.put("Quiz 3","quiz_3");
                columns.put("Assignment Marks","assesment");
                columns.put("Mid Exam Marks","m_marks");
                columns.put("End Theory Marks","f_theory");
                columns.put("End Practical Marks","f_practical");

                String columnName = columns.get(markType);

                if (columnName != null) {
                    String sql = "UPDATE mark SET " + columnName + " = ? WHERE stuid = ?";
                    try {
                        PreparedStatement pstmt = conn.prepareStatement(sql);
                        pstmt.setDouble(1, marks);
                        pstmt.setString(2, stuid);
                        int rows = pstmt.executeUpdate();

                        if (rows > 0) {
                            loadMarks();
                            JOptionPane.showMessageDialog(null, "Mark updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "Student ID not found!", "Warning", JOptionPane.WARNING_MESSAGE);
                        }

                    } catch (SQLException e) {
                        System.out.println("Statement error: " + e.getMessage());
                        JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid mark type selected.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }


            public void loadMarks(){
                DbConnector db = new DbConnector();
                conn = db.getConnection();

                System.out.println("loadMarks() called...");

                String sql = "select * from mark";

                try {
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    ResultSet rs = pstmt.executeQuery();

                    String[] columnNames = {"Stu_ID", "Cour_Code", "Quiz 1", "Quiz 2", "Quiz 3", "Assignments", "Mid Marks", "End Theory", "End Practical"};
                    DefaultTableModel model = new DefaultTableModel(columnNames,0);

                    while (rs.next()) {
                        Object[] row = {
                                rs.getString("stuid"),
                                rs.getString("ccode"),
                                rs.getDouble("quiz_1"),
                                rs.getDouble("quiz_2"),
                                rs.getDouble("quiz_3"),
                                rs.getDouble("assesment"),
                                rs.getDouble("m_marks"),
                                rs.getDouble("f_theory"),
                                rs.getDouble("f_practical")
                        };
                        model.addRow(row);
                    }
                    stuMarksTable.setModel(model);
                    stuMarksTable.repaint();
                    stuMarksTable.revalidate();

                    stuIDField.setText("");
                    enteredMarksField.setText("");
                    updateMarkTypeComboBox.setSelectedIndex(0);

                } catch (SQLException e) {
                    System.out.println("Statement error" + e.getMessage());
                }
            }
        });

        addMarksButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stuid2 = stuIDTextField.getText().trim();
                courseID = stuCourseTextField.getText().trim();
                markType2 = addMarkTypeComboBox.getSelectedItem().toString();
                marksValue2 = stuMarksField.getText();

                if (stuid2.isEmpty() || marksValue2.isEmpty() || markType2.isEmpty() || courseID.isEmpty()) {
                    JOptionPane.showMessageDialog(parentPanel, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try{
                    double marks = Double.parseDouble(marksValue2);
                    addStuMarks(stuid2,courseID,markType2,marks);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid number for marks.");
                }
            }

            public void addStuMarks(String stuid2, String courseID, String markType2, double marks){
                DbConnector db = new DbConnector();
                conn = db.getConnection();

                Map<String,String> columns = new HashMap<>();
                columns.put("Quiz 1","quiz_1");
                columns.put("Quiz 2","quiz_2");
                columns.put("Quiz 3","quiz_3");
                columns.put("Assignment Marks","assesment");
                columns.put("Mid Exam Marks","m_marks");
                columns.put("End Theory Marks","f_theory");
                columns.put("End Practical Marks","f_practical");

                String columnName = columns.get(markType2);

                if (columnName != null) {
                    String sql = "INSERT INTO mark(stuid,ccode," + columnName + ") VALUES (?,?,?)";
                    try {
                        PreparedStatement pstmt = conn.prepareStatement(sql);
                        pstmt.setString(1, stuid2);
                        pstmt.setString(2, courseID);
                        pstmt.setDouble(3, marks);
                        int rows = pstmt.executeUpdate();
                        if (rows > 0) {
                            loadAddMarksTable();
                            JOptionPane.showMessageDialog(null, "Mark Insertion successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (SQLException e) {
                        System.out.println("Statement error: " + e.getMessage());
                        JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

            public void loadAddMarksTable(){
                DbConnector db = new DbConnector();
                conn = db.getConnection();

                System.out.println("loadMarks() called...");

                String sql = "select * from mark";

                try {
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    ResultSet rs = pstmt.executeQuery();

                    String[] columnNames2 = {"Stu_ID", "Cour_Code", "Quiz 1", "Quiz 2", "Quiz 3", "Assignments", "Mid Marks", "End Theory", "End Practical"};
                    DefaultTableModel model2 = new DefaultTableModel(columnNames2,0);

                    while (rs.next()) {
                        Object[] row = {
                              rs.getString("stuid"),
                              rs.getString("ccode"),
                              rs.getDouble("quiz_1"),
                              rs.getDouble("quiz_2"),
                              rs.getDouble("quiz_3"),
                              rs.getDouble("assesment"),
                              rs.getDouble("m_marks"),
                              rs.getDouble("f_theory"),
                              rs.getDouble("f_practical")
                        };
                        model2.addRow(row);
                    }

                    addStudentMarksTable.setModel(model2);
                    addStudentMarksTable.repaint();
                    addStudentMarksTable.revalidate();

                    stuIDTextField.setText("");
                    stuMarksField.setText("");
                    stuCourseTextField.setText("");
                    addMarkTypeComboBox.setSelectedIndex(0);

                } catch (SQLException e) {
                    System.out.println("Statement error: " + e.getMessage());
                }

            }
        });

        studentLevelComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stuLevel = studentLevelComboBox.getSelectedIndex();

                if (stuLevel != 0) {
                    showStudentDetails(stuLevel);
                }
            }

            public void showStudentDetails(int stuLevel){
                DbConnector db = new DbConnector();
                conn = db.getConnection();

                String sql = "SELECT * FROM student WHERE stu_level=?";

                try {
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setInt(1, stuLevel);
                    ResultSet rs = pstmt.executeQuery();

                    String[] columnNames = {"stuid","username","password","fname","lname","email","dob","pnumber","ppicture","stu_level"};
                    DefaultTableModel model = new DefaultTableModel(columnNames,0);

                    while (rs.next()) {
                        Object[] row = {
                                rs.getString("stuid"),
                                rs.getString("username"),
                                rs.getString("password"),
                                rs.getString("fname"),
                                rs.getString("lname"),
                                rs.getString("email"),
                                rs.getString("dob"),
                                rs.getString("pnumber"),
                                rs.getString("ppicture"),
                                rs.getInt("stu_level")
                        };
                        model.addRow(row);

                        studentDetailsTable.setModel(model);
                        studentDetailsTable.repaint();
                        studentDetailsTable.validate();
                    }
                } catch (SQLException e) {
                    System.out.println("Statement error: " + e.getMessage());
                }
            }
        });
    }
}
