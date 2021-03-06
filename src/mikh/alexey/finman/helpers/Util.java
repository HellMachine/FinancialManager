package mikh.alexey.finman.helpers;

import mikh.alexey.finman.Start;
import mikh.alexey.finman.logic.LogicSystem;
import mikh.alexey.finman.swing.LoginUI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author lxmikh@gmail.com
 */

public class Util {

    private static Logger logger = LoggerFactory.getLogger(Util.class);
    private static String[] listFiles;

    public static void reLogin(JFrame frame) {
        frame.setVisible(false);
        LogicSystem logicSystem = new LogicSystem();
        JFrame loginFrame = new LoginUI(logicSystem);
        centerFrame(loginFrame);
        loginFrame.setVisible(true);
        logger.info("Reloging...");
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

    public static ImageIcon createIcon(Class<?> recallClass, String path) {

        java.net.URL imgURL = recallClass.getResource(path);

        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            logger.error("Couldn't find file: " + path);
            return null;
        }
    }

    public static String getHash(String str) {

        MessageDigest md5;
        StringBuffer hexString = new StringBuffer();

        try {
            md5 = MessageDigest.getInstance("md5");
            md5.reset();
            md5.update(str.getBytes());

            byte messageDigest[] = md5.digest();

            for (byte aMessageDigest : messageDigest) {
                hexString.append(Integer.toHexString(0xFF & aMessageDigest));
            }
        } catch (NoSuchAlgorithmException e) {
            return e.toString();
        }

        return hexString.toString();
    }

    public static String[] fillImageFileList() {

        try {
            URL url = Start.class.getResource("swing/img/imgAvatar");
            java.nio.file.Path resPath = java.nio.file.Paths.get(url.toURI());
            File dir = resPath.toFile();
            File[] filesInDir = dir.listFiles();
            listFiles = new String[filesInDir.length];
            for (int i = 0; i < filesInDir.length; i++) {
                listFiles[i] = filesInDir[i].getName();
            }
        } catch (Exception x) {
            x.printStackTrace();
        }
        return listFiles;
    }
}
