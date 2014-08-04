package mikh.alexey.finman.logic;

import java.util.Date;

/**
 * @author lxmikh@gmail.com
 */

public class Record {

    private String operationDesc;
    enum Operation {REFILL, WITHDRAW}
    private Date operationDate;
    private double operationSum;
    private int uidRec;
}
