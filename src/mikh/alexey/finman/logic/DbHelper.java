package mikh.alexey.finman.logic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author lxmikh@gmail.com
 */

public class DbHelper {

    private static Connection con;
    //TODO перенести поля настройки в отдельный фаил Settings.properties
    private String driver = "org.sqlite.JDBC";
    private String url = "jdbc:sqlite:finman.db";
    //На случай, если для подключения к базе нужен логин:пасс
    private String login = "";
    private String password = "";

    public Connection getConnection(){
        try{
            Class.forName(driver);
            if(login.equals("")){
                con = DriverManager.getConnection(url);
            }else{
                con = DriverManager.getConnection(url, login, password);
            }
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return con;
    }
}
