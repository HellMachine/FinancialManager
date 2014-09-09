package mikh.alexey.finman.swing;

import mikh.alexey.finman.logic.Account;
import mikh.alexey.finman.logic.LogicSystem;
import mikh.alexey.finman.logic.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author lxmikh@gmail.com
 */

public class AccountDialog extends JDialog implements ActionListener {

    private static Logger logger = LoggerFactory.getLogger(AccountDialog.class);

    private static final String CMD_ADD_ACTION = "cmdAddAction";
    private static final String CMD_EDIT_ACTION = "cmdEditAction";
    private static final String CMD_CANCEL_ACTION = "cmdCancelAction";

    private JTextField accountNameField;
    private JTextField accountBalanceField;
    private JTextArea accountDescripton;
    private boolean isAddAction = false;
    private boolean isEditAction = false;
    private LogicSystem logicSystem;

    public AccountDialog(LogicSystem logicSystem, boolean isAddAction, boolean isEditAction) {
        super();
        setTitle("Add/Edit account");
        this.logicSystem = logicSystem;
        this.isAddAction = isAddAction;
        this.isEditAction = isEditAction;

        JButton addAccountButton = new JButton("Add");
        if (!isAddAction) {
            addAccountButton.setEnabled(false);
        }
        JButton editAccountButton = new JButton("Edit");
        if (!isEditAction) {
            editAccountButton.setEnabled(false);
        }

        accountDescripton = new JTextArea(5, 5);
        JScrollPane descPane = new JScrollPane(accountDescripton);
        descPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        descPane.setAlignmentX(LEFT_ALIGNMENT);
        accountDescripton.setLineWrap(true);

        addAccountButton.setActionCommand(CMD_ADD_ACTION);
        addAccountButton.addActionListener(this);
        editAccountButton.setActionCommand(CMD_EDIT_ACTION);
        editAccountButton.addActionListener(this);
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setActionCommand(CMD_CANCEL_ACTION);
        cancelButton.addActionListener(this);

        JPanel accountPanel = new JPanel();
        accountPanel.setLayout(new GridBagLayout());

        JLabel accountNameLabel = new JLabel("Account name:");
        accountPanel.add(accountNameLabel, new GridBagConstraints(0, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        accountNameField = new JTextField(10);
        accountPanel.add(accountNameField, new GridBagConstraints(1, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        JLabel accountBalanceLabel = new JLabel("Account balance:");
        accountPanel.add(accountBalanceLabel, new GridBagConstraints(0, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        accountBalanceField = new JTextField(10);
        accountPanel.add(accountBalanceField, new GridBagConstraints(1, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        JLabel accountDescLabel = new JLabel("Description:");
        accountPanel.add(accountDescLabel, new GridBagConstraints(0, 2, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        accountPanel.add(descPane, new GridBagConstraints(0, 3, 2, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        accountPanel.add(addAccountButton, new GridBagConstraints(0, 4, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        accountPanel.add(editAccountButton, new GridBagConstraints(1, 4, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        accountPanel.add(cancelButton, new GridBagConstraints(0, 5, 2, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.CENTER,
                new Insets(2, 2, 2, 2), 0, 0));

        setContentPane(accountPanel);
        pack();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        switch (cmd){
            case CMD_ADD_ACTION:
                User curUser = logicSystem.getCurrentUser();
                String accountName = accountNameField.getText();
                Double balance = new Double(accountBalanceField.getText());
                String accountDesc = accountDescripton.getText();
                boolean accountDataReady = accountName.isEmpty() || accountDesc.isEmpty();// || accountBalanceField.getText().isEmpty();

                //if (accountDataReady) {
                    Account newAccount = new Account();
                    newAccount.setNameAcc(accountName);
                    newAccount.setCurBalance(balance);
                    newAccount.setAccountDesc(accountDesc);
                    logicSystem.addAccount(curUser, newAccount);
                    logger.info("New account - {} created success. Owner - {}", accountName, curUser.getLogin());
                //}
                dispose();
                break;
            case CMD_EDIT_ACTION:
                dispose();
                break;
            case CMD_CANCEL_ACTION:
                dispose();
                break;
        }

    }
}
