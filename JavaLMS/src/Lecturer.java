import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class Lecturer {
    private JPanel MainPanel;
    private JButton addLectureMaterialButton;
    private JButton viewStudentDetailsButton;
    private JButton viewGradesAndGPAButton;
    private JButton viewTimeTableButton;
    private JButton UpdateMarksButton;
    private JButton addMarksButton;
    private JButton viewStudentEligibilityButton;
    private JComboBox stuLevelComboBox;
    private JButton uploadLecNoteButton;
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
    private JButton uploadAssingmentButton;
    private JComboBox lecWeekComboBox;
    private JPanel viewGradesAndGPA;

    private JButton VIEWButton;
    private JLabel lecNameLabel;
    private JPanel actionButtons;
    private JPanel addLectureMaterialButtonPanel;
    private JPanel UpdateMarksButtonPanel;
    private JPanel viewStudentEligibilityButtonPanel;
    private JPanel viewGradesAndGPAButtonPanel;
    private JPanel viewTimeTableButtonPanel;
    private JPanel viewStudentDetailsButtonPanel;
    private JPanel titlePanel;
    private JPanel lecNamePanel;
    private JPanel logoutButtonPanel;
    private JButton logoutButton;
    private JComboBox lecCourseComboBox;
    private JLabel courseNameLabel;


    private String stuid;
    private String marksValue;
    private String markType;
    private String courseID;
    private String stuid2;
    private String marksValue2;
    private String markType2;
    private String lecUsername;
    private String depid;

    private String stuLevel1;

    private Connection conn = null;
    private Statement stmt = null;

    public Lecturer() {
        JFrame frame = new JFrame("Lecturer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(MainPanel);
        frame.setSize(1400,750);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
    public Lecturer(String lecUsername, String lecName) {

        this();
        lecNameLabel.setText(lecName);
        this.lecUsername = lecUsername;

        //Add Lec Materials
        addLectureMaterialButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentPanel.removeAll();
                parentPanel.add(addLecMaterialPanel);
                parentPanel.repaint();
                parentPanel.revalidate();
            }
        });
        uploadLecNoteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                int result = chooser.showOpenDialog(null);

                if (result == JFileChooser.APPROVE_OPTION) {
                    File lecNote = chooser.getSelectedFile();
                    String stuLevel = (String) stuLevelComboBox.getSelectedItem();
                    String lecWeek = (String) lecWeekComboBox.getSelectedItem();

                    if (!stuLevel.equals("") && !lecWeek.equals("")) {
                        saveLecNote(lecNote,stuLevel,lecWeek);
                    }else {
                        JOptionPane.showMessageDialog(null, "Please select both level and week.");
                    }
                }
            }

            public void saveLecNote(File file, String stuLevel, String lecWeek){

                File destination = new File("JavaProject/");
                if(!destination.exists()){
                    destination.mkdir();
                }


                try {
                    String destinationPath = destination.getAbsolutePath() + "/" + file.getName();
                    Files.copy(file.toPath(), Paths.get(destinationPath), StandardCopyOption.REPLACE_EXISTING);

                    DbConnector db = new DbConnector();
                    conn = db.getConnection();

                    String sql = "INSERT INTO lecture_material(level,week,file_name,file_path) VALUES (?,?,?,?)";
                    try {
                        PreparedStatement pstmt = conn.prepareStatement(sql);
                        pstmt.setString(1, stuLevel);
                        pstmt.setString(2, lecWeek);
                        pstmt.setString(3, file.getName());
                        pstmt.setString(4, destinationPath);
                        int rows = pstmt.executeUpdate();
                        if (rows > 0) {
                            JOptionPane.showMessageDialog(null, "Lecture note uploaded successfully!");
                        }
                    } catch (SQLException e) {
                        System.out.println("Statement error: " + e.getMessage());
                    }

                } catch (IOException e) {
                    System.out.println("File could not be copied" + e.getMessage());
                }

            }
        });
        uploadAssingmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.showOpenDialog(null);
            }
        });

        //Update Marks
        UpdateMarksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentPanel.removeAll();
                parentPanel.add(updateMarksPanel);
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

        //ADD MARKS
        addMarksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentPanel.removeAll();
                parentPanel.add(addMarksPanel);
                parentPanel.repaint();
                parentPanel.revalidate();

                showLecturerCourses();
            }

            public void showLecturerCourses(){
                lecCourseComboBox.removeAllItems();

                DbConnector db = new DbConnector();
                conn = db.getConnection();

                String sql = "SELECT ccode FROM course WHERE lecusername = ?";

                try {
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, lecUsername);
                    ResultSet rs = pstmt.executeQuery();

                    while (rs.next()) {
                        String ccode = rs.getString("ccode");
                        lecCourseComboBox.addItem(ccode);
                    }

                    rs.close();
                    pstmt.close();
                    conn.close();

                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }

            }
        });
        lecCourseComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showCourseName();
                loadMarks();
            }
            public void showCourseName(){
                if (lecCourseComboBox.getSelectedItem() == null) return;
                DbConnector db = new DbConnector();
                conn = db.getConnection();
                String courseCode = lecCourseComboBox.getSelectedItem().toString();

                String sql = "SELECT * FROM course WHERE ccode = ?";
                try {
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, courseCode);
                    ResultSet rs = pstmt.executeQuery();

                    while (rs.next()) {
                        String cname = rs.getString("cname");
                        courseNameLabel.setText(cname);
                    }

                    rs.close();
                    pstmt.close();
                    conn.close();

                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }

            public void loadMarks(){
                DbConnector db = new DbConnector();
                conn = db.getConnection();

                String sql = "select * from mark WHERE ccode = ?";

                try {
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, lecCourseComboBox.getSelectedItem().toString());
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
                    addMarkTypeComboBox.setSelectedIndex(0);

                } catch (SQLException e) {
                    System.out.println("Statement error: " + e.getMessage());
                }
            }

        });
        addMarksButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stuid2 = stuIDTextField.getText().trim();
                courseID = lecCourseComboBox.getSelectedItem().toString();
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

        viewGradesAndGPAButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentPanel.removeAll();
                parentPanel.add(viewGradesAndGPA);
                parentPanel.repaint();
                parentPanel.revalidate();
            }
        });

        //View Students Details
        studentLevelComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stuLevel1 = (String) studentLevelComboBox.getSelectedItem();
                System.out.println(stuLevel1);

                if (!stuLevel1.equals("") || !stuLevel1.equals(null)) {
                    showLecturerDetails();
                    showStudentDetails(stuLevel1);
                }
            }

            public void showLecturerDetails() {
                DbConnector db = new DbConnector();
                conn = db.getConnection();

                String sql = "SELECT * FROM lecturer WHERE username =?";

                try {
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, lecUsername);
                    ResultSet rs = pstmt.executeQuery();

                    if (rs.next()) {
                        depid = rs.getString("depid");
                        System.out.println(depid);
                    }

                } catch (SQLException e) {
                    System.out.println("Statement error: " + e.getMessage());
                }
            }

            public void showStudentDetails(String stuLevel){
                DbConnector db = new DbConnector();
                conn = db.getConnection();
                String level = stuLevel;

                String sql = "SELECT * FROM student WHERE stulevel=? AND depid = ?";

                try {
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, level);
                    pstmt.setString(2, depid);
                    ResultSet rs = pstmt.executeQuery();

                    String[] columnNames = {"stuid","username","fname","lname","email","dob","pnumber","stulevel","ppicture","depid"};
                    DefaultTableModel model = new DefaultTableModel(columnNames,0);

                    while (rs.next()) {
                        Object[] row = {
                                rs.getString("stuid"),
                                rs.getString("username"),
                                rs.getString("fname"),
                                rs.getString("lname"),
                                rs.getString("email"),
                                rs.getString("dob"),
                                rs.getString("pnumber"),
                                rs.getString("stulevel"),
                                rs.getString("ppicture"),
                                rs.getString("depid")
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
        viewStudentDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentPanel.removeAll();
                parentPanel.add(viewStudentDetailsPanel);
                parentPanel.repaint();
                parentPanel.revalidate();
            }
        });

    }
}
