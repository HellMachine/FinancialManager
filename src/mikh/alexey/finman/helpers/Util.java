package mikh.alexey.finman.helpers;

import mikh.alexey.finman.logic.LogicSystem;
import mikh.alexey.finman.swing.LoginUI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author lxmikh@gmail.com
 */

public class Util {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private LogicSystem logicSystem;

    public static Util instance = new Util();

    private Util(){
    }

    public static Util getInstance(){
        return instance;
    }

    public void reLogin(JFrame frame){
        frame.setVisible(false);
        JFrame loginFrame = new LoginUI(logicSystem);
        Util.getInstance().centerFrame(loginFrame);
        loginFrame.setVisible(true);
    }

    public void centerFrame(Window frame) {
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

    public String getHash(String str) {

        MessageDigest md5;
        StringBuffer hexString = new StringBuffer();

        try {
            md5 = MessageDigest.getInstance("md5");
            md5.reset();
            md5.update(str.getBytes());

            byte messageDigest[] = md5.digest();

            for (int i = 0; i < messageDigest.length; i++) {
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
            }
        } catch (NoSuchAlgorithmException e) {
            return e.toString();
        }

        return hexString.toString();
    }

    public File[] getAvatarFilesList(String path){

        File[] filesInAvatarFolder;
        File dir = new File(path);
        filesInAvatarFolder = dir.listFiles();

        return filesInAvatarFolder;
    }
}
