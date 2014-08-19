package mikh.alexey.finman.logic;

import java.util.Date;

/**
 * @author lxmikh@gmail.com
 */

public class Record {

    private double operationAmount;
    enum OperationеType {REFILL, WITHDRAW}
    private String operationDesc;
    private Category operationCat;
    private Date operationDate;

    public void setOperationAmount(double operationAmount) {
        this.operationAmount = operationAmount;
    }

    public double getOperationAmount() {
        return operationAmount;
    }

    public void setOperationDesc(String operationDesc) {
        this.operationDesc = operationDesc;
    }

    public String getOperationDesc() {
        return operationDesc;
    }

    public void setOperationCat(Category operationCat) {
        this.operationCat = operationCat;
    }

    public Category getOperationCat() {
        return operationCat;
    }

    public void setOperationDate(Date operationDate) {
        this.operationDate = operationDate;
    }

    public Date getOperationDate() {
        return operationDate;
    }

}
