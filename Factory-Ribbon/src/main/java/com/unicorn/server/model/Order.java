package com.unicorn.server.model;

import java.sql.Timestamp;

/**
 * @author Create by xuantang
 * @date on 12/14/17
 */
public class Order {
    private int id;
    private int state;
    private int p_id;
    private int num;
    private Timestamp time;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getP_id() {
        return p_id;
    }

    public void setP_id(int p_id) {
        this.p_id = p_id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
