package mikh.alexey.finman.helpers;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lxmikh@gmail.com
 */

public class DbHelperOld {

    static Logger logger = LoggerFactory.getLogger(DbHelperOld.class);

    private static String[] create_tables_sql = {
            "CREATE TABLE Users "
                    + "(LOGIN TEXT PRIMARY KEY NOT NULL, "
                    + "PASSWORD TEXT NOT NULL)",
            "CREATE TABLE Accounts "
                    + "(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                    + "USER_LOGIN TEXT NOT NULL, "
                    + "BALANCE REAL NOT NULL,"
                    + "DESCRIPTION TEXT NOT NULL)",
            "CREATE TABLE Records "
                    + "(ACCOUNT_ID INT NOT NULL, "
                    + "OPERATION_AMOUNT REAL NOT NULL, "
                    + "OPERATION_TYPE TEXT NOT NULL, "
                    + "DESCRIPTION TEXT NOT NULL, "
                    + "CATEGORY_ID INT NOT NULL, "
                    + "DATE TEXT NOT NULL)",
            "CREATE TABLE Categories "
                    + "(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                    + "CATEGORY_NAME TEXT NOT NULL)"};

    private static Connection con;

    //TODO перенести поля настройки в отдельный фаил Settings.properties?
    private String driver = "org.sqlite.JDBC";
    private String url = "jdbc:sqlite:finman.db";
    private String login = "";
    private String password = "";

    public static DbHelperOld instance = new DbHelperOld();

    private DbHelperOld() {
        try {
            Class.forName(driver);
            if (login.equals("")) {
                con = DriverManager.getConnection(url);
            } else {
                con = DriverManager.getConnection(url, login, password);
            }
            logger.info("Connection OK!");
            Statement st = con.createStatement();
            for (int i = 0; i < create_tables_sql.length; i++) {
                try {
                    st.executeUpdate(create_tables_sql[i]);
                    logger.info("Table {} create!", i);
                } catch (SQLException e) {
                    logger.error("SQLException when create table! Table already exist.");
                }
            }
            st.close();
        } catch (ClassNotFoundException e) {
            logger.error("ClassNotFoundException when load DB driver!");
            e.printStackTrace();
            System.exit(0);
        } catch (SQLException e) {
            logger.error("SQLException when create connection!");
            e.printStackTrace();
            System.exit(0);
        }
    }

    public static DbHelperOld getInstance() {
        return instance;
    }

    public Connection getConnection() {
        return con;
    }
}
