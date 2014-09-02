package mikh.alexey.finman.helpers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;

/**
 * @author lxmikh@gmail.com
 */

public class JFrameHelper {

    private Logger logger = LoggerFactory.getLogger(getClass());

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

    public ImageIcon createIcon(Class<?> recallClass, String path) {

        java.net.URL imgURL = recallClass.getResource(path);

        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            logger.error("Couldn't find file: " + path);
            return null;
        }
    }

}
