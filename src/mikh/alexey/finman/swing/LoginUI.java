package mikh.alexey.finman.swing;

import mikh.alexey.finman.helpers.MD5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author lxmikh@gmial.com
 */

public class LoginUI extends JFrame implements ActionListener{

    private JTextField loginField = new JTextField();
    private JPasswordField passwordField = new JPasswordField();
    private JLabel loginLabel = new JLabel("Login:");
    private JLabel passLabel = new JLabel("Password:");
    private JButton buttonLogin = new JButton("Login");
    private JButton buttonReg = new JButton("Registration");
    private JPanel mainPanel = new JPanel();
    private JPanel upPanel = new JPanel();
    private JPanel downPanel = new JPanel();

    String pass = "";

    public LoginUI(){
        super("Financial Manager");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(250, 150);
        setResizable(false);

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        upPanel.setLayout(new BoxLayout(upPanel, BoxLayout.Y_AXIS));
        downPanel.setLayout(new FlowLayout());

        loginField.setToolTipText("Input login");
        passwordField.setToolTipText("Input password");

        upPanel.add(loginLabel);
        upPanel.add(loginField);
        upPanel.add(passLabel);
        upPanel.add(passwordField);
        downPanel.add(buttonReg);
        downPanel.add(buttonLogin);
        mainPanel.add(upPanel);
        mainPanel.add(downPanel);
        setContentPane(mainPanel);

        buttonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MD5 md5 = new MD5();

                char[] tmp = passwordField.getPassword();
                for(int i = 0; i < tmp.length; i++){
                    pass += tmp[i];
                }

                System.out.println("Origin login: " + loginField.getText() + "\nMD5 login: " + md5.getHash(loginField.getText()) + "\n");
                System.out.println("Origin pass: " + pass + "\nMD5 pass: " + md5.getHash(pass) + "\n");

                pass = "";
            }
        });

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
