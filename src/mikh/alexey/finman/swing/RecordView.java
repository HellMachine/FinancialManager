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
        recordViewPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        if(value != null){
            JLabel transInfo = new JLabel(value.getOperationAmount() + " RUB " + value.getOperationCat().getCategoryName()
                                          + " " + value.getOperationDate());
            JLabel transDesc = new JLabel(value.getOperationDesc());
            JLabel transImage = (value.isAddOperation() ? plusImageLabel : minusImageLabel);
            recordViewPanel.add(transInfo, BorderLayout.NORTH);
            recordViewPanel.add(transDesc, BorderLayout.CENTER);
            recordViewPanel.add(transImage, BorderLayout.WEST);
        }


        return recordViewPanel;
    }
}
