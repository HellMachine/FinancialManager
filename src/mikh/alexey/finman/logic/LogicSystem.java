package mikh.alexey.finman.logic;

import mikh.alexey.finman.helpers.DbHelper;

/**
 * @author lxmikh@gmail.com
 */

public class LogicSystem {

    public static DbHelper dbHelper = DbHelper.getInstance();
    private DataStore dataStore;

    public static String[] avatarFilesList = new String[]{
            "angry.png", "clown.png", "emo.png", "ninja.png", "santa.png", "sweating.png"};

    public LogicSystem(){
        dataStore = new DbDataStore();
    }
}
