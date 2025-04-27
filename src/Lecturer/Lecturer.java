package Lecturer;

import Database.DbConnector;
import LoginForm.LoginForm;
import ViewNotice.ViewNotice;
import LecturerAtten.LecturerAtten;
import profileUi.profileUi;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class Lecturer {
    private JPanel MainPanel;
    private JButton addLectureMaterialButton;
    private JButton viewStudentDetailsButton;
    private JButton viewGradesAndGPAButton;
    private JButton UpdateMarksButton;
    private JButton addMarksButton;
    private JButton viewStudentEligibilityButton;
    private JComboBox stuCourseComboBox;
    private JButton uploadLecNoteButton;
    private JComboBox updateMarkTypeComboBox;
    private JPanel addLecMaterialPanel;
    private JPanel updateMarksPanel;
    private JPanel addMarksPanel;
    private JPanel viewStuEligibilityPanel;
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
    private JLabel lecNameLabel;
    private JPanel actionButtons;
    private JPanel addLectureMaterialButtonPanel;
    private JPanel UpdateMarksButtonPanel;
    private JPanel viewStudentEligibilityButtonPanel;
    private JPanel viewGradesAndGPAButtonPanel;
    private JPanel viewStudentDetailsButtonPanel;
    private JPanel titlePanel;
    private JPanel lecNamePanel;
    private JPanel logoutButtonPanel;
    private JButton signOutButton;
    private JComboBox lecCourseComboBox;
    private JLabel courseNameLabel;
    private JComboBox selectLecCourseComboBox;
    private JLabel LecCourseNameLabel;
    private JComboBox selectCourseforQuizComboBox;
    private JTextField quizLinkTextField;
    private JButton uploadButton;
    private JComboBox selectQuizNumber;
    private JComboBox selectCourseForGradeComboBox;
    private JTable gradesTable;
    private JPanel gradesTablePanel;
    private JButton updateProfileButton;
    private JButton viewStudentAttendanceButton;
    private JLabel profileImage;


    private String stuid;
    private String marksValue;
    private String markType;
    private String courseID;
    private String stuid2;
    private String marksValue2;
    private String markType2;
    private String lecUsername;
    private String lecName;
    private String depid;
    private String courCode;

    private String stuLevel1;
    private String lecturerid;
    private String profileP;
    private String departmentid;

    private Connection conn = null;
    private Statement stmt = null;

    public Lecturer() {
        JFrame frame = new JFrame("Lecturer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(MainPanel);
        frame.setSize(1400, 750);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DbConnector db = new DbConnector();
                conn = db.getConnection();

                String courseCode = selectCourseforQuizComboBox.getSelectedItem().toString();
                int qNumber = selectQuizNumber.getSelectedIndex();
                String quizLink = quizLinkTextField.getText().toString();

                String sql = "INSERT INTO quiz(ccode, quiz_number, quiz_link) VALUES (?,?,?)";
                try {
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, courseCode);
                    pstmt.setInt(2, qNumber);
                    pstmt.setString(3, quizLink);
                    int rowCount = pstmt.executeUpdate();

                    if (rowCount > 0) {
                        JOptionPane.showMessageDialog(null, "Quiz uploaded successfully!");
                        quizLinkTextField.setText("");
                    }
                    pstmt.close();
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println("Error: " + ex.getMessage());
                }
            }
        });
        selectCourseForGradeComboBox.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                String courCode = selectCourseForGradeComboBox.getSelectedItem().toString();

                if (courCode.equals("ICT2122")) {
                    DbConnector db = new DbConnector();
                    conn = db.getConnection();

                    String courseCode = selectCourseForGradeComboBox.getSelectedItem().toString();
                    String sql = "CALL calculate_grades_level2_ict_course(?)";

                    try {
                        PreparedStatement pstmt = conn.prepareStatement(sql);
                        pstmt.setString(1, courseCode);
                        ResultSet rs = pstmt.executeQuery();

                        String[] columnNames = {"stuid", "ccode", "total_marks", "grade"};
                        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

                        gradesTable.removeAll();
                        while (rs.next()) {
                            Object[] row = {
                                    rs.getString("stuid"),
                                    rs.getString("ccode"),
                                    rs.getDouble("total_marks"),
                                    rs.getString("grade")
                            };
                            model.addRow(row);
                        }
                        gradesTable.setModel(model);
                        gradesTable.repaint();
                        gradesTable.revalidate();

                        pstmt.close();
                        rs.close();
                        conn.close();

                    } catch (SQLException ex) {
                        System.out.println("Error: " + ex.getMessage());
                    }
                } else if (courCode.equals("ICT2133")) {
                    DbConnector db = new DbConnector();
                    conn = db.getConnection();

                    String courseCode = selectCourseForGradeComboBox.getSelectedItem().toString();
                    String sql = "CALL calculate_grades_level2_ict_course(?)";

                    try {
                        PreparedStatement pstmt = conn.prepareStatement(sql);
                        pstmt.setString(1, courseCode);
                        ResultSet rs = pstmt.executeQuery();

                        String[] columnNames = {"stuid", "ccode", "total_marks", "grade"};
                        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

                        gradesTable.removeAll();
                        while (rs.next()) {
                            Object[] row = {
                                    rs.getString("stuid"),
                                    rs.getString("ccode"),
                                    rs.getDouble("total_marks"),
                                    rs.getString("grade")
                            };
                            model.addRow(row);
                        }
                        gradesTable.setModel(model);
                        gradesTable.repaint();
                        gradesTable.revalidate();

                        pstmt.close();
                        rs.close();
                        conn.close();

                    } catch (SQLException ex) {
                        System.out.println("Error: " + ex.getMessage());
                    }
                } else if (courCode.equals("ICT2113")) {
                    DbConnector db = new DbConnector();
                    conn = db.getConnection();

                    String courseCode = selectCourseForGradeComboBox.getSelectedItem().toString();
                    String sql = "CALL calculate_grades_level2_ict_course(?)";

                    try {
                        PreparedStatement pstmt = conn.prepareStatement(sql);
                        pstmt.setString(1, courseCode);
                        ResultSet rs = pstmt.executeQuery();

                        String[] columnNames = {"stuid", "ccode", "total_marks", "grade"};
                        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

                        gradesTable.removeAll();
                        while (rs.next()) {
                            Object[] row = {
                                    rs.getString("stuid"),
                                    rs.getString("ccode"),
                                    rs.getDouble("total_marks"),
                                    rs.getString("grade")
                            };
                            model.addRow(row);
                        }
                        gradesTable.setModel(model);
                        gradesTable.repaint();
                        gradesTable.revalidate();

                        pstmt.close();
                        rs.close();
                        conn.close();

                    } catch (SQLException ex) {
                        System.out.println("Error: " + ex.getMessage());
                    }
                } else if (courCode.equals("ICT2142")) {
                    DbConnector db = new DbConnector();
                    conn = db.getConnection();

                    String courseCode = selectCourseForGradeComboBox.getSelectedItem().toString();
                    String sql = "CALL calculate_grades_level2_ict_course(?)";

                    try {
                        PreparedStatement pstmt = conn.prepareStatement(sql);
                        pstmt.setString(1, courseCode);
                        ResultSet rs = pstmt.executeQuery();

                        String[] columnNames = {"stuid", "ccode", "total_marks", "grade"};
                        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

                        gradesTable.removeAll();
                        while (rs.next()) {
                            Object[] row = {
                                    rs.getString("stuid"),
                                    rs.getString("ccode"),
                                    rs.getDouble("total_marks"),
                                    rs.getString("grade")
                            };
                            model.addRow(row);
                        }
                        gradesTable.setModel(model);
                        gradesTable.repaint();
                        gradesTable.revalidate();

                        pstmt.close();
                        rs.close();
                        conn.close();

                    } catch (SQLException ex) {
                        System.out.println("Error: " + ex.getMessage());
                    }
                } else if (courCode.equals("ICT2152")) {
                    DbConnector db = new DbConnector();
                    conn = db.getConnection();

                    String courseCode = selectCourseForGradeComboBox.getSelectedItem().toString();
                    String sql = "CALL calculate_grades_level2_ict_course(?)";

                    try {
                        PreparedStatement pstmt = conn.prepareStatement(sql);
                        pstmt.setString(1, courseCode);
                        ResultSet rs = pstmt.executeQuery();

                        String[] columnNames = {"stuid", "ccode", "total_marks", "grade"};
                        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

                        gradesTable.removeAll();
                        while (rs.next()) {
                            Object[] row = {
                                    rs.getString("stuid"),
                                    rs.getString("ccode"),
                                    rs.getDouble("total_marks"),
                                    rs.getString("grade")
                            };
                            model.addRow(row);
                        }
                        gradesTable.setModel(model);
                        gradesTable.repaint();
                        gradesTable.revalidate();

                        pstmt.close();
                        rs.close();
                        conn.close();

                    } catch (SQLException ex) {
                        System.out.println("Error: " + ex.getMessage());
                    }
                }
            }

            public void insertGrade(String stuid, String ccode, double totalMarks, String grade) {
                DbConnector db = new DbConnector();
                conn = db.getConnection();

                String stuid2 = stuid;
                String ccode2 = ccode;
                double totalMarks2 = totalMarks;
                String grade2 = grade;

                String sql = "INSERT INTO grades(stuid, ccode, total_marks, grade) VALUES (?,?,?,?)";

                try {
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, stuid2);
                    pstmt.setString(2, ccode2);
                    pstmt.setDouble(3, totalMarks2);
                    pstmt.setString(4, grade2);
                    pstmt.executeUpdate();
                    //JOptionPane.showMessageDialog(null, "Grade added successfully!");
                    pstmt.close();
                    conn.close();
                } catch (SQLException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }

        });

        // Sign Out button
        signOutButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(frame,
                    "Are you sure you want to sign out?",
                    "Confirm Sign Out",
                    JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                frame.dispose();
                new LoginForm();
            }
        });
        updateProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new profileUi(lecUsername, lecName);
            }
        });
        viewStudentAttendanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LecturerAtten(lecUsername);
            }
        });
    }

    public Lecturer(String lecturerid, String lecUsername, String lecName, String profileP, String departmentid) {

        this();
        lecNameLabel.setText(lecName);
        this.lecUsername = lecUsername;
        this.lecName = lecName;
        this.lecturerid = lecturerid;
        this.profileP = profileP;
        this.departmentid = departmentid;



        String basepath = String.format("%s%s%s%s%s", "Resources", "\\", "ProfileImage", "\\", "Lecturer\\");

        // Resize and set the profile picture
        try {
            File imageFile = new File(basepath + profileP);
            if (!imageFile.exists()) {
                throw new IOException("Image file not found: " + imageFile.getAbsolutePath());
            }

            // Load and resize the image
            BufferedImage originalImage = ImageIO.read(imageFile);
            BufferedImage resizedImage = resizeImage(originalImage, 100, 100);

            // Clip to circular shape
            BufferedImage roundImage = makeRoundImage(resizedImage);
            profileImage.setIcon(new ImageIcon(roundImage));

            // Your original result check
            String result = profileP != null ? profileP : "nothing";
            System.out.println("Profile Picture: " + result);

        } catch (IOException e) {
            System.err.println("Error loading image: " + e.getMessage());
            profileImage.setText("No Image");
        }


        //Add Lec Materials
        addLectureMaterialButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentPanel.removeAll();
                parentPanel.add(addLecMaterialPanel);
                parentPanel.repaint();
                parentPanel.revalidate();

                if (selectCourseforQuizComboBox.getSelectedItem() == null) {
                    showCoursesList();
                }

                if (stuCourseComboBox.getSelectedItem() == null) {
                    showCourseForMaterial();
                }
            }

            public void showCourseForMaterial() {
                stuCourseComboBox.removeAllItems();

                DbConnector db = new DbConnector();
                conn = db.getConnection();

                String sql = "SELECT ccode FROM course WHERE lecusername = ?";
                try {
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, lecUsername);
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        courseID = rs.getString("ccode");
                        stuCourseComboBox.addItem(courseID);
                    }
                    rs.close();
                    pstmt.close();
                    conn.close();
                } catch (SQLException e) {
                    System.out.println("Error" + e.getMessage());
                }

            }

            public void showCoursesList() {
                selectCourseforQuizComboBox.removeAllItems();

                DbConnector db = new DbConnector();
                conn = db.getConnection();

                String sql = "SELECT ccode FROM course WHERE lecusername = ?";
                try {
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, lecUsername);
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        courseID = rs.getString("ccode");
                        selectCourseforQuizComboBox.addItem(courseID);
                    }
                    rs.close();
                    pstmt.close();
                    conn.close();
                } catch (SQLException e) {
                    System.out.println("SQLException: " + e.getMessage());
                }
            }

        });
        uploadLecNoteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String courseCodeForLecNote = stuCourseComboBox.getSelectedItem().toString();
                int week = lecWeekComboBox.getSelectedIndex();

                JFileChooser chooser = new JFileChooser();
                int result = chooser.showOpenDialog(null);

                if (result == JFileChooser.APPROVE_OPTION) {
                    File file = chooser.getSelectedFile();

                    try {
                        FileInputStream fis = new FileInputStream(file);
                        DbConnector db = new DbConnector();
                        conn = db.getConnection();

                        String sql = "INSERT INTO lecNotes(ccode, week, file_name, file_data) VALUES(?,?,?,?)";
                        try {
                            PreparedStatement pstmt = conn.prepareStatement(sql);
                            pstmt.setString(1, courseCodeForLecNote);
                            pstmt.setInt(2, week);
                            pstmt.setString(3, file.getName());
                            pstmt.setBinaryStream(4, fis, (int) file.length());

                            pstmt.executeUpdate();
                            pstmt.close();
                            conn.close();
                        } catch (SQLException ex) {
                            System.out.println("Error: " + ex.getMessage());
                        }
                        JOptionPane.showMessageDialog(null, "File uploaded successfully!");
                        fis.close();
                        chooser.setVisible(false);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Upload failed: " + ex.getMessage());
                    }
                }
            }
        });

        uploadAssingmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String courseCodeForAssessments = stuCourseComboBox.getSelectedItem().toString();
                int week = lecWeekComboBox.getSelectedIndex();

                JFileChooser chooser = new JFileChooser();
                int result = chooser.showOpenDialog(null);

                if (result == JFileChooser.APPROVE_OPTION) {
                    File file = chooser.getSelectedFile();

                    try {
                        FileInputStream fis = new FileInputStream(file);
                        DbConnector db = new DbConnector();
                        conn = db.getConnection();

                        String sql = "INSERT INTO assessments(ccode, week, file_name, file_data) VALUES(?,?,?,?)";
                        try {
                            PreparedStatement pstmt = conn.prepareStatement(sql);
                            pstmt.setString(1, courseCodeForAssessments);
                            pstmt.setInt(2, week);
                            pstmt.setString(3, file.getName());
                            pstmt.setBinaryStream(4, fis, (int) file.length());

                            pstmt.executeUpdate();
                            pstmt.close();
                            conn.close();
                        } catch (SQLException ex) {
                            System.out.println("Error: " + ex.getMessage());
                        }
                        JOptionPane.showMessageDialog(null, "File uploaded successfully!");
                        fis.close();
                        chooser.setVisible(false);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Upload failed: " + ex.getMessage());
                    }
                }
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

                if (selectLecCourseComboBox.getSelectedItem() == null) {
                    showLecturerCourses2();
                }
            }

            public void showLecturerCourses2() {
                selectLecCourseComboBox.removeAllItems();

                DbConnector db = new DbConnector();
                conn = db.getConnection();

                String sql = "SELECT ccode FROM course WHERE lecusername=?";

                try {
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, lecUsername);
                    ResultSet rs = pstmt.executeQuery();

                    while (rs.next()) {
                        String ccode = rs.getString("ccode");
                        selectLecCourseComboBox.addItem(ccode);
                    }
                    rs.close();
                    pstmt.close();
                    conn.close();
                } catch (SQLException e) {
                    System.out.println("Statement error: " + e.getMessage());
                }
            }
        });

        updateMarksButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stuid = stuIDField.getText().trim().toUpperCase();
                courCode = selectLecCourseComboBox.getSelectedItem().toString();
                marksValue = enteredMarksField.getText();
                markType = updateMarkTypeComboBox.getSelectedItem().toString();

                if (stuid.isEmpty() || marksValue.isEmpty() || markType.isEmpty()) {
                    JOptionPane.showMessageDialog(parentPanel, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    double marks = Double.parseDouble(marksValue);
                    if (marks < 0 || marks > 100) {
                        JOptionPane.showMessageDialog(parentPanel, "Marks must be between 0 and 100!");
                        return;
                    }
                    updateStuMarks(stuid, marks, markType);

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid number for marks.");
                }
            }

            public void updateStuMarks(String stuid, double marks, String markType) {
                DbConnector db = new DbConnector();
                conn = db.getConnection();

                Map<String, String> columns = new HashMap<>();
                columns.put("Quiz 1", "quiz_1");
                columns.put("Quiz 2", "quiz_2");
                columns.put("Quiz 3", "quiz_3");
                columns.put("Quiz 4", "quiz_4");
                columns.put("Assignment 1", "assesment_1");
                columns.put("Assignment 2", "assesment_2");
                columns.put("Mid Exam Marks", "m_marks");
                columns.put("End Theory Marks", "f_theory");
                columns.put("End Practical Marks", "f_practical");

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

            public void loadMarks() {
                DbConnector db = new DbConnector();
                conn = db.getConnection();

                String sql = "select * from mark where ccode = ?";

                try {
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, courCode);
                    ResultSet rs = pstmt.executeQuery();

                    String[] columnNames = {"Stu_ID", "Cour_Code", "Quiz 1", "Quiz 2", "Quiz 3", "Quiz 4", "Assignment 1", "Assignment 2", "Mid Marks", "End Theory", "End Practical"};
                    DefaultTableModel model = new DefaultTableModel(columnNames, 0);

                    while (rs.next()) {
                        Object[] row = {
                                rs.getString("stuid"),
                                rs.getString("ccode"),
                                rs.getDouble("quiz_1"),
                                rs.getDouble("quiz_2"),
                                rs.getDouble("quiz_3"),
                                rs.getDouble("quiz_4"),
                                rs.getDouble("assesment_1"),
                                rs.getDouble("assesment_2"),
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

        selectLecCourseComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showCourseName();
            }

            public void showCourseName() {
                if (selectLecCourseComboBox.getSelectedItem() == null) return;
                DbConnector db = new DbConnector();
                conn = db.getConnection();
                String courseCode = selectLecCourseComboBox.getSelectedItem().toString();

                String sql = "SELECT * FROM course WHERE ccode = ?";
                try {
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, courseCode);
                    ResultSet rs = pstmt.executeQuery();

                    while (rs.next()) {
                        String cname = rs.getString("cname");
                        LecCourseNameLabel.setText(cname);
                    }

                    rs.close();
                    pstmt.close();
                    conn.close();

                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
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

                if (lecCourseComboBox.getSelectedItem() == null) {
                    showLecturerCourses();
                }
            }

            public void showLecturerCourses() {
                lecCourseComboBox.removeAllItems();

                DbConnector db = new DbConnector();
                conn = db.getConnection();

                String sql = "SELECT ccode FROM course WHERE lecusername = ?";

                try {
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, lecUsername);
                    ResultSet rs = pstmt.executeQuery();

                    lecCourseComboBox.removeAllItems();

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

            public void loadMarks() {
                DbConnector db = new DbConnector();
                conn = db.getConnection();


                String sql = "select * from mark WHERE ccode = ?";

                try {
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, lecCourseComboBox.getSelectedItem().toString());
                    ResultSet rs = pstmt.executeQuery();

                    String[] columnNames2 = {"Stu_ID", "Cour_Code", "Quiz 1", "Quiz 2", "Quiz 3", "Quiz 4", "Assignment 1", "Assignment 2", "Mid Marks", "End Theory", "End Practical"};
                    DefaultTableModel model2 = new DefaultTableModel(columnNames2, 0);

                    while (rs.next()) {
                        Object[] row = {
                                rs.getString("stuid"),
                                rs.getString("ccode"),
                                rs.getDouble("quiz_1"),
                                rs.getDouble("quiz_2"),
                                rs.getDouble("quiz_3"),
                                rs.getDouble("quiz_4"),
                                rs.getDouble("assesment_1"),
                                rs.getDouble("assesment_2"),
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

            public void showCourseName() {
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
        });

        addMarksButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stuid2 = stuIDTextField.getText().trim().toUpperCase();
                courseID = lecCourseComboBox.getSelectedItem().toString();
                markType2 = addMarkTypeComboBox.getSelectedItem().toString();
                marksValue2 = stuMarksField.getText();

                if (stuid2.isEmpty() || marksValue2.isEmpty() || markType2.isEmpty() || courseID.isEmpty()) {
                    JOptionPane.showMessageDialog(parentPanel, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    double marks = Double.parseDouble(marksValue2);
                    if (marks < 0 || marks > 100) {
                        JOptionPane.showMessageDialog(parentPanel, "Marks must be between 0 and 100", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    addStuMarks(stuid2, courseID, markType2, marks);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid number for marks.");
                }
            }

            public void addStuMarks(String stuid2, String courseID, String markType2, double marks) {
                DbConnector db = new DbConnector();
                conn = db.getConnection();

                Map<String, String> columns = new HashMap<>();
                columns.put("Quiz 1", "quiz_1");
                columns.put("Quiz 2", "quiz_2");
                columns.put("Quiz 3", "quiz_3");
                columns.put("Assignment Marks", "assesment");
                columns.put("Mid Exam Marks", "m_marks");
                columns.put("End Theory Marks", "f_theory");
                columns.put("End Practical Marks", "f_practical");

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

            public void loadAddMarksTable() {
                DbConnector db = new DbConnector();
                conn = db.getConnection();

                String sql = "select * from mark where ccode = ?";

                try {
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, courseID);
                    ResultSet rs = pstmt.executeQuery();

                    String[] columnNames2 = {"Stu_ID", "Cour_Code", "Quiz 1", "Quiz 2", "Quiz 3", "Quiz 4", "Assignment 1", "Assignment 2", "Mid Marks", "End Theory", "End Practical"};
                    DefaultTableModel model2 = new DefaultTableModel(columnNames2, 0);

                    while (rs.next()) {
                        Object[] row = {
                                rs.getString("stuid"),
                                rs.getString("ccode"),
                                rs.getDouble("quiz_1"),
                                rs.getDouble("quiz_2"),
                                rs.getDouble("quiz_3"),
                                rs.getDouble("quiz_4"),
                                rs.getDouble("assesment_1"),
                                rs.getDouble("assesment_2"),
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

        viewStudentEligibilityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentPanel.removeAll();
                parentPanel.add(viewStuEligibilityPanel);
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

                if (selectCourseForGradeComboBox.getSelectedItem() == null) {
                    showLecturerCourses();
                }
            }

            public void showLecturerCourses() {
                selectCourseForGradeComboBox.removeAllItems();

                DbConnector db = new DbConnector();
                conn = db.getConnection();

                String sql = "SELECT ccode FROM course WHERE lecusername = ?";

                try {
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, lecUsername);
                    ResultSet rs = pstmt.executeQuery();

                    selectCourseForGradeComboBox.removeAllItems();

                    while (rs.next()) {
                        String ccode = rs.getString("ccode");
                        selectCourseForGradeComboBox.addItem(ccode);
                    }

                    rs.close();
                    pstmt.close();
                    conn.close();

                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
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

            public void showStudentDetails(String stuLevel) {
                DbConnector db = new DbConnector();
                conn = db.getConnection();
                String level = stuLevel;

                String sql = "SELECT * FROM student WHERE stulevel=? AND depid = ?";

                try {
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, level);
                    pstmt.setString(2, depid);
                    ResultSet rs = pstmt.executeQuery();

                    String[] columnNames = {"stuid", "username", "fname", "lname", "email", "dob", "pnumber", "stulevel", "ppicture", "depid"};
                    DefaultTableModel model = new DefaultTableModel(columnNames, 0);

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

    //---------------------------------------------------------------Profile picture------------------------------------------------------------------------------
    static class RoundImageLabel extends JLabel {
        private BufferedImage image;
        private final int width;
        private final int height;

        public RoundImageLabel(int width, int height) {
            this.width = width;
            this.height = height;
            setPreferredSize(new Dimension(width, height));
        }

        public void setImage(BufferedImage image) {
            this.image = image;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (image != null) {
                Graphics2D g2d = (Graphics2D) g.create();
                // Enable anti-aliasing for smooth edges
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Clip to a circle
                g2d.setClip(new Ellipse2D.Float(0, 0, width, height));

                // Draw the image
                g2d.drawImage(image, 0, 0, width, height, this);

                // Optional: Draw a border
                g2d.setClip(null); // Reset clip for border
                g2d.setColor(Color.GRAY);
                g2d.setStroke(new BasicStroke(2));
                g2d.drawOval(0, 0, width - 1, height - 1);

                g2d.dispose();
            }
        }
    }

    // Resize image to target dimensions
    private static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resizedImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        g2d.dispose();
        return resizedImage;
    }

    // Clip image to circular shape
    private static BufferedImage makeRoundImage(BufferedImage image) {
        int size = Math.min(image.getWidth(), image.getHeight());
        BufferedImage roundImage = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = roundImage.createGraphics();

        // Enable anti-aliasing
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Clip to a circle
        g2d.setClip(new Ellipse2D.Float(0, 0, size, size));

        // Draw the image
        g2d.drawImage(image, 0, 0, size, size, null);
        g2d.dispose();

        return roundImage;
    }
}
