package com.unicorn.finance.model;

import java.sql.Timestamp;

/**
 * @author Create by xuantang
 * @date on 12/31/17
 */
public class Bill {
    private String fromAccount;
    private String toAccount;
    private float sum;

    public float getSum() {
        return sum;
    }

    public void setSum(float sum) {
        this.sum = sum;
    }


    public String getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }

    public String getToAccount() {
        return toAccount;
    }

    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
    }


}
