package mikh.alexey.finman.helpers;

import java.awt.*;

/**
 * @author lxmikh@gmail.com
 */

public class JFrameHelper {

    public static JFrameHelper instance = new JFrameHelper();

    private JFrameHelper(){
    }

    public static JFrameHelper getInstance(){
        return instance;
    }

    public static void centerFrame(Window frame) {
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((screenSize.getWidth() - frame.getWidth()) / 2);
        if (x < 0) {
            x = 0;
        }
        int y = (int) ((screenSize.getHeight() - frame.getHeight()) / 2);
        if (y < 0) {
            y = 0;
        }
        frame.setBounds(x, y, frame.getWidth(), frame.getHeight());
    }

}
