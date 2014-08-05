package mikh.alexey.finman.logic;

import java.util.Set;

/**
 * @author lxmikh@gmail.com
 */

public class Account {

    private int idAcc;
    private double curBalance;
    private Set<Record> transList;
    private String accountDesc;

    public int getIdAcc() {
        return idAcc;
    }

    public void setIdAcc(int uidAcc) {
        this.idAcc = uidAcc;
    }

    public double getCurBalance() {
        return curBalance;
    }

    public void setCurBalance(double curBalance) {
        this.curBalance = curBalance;
    }

    public Set<Record> getTransList() {
        return transList;
    }

    public void setTransList(Set<Record> transList) {
        this.transList = transList;
    }

    public String getAccountDesc() {
        return accountDesc;
    }

    public void setAccountDesc(String accountDesc) {
        this.accountDesc = accountDesc;
    }

}
