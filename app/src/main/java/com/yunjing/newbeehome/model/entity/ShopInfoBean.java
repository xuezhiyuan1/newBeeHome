package com.yunjing.newbeehome.model.entity;

import java.util.List;

/**
 * 作者：zhiyuan Xue on 2018/10/22 17:02
 * 邮箱：xzy7319@sina.com
 */

public class ShopInfoBean {

    /**
     * meta : {"success":true,"message":"ok"}
     * data : {"productId":1,"equipmentOperatorId":0,"barCode":"115588990","skuCode":"1100","productFullName":"商品全称1","productName":"商品名称","brand":"商品品牌","attribute":"商品属性","specifications":"规格型号","manufacturer":"生产厂家","expirationDate":30,"productStoreType":null,"auditStatus":"1","rank":null,"remarks":null,"delFlag":"0","categoryProducts":null,"categoryProductMaps":null,"productImageMaps":[{"productImageMapId":7,"productId":1,"imageId":"3cbb49e327b64df48e8a127f65a5213d","imageType":"0","rank":null,"remarks":null,"delFlag":"0"},{"productImageMapId":8,"productId":1,"imageId":"3cbb49e327b64df48e8a127f65a5213d","imageType":"1","rank":null,"remarks":null,"delFlag":"0"}],"imageId":null,"inventoryNum":null,"originalPrice":1000,"normalPrice":100,"coldStoragePrice":null}
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
         * productId : 1
         * equipmentOperatorId : 0
         * barCode : 115588990
         * skuCode : 1100
         * productFullName : 商品全称1
         * productName : 商品名称
         * brand : 商品品牌
         * attribute : 商品属性
         * specifications : 规格型号
         * manufacturer : 生产厂家
         * expirationDate : 30
         * productStoreType : null
         * auditStatus : 1
         * rank : null
         * remarks : null
         * delFlag : 0
         * categoryProducts : null
         * categoryProductMaps : null
         * productImageMaps : [{"productImageMapId":7,"productId":1,"imageId":"3cbb49e327b64df48e8a127f65a5213d","imageType":"0","rank":null,"remarks":null,"delFlag":"0"},{"productImageMapId":8,"productId":1,"imageId":"3cbb49e327b64df48e8a127f65a5213d","imageType":"1","rank":null,"remarks":null,"delFlag":"0"}]
         * imageId : null
         * inventoryNum : null
         * originalPrice : 1000
         * normalPrice : 100
         * coldStoragePrice : null
         */

        private int productId;
        private int equipmentOperatorId;
        private String barCode;
        private String skuCode;
        private String productFullName;
        private String productName;
        private String brand;
        private String attribute;
        private String specifications;
        private String manufacturer;
        private int expirationDate;
        private Object productStoreType;
        private String auditStatus;
        private Object rank;
        private Object remarks;
        private String delFlag;
        private Object categoryProducts;
        private Object categoryProductMaps;
        private Object imageId;
        private Object inventoryNum;
        private int originalPrice;
        private int normalPrice;
        private Object coldStoragePrice;
        private List<ProductImageMapsBean> productImageMaps;

        public int getProductId() {
            return productId;
        }

        public void setProductId(int productId) {
            this.productId = productId;
        }

        public int getEquipmentOperatorId() {
            return equipmentOperatorId;
        }

        public void setEquipmentOperatorId(int equipmentOperatorId) {
            this.equipmentOperatorId = equipmentOperatorId;
        }

        public String getBarCode() {
            return barCode;
        }

        public void setBarCode(String barCode) {
            this.barCode = barCode;
        }

        public String getSkuCode() {
            return skuCode;
        }

        public void setSkuCode(String skuCode) {
            this.skuCode = skuCode;
        }

        public String getProductFullName() {
            return productFullName;
        }

        public void setProductFullName(String productFullName) {
            this.productFullName = productFullName;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public String getAttribute() {
            return attribute;
        }

        public void setAttribute(String attribute) {
            this.attribute = attribute;
        }

        public String getSpecifications() {
            return specifications;
        }

        public void setSpecifications(String specifications) {
            this.specifications = specifications;
        }

        public String getManufacturer() {
            return manufacturer;
        }

        public void setManufacturer(String manufacturer) {
            this.manufacturer = manufacturer;
        }

        public int getExpirationDate() {
            return expirationDate;
        }

        public void setExpirationDate(int expirationDate) {
            this.expirationDate = expirationDate;
        }

        public Object getProductStoreType() {
            return productStoreType;
        }

        public void setProductStoreType(Object productStoreType) {
            this.productStoreType = productStoreType;
        }

        public String getAuditStatus() {
            return auditStatus;
        }

        public void setAuditStatus(String auditStatus) {
            this.auditStatus = auditStatus;
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

        public Object getCategoryProducts() {
            return categoryProducts;
        }

        public void setCategoryProducts(Object categoryProducts) {
            this.categoryProducts = categoryProducts;
        }

        public Object getCategoryProductMaps() {
            return categoryProductMaps;
        }

        public void setCategoryProductMaps(Object categoryProductMaps) {
            this.categoryProductMaps = categoryProductMaps;
        }

        public Object getImageId() {
            return imageId;
        }

        public void setImageId(Object imageId) {
            this.imageId = imageId;
        }

        public Object getInventoryNum() {
            return inventoryNum;
        }

        public void setInventoryNum(Object inventoryNum) {
            this.inventoryNum = inventoryNum;
        }

        public int getOriginalPrice() {
            return originalPrice;
        }

        public void setOriginalPrice(int originalPrice) {
            this.originalPrice = originalPrice;
        }

        public int getNormalPrice() {
            return normalPrice;
        }

        public void setNormalPrice(int normalPrice) {
            this.normalPrice = normalPrice;
        }

        public Object getColdStoragePrice() {
            return coldStoragePrice;
        }

        public void setColdStoragePrice(Object coldStoragePrice) {
            this.coldStoragePrice = coldStoragePrice;
        }

        public List<ProductImageMapsBean> getProductImageMaps() {
            return productImageMaps;
        }

        public void setProductImageMaps(List<ProductImageMapsBean> productImageMaps) {
            this.productImageMaps = productImageMaps;
        }

        public static class ProductImageMapsBean {
            /**
             * productImageMapId : 7
             * productId : 1
             * imageId : 3cbb49e327b64df48e8a127f65a5213d
             * imageType : 0
             * rank : null
             * remarks : null
             * delFlag : 0
             */

            private int productImageMapId;
            private int productId;
            private String imageId;
            private String imageType;
            private Object rank;
            private Object remarks;
            private String delFlag;

            public int getProductImageMapId() {
                return productImageMapId;
            }

            public void setProductImageMapId(int productImageMapId) {
                this.productImageMapId = productImageMapId;
            }

            public int getProductId() {
                return productId;
            }

            public void setProductId(int productId) {
                this.productId = productId;
            }

            public String getImageId() {
                return imageId;
            }

            public void setImageId(String imageId) {
                this.imageId = imageId;
            }

            public String getImageType() {
                return imageType;
            }

            public void setImageType(String imageType) {
                this.imageType = imageType;
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
        }
    }
}
