package mikh.alexey.finman.swing;

import mikh.alexey.finman.helpers.Util;
import mikh.alexey.finman.logic.LogicSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author lxmikh@gmail.com
 */

public class RegUI extends JFrame implements ActionListener{

    private static Logger logger = LoggerFactory.getLogger(RegUI.class);

    private JLabel avatarImgLabel;
    private JLabel loginLabel = new JLabel("Login:");
    private JLabel passLabel = new JLabel("Password:");
    private JLabel passConfirmLabel = new JLabel("Confirm password:");
    private JLabel accountFirstName = new JLabel("First account name:");
    private JLabel accountBalance = new JLabel("Account balance:");
    private JLabel accountDescLabel = new JLabel("Description:");
    private JTextField loginField = new JTextField(10);
    private JPasswordField passField = new JPasswordField(10);
    private JPasswordField passConfirmField = new JPasswordField(10);
    private JTextField accountNameField = new JTextField(10);
    private JTextField balanceField = new JTextField(10);
    private JTextArea accountDescripton = new JTextArea(5,5);
    private JButton createUserButton = new JButton("Create user");
    private JScrollPane descPane = new JScrollPane(accountDescripton);
    private JComboBox avatarList = new JComboBox();
    private JPanel regPanel = new JPanel();

    private LogicSystem logicSystem;

    public RegUI(LogicSystem logicSystem){
        super("New user");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        this.logicSystem = logicSystem;

        avatarImgLabel = new JLabel(Util.getInstance().createIcon(getClass(), "img/imgAvatar/emo.png"));

        descPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        descPane.setAlignmentX(LEFT_ALIGNMENT);
        accountDescripton.setLineWrap(true);

        createUserButton.addActionListener(this);

        regPanel.setLayout(new GridBagLayout());

        regPanel.add(avatarImgLabel, new GridBagConstraints(0, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        regPanel.add(avatarList, new GridBagConstraints(1, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        regPanel.add(loginLabel, new GridBagConstraints(0, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        regPanel.add(loginField, new GridBagConstraints(1, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        regPanel.add(passLabel, new GridBagConstraints(0, 2, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        regPanel.add(passField, new GridBagConstraints(1, 2, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        regPanel.add(passConfirmLabel, new GridBagConstraints(0, 3, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        regPanel.add(passConfirmField, new GridBagConstraints(1, 3, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        regPanel.add(new JSeparator(), new GridBagConstraints(0, 4, 2, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        regPanel.add(accountFirstName, new GridBagConstraints(0, 5, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        regPanel.add(accountNameField, new GridBagConstraints(1, 5, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        regPanel.add(accountBalance, new GridBagConstraints(0, 6, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        regPanel.add(balanceField, new GridBagConstraints(1, 6, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        regPanel.add(accountDescLabel, new GridBagConstraints(0, 7, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        regPanel.add(descPane, new GridBagConstraints(0, 8, 2, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        regPanel.add(createUserButton, new GridBagConstraints(0, 9, 2, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.CENTER,
                new Insets(2, 2, 2, 2), 0, 0));

        setContentPane(regPanel);
        pack();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Util.getInstance().reLogin(RegUI.this);

    }

}
