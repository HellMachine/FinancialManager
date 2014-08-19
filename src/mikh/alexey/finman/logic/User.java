package mikh.alexey.finman.logic;

/**
 * @author lxmikh@gmail.com
 */

public class User {

    private String login;
    private String password;
    private String avatarFileName;

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setAvatarFileName(String avatarFileName) {
        this.avatarFileName = avatarFileName;
    }

    public String getAvatarFileName() {
        return avatarFileName;
    }


}
