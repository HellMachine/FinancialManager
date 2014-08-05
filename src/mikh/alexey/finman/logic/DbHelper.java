package mikh.alexey.finman.logic;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author lxmikh@gmail.com
 */

public class DbHelper {

    private static Connection con;
    //TODO перенести поля настройки в отдельный фаил Settings.properties?
    private String driver = "org.sqlite.JDBC";
    private String url = "jdbc:sqlite:finman.db";
    //На случай, если для подключения к базе нужен логин:пасс
    private String login = "";
    private String password = "";

    private String tableUsersSQL = "CREATE TABLE Users (LOGIN TEXT PRIMARY KEY NOT NULL, PASSWORD TEXT NOT NULL)";
    private String tableAccountsSQL = "CREATE TABLE Accounts";
    private String tableRecordsSQL = "CREATE TABLE Records";
    private String tableCategorySQL = "CREATE TABLE Categories";

    public DbHelper() {
        try{
            Class.forName(driver);
            if(login.equals("")){
                con = DriverManager.getConnection(url);
            }else{
                con = DriverManager.getConnection(url, login, password);
            }
            //TODO дописать, можно как-то по другому, а так придётся на каждый executeUpdate ловить искл-е
/*            try{
                Statement st = con.createStatement();
                st.executeUpdate(tableUsersSQL);
                st.executeUpdate(tableAccountsSQL);
                st.executeUpdate(tableRecordsSQL);
                st.executeUpdate(tableCategorySQL);
            }catch (Exception e){
                e.printStackTrace();
            }*/
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            System.exit(0);
        }
    }

    public Connection getConnection(){
        return con;
    }
}
