package mikh.alexey.finman.swing;

import mikh.alexey.finman.logic.Account;
import mikh.alexey.finman.logic.LogicSystem;
import mikh.alexey.finman.logic.Record;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static mikh.alexey.finman.helpers.Util.centerFrame;
import static mikh.alexey.finman.helpers.Util.createIcon;
import static mikh.alexey.finman.helpers.Util.reLogin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

/**
 * @author lxmikh@gmail.com
 */

public class MainUI extends JFrame implements ActionListener {

    private static Logger logger = LoggerFactory.getLogger(MainUI.class);

    private static final String CMD_ADD_RECORD = "cmdAddRecord";
    private static final String CMD_RELOGIN = "cmdRelogin";
    private static final String CMD_EXIT = "cmdExit";
    private static final String CMD_ADD_ACCOUNT = "cmdAddAccount";
    private static final String CMD_EDIT_ACCOUNT = "cmdEditAccount";
    private static final String CMD_DELETE_ACCOUNT = "cmdDeleteAccount";
    private static final String CMD_EDIT_CATEGORIES = "cmdEditCategories";
    private static final String CMD_SETTINGS = "cmdSettings";
    private static final String CMD_ABOUT = "cmdAbout";

    private JLabel userNameLabel = new JLabel("User: ");
    private JLabel balanceLabel = new JLabel("Balance: ");
    private JLabel accountLabel = new JLabel("Account: ");
    private JLabel currentBalanceLabel = new JLabel();
    private DefaultComboBoxModel<Account> modelComboBox;
    private JComboBox accountList;
    private DefaultListModel<Record> recordsListModel;
    private JButton addRecordButton = new JButton("Add Record");
    private double accountBalance;

    private LogicSystem logicSystem;

