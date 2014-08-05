package mikh.alexey.finman.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author lxmikh@gmial.com
 */

public class LoginUI extends JFrame implements ActionListener{

    private JTextField loginField;
    private JPasswordField passwordField;
    private JLabel infoLabel;
    private JButton buttonLogin;
    private JButton buttonReg;

    public LoginUI(){
        super("Financial Manager");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(250, 150);
        setLayout(new BorderLayout());


    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
