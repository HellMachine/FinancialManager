package mikh.alexey.finman.swing;

import mikh.alexey.finman.logic.Category;
import mikh.alexey.finman.logic.LogicSystem;
import mikh.alexey.finman.logic.Record;
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

    private static final String CMD_ADD = "cmdAddRecord";
    private static final String CMD_DELETE = "cmdDeleteRecord";
    private static final String CMD_CANCEL = "cmdCancelAction";

    private JTextField amountField = new JTextField(10);
    private JTextArea recordDescription = new JTextArea(5, 5);
    private JComboBox<Category> categoryList;
    private boolean isRefill = true;

    private LogicSystem logicSystem;

    public RecordDialog(LogicSystem logicSystem, boolean isAddAction, boolean isDeleteAction) {
        super();
        setTitle("Add/delete record");
        this.logicSystem = logicSystem;
        boolean addAction = isAddAction;
        boolean deleteAction = isDeleteAction;

        categoryList = new JComboBox<>();

        JRadioButton refillRadioButton = new JRadioButton("Plus");
        JRadioButton withdrawRadioButton = new JRadioButton("Minus");

        JButton addButton = new JButton("Add");
        JButton deleteButton = new JButton("Delete");
        JButton cancelButton = new JButton("Cancel");

        if (!isAddAction) {
            refillRadioButton.setEnabled(false);
            withdrawRadioButton.setEnabled(false);
            amountField.setEnabled(false);
            recordDescription.setEnabled(false);
            categoryList.setEnabled(false);
            addButton.setEnabled(false);
        }
        JCheckBox confirmCheckBox = new JCheckBox();

        if (!isDeleteAction) {
            confirmCheckBox.setEnabled(false);
            deleteButton.setEnabled(false);
        }

        DefaultComboBoxModel<Category> modelComboBox = new DefaultComboBoxModel<>();
        for (Category categoryName : logicSystem.getCategories()) {
            modelComboBox.addElement(categoryName);
        }
        categoryList = new JComboBox<>(modelComboBox);
        categoryList.setSelectedIndex(0);

        categoryList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logger.info("Category chosen: {}", categoryList.getSelectedItem());
            }
        });

        JScrollPane descPane = new JScrollPane(recordDescription);
        descPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        descPane.setAlignmentX(LEFT_ALIGNMENT);
        recordDescription.setLineWrap(true);

        ButtonGroup refillWithdrawGroup = new ButtonGroup();
        refillWithdrawGroup.add(refillRadioButton);
        refillWithdrawGroup.add(withdrawRadioButton);
        refillRadioButton.setSelected(true);

        refillRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isRefill = true;
            }
        });
        withdrawRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isRefill = false;
            }
        });
        addButton.setActionCommand(CMD_ADD);
        addButton.addActionListener(this);
        deleteButton.setActionCommand(CMD_DELETE);
        deleteButton.addActionListener(this);
        cancelButton.setActionCommand(CMD_CANCEL);
        cancelButton.addActionListener(this);

        JPanel recordPanel = new JPanel();
        recordPanel.setLayout(new GridBagLayout());

        recordPanel.add(refillRadioButton, new GridBagConstraints(0, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.CENTER,
                new Insets(2, 2, 2, 2), 0, 0));
        recordPanel.add(withdrawRadioButton, new GridBagConstraints(1, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.CENTER,
                new Insets(2, 2, 2, 2), 0, 0));
        JLabel amountLabel = new JLabel("Amount:");
        recordPanel.add(amountLabel, new GridBagConstraints(0, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        recordPanel.add(amountField, new GridBagConstraints(1, 1, 2, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        JLabel categoryLabel = new JLabel("Category:");
        recordPanel.add(categoryLabel, new GridBagConstraints(0, 2, 1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        recordPanel.add(categoryList, new GridBagConstraints(1, 2, 2, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        JLabel recordDescLabel = new JLabel("Description:");
        recordPanel.add(recordDescLabel, new GridBagConstraints(0, 3, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        recordPanel.add(descPane, new GridBagConstraints(0, 4, 3, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        JLabel confirmMessageLabel = new JLabel("I agree to the deletion of a record.");
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
        switch (cmd) {
            case CMD_ADD:
                boolean isAddOperation = isRefill;
                //FIXME возможная ошибка на ввод данных в поле amountField!
                Double curAccountBalance = logicSystem.getCurrentAccount().getCurBalance();
                Double amountOperation = new Double(amountField.getText());
                Category categorySelect = (Category) categoryList.getSelectedItem();
                String descOperation = recordDescription.getText();
                long operationDate = System.currentTimeMillis();

                if (isAddOperation){
                    curAccountBalance = curAccountBalance + amountOperation;
                } else {
                    curAccountBalance = curAccountBalance - amountOperation;
                }

                Record newRecord = new Record();
                newRecord.setAddOperation(isAddOperation);
                newRecord.setOperationAmount(amountOperation);
                newRecord.setOperationCat(categorySelect);
                newRecord.setOperationDesc(descOperation);
                newRecord.setOperationDate(operationDate);

                logicSystem.addRecord(logicSystem.getCurrentAccount(), newRecord);

                dispose();
                break;
            case CMD_DELETE:
                break;
            case CMD_CANCEL:
                dispose();
                break;
        }

    }
}