    public MainUI(final LogicSystem logicSystem) {
        super("Financial Manager");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(400, 600);
        setResizable(false);

        this.logicSystem = logicSystem;

        recordsListModel = new DefaultListModel<>();
        JList<Record> listRecords = new JList<>();
        listRecords.setCellRenderer(new RecordView());
        listRecords.setModel(recordsListModel);
        listRecords.setLayoutOrientation(JList.VERTICAL);

        modelComboBox = new DefaultComboBoxModel<>();
        updateModelComboBox();
        accountList = new JComboBox<>(modelComboBox);
        accountList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Account account = (Account) accountList.getSelectedItem();
                logicSystem.setCurrentAccount(account);
                viewRecords(account);
            }
        });

        Account curAccount;
        if ((curAccount = modelComboBox.getElementAt(0)) != null) {
            logicSystem.setCurrentAccount(curAccount);
            accountList.setSelectedIndex(0);
            viewRecords(curAccount);
        }

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel recordPanel = new JPanel();
        recordPanel.setBorder(BorderFactory.createTitledBorder("Records"));
        recordPanel.setLayout(new BorderLayout());
        recordPanel.add(new JScrollPane(listRecords), BorderLayout.CENTER);

        setJMenuBar(addMenu());
        mainPanel.add(createUserPanel(curAccount), BorderLayout.NORTH);
        mainPanel.add(recordPanel, BorderLayout.CENTER);
        setContentPane(mainPanel);

    }

    public JPanel createUserPanel(Account account) {
        JPanel userPanel = new JPanel();
        userPanel.setBorder(BorderFactory.createTitledBorder("User data"));
        userPanel.setLayout(new GridBagLayout());

        JLabel avatarImgLabel = new JLabel(createIcon(getClass(), "img/imgAvatar/" + logicSystem.getCurrentUser().getAvatarFileName()));
        JLabel currentUserLabel = new JLabel(logicSystem.getCurrentUser().getLogin());
        currentUserLabel.setForeground(Color.RED);

        if (account != null) {
            updateBalance(account);
        }

        addRecordButton.setActionCommand(CMD_ADD_RECORD);
        addRecordButton.addActionListener(this);

        userPanel.add(avatarImgLabel, new GridBagConstraints(0, 0, 2, 2, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        userPanel.add(userNameLabel, new GridBagConstraints(2, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        userPanel.add(currentUserLabel, new GridBagConstraints(3, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        userPanel.add(accountLabel, new GridBagConstraints(4, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        userPanel.add(accountList, new GridBagConstraints(5, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        userPanel.add(balanceLabel, new GridBagConstraints(2, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        userPanel.add(currentBalanceLabel, new GridBagConstraints(3, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        userPanel.add(addRecordButton, new GridBagConstraints(5, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        return userPanel;
    }

    private void viewRecords(Account curAccount) {
        updateBalance(curAccount);
        recordsListModel.clear();
        Set<Record> records = logicSystem.getRecords(curAccount);
        for (Record record : records) {
            recordsListModel.addElement(record);
        }
    }

    public void updateBalance(Account account){
        accountBalance = account.getCurBalance();
        currentBalanceLabel.setText(accountBalance + " RUB");
    }

    public void updateModelComboBox(){
        for (Account acc : logicSystem.getAccounts(logicSystem.getCurrentUser())) {
            modelComboBox.addElement(acc);
        }
        accountList = new JComboBox<>(modelComboBox);
    }

    public JMenuBar addMenu() {

        final JMenuBar menuBar = new JMenuBar();
        JMenu genMenu = new JMenu("Menu");
        JMenu accountMenu = new JMenu("Account");
        JMenu otherMenu = new JMenu("Other");
        JMenu aboutMenu = new JMenu("?");

        JMenuItem relogin = new JMenuItem("Relogin");
        relogin.setActionCommand(CMD_RELOGIN);
        relogin.addActionListener(this);
        JMenuItem exit = new JMenuItem("Exit");
        exit.setActionCommand(CMD_EXIT);
        exit.addActionListener(this);

        genMenu.add(relogin);
        genMenu.add(new JSeparator());
        genMenu.add(exit);

        JMenuItem addAccount = new JMenuItem("Add");
        addAccount.setActionCommand(CMD_ADD_ACCOUNT);
        addAccount.addActionListener(this);
        JMenuItem editAccount = new JMenuItem("Edit");
        editAccount.setActionCommand(CMD_EDIT_ACCOUNT);
        editAccount.addActionListener(this);
        JMenuItem deleteAccount = new JMenuItem("Delete");
        deleteAccount.setActionCommand(CMD_DELETE_ACCOUNT);
        deleteAccount.addActionListener(this);

        accountMenu.add(addAccount);
        accountMenu.add(editAccount);
        accountMenu.add(deleteAccount);

        JMenuItem categories = new JMenuItem("Categories");
        categories.setActionCommand(CMD_EDIT_CATEGORIES);
        categories.addActionListener(this);
        JMenuItem settings = new JMenuItem("Settings");
        settings.setActionCommand(CMD_SETTINGS);
        settings.addActionListener(this);

        otherMenu.add(categories);
        otherMenu.add(new JSeparator());
        otherMenu.add(settings);

        JMenuItem about = new JMenuItem("About");
        about.setActionCommand(CMD_ABOUT);
        about.addActionListener(this);
        aboutMenu.add(about);

        menuBar.add(genMenu);
        menuBar.add(accountMenu);
        menuBar.add(otherMenu);
        menuBar.add(Box.createHorizontalGlue());
        menuBar.add(aboutMenu);

        return menuBar;
    }

    public AccountDialog createAccDialog(boolean isAddAccount, boolean isEditAccount) {
        AccountDialog aDialog = new AccountDialog(logicSystem, isAddAccount, isEditAccount);
        centerFrame(aDialog);
        aDialog.setModal(true);
        aDialog.setVisible(true);
        return aDialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        switch (cmd) {
            case CMD_ADD_RECORD:
                RecordDialog rDialog = new RecordDialog(logicSystem, true, false);
                centerFrame(rDialog);
                rDialog.setModal(true);
                rDialog.setVisible(true);
                viewRecords(logicSystem.getCurrentAccount());
                break;
            case CMD_RELOGIN:
                reLogin(MainUI.this);
                break;
            case CMD_EXIT:
                System.exit(0);
                break;
            case CMD_ADD_ACCOUNT:
                createAccDialog(true, false);
                //updateModelComboBox();
                break;
            case CMD_EDIT_ACCOUNT:
                createAccDialog(false, true);
                break;
            case CMD_DELETE_ACCOUNT:
                break;
            case CMD_EDIT_CATEGORIES:
                break;
            case CMD_SETTINGS:
                break;
            case CMD_ABOUT:
                JOptionPane.showOptionDialog(getComponent(0),
                        "Financial manager \n ver.00001a \n " +
                                "Idea: Dmitriy Archangelskiy \n " +
                                "Releaser: Alexey Mikhaylyuk",
                        "About",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null, null, null);
                break;
        }
    }
}
