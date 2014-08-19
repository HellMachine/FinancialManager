package mikh.alexey.finman.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author lxmikh@gmail.com
 */

public class RegUI extends JFrame implements ActionListener{

    private JLabel loginLabel = new JLabel("Login:");
    private JLabel passLabel = new JLabel("Password:");
    private JLabel passConfirmLabel = new JLabel("Confirm password:");
    private JLabel accountFirstName = new JLabel("First account name:");
    private JLabel accountBalance = new JLabel("Account balance:");
    private JLabel accountDescLabel = new JLabel("Description:");
    private JTextField loginField = new JTextField();
    private JPasswordField passField = new JPasswordField();
    private JPasswordField passConfirmField = new JPasswordField();
    private JTextField accountNameField = new JTextField();
    private JTextField balanceField = new JTextField();
    private JTextArea accountDescripton = new JTextArea(5,5);
    private JButton createUserButton = new JButton("Create user");
    private JScrollPane descPane = new JScrollPane(accountDescripton);
    private Image avatarImg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imgAvatar/angry.png"));
    private JComboBox avatarList = new JComboBox();
    private JPanel regPanel = new JPanel();

    public RegUI(){
        super("Registration new user");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(250,400);
        setResizable(false);

        regPanel.setLayout(new BoxLayout(regPanel, BoxLayout.Y_AXIS));

        descPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        descPane.setAlignmentX(LEFT_ALIGNMENT);
        accountDescripton.setLineWrap(true);

        regPanel.add(new JLabel((Icon) avatarImg));
        regPanel.add(avatarList);
        regPanel.add(loginLabel);
        regPanel.add(loginField);
        regPanel.add(passLabel);
        regPanel.add(passField);
        regPanel.add(passConfirmLabel);
        regPanel.add(passConfirmField);
        regPanel.add(accountFirstName);
        regPanel.add(accountNameField);
        regPanel.add(accountBalance);
        regPanel.add(balanceField);
        regPanel.add(accountDescLabel);
        regPanel.add(descPane);
        //FIXME выровнить кнопку по центру или правому краю
        regPanel.add(createUserButton);
        setContentPane(regPanel);

        createUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
