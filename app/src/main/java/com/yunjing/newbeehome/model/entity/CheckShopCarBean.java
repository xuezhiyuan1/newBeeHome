package com.yunjing.newbeehome.model.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：zhiyuan Xue on 2018/10/22 10:25
 * 邮箱：xzy7319@sina.com
 */

public class CheckShopCarBean implements Serializable {

    /**
     * meta : {"success":true,"message":"ok"}
     * data : {"countAll":4,"equipmentOperatorId":0,"carts":[{"cartId":3,"machineId":1,"shopId":1,"productId":1,"machineOrderId":null,"customerId":null,"quantity":2,"status":"0","createDate":"2018-10-22T02:08:15.000+0000","rank":null,"remarks":null,"delFlag":"0","productFullName":null,"inventoryNum":91},{"cartId":4,"machineId":1,"shopId":1,"productId":2,"machineOrderId":null,"customerId":null,"quantity":2,"status":"0","createDate":"2018-10-22T02:10:17.000+0000","rank":null,"remarks":null,"delFlag":"0","productFullName":null,"inventoryNum":18}],"productIds":[1,1,2,2],"feeAll":12001,"quantityAll":1001}
     */

    private MetaBean meta;
    private DataBean data;

    public MetaBean getMeta() {
        return meta;
    }

    public void setMeta(MetaBean meta) {
        this.meta = meta;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class MetaBean implements Serializable{
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

    public static class DataBean implements Serializable {
        /**
         * countAll : 4
         * equipmentOperatorId : 0
         * carts : [{"cartId":3,"machineId":1,"shopId":1,"productId":1,"machineOrderId":null,"customerId":null,"quantity":2,"status":"0","createDate":"2018-10-22T02:08:15.000+0000","rank":null,"remarks":null,"delFlag":"0","productFullName":null,"inventoryNum":91},{"cartId":4,"machineId":1,"shopId":1,"productId":2,"machineOrderId":null,"customerId":null,"quantity":2,"status":"0","createDate":"2018-10-22T02:10:17.000+0000","rank":null,"remarks":null,"delFlag":"0","productFullName":null,"inventoryNum":18}]
         * productIds : [1,1,2,2]
         * feeAll : 12001
         * quantityAll : 1001
         */

        private int countAll;
        private int equipmentOperatorId;
        private int feeAll;
        private int quantityAll;
        private List<CartsBean> carts;
        private List<Integer> productIds;

        public int getCountAll() {
            return countAll;
        }

        public void setCountAll(int countAll) {
            this.countAll = countAll;
        }

        public int getEquipmentOperatorId() {
            return equipmentOperatorId;
        }

        public void setEquipmentOperatorId(int equipmentOperatorId) {
            this.equipmentOperatorId = equipmentOperatorId;
        }

        public int getFeeAll() {
            return feeAll;
        }

        public void setFeeAll(int feeAll) {
            this.feeAll = feeAll;
        }

        public int getQuantityAll() {
            return quantityAll;
        }

        public void setQuantityAll(int quantityAll) {
            this.quantityAll = quantityAll;
        }

        public List<CartsBean> getCarts() {
            return carts;
        }

        public void setCarts(List<CartsBean> carts) {
            this.carts = carts;
        }

        public List<Integer> getProductIds() {
            return productIds;
        }

        public void setProductIds(List<Integer> productIds) {
            this.productIds = productIds;
        }

        public static class CartsBean implements Serializable {
            /**
             * cartId : 3
             * machineId : 1
             * shopId : 1
             * productId : 1
             * machineOrderId : null
             * customerId : null
             * quantity : 2
             * status : 0
             * createDate : 2018-10-22T02:08:15.000+0000
             * rank : null
             * remarks : null
             * delFlag : 0
             * productFullName : null
             * inventoryNum : 91
             */

            private int cartId;
            private int machineId;
            private int shopId;
            private int productId;
            private Object machineOrderId;
            private Object customerId;
            private int quantity;
            private String status;
            private String createDate;
            private Object rank;
            private Object remarks;
            private String delFlag;
            private Object productFullName;
            private int inventoryNum;

            public int getCartId() {
                return cartId;
            }

            public void setCartId(int cartId) {
                this.cartId = cartId;
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

            public int getProductId() {
                return productId;
            }

            public void setProductId(int productId) {
                this.productId = productId;
            }

            public Object getMachineOrderId() {
                return machineOrderId;
            }

            public void setMachineOrderId(Object machineOrderId) {
                this.machineOrderId = machineOrderId;
            }

            public Object getCustomerId() {
                return customerId;
            }

            public void setCustomerId(Object customerId) {
                this.customerId = customerId;
            }

            public int getQuantity() {
                return quantity;
            }

            public void setQuantity(int quantity) {
                this.quantity = quantity;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public Object getRank() {
                return rank;
            }

            public void setRank(Object rank) {
                this.rank = rank;
            }

            public Object getRemarks() {
                return remarks;
            }

            public void setRemarks(Object remarks) {
                this.remarks = remarks;
            }

            public String getDelFlag() {
                return delFlag;
            }

            public void setDelFlag(String delFlag) {
                this.delFlag = delFlag;
            }

            public Object getProductFullName() {
                return productFullName;
            }

            public void setProductFullName(Object productFullName) {
                this.productFullName = productFullName;
            }

            public int getInventoryNum() {
                return inventoryNum;
            }

            public void setInventoryNum(int inventoryNum) {
                this.inventoryNum = inventoryNum;
            }
        }
    }
}
