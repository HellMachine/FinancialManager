package mikh.alexey.finman.logic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author lxmikh@gmail.com
 */

public class DbHelper {

    private String driver = "org.sqlite.JDBC";
    private String url = "jdbc:sqlite:finman.db";
    private Connection con;

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        con = DriverManager.getConnection(url);
        return con;
    }
}
