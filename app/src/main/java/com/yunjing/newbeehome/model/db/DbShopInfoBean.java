package com.yunjing.newbeehome.model.db;



/**
 * 作者：zhiyuan Xue on 2018/9/17 15:46
 * 邮箱：xzy7319@sina.com
 */

public class DbShopInfoBean {

    //id
    private int id;
    //商品库存
    private  int number;
    //机器Id
    private int machineId;
    //商品Id
    private int shopId;

    public DbShopInfoBean(int id, int number, int machineId, int shopId) {
        this.id = id;
        this.number = number;
        this.machineId = machineId;
        this.shopId = shopId;
    }

    public DbShopInfoBean(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getMachineId() {
        return machineId;
    }

    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    @Override
    public String toString() {
        return "DbShopInfoBean{" +
                "id=" + id +
                ", number=" + number +
                ", machineId=" + machineId +
                ", shopId=" + shopId +
                '}';
    }
}
