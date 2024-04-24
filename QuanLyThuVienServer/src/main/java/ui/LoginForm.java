package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm extends  JFrame{
    private JTextField textField1;
    private JPasswordField password1;
    private JButton login1;
    private JButton login2;
    private JPanel loginPanel;
    

    public LoginForm() {
        super();
        setTitle("Login");
        setContentPane(loginPanel);
        setMinimumSize(new Dimension(500, 300));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        login1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        login2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                String username = textField1.getText();
//                String password = new password1.getPassword();
//                System.out.println(username + " ");
            }
        });

    }
    
    public static void main(String[] args) {
    	System.out.println("Hello");
		JFrame jf = new JFrame();
		LoginForm loginForm = new LoginForm();
		loginForm.setVisible(true);
	}


}
