import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

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
    private JComboBox comboBox7;
    private JComboBox comboBox8;
    private JComboBox comboBox9;
    private JTextField textField9;
    private JTextField textField10;
    private JTextField textField11;
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


    }

    private void createAttendanceTable() {
        String[] columns = {"Student ID", "Course Code", "Date", "Type","Present", "Level"};
        Object[][] data = {}; // or populate from DB later

        DefaultTableModel model = new DefaultTableModel(data, columns);
        attenViewtable.setModel(model);


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
