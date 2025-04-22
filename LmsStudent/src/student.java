import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class student {
    private JPanel mainPanal;
    private JPanel L_student;
    private JButton updateProfileButton;
    private JButton courseDetailsButton;
    private JButton viewAttendanceButton;
    private JButton timeTableButton;
    private JButton signOutButton;
    private JButton updateProfilePictureBtn;
    private JTextField textField2;
    private JTextField textField3;
    private JButton saveChangesButton;
    private JButton noticeButton;
    private JPanel sideBar;
    private JLabel userTitle;
    private JButton viewGradeBtn;
    private JButton addMedicalBtn;


    public student() {
        JFrame frame = new JFrame("Student");
        frame.add(mainPanal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1400,750);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


        saveChangesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == updateProfileButton) {
                    student st = new student();
                } else if (e.getSource() == viewAttendanceButton) {
//                    viewAttendance vt = new viewAttendance();
                } else if (e.getSource() == timeTableButton) {
                    TimeTable tb = new TimeTable();
                } else if (e.getSource() == signOutButton) {

                } else if (e.getSource() == noticeButton) {

                } else if (e.getSource() == addMedicalBtn) {

                }
            }
        };
        signOutButton.addActionListener(listener);
        courseDetailsButton.addActionListener(listener);
        viewAttendanceButton.addActionListener(listener);
        timeTableButton.addActionListener(listener);
        noticeButton.addActionListener(listener);
        viewGradeBtn.addActionListener(listener);
        addMedicalBtn.addActionListener(listener);
    }

    public static void main(String[] args) {
        student stu = new student();
    }

    public void showProfileDetails() {


    }

}
