package mikh.alexey.finman;

import mikh.alexey.finman.logic.DbHelper;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author lxmikh@gmail.com
 */

public class Start {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        final DbHelper dbh = new DbHelper();
        final Connection con = dbh.getConnection();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

            }
        });
    }


}
