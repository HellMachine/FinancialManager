package mikh.alexey.finman;

import mikh.alexey.finman.helpers.JFrameHelper;
import mikh.alexey.finman.swing.LoginUI;
import javax.swing.*;
import java.sql.SQLException;

/**
 * @author lxmikh@gmail.com
 */

public class Start {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowLoginUI();
            }
        });
    }

    private static void createAndShowLoginUI() {
        JFrame loginFrame = new LoginUI();
        JFrameHelper.getInstance().centerFrame(loginFrame);
        loginFrame.setVisible(true);
    }


}
