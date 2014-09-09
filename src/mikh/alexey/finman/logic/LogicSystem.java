package mikh.alexey.finman.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

/**
 * @author lxmikh@gmail.com
 */

public class LogicSystem {

    private Logger logger = LoggerFactory.getLogger(LogicSystem.class);

    private DataStore dbData;
    private User currentUser;
    private Account currentAccount;

    public LogicSystem() {
        dbData = new DbDataStore();
    }

    public User login(String userLogin, String pass) {
        if (isUserExist(userLogin)) {
            User user = dbData.getUser(userLogin);
            if (isPasswordCorrect(user, pass)) {
                logger.info("User: {} login in system.", userLogin);
                setCurrentUser(user);
                return user;
            }
        }
        return null;
    }

    public User createNewUser(String userLogin, String pass, String avatar) {
        if (!isUserExist(userLogin)) {
            User user = new User(userLogin, pass, avatar);
            dbData.addUser(user);
            return user;
        }
        return null;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public boolean isUserExist(String userLogin) {
        return dbData.getUser(userLogin) != null;
    }

    private boolean isPasswordCorrect(User user, String pass) {
        return user.getPassword().equals(pass);
    }

    public void setCurrentAccount(Account currentAccount) {
        this.currentAccount = currentAccount;
    }

    public Account getCurrentAccount() {
        return currentAccount;
    }

    public void addAccount(User userLogin, Account account) {
        dbData.addAccount(userLogin, account);
    }

    public Set<Account> getAccounts(User userLogin) {
        return dbData.getAccounts(userLogin);
    }

    public Set<Record> getRecords(Account account) {
        return dbData.getRecords(account);
    }

    public void addRecord(Account account, Record record) {
        dbData.addRecord(account, record);
    }

    public Set<Category> getCategories() {
        return dbData.getCategories();
    }

}
