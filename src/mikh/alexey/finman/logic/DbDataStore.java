package mikh.alexey.finman.logic;

import mikh.alexey.finman.helpers.DbHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class DbDataStore implements DataStore {

    private static Logger logger = LoggerFactory.getLogger(DbDataStore.class);

    DbHelper dbHelper = DbHelper.getInstance();

    public Set<Category> getCategories() {
        Set<Category> categories = new HashSet<>();

        // Close it manually
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = dbHelper.getConn().createStatement();
            rs = stmt.executeQuery("SELECT * FROM CATEGORIES;");
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                Category cat = new Category();
                cat.setCategoryId(id);
                cat.setCategoryName(name);
                categories.add(cat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbHelper.closeResource(rs);
            DbHelper.closeResource(stmt);
        }
        logger.debug("categories: {}", categories);
        return categories;
    }

    @Override
    public User getUser(String name) {
        User user = null;
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            pStmt = dbHelper.getConn().prepareStatement("SELECT * FROM USERS WHERE LOGIN=?");
            pStmt.setString(1, name);
            rs = pStmt.executeQuery();
            while (rs.next()) {
                String userName = rs.getString(1);
                String pass = rs.getString(2);
                String avatar = rs.getString(3);
                user = new User(userName, pass, avatar);
                user.setLogin(userName);
                user.setPassword(pass);
                user.setAvatarFileName(avatar);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbHelper.closeResource(rs);
            DbHelper.closeResource(pStmt);
        }

        logger.debug("getUser(): " + user);
        return user;
    }

    @Override
    public Set<String> getUserNames() {
        Set<String> names = new HashSet<>();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = dbHelper.getConn().createStatement();
            rs = stmt.executeQuery("SELECT LOGIN FROM USERS;");
            while (rs.next()) {
                String name = rs.getString(1);
                names.add(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbHelper.closeResource(rs);
            DbHelper.closeResource(stmt);
        }
        logger.debug("Users: {}", names);
        return names;
    }

    @Override
    public Set<Account> getAccounts(User owner) {
        Set<Account> accounts = new HashSet<>();
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            pStmt = dbHelper.getConn().prepareStatement("SELECT * FROM ACCOUNTS WHERE ACCOUNT_OWNER=?");
            pStmt.setString(1, owner.getLogin());
            rs = pStmt.executeQuery();
            while (rs.next()) {
                Account acc = new Account();
                acc.setIdAcc(rs.getInt(rs.findColumn("ACCOUNT_ID")));
                acc.setNameAcc(rs.getString(rs.findColumn("ACCOUNT_NAME")));
                acc.setCurBalance(rs.getDouble(rs.findColumn("BALANCE")));
                acc.setAccountDesc(rs.getString(rs.findColumn("DESCRIPTION")));
                accounts.add(acc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbHelper.closeResource(rs);
            DbHelper.closeResource(pStmt);
        }
        logger.debug("User: {} -> {}", owner, accounts);
        return accounts;
    }

    @Override
    public Set<Record> getRecords(Account account) {
        Set<Record> records = new HashSet<>();

        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            pStmt = dbHelper.getConn().prepareStatement("SELECT * FROM RECORDS WHERE ACCOUNT_ID=? ");
            pStmt.setInt(1, account.getIdAcc());
            rs = pStmt.executeQuery();
            while (rs.next()) {
                Record record = new Record();
                record.setOperationAmount(rs.getDouble(rs.findColumn("OPERATION_AMOUNT")));
                int isAdditionType = rs.getInt(rs.findColumn("IS_ADD_TYPE"));
                record.setAddOperation(isAdditionType == 1);
                record.setOperationDesc(rs.getString(rs.findColumn("DESCRIPTION")));
                int categoryId = (rs.getInt(rs.findColumn("CATEGORY_ID")));
                record.setOperationCat(getCategoryById(categoryId));
                Date recordDate = (rs.getDate(rs.findColumn("OPERATION_DATE")));
                record.setOperationDate(recordDate);
                records.add(record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbHelper.closeResource(rs);
            DbHelper.closeResource(pStmt);
        }
        logger.debug("Account: {} -> {}", account, records);
        return records;
    }

    private Category getCategoryById(int id) {
        Category category = null;
        logger.debug("Get category {}", id);
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            pStmt = dbHelper.getConn().prepareStatement("SELECT * FROM CATEGORIES WHERE ID=?");
            pStmt.setInt(1, id);
            rs = pStmt.executeQuery();
            if (rs.next()) {
                category = new Category();
                String name = rs.getString(rs.findColumn("CATEGORY_NAME"));
                category.setCategoryName(name);
                category.setCategoryId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbHelper.closeResource(rs);
            DbHelper.closeResource(pStmt);
        }
        return category;
    }

    @Override
    public void addUser(User user) {
        logger.debug("Adding new user: {}", user);
        PreparedStatement pStmt = null;
        try {
            pStmt = dbHelper.getConn().prepareStatement(
                    "INSERT INTO USERS (LOGIN, PASSWORD, USER_AVATAR) " +
                            "VALUES (?, ?, ?);");
            pStmt.setString(1, user.getLogin());
            pStmt.setString(2, user.getPassword());
            pStmt.setString(3, user.getAvatarFileName());
            int result = pStmt.executeUpdate();
            logger.info("Add user " + user.getLogin() + ": " + result);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbHelper.closeResource(pStmt);
        }

    }

    @Override
    public void addAccount(User user, Account account) {
        logger.debug("Adding account {} -> {}", user, account);
        PreparedStatement pStmt = null;
        try {
            pStmt = dbHelper.getConn().prepareStatement(
                    "INSERT INTO ACCOUNTS (ACCOUNT_NAME, ACCOUNT_OWNER, BALANCE, DESCRIPTION) " +
                            "VALUES (?, ?, ?, ?);");
            pStmt.setString(1, account.getNameAcc());
            pStmt.setString(2, user.getLogin());
            pStmt.setDouble(3, account.getCurBalance());
            pStmt.setString(4, account.getAccountDesc());
            int result = pStmt.executeUpdate();
            logger.info("Add account " + account.getNameAcc() + ": " + result);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbHelper.closeResource(pStmt);
        }
    }

    @Override
    public void addRecord(Account account, Record record) {
        logger.debug("Adding record {}->{}", account, record);
        PreparedStatement pStmt = null;
        try {
            pStmt = dbHelper.getConn().prepareStatement(
                    "INSERT INTO RECORDS (ACCOUNT_ID, OPERATION_AMOUNT, IS_ADD_TYPE, DESCRIPTION, CATEGORY_ID, OPERATION_DATE) " +
                            "VALUES (?, ?, ?, ?, ?, ?);");
            pStmt.setInt(1, account.getIdAcc());
            pStmt.setDouble(2, record.getOperationAmount());
            pStmt.setInt(3, record.isAddOperation() ? 1 : 0);
            pStmt.setString(4, record.getOperationDesc());
            pStmt.setInt(5, record.getOperationCat().getCategoryId());
            pStmt.setDate(6, new java.sql.Date(System.currentTimeMillis()));
            int result = pStmt.executeUpdate();
            logger.info("Add record " + account + ": " + result);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbHelper.closeResource(pStmt);
        }
    }

    @Override
    public User removeUser(String name) {
        return null;
    }

    @Override
    public Account removeAccount(User owner, Account account) {
        return null;
    }

    @Override
    public Record removeRecord(Account from, Record record) {
        return null;
    }
}
