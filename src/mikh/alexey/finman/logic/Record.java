package mikh.alexey.finman.logic;

/**
 * @author lxmikh@gmail.com
 */

public class Record {

    private double operationAmount;
    private boolean isAddOperation;
    private String operationDesc;
    private Category operationCat;
    private long operationDate;

    public void setOperationAmount(double operationAmount) {
        this.operationAmount = operationAmount;
    }

    public double getOperationAmount() {
        return operationAmount;
    }

    public void setAddOperation(boolean addOperation) {
        isAddOperation = addOperation;
    }

    public boolean isAddOperation() {
        return isAddOperation;
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

    public void setOperationDate(long operationDate) {
        this.operationDate = operationDate;
    }

    public long getOperationDate() {
        return operationDate;
    }

}
