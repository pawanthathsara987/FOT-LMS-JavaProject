import javax.swing.*;

public class LoginForm {
    private JPanel Main_panel;
    private JPanel Loading_panel;
    private JPanel Form_panel;
    private JTextField textField1;
    private JTextField textField2;
    private JButton loginButton;
    private JFrame frame;


    public LoginForm() {
        frame = new JFrame();
        frame.setTitle("Login Form");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(Main_panel);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }
}
