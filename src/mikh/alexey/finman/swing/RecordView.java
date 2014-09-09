package mikh.alexey.finman.swing;

import mikh.alexey.finman.helpers.Util;
import mikh.alexey.finman.logic.Record;

import javax.swing.*;
import java.awt.*;

/**
 * @author lxmikh@gmail.com
 */
public class RecordView implements ListCellRenderer<Record> {

    JLabel plusImageLabel = new JLabel(Util.getInstance().createIcon(getClass(), "img/plus.png"));
    JLabel minusImageLabel = new JLabel(Util.getInstance().createIcon(getClass(), "img/minus.png"));

    @Override
    public Component getListCellRendererComponent(JList<? extends Record> list,
                                                  Record value, int index, boolean isSelected, boolean cellHasFocus) {

        JPanel recordViewPanel = new JPanel();
        recordViewPanel.setLayout(new BorderLayout());
        recordViewPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        if (value != null) {
            JLabel transAmount = new JLabel(value.getOperationAmount() + " RUB");
            JLabel transInfo = new JLabel(value.getOperationCat().getCategoryName() + " " + value.getOperationDate());
            JLabel transDesc = new JLabel(value.getOperationDesc());
            JLabel transImage = (value.isAddOperation() ? plusImageLabel : minusImageLabel);
            recordViewPanel.add(transAmount, BorderLayout.NORTH);
            recordViewPanel.add(transInfo, BorderLayout.CENTER);
            recordViewPanel.add(transDesc, BorderLayout.SOUTH);
            recordViewPanel.add(transImage, BorderLayout.EAST);
        }
        return recordViewPanel;
    }
}
