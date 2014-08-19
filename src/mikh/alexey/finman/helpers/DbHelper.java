package mikh.alexey.finman.helpers;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 */

public class DbHelper {

    static Logger logger = LoggerFactory.getLogger(DbHelper.class);

    private Connection conn;
    private static DbHelper instance;
    private String driver = "org.sqlite.JDBC";
    private String url = "jdbc:sqlite:finman.db";
    private String login = "";
    private String password = "";

    public static DbHelper getInstance() {
        if (instance == null) {
            instance = new DbHelper();
        }
        return instance;
    }

    private DbHelper() {
        try {
            Class.forName(driver);
            if (login.equals("")) {
                conn = DriverManager.getConnection(url);
            } else {
                conn = DriverManager.getConnection(url, login, password);
            }
            logger.info("Connection OK!");

            if (!isTablesExist()) {
                Statement stmt = conn.createStatement();
                String createSql = readResource(DbHelper.class, "create_tables.sql");
                stmt.executeUpdate(createSql);
                stmt.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public Connection getConn() {
        return conn;
    }

    boolean isTablesExist() throws Exception {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT count(*) FROM sqlite_master WHERE type='table' AND name='USERS';");
        boolean result = true;
        int count = rs.getInt(1);
        if (count == 0) {
            result = false;
        }
        rs.close();
        stmt.close();
        return result;
    }

    String readResource(Class cpHolder, String path) throws Exception {
        java.net.URL url = cpHolder.getResource(path);
        java.nio.file.Path resPath = java.nio.file.Paths.get(url.toURI());
        return new String(java.nio.file.Files.readAllBytes(resPath), "UTF8");
    }

    public static void closeResource(AutoCloseable res) {
        try {
            if (res != null) {
                res.close();
            }
        } catch (Exception e) {
            logger.warn("Failed to close resource: {}", res);
        }
    }
}
