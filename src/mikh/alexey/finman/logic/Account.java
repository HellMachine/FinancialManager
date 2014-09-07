package mikh.alexey.finman.logic;

import java.util.Set;

/**
 * @author lxmikh@gmail.com
 */

public class Account {

    private int idAcc;
    private String nameAcc;
    private String ownerAcc;
    private double curBalance;
    private Set<Record> transList;
    private String accountDesc;

    public Account(){
    }

    public int getIdAcc() {
        return idAcc;
    }

    public void setIdAcc(int idAcc) {
        this.idAcc = idAcc;
    }

    public String getNameAcc() {
        return nameAcc;
    }

    public void setNameAcc(String nameAcc) {
        this.nameAcc = nameAcc;
    }

    public String getOwnerAcc() {
        return ownerAcc;
    }

    public void setOwnerAcc(String ownerAcc) {
        this.ownerAcc = ownerAcc;
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
