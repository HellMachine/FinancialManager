package mikh.alexey.finman.swing;

import mikh.alexey.finman.helpers.JFrameHelper;
import mikh.alexey.finman.helpers.MD5;
import mikh.alexey.finman.logic.LogicSystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author lxmikh@gmail.com
 */

public class LoginUI extends JFrame implements ActionListener {

    private JLabel loginLabel;
    private JLabel passLabel;
    private JTextField loginField = new JTextField(10);
    private JPasswordField passwordField = new JPasswordField(10);
    private JButton buttonLogin = new JButton("Login");
    private JButton buttonReg = new JButton("Registration");
    private JLabel alertMessage = new JLabel();
    private JPanel mainPanel = new JPanel();

    private String pass = "";
    private LogicSystem logicSystem;

    public LoginUI(LogicSystem logicSystem) {
        super("Financial Manager");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        this.logicSystem = logicSystem;

        loginField.setToolTipText("Input login");
        passwordField.setToolTipText("Input password");

        loginLabel = new JLabel();
        ImageIcon loginIcon = JFrameHelper.getInstance().createIcon(getClass(), "img/Login.png");
        loginLabel.setIcon(new ImageIcon(loginIcon.getImage().getScaledInstance(24, 24, Image.SCALE_DEFAULT)));

        passLabel = new JLabel();
        ImageIcon passIcon = JFrameHelper.getInstance().createIcon(getClass(), "img/Lock.png");
        passLabel.setIcon(new ImageIcon(passIcon.getImage().getScaledInstance(24, 24, Image.SCALE_DEFAULT)));

        mainPanel.setLayout(new GridBagLayout());

        mainPanel.add(loginLabel, new GridBagConstraints(0, 0, 1, 1, 0, 0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        mainPanel.add(loginField, new GridBagConstraints(1, 0, 2, 1, 0, 0,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(2, 2, 2, 2), 0, 0));
        mainPanel.add(passLabel, new GridBagConstraints(0, 1, 1, 1, 0, 0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        mainPanel.add(passwordField, new GridBagConstraints(1, 1, 2, 1, 0, 0,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(2, 2, 2, 2), 0, 0));
        mainPanel.add(alertMessage, new GridBagConstraints(1, 2, 2, 1, 0, 0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        mainPanel.add(buttonReg, new GridBagConstraints(1, 3, 1, 1, 0, 0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        mainPanel.add(buttonLogin, new GridBagConstraints(2, 3, 1, 1, 0, 0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));

        setContentPane(mainPanel);
        pack();

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
                alertMessage.setText("Invalid login/pass!");
                pack();
            }
        });

        buttonReg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LogicSystem logicSystem = new LogicSystem();
                JFrame regFrame = new RegUI(logicSystem);
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
