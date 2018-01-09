package com.unicorn.server.model;

import java.sql.Timestamp;

/**
 * @author Create by xuantang
 * @date on 1/9/18
 */
public class ProductTransfer {
    private int id;
    private int num;
    /**
     * product name
     */
    private String name;
    /**
     * product id
     */
    private int p_id;
    private int type;
    private int state;
    private Timestamp time;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public int getP_id() {
        return p_id;
    }

    public void setP_id(int p_id) {
        this.p_id = p_id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
