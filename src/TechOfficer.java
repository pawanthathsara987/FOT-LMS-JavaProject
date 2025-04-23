import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class TechOfficer {
    private JPanel MainPanel;
    private JPanel leftpanel;
    private JLabel namelabel;
    private JLabel picturelabel;
    private JButton addAttendanceButton;
    private JButton viewAttendanceButton;
    private JButton addMedicalButton;
    private JButton viewMedicalButton;
    private JButton timeTableButton;
    private JButton noticeButton;
    private JPanel cardpanel;
    private JPanel viewattenpanel;
    private JPanel addattenpanel;
    private JPanel titlepanel;
    private JLabel viewlabel;
    private JComboBox selectcour;
    private JScrollBar scrollBar1;
    private JTable view_atten_table;
    private JPanel addmedicalpanel;
    private JComboBox comboBox5;
    private JButton deleteButton1;
    private JButton updateButton;
    private JScrollBar scrollBar2;
    private JTable table2;
    private JPanel viewmedicalpanel;

    private JTextField attenstu_text;
    private JTextField attencour_text;
    private JTextField attendate_text;
    private JComboBox atten_type_combo;
    private JComboBox atten_pre_combo;
    private JScrollBar scrollBar3;
    private JTable attenViewtable;
    private JPanel Addatten;
    private JPanel Editatten;
    private JPanel deleteatten;
    private JButton createButton;
    private JButton editButton2;
    private JButton deleteButton3;
    private JButton atten_submitButton;
    private JButton atten_deleteButton;
    private JComboBox aetype_combo;
    private JComboBox aepre_combo;
    private JTextField aestu_text;
    private JTextField aecour_text;
    private JTextField aedate_text;
    private JButton aedit_button;
    private JPanel crtattenpanel;
    private JPanel vatten;
    private JLabel attenstu;
    private JLabel attencourse;
    private JLabel attendate;
    private JLabel attentype;
    private JLabel attenpre;
    private JLabel attenlevel;
    private JPanel attentitle;
    private JLabel atitlelabel;
    private JPanel attenbutton;
    private JLabel aetype;
    private JLabel aepresent;
    private JLabel aelevel;
    private JLabel aestu;
    private JLabel aecourse;
    private JLabel aedate;
    private JPanel attenDeletepanel;
    private JPanel attenbodypanel;
    private JPanel medbody_panel;
    private JPanel medcard;
    private JPanel medcreate;
    private JPanel med_edit;
    private JPanel medDelete;
    private JScrollBar scrollBar4;
    private JTable med_table;
    private JTextField medtextstu;
    private JTextField medtextcourse;
    private JPanel medtitlepanel;
    private JPanel medvtable;
    private JButton med_editButton;
    private JButton med_deleteButton;
    private JButton med_createButton;
    private JPanel med_button;
    private JTextField Emedstu;
    private JTextField Emedcour;
    private JButton mededit;
    private JLabel medstu;
    private JLabel medcourse;
    private JLabel meddate;
    private JLabel med_des;
    private JLabel medestu;
    private JLabel medecourse;
    private JLabel mededate;
    private JLabel mededes;
    private JLabel medtitlelabel;
    private JButton deleteButton2;
    private JButton medsubmit;
    private JButton aeselect;
    private JTextField medtextdate;
    private JTextArea medtextdes;
    private JButton medselect;
    private JTextField Emeddate;
    private JTextArea Emeddes;
    private JLabel username;
    private JButton selectButton;
    private JComboBox attenmed;
    private JTextField attenhour;
    private JLabel attendep;
    private JComboBox attendepcombo;
    private JTextField aehour;
    private JLabel depa;
    private JComboBox aedepcombo;
    private JComboBox aemedical;
    private JTextField sestu;
    private JTextField secour;
    private JButton sebtn;
    private JButton refreshButton;
    private JButton deselect;
    private JTextField semedstu;
    private JTextField semedcour;
    private JButton medsebtn;
    private JTextField medid;
    private JButton dmedselect;
    private JButton attenselect;

    private String stuid;
    private String course;
    private String date;
    private String des;
    private String type;
    private String pre;
    private String hour;
    private String med;
    private String dep;



    public TechOfficer(String techuser, String techName) {
        JFrame frame = new JFrame("Add Attendance");
        frame.setContentPane(MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(1400, 750);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);

        username.setText(techName);


        CardLayout cardlayout = new CardLayout();
        cardpanel.setLayout(cardlayout);

        crtattenpanel.setLayout(cardlayout);

        cardpanel.add(addattenpanel,"card1");
        cardpanel.add(addmedicalpanel,"card2");
        cardpanel.add(viewattenpanel,"card3");
        cardpanel.add(viewmedicalpanel,"card4");

        crtattenpanel.add(Addatten,"card5");
        crtattenpanel.add(Editatten,"card6");
        crtattenpanel.add(deleteatten,"card7");

        medcard.setLayout(cardlayout);

        medcard.add(medcreate,"mcard1");
        medcard.add(med_edit,"mcard2");
        medcard.add(medDelete,"mcard3");

        addAttendanceButton.addActionListener(e -> cardlayout.show(cardpanel,"card1"));
        viewAttendanceButton.addActionListener(e -> cardlayout.show(cardpanel,"card3"));
        addMedicalButton.addActionListener(e -> cardlayout.show(cardpanel,"card2"));
        viewMedicalButton.addActionListener(e -> cardlayout.show(cardpanel,"card4"));

        createButton.addActionListener(e -> cardlayout.show(crtattenpanel,"card5"));
        editButton2.addActionListener(e -> cardlayout.show(crtattenpanel,"card6"));
        deleteButton3.addActionListener(e -> cardlayout.show(crtattenpanel,"card7"));

        med_createButton.addActionListener(e -> cardlayout.show(medcard,"mcard1"));
        med_editButton.addActionListener(e -> cardlayout.show(medcard,"mcard2"));
        med_deleteButton.addActionListener(e -> cardlayout.show(medcard,"mcard3"));

        createAttendanceTable();

        createMedicalTable();

        loadAttendanceTable();

        loadMedicalTable();

        //viewAttendance();

        createAttendanceViewTable();





        atten_submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addAttendance();
                loadAttendanceTable();
            }
        });

        aedit_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               editAttendance();
            }
        });


        aeselect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = attenViewtable.getSelectedRow();
                if (selectedRow >= 0) {
                    aestu_text.setText(attenViewtable.getValueAt(selectedRow, 0).toString());
                    aecour_text.setText(attenViewtable.getValueAt(selectedRow, 1).toString());
                    aedate_text.setText(attenViewtable.getValueAt(selectedRow, 3).toString()); // sdate
                    aetype_combo.setSelectedItem(attenViewtable.getValueAt(selectedRow, 4).toString()); // ctype
                    aepre_combo.setSelectedItem(attenViewtable.getValueAt(selectedRow, 5).toString()); // present
                    aedepcombo.setSelectedItem(attenViewtable.getValueAt(selectedRow, 2).toString()); // depid
                    aehour.setText(attenViewtable.getValueAt(selectedRow, 6).toString()); // hours
                    aemedical.setSelectedItem(attenViewtable.getValueAt(selectedRow, 7).toString()); // medical
                }
            }
        });




        medsubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addMedical();
                loadMedicalTable();
            }
        });


        medselect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = med_table.getSelectedRow();
                if (selectedRow >= 0) {
                    medid.setText(med_table.getValueAt(selectedRow, 0).toString());
                    Emedstu.setText(med_table.getValueAt(selectedRow, 1).toString());
                    Emedcour.setText(med_table.getValueAt(selectedRow, 2).toString());
                    Emeddate.setText(med_table.getValueAt(selectedRow, 3).toString());
                    Emeddes.setText(med_table.getValueAt(selectedRow, 4).toString());

                }
            }
        });

        mededit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editMedical();
            }
        });

        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //viewAttendance();
            }
        });
        sebtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchAttendanceByIdOrCourse();
            }
        });
        atten_deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteAttendance();
            }
        });

        deselect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = attenViewtable.getSelectedRow();
                if (selectedRow >= 0) {
                    aestu_text.setText(attenViewtable.getValueAt(selectedRow, 0).toString());
                    aecour_text.setText(attenViewtable.getValueAt(selectedRow, 1).toString());
                    aedate_text.setText(attenViewtable.getValueAt(selectedRow, 3).toString()); // sdate
                    aetype_combo.setSelectedItem(attenViewtable.getValueAt(selectedRow, 4).toString()); // ctype
                    aepre_combo.setSelectedItem(attenViewtable.getValueAt(selectedRow, 5).toString()); // present
                    aedepcombo.setSelectedItem(attenViewtable.getValueAt(selectedRow, 2).toString()); // depid
                    aehour.setText(attenViewtable.getValueAt(selectedRow, 6).toString()); // hours
                    aemedical.setSelectedItem(attenViewtable.getValueAt(selectedRow, 7).toString()); // medical
                }
            }
        });

        medsebtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchMedicalRecord();
            }
        });

        dmedselect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = med_table.getSelectedRow();
                if (selectedRow >= 0) {
                    medid.setText(med_table.getValueAt(selectedRow, 0).toString());
                    Emedstu.setText(med_table.getValueAt(selectedRow, 1).toString());
                    Emedcour.setText(med_table.getValueAt(selectedRow, 2).toString());
                    Emeddate.setText(med_table.getValueAt(selectedRow, 3).toString());
                    Emeddes.setText(med_table.getValueAt(selectedRow, 4).toString());

                }
            }
        });
        deleteButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteMedicalRecord();
                loadMedicalTable();
            }
        });
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadAttendanceTable();
            }
        });
    }

    //Attendance add,edit,delete,view

    private void clearAttendanceFields() {
        attenstu_text.setText("");
        attencour_text.setText("");
        attendate_text.setText("");
        atten_type_combo.setSelectedIndex(0);
        atten_pre_combo.setSelectedIndex(0);
    }


    private void createAttendanceTable() {
        String[] columns = {"Student ID", "Course Code", "Dep", "Date", "Type", "Present", "Hours", "Medical"};
        Object[][] data = {}; // or populate from DB later

        DefaultTableModel model = new DefaultTableModel(data, columns);
        attenViewtable.setModel(model);

        // Customize header
        JTableHeader header = attenViewtable.getTableHeader();
        header.setFont(new Font("SansSerif", Font.BOLD, 14));
        header.setBackground(new Color(204, 255, 204));
        header.setForeground(Color.BLACK);
    }

    private void addAttendance() {
        stuid = attenstu_text.getText().trim();
        course = attencour_text.getText().trim();
        date = attendate_text.getText().trim();
        type = (String) atten_type_combo.getSelectedItem();  // ctype
        pre = (String) atten_pre_combo.getSelectedItem();
        dep = (String) attendepcombo.getSelectedItem();
        hour = attenhour.getText().trim();
        med = (String) attenmed.getSelectedItem();

        // Validate input
        if (stuid.isEmpty() || course.isEmpty() || date.isEmpty() || type == null || pre == null || hour.isEmpty() || med == null || dep == null) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields.");
            return;
        }

        // Convert presence to TINYINT (1 = present, 0 = absent)
        int present = pre.equalsIgnoreCase("Present") ? 1 : 0;
        int medical = med.equalsIgnoreCase("True") ? 1 : 0;
        int hourInt;

        try {
            hourInt = Integer.parseInt(hour);  // Ensure it's an integer for database
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid hours. Please enter a number.");
            return;
        }

        String sql = "INSERT INTO attendance (stuid, ccode, sdate, ctype, present, msubmit, depid, hours) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            DbConnector db = new DbConnector();
            Connection conn = db.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, stuid);
            pstmt.setString(2, course);
            pstmt.setDate(3, Date.valueOf(date)); // Format: YYYY-MM-DD
            pstmt.setString(4, type);
            pstmt.setInt(5, present);
            pstmt.setInt(6, medical);
            pstmt.setString(7, dep);
            pstmt.setInt(8, hourInt);  // Insert hours as int

            pstmt.executeUpdate();

            pstmt.close();
            conn.close();

            JOptionPane.showMessageDialog(null, "Attendance added successfully!");
            clearAttendanceFields();
            createAttendanceTable(); // Refresh table

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to add attendance.");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, "Invalid date format. Use YYYY-MM-DD.");
        }
    }


    private void loadAttendanceTable() {
        String sql = "SELECT a.stuid, a.ccode, a.depid, a.sdate, a.ctype, " +
                "CASE a.present WHEN 1 THEN 'Present' ELSE 'Absent' END AS status, " +
                "a.hours, " +
                "CASE a.msubmit WHEN 1 THEN 'Yes' ELSE 'No' END AS medical " +
                "FROM attendance a " +
                "JOIN student s ON a.stuid = s.stuid " +
                "ORDER BY a.sdate DESC";

        try {
            DbConnector db = new DbConnector();
            Connection conn = db.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            DefaultTableModel model = (DefaultTableModel) attenViewtable.getModel();
            model.setRowCount(0); // Clear old data

            while (rs.next()) {
                String stuid = rs.getString("stuid");
                String course = rs.getString("ccode");
                String dep = rs.getString("depid");
                String date = rs.getString("sdate");
                String type = rs.getString("ctype");
                String status = rs.getString("status"); // 'status' is the alias in the query
                int hours = rs.getInt("hours");
                String medical = rs.getString("medical");

                model.addRow(new Object[]{stuid, course, dep, date, type, status, hours, medical});
            }

            rs.close();
            pstmt.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading attendance data.");
        }
    }




    // Edit Button Action (Save changes)
    private void editAttendance() {
        String stuId = aestu_text.getText().trim();
        String course = aecour_text.getText().trim();
        String date = aedate_text.getText().trim();
        String type = (String) aetype_combo.getSelectedItem();
        String present = (String) aepre_combo.getSelectedItem();
        String hours = aehour.getText().trim();
        String dep = (String)aedepcombo.getSelectedItem().toString();
        String med = (String)aemedical.getSelectedItem().toString();

        // Validate input
        if (stuId.isEmpty() || course.isEmpty() || date.isEmpty() || type == null || present == null || hours.isEmpty() || dep == null || medvtable == null) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields.");
            return;
        }

        int presentValue = present.equalsIgnoreCase("Present") ? 1 : 0;
        int medValue = med.equalsIgnoreCase("Yes") || med.equalsIgnoreCase("True") ? 1 : 0;

        // Prepare the update query
        String sql = "UPDATE attendance SET hours = ?, present = ?, depid = ?, msubmit = ? WHERE stuid = ? AND ccode = ? AND sdate = ? AND ctype = ?";

        try {
            DbConnector db = new DbConnector();
            Connection conn = db.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, hours);
            pstmt.setInt(2, presentValue);
            pstmt.setString(3, dep);
            pstmt.setInt(4, medValue);
            pstmt.setString(5, stuId);
            pstmt.setString(6, course);
            pstmt.setDate(7, Date.valueOf(date));
            pstmt.setString(8, type);

            int updated = pstmt.executeUpdate();

            pstmt.close();
            conn.close();

            if (updated > 0) {
                JOptionPane.showMessageDialog(null, "Attendance updated successfully!");
                loadAttendanceTable(); // Refresh the table
            } else {
                JOptionPane.showMessageDialog(null, "Update failed. Record not found.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database error occurred.");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, "Invalid date format. Use YYYY-MM-DD.");
        }


}

    private void searchAttendanceByIdOrCourse() {
        String stuId = sestu.getText().trim();
        String courseCode = secour.getText().trim();

        String sql = "SELECT a.stuid, a.ccode, a.depid, a.sdate, a.ctype, " +
                "CASE a.present WHEN 1 THEN 'Present' ELSE 'Absent' END AS status, " +
                "a.hours, " +
                "CASE a.msubmit WHEN 1 THEN 'Yes' ELSE 'No' END AS medical " +
                "FROM attendance a " +
                "JOIN student s ON a.stuid = s.stuid " +
                "WHERE (? = '' OR a.stuid = ?) AND (? = '' OR a.ccode = ?) " +
                "ORDER BY a.sdate DESC";

        try {
            DbConnector db = new DbConnector();
            Connection conn = db.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, stuId);
            pstmt.setString(2, stuId);
            pstmt.setString(3, courseCode);
            pstmt.setString(4, courseCode);

            ResultSet rs = pstmt.executeQuery();
            DefaultTableModel model = (DefaultTableModel) attenViewtable.getModel();
            model.setRowCount(0); // Clear table

            while (rs.next()) {
                String id = rs.getString("stuid");
                String course = rs.getString("ccode");
                String dep = rs.getString("depid");
                String date = rs.getString("sdate");
                String type = rs.getString("ctype");
                String status = rs.getString("status");
                int hours = rs.getInt("hours");
                String medical = rs.getString("medical");

                model.addRow(new Object[]{id, course, dep, date, type, status, hours, medical});
            }

            rs.close();
            pstmt.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error fetching attendance data.");
        }
    }


    private void deleteAttendance() {
        String stuId = aestu_text.getText().trim();
        String course = aecour_text.getText().trim();
        String date = aedate_text.getText().trim();
        String type = (String) aetype_combo.getSelectedItem();

        // Validate input
        if (stuId.isEmpty() || course.isEmpty() || date.isEmpty() || type == null) {
            JOptionPane.showMessageDialog(null, "Please fill in Student ID, Course Code, Date, and Type.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(null,
                "Are you sure you want to delete this attendance record?",
                "Confirm Deletion", JOptionPane.YES_NO_OPTION);

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        String sql = "DELETE FROM attendance WHERE stuid = ? AND ccode = ? AND sdate = ? AND ctype = ?";

        try {
            DbConnector db = new DbConnector();
            Connection conn = db.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, stuId);
            pstmt.setString(2, course);
            pstmt.setDate(3, Date.valueOf(date));
            pstmt.setString(4, type);

            int deleted = pstmt.executeUpdate();

            pstmt.close();
            conn.close();

            if (deleted > 0) {
                JOptionPane.showMessageDialog(null, "Attendance record deleted successfully!");
                loadAttendanceTable(); // Refresh table
            } else {
                JOptionPane.showMessageDialog(null, "Delete failed. Record not found.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database error occurred.");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, "Invalid date format. Use YYYY-MM-DD.");
        }
    }



    private void clearMedicalFields() {
        medtextstu.setText("");
        medtextcourse.setText("");
        medtextdate.setText("");
        medtextdes.setText("");

    }


