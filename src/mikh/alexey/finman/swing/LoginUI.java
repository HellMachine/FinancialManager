package mikh.alexey.finman.swing;

import mikh.alexey.finman.logic.LogicSystem;
import static mikh.alexey.finman.helpers.Util.centerFrame;
import static mikh.alexey.finman.helpers.Util.createIcon;
import static mikh.alexey.finman.helpers.Util.getHash;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author lxmikh@gmail.com
 */

public class LoginUI extends JFrame implements ActionListener {

    private static final String CMD_LOGIN = "cmdLogin";
    private static final String CMD_REGISTRATION = "cmdRegistration";

    private JTextField loginField = new JTextField(10);
    private JPasswordField passwordField = new JPasswordField(10);
    private JLabel alertMessage = new JLabel("Input login and password");
    private LogicSystem logicSystem;

    public LoginUI(LogicSystem logicSystem) {
        super("Financial Manager");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        this.logicSystem = logicSystem;

        loginField.setToolTipText("Input login");
        passwordField.setToolTipText("Input password");

        JLabel loginLabel = new JLabel();
        ImageIcon loginIcon = createIcon(getClass(), "img/Login.png");
        loginLabel.setIcon(new ImageIcon(loginIcon.getImage().getScaledInstance(24, 24, Image.SCALE_DEFAULT)));

        JLabel passLabel = new JLabel();
        ImageIcon passIcon = createIcon(getClass(), "img/Lock.png");
        passLabel.setIcon(new ImageIcon(passIcon.getImage().getScaledInstance(24, 24, Image.SCALE_DEFAULT)));

        JButton buttonLogin = new JButton("Login");
        buttonLogin.setActionCommand(CMD_LOGIN);
        buttonLogin.addActionListener(this);
        JButton buttonReg = new JButton("Registration");
        buttonReg.setActionCommand(CMD_REGISTRATION);
        buttonReg.addActionListener(this);

        JPanel mainPanel = new JPanel();
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
                String login = loginField.getText();
                String pass = getHash(new String(passwordField.getPassword()));
                if (login.isEmpty() || pass.isEmpty()){
                    return;
                }else if (logicSystem.login(login, pass) != null){
                    JFrame mainUI = new MainUI(logicSystem);
                    centerFrame(mainUI);
                    LoginUI.this.setVisible(false);
                    mainUI.setVisible(true);
                } else {
                    alertMessage.setText("Invalid login/pass!");
                }
                break;
            case CMD_REGISTRATION:
                JFrame regFrame = new RegUI(logicSystem);
                centerFrame(regFrame);
                LoginUI.this.setVisible(false);
                regFrame.setVisible(true);
                break;
        }

    }
}
