package com.example.schoolshop.entity;

public class Goodskind {

    /**
     * goodskind : 文具
     * id : 1
     */

    private String goodskind;
    private String id;

    public String getGoodskind() {
        return goodskind;
    }

    public void setGoodskind(String goodskind) {
        this.goodskind = goodskind;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Goodskind{" +
                "goodskind='" + goodskind + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    public void setId(String id) {
        this.id = id;
    }
}
