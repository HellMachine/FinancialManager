package mikh.alexey.finman.swing;

import mikh.alexey.finman.logic.Category;
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

public class RecordDialog extends JDialog implements ActionListener {

    private static Logger logger = LoggerFactory.getLogger(RecordDialog.class);

    private static final String CMD_REFILL_RB = "cmdIsRefill";
    private static final String CMD_WITHDRAW_RB = "cmdIsWithdraw";
    private static final String CMD_ADD = "cmdAddRecord";
    private static final String CMD_DELETE = "cmdDeleteRecord";
    private static final String CMD_CANCEL = "cmdCancelAction";

    private JRadioButton refillRadioButton = new JRadioButton("Plus");
    private JRadioButton withdrawRadioButton = new JRadioButton("Minus");
    private JLabel amountLabel = new JLabel("Amount:");
    private JLabel categoryLabel = new JLabel("Category:");
    private JLabel recordDescLabel = new JLabel("Description:");
    private JLabel confirmMessageLabel = new JLabel("I agree to the deletion of a record.");
    private JTextField amountField = new JTextField(10);
    private JTextArea recordDescripton = new JTextArea(5, 5);
    private JScrollPane descPane = new JScrollPane(recordDescripton);
    private JCheckBox confirmCheckBox = new JCheckBox();
    private JButton addButton = new JButton("Add");
    private JButton deleteButton = new JButton("Delete");
    private JButton cancelButton = new JButton("Cancel");
    private JPanel recordPanel = new JPanel();
    private JComboBox<Category> categoryList;
    private boolean isRefill = false;
    private boolean isAddAction = false;
    private boolean isDeleteAction = false;

    private LogicSystem logicSystem;

    public RecordDialog(LogicSystem logicSystem, boolean isAddAction, boolean isDeleteAction){
        super();
        setTitle("Add/delete record");
        this.logicSystem = logicSystem;
        this.isAddAction = isAddAction;
        this.isDeleteAction = isDeleteAction;

        categoryList = new JComboBox<>();

        if (!isAddAction){
            refillRadioButton.setEnabled(false);
            withdrawRadioButton.setEnabled(false);
            amountField.setEnabled(false);
            recordDescripton.setEnabled(false);
            categoryList.setEnabled(false);
            addButton.setEnabled(false);
        }
        if (!isDeleteAction){
            confirmCheckBox.setEnabled(false);
            deleteButton.setEnabled(false);
        }

        descPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        descPane.setAlignmentX(LEFT_ALIGNMENT);
        recordDescripton.setLineWrap(true);

        ButtonGroup refillWithdrawGroup = new ButtonGroup();
        refillWithdrawGroup.add(refillRadioButton);
        refillWithdrawGroup.add(withdrawRadioButton);
        refillRadioButton.setSelected(true);

        refillRadioButton.setActionCommand(CMD_REFILL_RB);
        refillRadioButton.addActionListener(this);
        withdrawRadioButton.setActionCommand(CMD_WITHDRAW_RB);
        withdrawRadioButton.addActionListener(this);
        addButton.setActionCommand(CMD_ADD);
        addButton.addActionListener(this);
        deleteButton.setActionCommand(CMD_DELETE);
        deleteButton.addActionListener(this);
        cancelButton.setActionCommand(CMD_CANCEL);
        cancelButton.addActionListener(this);

        recordPanel.setLayout(new GridBagLayout());

        recordPanel.add(refillRadioButton, new GridBagConstraints(0, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.CENTER,
                new Insets(2, 2, 2, 2), 0, 0));
        recordPanel.add(withdrawRadioButton, new GridBagConstraints(1, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.CENTER,
                new Insets(2, 2, 2, 2), 0, 0));
        recordPanel.add(amountLabel, new GridBagConstraints(0, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        recordPanel.add(amountField, new GridBagConstraints(1, 1, 2, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        recordPanel.add(categoryLabel, new GridBagConstraints(0, 2, 1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        recordPanel.add(categoryList, new GridBagConstraints(1, 2, 2, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        recordPanel.add(recordDescLabel, new GridBagConstraints(0, 3, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        recordPanel.add(descPane, new GridBagConstraints(0, 4, 3, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        recordPanel.add(confirmMessageLabel, new GridBagConstraints(0, 5, 2, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        recordPanel.add(confirmCheckBox, new GridBagConstraints(2, 5, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        recordPanel.add(deleteButton, new GridBagConstraints(0, 6, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        recordPanel.add(addButton, new GridBagConstraints(1, 6, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        recordPanel.add(cancelButton, new GridBagConstraints(1, 7, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.CENTER,
                new Insets(2, 2, 2, 2), 0, 0));

        setContentPane(recordPanel);
        pack();
        logger.info("Width:" + recordPanel.getWidth() + ", Height:" + recordPanel.getHeight());
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        switch (cmd){
            case CMD_REFILL_RB:
                isRefill = true;
                break;
            case CMD_WITHDRAW_RB:
                isRefill = false;
                break;
            case CMD_ADD:
                break;
            case CMD_DELETE:
                break;
            case CMD_CANCEL:
                break;
        }

    }
}
