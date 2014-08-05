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
    //TODO перенести поля настройки в отдельный фаил Settings.properties
    private String driver = "org.sqlite.JDBC";
    private String url = "jdbc:sqlite:finman.db";
    //На случай, если для подключения к базе нужен логин:пасс
    private String login = "";
    private String password = "";

    private String tableUsersSQL = "CREATE TABLE Users ()";
    private String tableAccountsSQL = "CREATE TABLE Accounts";
    private String tableRecordsSQL = "CREATE TABLE Records";
    private String tableCategorySQL = "CREATEA TABLE Categories";

    public Connection getConnection(){
        try{
            Class.forName(driver);
            if(login.equals("")){
                con = DriverManager.getConnection(url);
            }else{
                con = DriverManager.getConnection(url, login, password);
            }
            //TODO дописать
            Statement st = con.createStatement();
            st.executeUpdate(tableUsersSQL);


        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        return con;
    }
}
