package com.yunjing.newbeehome.model.entity;

import java.util.List;

/**
 * 作者：zhiyuan Xue on 2018/10/19 16:42
 * 邮箱：xzy7319@sina.com
 */

public class PushShopInfoBean {


    /**
     * meta : {"success":true,"message":"ok"}
     * data : [{"orderProductCellMachineMapId":7,"cellMachineProductTargetMapId":6,"deliverState":null,"productId":1,"productFullName":"商品全称1","cargoRoadName":"R6C1","machineId":1,"xAxis":10,"yAxis":100,"zAxis":900,"deliverySpeed":1000,"maxQuantity":20,"currentQuantity":20}]
     */

    private MetaBean meta;
    private List<DataBean> data;

    public MetaBean getMeta() {
        return meta;
    }

    public void setMeta(MetaBean meta) {
        this.meta = meta;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class MetaBean {
        /**
         * success : true
         * message : ok
         */

        private boolean success;
        private String message;

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    public static class DataBean {
        /**
         * orderProductCellMachineMapId : 7
         * cellMachineProductTargetMapId : 6
         * deliverState : null
         * productId : 1
         * productFullName : 商品全称1
         * cargoRoadName : R6C1
         * machineId : 1
         * xAxis : 10
         * yAxis : 100
         * zAxis : 900
         * deliverySpeed : 1000
         * maxQuantity : 20
         * currentQuantity : 20
         */

        private int orderProductCellMachineMapId;
        private int cellMachineProductTargetMapId;
        private Object deliverState;
        private int productId;
        private String productFullName;
        private String cargoRoadName;
        private int machineId;
        private int xAxis;
        private int yAxis;
        private int zAxis;
        private int deliverySpeed;
        private int maxQuantity;
        private int currentQuantity;

        public int getOrderProductCellMachineMapId() {
            return orderProductCellMachineMapId;
        }

        public void setOrderProductCellMachineMapId(int orderProductCellMachineMapId) {
            this.orderProductCellMachineMapId = orderProductCellMachineMapId;
        }

        public int getCellMachineProductTargetMapId() {
            return cellMachineProductTargetMapId;
        }

        public void setCellMachineProductTargetMapId(int cellMachineProductTargetMapId) {
            this.cellMachineProductTargetMapId = cellMachineProductTargetMapId;
        }

        public Object getDeliverState() {
            return deliverState;
        }

        public void setDeliverState(Object deliverState) {
            this.deliverState = deliverState;
        }

        public int getProductId() {
            return productId;
        }

        public void setProductId(int productId) {
            this.productId = productId;
        }

        public String getProductFullName() {
            return productFullName;
        }

        public void setProductFullName(String productFullName) {
            this.productFullName = productFullName;
        }

        public String getCargoRoadName() {
            return cargoRoadName;
        }

        public void setCargoRoadName(String cargoRoadName) {
            this.cargoRoadName = cargoRoadName;
        }

        public int getMachineId() {
            return machineId;
        }

        public void setMachineId(int machineId) {
            this.machineId = machineId;
        }

        public int getXAxis() {
            return xAxis;
        }

        public void setXAxis(int xAxis) {
            this.xAxis = xAxis;
        }

        public int getYAxis() {
            return yAxis;
        }

        public void setYAxis(int yAxis) {
            this.yAxis = yAxis;
        }

        public int getZAxis() {
            return zAxis;
        }

        public void setZAxis(int zAxis) {
            this.zAxis = zAxis;
        }

        public int getDeliverySpeed() {
            return deliverySpeed;
        }

        public void setDeliverySpeed(int deliverySpeed) {
            this.deliverySpeed = deliverySpeed;
        }

        public int getMaxQuantity() {
            return maxQuantity;
        }

        public void setMaxQuantity(int maxQuantity) {
            this.maxQuantity = maxQuantity;
        }

        public int getCurrentQuantity() {
            return currentQuantity;
        }

        public void setCurrentQuantity(int currentQuantity) {
            this.currentQuantity = currentQuantity;
        }
    }
}
