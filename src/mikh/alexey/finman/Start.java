package mikh.alexey.finman;

import mikh.alexey.finman.logic.LogicSystem;
import mikh.alexey.finman.swing.LoginUI;
import static mikh.alexey.finman.helpers.Util.centerFrame;

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

        LogicSystem logicSystem = new LogicSystem();
        JFrame loginFrame = new LoginUI(logicSystem);
        centerFrame(loginFrame);
        loginFrame.setVisible(true);
    }
}