//Medical add,edit,delete
private void addMedical() {
    stuid = medtextstu.getText().trim();
    course = medtextcourse.getText().trim();
    date = medtextdate.getText().trim();
    des = medtextdes.getText();

    // Validate input
    if (stuid.isEmpty() || course.isEmpty() || date.isEmpty() || des == null ) {
        JOptionPane.showMessageDialog(null, "Please fill in all fields.");
        return;
    }

    // Check if the student has an absent status for that date and course
    String checkStatusSql = "SELECT present FROM attendance WHERE stuid = ? AND ccode = ? AND sdate = ?";

    try {
        DbConnector db = new DbConnector();
        Connection conn = db.getConnection();
        PreparedStatement checkStmt = conn.prepareStatement(checkStatusSql);
        checkStmt.setString(1, stuid);
        checkStmt.setString(2, course);
        checkStmt.setDate(3, Date.valueOf(date));

        ResultSet rs = checkStmt.executeQuery();

        if (rs.next()) {
            int presentStatus = rs.getInt("present");

            // If the student is not absent (present status is not 0), do not allow adding medical
            if (presentStatus != 0) {
                JOptionPane.showMessageDialog(null, "Medical can only be added for absent students.");
                return;
            }
        } else {
            JOptionPane.showMessageDialog(null, "No attendance record found for the given student, course, and date.");
            return;
        }

        // Insert the medical record
        String sql = "INSERT INTO medical (stuid, ccode, mdate, mdescription) VALUES (?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, stuid);
        pstmt.setString(2, course);
        pstmt.setDate(3, Date.valueOf(date)); // Ensure date is in YYYY-MM-DD format
        pstmt.setString(4, des);

        pstmt.executeUpdate();

        // Update the attendance to set msubmit = 1 (medical submitted) for that record
        String updateSql = "UPDATE attendance SET msubmit = 1 WHERE stuid = ? AND ccode = ? AND sdate = ? AND present = 0";
        PreparedStatement updateStmt = conn.prepareStatement(updateSql);
        updateStmt.setString(1, stuid);
        updateStmt.setString(2, course);
        updateStmt.setDate(3, Date.valueOf(date));
        updateStmt.executeUpdate();

        updateStmt.close();
        pstmt.close();
        conn.close();

        JOptionPane.showMessageDialog(null, "Medical added successfully!");
        clearMedicalFields();
        createMedicalTable(); // Refresh the table

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Failed to add medical.");
    } catch (IllegalArgumentException ex) {
        JOptionPane.showMessageDialog(null, "Invalid date format. Use YYYY-MM-DD.");
    }
}



    private void loadMedicalTable() {
        String sql = "SELECT m.medid, m.stuid, m.ccode, m.mdate, m.mdescription " +
                "FROM medical m " +
                "JOIN student s ON m.stuid = s.stuid " +
                "ORDER BY m.mdate DESC";


        try {
            DbConnector db = new DbConnector();
            Connection conn = db.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // You can use DefaultTableModel to populate JTable
            DefaultTableModel model = (DefaultTableModel) med_table.getModel();
            model.setRowCount(0); // Clear existing data

            while (rs.next()) {
                int med = rs.getInt("medid");
                String stuid = rs.getString("stuid");
                String course = rs.getString("ccode");
                String date = rs.getString("mdate");
                String des = rs.getString("mdescription");


                model.addRow(new Object[]{med,stuid, course, date, des});
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading attendance data.");
        }
    }


    private void editMedical() {
        String mid = medid.getText().trim();
        String stuId = Emedstu.getText().trim();
        String course = Emedcour.getText().trim();
        String date = Emeddate.getText().trim();
        String description = Emeddes.getText().trim();

        // Validate input
        if (stuId.isEmpty() || course.isEmpty() || date.isEmpty() || description.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields.");
            return;
        }

        String sql = "UPDATE medical SET mdescription = ?, mdate = ? ,stuid = ? , ccode = ? WHERE medid = ?";

        try {
            DbConnector db = new DbConnector();
            Connection conn = db.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, description);
            pstmt.setString(3, stuId);
            pstmt.setString(4, course);
            pstmt.setDate(2, Date.valueOf(date));
            pstmt.setInt(5, Integer.parseInt(mid));


            int updated = pstmt.executeUpdate();

            pstmt.close();
            conn.close();

            if (updated > 0) {
                JOptionPane.showMessageDialog(null, "Medical record updated successfully!");
                loadMedicalTable(); // Refresh the table
            } else {
                JOptionPane.showMessageDialog(null, "Update failed. Record not found.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database error occurred.");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, "Invalid date format. Use YYYY-MM-DD.");
        }
    }


    private void searchMedicalRecord() {
        String stuId = semedstu.getText().trim();
        String courseCode = semedcour.getText().trim();

        String sql = "SELECT m.medid, m.stuid, m.ccode, m.mdate, m.mdescription " +
                "FROM medical m " +
                "JOIN student s ON m.stuid = s.stuid " +
                "WHERE (? = '' OR m.stuid = ?) AND (? = '' OR m.ccode = ?) " +
                "ORDER BY m.mdate DESC";

        try {
            DbConnector db = new DbConnector();
            Connection conn = db.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // Set parameters for student ID and course code, even if empty
            pstmt.setString(1, stuId.isEmpty() ? "" : stuId);
            pstmt.setString(2, stuId);
            pstmt.setString(3, courseCode.isEmpty() ? "" : courseCode);
            pstmt.setString(4, courseCode);

            ResultSet rs = pstmt.executeQuery();

            DefaultTableModel model = (DefaultTableModel) med_table.getModel();
            model.setRowCount(0); // Clear table first

            boolean foundRecords = false; // Flag to check if any records are found

            while (rs.next()) {
                foundRecords = true; // Set flag to true when records are found

                String mid = rs.getString("medid");
                String id = rs.getString("stuid");
                String course = rs.getString("ccode");
                String date = rs.getString("mdate");
                String description = rs.getString("mdescription");

                model.addRow(new Object[]{mid, id, course, date, description});
            }

            // If no records are found, show a message
            if (!foundRecords) {
                JOptionPane.showMessageDialog(null, "No medical records found for the provided Student ID and Course Code.");
            }

            rs.close();
            pstmt.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error fetching medical records.");
        }
    }


    private void deleteMedicalRecord() {
        String mid = medid.getText().trim();

        int confirm = JOptionPane.showConfirmDialog(null,
                "Are you sure you want to delete this attendance record?",
                "Confirm Deletion", JOptionPane.YES_NO_OPTION);

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        String sql = "DELETE FROM medical WHERE medid = ?";

        try {
            DbConnector db = new DbConnector();
            Connection conn = db.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, mid);


            int deleted = pstmt.executeUpdate();

            pstmt.close();
            conn.close();

            if (deleted > 0) {
                JOptionPane.showMessageDialog(null, "Medical record deleted successfully!");
                loadAttendanceTable(); // Refresh table
            } else {
                JOptionPane.showMessageDialog(null, "Delete failed. Record not found.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database error occurred.");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, "Invalid date format. Use YYYY-MM-DD.");
        }


    }





    private void createMedicalTable() {
        String[] medcol = {"Medical ID","Student ID","Course Code","Date", "Description"};
        Object[][] data = {};

        DefaultTableModel model = new DefaultTableModel(data, medcol);
        med_table.setModel(model);

        JTableHeader header = med_table.getTableHeader();
        header.setFont(new Font("SansSerif", Font.BOLD, 14));
        header.setBackground(new Color(204, 255, 204));
        header.setForeground(Color.BLACK);
    }





    private void viewAttendance() {
        DbConnector db = new DbConnector();
        Connection conn = db.getConnection();

        String sql = "SELECT * FROM attendance";

        try {

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // Set column headers only once
            String[] columns = {"Student ID", "Course Code", "Date", "Type", "Present", "Hours", "Medical ID"};
            DefaultTableModel model = new DefaultTableModel(columns, 0);
            view_atten_table.setModel(model);

            while (rs.next()) {
                String stuid = rs.getString("stuid");
                String course = rs.getString("ccode");
                String date = rs.getString("sdate");
                String type = rs.getString("ctype");
                String status = rs.getString("status");
                String hours = rs.getString("hours");
                String med = rs.getString("medid");

                model.addRow(new Object[]{stuid, course, date, type, status, hours, med});
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void createAttendanceViewTable() {
        String[] latent = {"Student ID", "Course Code", "Date", "Type", "Present", "Hours", "Medical Submitted"};
        DefaultTableModel model = new DefaultTableModel(null, latent);
        view_atten_table.setModel(model);
    }

    private void viewMedical(){

    }

    public static void main(String[] args) {
        new TechOfficer("John1","John");

    }

}
