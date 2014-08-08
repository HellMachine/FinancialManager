package mikh.alexey.finman.swing;

import mikh.alexey.finman.helpers.JFrameHelper;
import mikh.alexey.finman.helpers.MD5;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author lxmikh@gmial.com
 */

public class LoginUI extends JFrame implements ActionListener {

    private JTextField loginField = new JTextField();
    private JPasswordField passwordField = new JPasswordField();
    private JLabel loginLabel = new JLabel("Login:");
    private JLabel passLabel = new JLabel("Password:");
    private JButton buttonLogin = new JButton("Login");
    private JButton buttonReg = new JButton("Registration");
    private JPanel upPanel = new JPanel();
    private JPanel downPanel = new JPanel();
    private JPanel mainPanel = new JPanel();

    private String pass = "";

    public LoginUI() {
        super("Financial Manager");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(250, 150);
        setResizable(false);

        upPanel.setLayout(new BoxLayout(upPanel, BoxLayout.Y_AXIS));
        downPanel.setLayout(new FlowLayout());
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

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

                //3 след. строки, чтобы избежать deprecated метода getText() для JPasswordField
                char[] tmp = passwordField.getPassword();
                for (int i = 0; i < tmp.length; i++) {
                    pass += tmp[i];
                }
                    //Проверка на соответствие [must delete]
                    System.out.println("Origin pass: " + pass + "\nMD5 pass: " + md5.getHash(pass) + "\n");
                pass = "";
            }
        });

        buttonReg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame regFrame = new RegUI();
                JFrameHelper.getInstance().centerFrame(regFrame);
                LoginUI.this.setVisible(false);
                regFrame.setVisible(true);
            }
        });

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
