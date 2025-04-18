import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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
    private JComboBox comboBox3;
    private JButton deleteButton;
    private JButton editButton;
    private JScrollBar scrollBar1;
    private JTable table1;
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
    private JComboBox atten_level_combo;
    private JScrollBar scrollBar3;
    private JTable attenViewtable;
    private JPanel Addatten;
    private JPanel Editatten;
    private JPanel deleteatten;
    private JButton createButton;
    private JButton editButton2;
    private JButton deleteButton3;
    private JButton atten_submitButton;
    private JTextField textField4;
    private JButton atten_deleteButton;
    private JComboBox aetype_combo;
    private JComboBox aepre_combo;
    private JComboBox ae_combo;
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
    private JComboBox med_comboBox1;
    private JComboBox med_comboBox2;
    private JPanel medtitlepanel;
    private JPanel medvtable;
    private JButton med_editButton;
    private JButton med_deleteButton;
    private JButton med_createButton;
    private JPanel med_button;
    private JTextField textField3;
    private JTextField textField5;
    private JComboBox comboBox4;
    private JComboBox comboBox6;
    private JButton editButton1;
    private JLabel medstu;
    private JLabel medcourse;
    private JLabel meddate;
    private JLabel med_des;
    private JLabel medestu;
    private JLabel medecourse;
    private JLabel mededate;
    private JLabel mededes;
    private JLabel medtitlelabel;
    private JTextField textField1;
    private JButton deleteButton2;
    private JButton submitButton;

    private String stuid;
    private String course;
    private String date;
    private String des;
    private String type;
    private String pre;


    public TechOfficer() {
        JFrame frame = new JFrame("Add Attendance");
        frame.setContentPane(MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(1400, 750);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);

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
        viewAttendanceButton.addActionListener(e -> cardlayout.show(cardpanel,"card4"));
        addMedicalButton.addActionListener(e -> cardlayout.show(cardpanel,"card2"));
        viewMedicalButton.addActionListener(e -> cardlayout.show(cardpanel,"card3"));

        createButton.addActionListener(e -> cardlayout.show(crtattenpanel,"card5"));
        editButton2.addActionListener(e -> cardlayout.show(crtattenpanel,"card6"));
        deleteButton3.addActionListener(e -> cardlayout.show(crtattenpanel,"card7"));

        med_createButton.addActionListener(e -> cardlayout.show(medcard,"mcard1"));
        med_editButton.addActionListener(e -> cardlayout.show(medcard,"mcard2"));
        med_deleteButton.addActionListener(e -> cardlayout.show(medcard,"mcard3"));

        createAttendanceTable();

        createMedicalTable();

        loadAttendanceTable();






        atten_submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addAttendance();
            }
        });

        aedit_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

    }

    private void clearAttendanceFields() {
        attenstu_text.setText("");
        attencour_text.setText("");
        attendate_text.setText("");
        atten_type_combo.setSelectedIndex(0);
        atten_pre_combo.setSelectedIndex(0);
    }


    private void createAttendanceTable() {
        String[] columns = {"Student ID", "Course Code", "Date", "Type","Present"};
        Object[][] data = {}; // or populate from DB later

        DefaultTableModel model = new DefaultTableModel(data, columns);
        attenViewtable.setModel(model);

    }

    private void addAttendance() {
        stuid = attenstu_text.getText().trim();
        course = attencour_text.getText().trim();
        date = attendate_text.getText().trim();
        type = (String) atten_type_combo.getSelectedItem();  // ctype
        pre = (String) atten_pre_combo.getSelectedItem();    // "Present"/"Absent"

        // Validate input
        if (stuid.isEmpty() || course.isEmpty() || date.isEmpty() || type == null || pre == null) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields.");
            return;
        }

        // Convert presence to TINYINT (1 = present, 0 = absent)
        int present = pre.equalsIgnoreCase("Present") ? 1 : 0;

        String sql = "INSERT INTO attendance (stuid, ccode, sdate, ctype, present, msubmit) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            DbConnector db = new DbConnector();
            Connection conn = db.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, stuid);
            pstmt.setString(2, course);
            pstmt.setDate(3, java.sql.Date.valueOf(date)); // Ensure date is in YYYY-MM-DD format
            pstmt.setString(4, type);
            pstmt.setInt(5, present);
            pstmt.setInt(6, 0); // msubmit is initially 0 unless medical is submitted

            pstmt.executeUpdate();

            pstmt.close();
            conn.close();

            JOptionPane.showMessageDialog(null, "Attendance added successfully!");
            clearAttendanceFields();
            createAttendanceTable(); // Refresh the table

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to add attendance.");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, "Invalid date format. Use YYYY-MM-DD.");
        }
    }



    private void loadAttendanceTable() {
        String sql = "SELECT a.stuid, a.ccode, a.sdate, a.ctype, " +
                "CASE a.present WHEN 1 THEN 'Present' ELSE 'Absent' END AS status " +
                "FROM attendance a " +
                "JOIN student s ON a.stuid = s.stuid " +
                "ORDER BY a.sdate DESC";

        try {
            DbConnector db = new DbConnector();
            Connection conn = db.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // You can use DefaultTableModel to populate JTable
            DefaultTableModel model = (DefaultTableModel) attenViewtable.getModel();
            model.setRowCount(0); // Clear existing data

            while (rs.next()) {
                String stuid = rs.getString("stuid");
                String course = rs.getString("ccode");
                String date = rs.getString("sdate");
                String type = rs.getString("ctype");
                String status = rs.getString("status");

                model.addRow(new Object[]{stuid, course, date, type, status});
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading attendance data.");
        }
    }


    






    private void createMedicalTable() {
        String[] medcol = {"Medical ID","Student ID","Course Code","Date", "Description"};
        Object[][] data = {};

        DefaultTableModel model = new DefaultTableModel(data, medcol);
        med_table.setModel(model);
    }




    public static void main(String[] args) {
        new TechOfficer();

    }










}
