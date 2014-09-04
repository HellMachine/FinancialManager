package mikh.alexey.finman.swing;

import mikh.alexey.finman.helpers.Util;
import mikh.alexey.finman.logic.LogicSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author lxmikh@gmail.com
 */

public class LoginUI extends JFrame implements ActionListener {

    private static final String CMD_LOGIN = "cmdLogin";
    private static final String CMD_REGESTRATION = "cmdRegistration";

    private JLabel loginLabel;
    private JLabel passLabel;
    private JTextField loginField = new JTextField(10);
    private JPasswordField passwordField = new JPasswordField(10);
    private JButton buttonLogin = new JButton("Login");
    private JButton buttonReg = new JButton("Registration");
    private JLabel alertMessage = new JLabel("Input login and password");
    private JPanel mainPanel = new JPanel();

    private LogicSystem logicSystem;

    public LoginUI(LogicSystem logicSystem) {
        super("Financial Manager");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        this.logicSystem = logicSystem;

        loginField.setToolTipText("Input login");
        passwordField.setToolTipText("Input password");

        loginLabel = new JLabel();
        ImageIcon loginIcon = Util.getInstance().createIcon(getClass(), "img/Login.png");
        loginLabel.setIcon(new ImageIcon(loginIcon.getImage().getScaledInstance(24, 24, Image.SCALE_DEFAULT)));

        passLabel = new JLabel();
        ImageIcon passIcon = Util.getInstance().createIcon(getClass(), "img/Lock.png");
        passLabel.setIcon(new ImageIcon(passIcon.getImage().getScaledInstance(24, 24, Image.SCALE_DEFAULT)));

        buttonLogin.setActionCommand(CMD_LOGIN);
        buttonLogin.addActionListener(this);
        buttonReg.setActionCommand(CMD_REGESTRATION);
        buttonReg.addActionListener(this);

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

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        switch (cmd){
            case CMD_LOGIN:
                //Проверка на соответствие [must delete]
                System.out.println("Origin pass: " + new String(passwordField.getPassword()) +
                        "\nMD5 pass: " + Util.getInstance().getHash(new String(passwordField.getPassword())));

                alertMessage.setText("Invalid login/pass!");
                pack();

                JFrame mainUI = new MainUI(logicSystem);
                Util.getInstance().centerFrame(mainUI);
                LoginUI.this.setVisible(false);
                mainUI.setVisible(true);
                break;
            case CMD_REGESTRATION:
                JFrame regFrame = new RegUI(logicSystem);
                Util.getInstance().centerFrame(regFrame);
                LoginUI.this.setVisible(false);
                regFrame.setVisible(true);
                break;
        }

    }
}
