package com.example.schoolshop.entity;

import java.io.Serializable;

public class Goods implements Serializable {

    /**
     * goodsImg : 1
     * goodsUser : 123
     * goodsKind : 文具
     * goodsState : 1
     * goodsPrice : 5
     * ID : 6
     * goodsName : 钢笔
     */

    private String goodsImg;
    private String goodsUser;
    private String goodsKind;
    private int goodsState;
    private int goodsPrice;
    private int ID;
    private String goodsName;

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }

    public String getGoodsUser() {
        return goodsUser;
    }

    public void setGoodsUser(String goodsUser) {
        this.goodsUser = goodsUser;
    }

    public String getGoodsKind() {
        return goodsKind;
    }

    public void setGoodsKind(String goodsKind) {
        this.goodsKind = goodsKind;
    }

    public int getGoodsState() {
        return goodsState;
    }

    public void setGoodsState(int goodsState) {
        this.goodsState = goodsState;
    }

    public int getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(int goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }
}
