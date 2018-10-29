package com.yunjing.newbeehome.model.entity;

/**
 * 作者：zhiyuan Xue on 2018/9/7 17:55
 * 邮箱：xzy7319@sina.com
 */

public class QcodeBean {

    /**
     * meta : {"success":true,"message":"ok"}
     * data : {"machineOrder":{"machineOrderId":32,"shopId":1,"equipmentOperatorId":0,"customerId":null,"addressId":null,"outTradeNo":"8509ed1d2e914750a6fc57684364bcbd","totalFee":1000,"currentTotalPrice":2000,"orderStatus":"1","createDate":"2018-10-19T07:05:20.274+0000","orderType":null,"paymentType":null,"rank":null,"remarks":null,"delFlag":null,"link":"二维码链接"}}
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
         * machineOrder : {"machineOrderId":32,"shopId":1,"equipmentOperatorId":0,"customerId":null,"addressId":null,"outTradeNo":"8509ed1d2e914750a6fc57684364bcbd","totalFee":1000,"currentTotalPrice":2000,"orderStatus":"1","createDate":"2018-10-19T07:05:20.274+0000","orderType":null,"paymentType":null,"rank":null,"remarks":null,"delFlag":null,"link":"二维码链接"}
         */

        private MachineOrderBean machineOrder;

        public MachineOrderBean getMachineOrder() {
            return machineOrder;
        }

        public void setMachineOrder(MachineOrderBean machineOrder) {
            this.machineOrder = machineOrder;
        }

        public static class MachineOrderBean {
            /**
             * machineOrderId : 32
             * shopId : 1
             * equipmentOperatorId : 0
             * customerId : null
             * addressId : null
             * outTradeNo : 8509ed1d2e914750a6fc57684364bcbd
             * totalFee : 1000
             * currentTotalPrice : 2000
             * orderStatus : 1
             * createDate : 2018-10-19T07:05:20.274+0000
             * orderType : null
             * paymentType : null
             * rank : null
             * remarks : null
             * delFlag : null
             * link : 二维码链接
             */

            private int machineOrderId;
            private int shopId;
            private int equipmentOperatorId;
            private Object customerId;
            private Object addressId;
            private String outTradeNo;
            private int totalFee;
            private int currentTotalPrice;
            private String orderStatus;
            private String createDate;
            private Object orderType;
            private Object paymentType;
            private Object rank;
            private Object remarks;
            private Object delFlag;
            private String link;

            public int getMachineOrderId() {
                return machineOrderId;
            }

            public void setMachineOrderId(int machineOrderId) {
                this.machineOrderId = machineOrderId;
            }

            public int getShopId() {
                return shopId;
            }

            public void setShopId(int shopId) {
                this.shopId = shopId;
            }

            public int getEquipmentOperatorId() {
                return equipmentOperatorId;
            }

            public void setEquipmentOperatorId(int equipmentOperatorId) {
                this.equipmentOperatorId = equipmentOperatorId;
            }

            public Object getCustomerId() {
                return customerId;
            }

            public void setCustomerId(Object customerId) {
                this.customerId = customerId;
            }

            public Object getAddressId() {
                return addressId;
            }

            public void setAddressId(Object addressId) {
                this.addressId = addressId;
            }

            public String getOutTradeNo() {
                return outTradeNo;
            }

            public void setOutTradeNo(String outTradeNo) {
                this.outTradeNo = outTradeNo;
            }

            public int getTotalFee() {
                return totalFee;
            }

            public void setTotalFee(int totalFee) {
                this.totalFee = totalFee;
            }

            public int getCurrentTotalPrice() {
                return currentTotalPrice;
            }

            public void setCurrentTotalPrice(int currentTotalPrice) {
                this.currentTotalPrice = currentTotalPrice;
            }

            public String getOrderStatus() {
                return orderStatus;
            }

            public void setOrderStatus(String orderStatus) {
                this.orderStatus = orderStatus;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public Object getOrderType() {
                return orderType;
            }

            public void setOrderType(Object orderType) {
                this.orderType = orderType;
            }

            public Object getPaymentType() {
                return paymentType;
            }

            public void setPaymentType(Object paymentType) {
                this.paymentType = paymentType;
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

            public Object getDelFlag() {
                return delFlag;
            }

            public void setDelFlag(Object delFlag) {
                this.delFlag = delFlag;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }
        }
    }
}
