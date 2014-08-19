package mikh.alexey.finman.logic;

import mikh.alexey.finman.helpers.DbHelper;

/**
 * @author lxmikh@gmail.com
 */

public class LogicSystem {

    public static DbHelper dbHelper = DbHelper.getInstance();

    private DataStore dataStore;

    public LogicSystem(){
        dataStore = new DbDataStore();
    }
}
