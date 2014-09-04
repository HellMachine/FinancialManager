package mikh.alexey.finman.swing;

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

public class AccountDialog extends JDialog implements ActionListener {

    private static Logger logger = LoggerFactory.getLogger(AccountDialog.class);

    private static final String CMD_ADD_ACTION = "cmdAddAction";
    private static final String CMD_EDIT_ACTION = "cmdEditAction";
    private static final String CMD_CANCEL_ACTION = "cmdCancelAction";

    private JLabel accountNameLabel = new JLabel("Account name:");
    private JLabel accountBalanceLabel = new JLabel("Account balance:");
    private JLabel accountDescLabel = new JLabel("Description:");
    private JTextField accountNameField = new JTextField(10);
    private JTextField accountBalanceField = new JTextField(10);
    private JTextArea accountDescripton = new JTextArea(5, 5);
    private JScrollPane descPane = new JScrollPane(accountDescripton);
    private JButton addAccountButton = new JButton("Add");
    private JButton editAccountButton = new JButton("Edit");
    private JButton cancelButton = new JButton("Cancel");
    private JPanel accountPanel = new JPanel();
    private boolean isAddAction = false;
    private boolean isEditAction = false;
    private LogicSystem logicSystem;

    public AccountDialog(LogicSystem logicSystem, boolean isAddAction, boolean isEditAction) {
        super();
        setTitle("Add/Edit account");
        this.logicSystem = logicSystem;
        this.isAddAction = isAddAction;
        this.isEditAction = isEditAction;

        if (!isAddAction) {
            addAccountButton.setEnabled(false);
        }
        if (!isEditAction) {
            editAccountButton.setEnabled(false);
        }

        descPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        descPane.setAlignmentX(LEFT_ALIGNMENT);
        accountDescripton.setLineWrap(true);

        addAccountButton.setActionCommand(CMD_ADD_ACTION);
        addAccountButton.addActionListener(this);
        editAccountButton.setActionCommand(CMD_EDIT_ACTION);
        editAccountButton.addActionListener(this);
        cancelButton.setActionCommand(CMD_CANCEL_ACTION);
        cancelButton.addActionListener(this);

        accountPanel.setLayout(new GridBagLayout());

        accountPanel.add(accountNameLabel, new GridBagConstraints(0, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        accountPanel.add(accountNameField, new GridBagConstraints(1, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        accountPanel.add(accountBalanceLabel, new GridBagConstraints(0, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        accountPanel.add(accountBalanceField, new GridBagConstraints(1, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
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
