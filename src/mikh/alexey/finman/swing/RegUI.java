package mikh.alexey.finman.swing;

import mikh.alexey.finman.helpers.Util;
import mikh.alexey.finman.logic.Account;
import mikh.alexey.finman.logic.LogicSystem;
import mikh.alexey.finman.logic.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Arc2D;
import java.text.NumberFormat;

/**
 * @author lxmikh@gmail.com
 */

public class RegUI extends JFrame implements ActionListener {

    private static Logger logger = LoggerFactory.getLogger(RegUI.class);

    private String alertMessage = "Fill all fields!";
    private JLabel loginLabel = new JLabel("Login:");
    private JLabel passLabel = new JLabel("Password:");
    private JLabel passConfirmLabel = new JLabel("Confirm password:");
    private JLabel accountFirstName = new JLabel("First account name:");
    private JLabel accountBalance = new JLabel("Account balance:");
    private JLabel accountDescLabel = new JLabel("Description:");
    private JLabel alertMessageLabel = new JLabel(alertMessage);
    private JTextField loginField = new JTextField(10);
    private JPasswordField passField = new JPasswordField(10);
    private JPasswordField passConfirmField = new JPasswordField(10);
    private JTextField accountNameField = new JTextField(10);
    private JTextArea accountDescripton = new JTextArea(5, 5);
    private JButton createUserButton = new JButton("Create user");
    private JScrollPane descPane = new JScrollPane(accountDescripton);
    private JFormattedTextField balanceField;
    private NumberFormat balanceFormat;
    private JComboBox avatarList;
    private JPanel regPanel = new JPanel();

    private LogicSystem logicSystem;
    public static String[] avatarFilesList = new String[]{
            "angry.png", "clown.png", "emo.png", "ninja.png", "santa.png", "sweating.png"};

    public RegUI(LogicSystem logicSystem) {
        super("New user");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        this.logicSystem = logicSystem;

        DefaultComboBoxModel<String> modelComboBox = new DefaultComboBoxModel<>();
        for (String avatarFileName : avatarFilesList) {
            modelComboBox.addElement(avatarFileName);
        }
        avatarList = new JComboBox<>(modelComboBox);
        avatarList.setSelectedIndex(0);

        final JLabel avatarImgLabel = new JLabel(Util.getInstance().createIcon(getClass(), "img/imgAvatar/" + avatarList.getSelectedItem()));
        avatarList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                avatarImgLabel.setIcon(Util.getInstance().createIcon(getClass(), "img/imgAvatar/" + avatarList.getSelectedItem()));
                logger.info("Avatar name: {}", avatarList.getSelectedItem().toString());
            }
        });

        balanceFormat = NumberFormat.getNumberInstance();
        balanceField = new JFormattedTextField(balanceFormat);

        descPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        descPane.setAlignmentX(LEFT_ALIGNMENT);
        accountDescripton.setLineWrap(true);

        createUserButton.addActionListener(this);

        regPanel.setLayout(new GridBagLayout());

        regPanel.add(alertMessageLabel, new GridBagConstraints(0, 0, 2, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.CENTER,
                new Insets(2, 2, 2, 2), 0, 0));
        regPanel.add(avatarImgLabel, new GridBagConstraints(0, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        regPanel.add(avatarList, new GridBagConstraints(1, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        regPanel.add(loginLabel, new GridBagConstraints(0, 2, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        regPanel.add(loginField, new GridBagConstraints(1, 2, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        regPanel.add(passLabel, new GridBagConstraints(0, 3, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        regPanel.add(passField, new GridBagConstraints(1, 3, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        regPanel.add(passConfirmLabel, new GridBagConstraints(0, 4, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        regPanel.add(passConfirmField, new GridBagConstraints(1, 4, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        regPanel.add(new JSeparator(), new GridBagConstraints(0, 5, 2, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        regPanel.add(accountFirstName, new GridBagConstraints(0, 6, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        regPanel.add(accountNameField, new GridBagConstraints(1, 6, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        regPanel.add(accountBalance, new GridBagConstraints(0, 7, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        regPanel.add(balanceField, new GridBagConstraints(1, 7, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        regPanel.add(accountDescLabel, new GridBagConstraints(0, 8, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        regPanel.add(descPane, new GridBagConstraints(0, 9, 2, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        regPanel.add(createUserButton, new GridBagConstraints(0, 10, 2, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.CENTER,
                new Insets(2, 2, 2, 2), 0, 0));

        setContentPane(regPanel);
        pack();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        User newUser;
        Account newAccount;
        String avatarName = avatarList.getSelectedItem().toString();
        String login = loginField.getText();
        String pass = Util.getInstance().getHash(new String(passField.getPassword()));
        String passConfirm = Util.getInstance().getHash(new String(passConfirmField.getPassword()));
        String accountName = accountNameField.getText();
        Double balance = ((Number)balanceField.getValue()).doubleValue();
        String accountDesc = accountDescripton.getText();

        if (login.isEmpty() || pass.isEmpty() || passConfirm.isEmpty() ||
                accountName.isEmpty() || balance.equals("") || accountDesc.isEmpty()){
            alertMessage.toUpperCase();
            alertMessageLabel.setForeground(Color.RED);
            return;
        } else if (logicSystem.isUserExist(login)){
            alertMessage = "User with this name EXIST!";
            return;
        } else if (pass != passConfirm){
            alertMessage = "Passwords don't match!";
            return;
        } else {
            newUser = new User(login, pass, avatarName);
            newAccount = new Account();
            newAccount.setNameAcc(accountName);
            newAccount.setCurBalance(balance);
            newAccount.setAccountDesc(accountDesc);
            logicSystem.createNewUser(login, pass, avatarName);
            logger.info("New user - {} created success.", login);
            logicSystem.addAccount(newUser, newAccount);
            logger.info("New account - {} created success. Owner - {}", accountName, login);
        }
        Util.getInstance().reLogin(RegUI.this);
    }

}
