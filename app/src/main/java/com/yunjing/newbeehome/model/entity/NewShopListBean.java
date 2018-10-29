package com.yunjing.newbeehome.model.entity;

import java.util.List;

/**
 * 作者：zhiyuan Xue on 2018/10/17 17:06
 * 邮箱：xzy7319@sina.com
 */

public class NewShopListBean {

    /**
     * meta : {"success":true,"message":"ok"}
     * data : {"productPages":[{"productPageId":3,"pageStrategyId":1,"productPageName":"分页名称1","imageId":"3cbb49e327b64df48e8a127f65a5213d","rank":null,"remarks":null,"delFlag":"0","products":[{"productId":1,"equipmentOperatorId":0,"barCode":"115588990","skuCode":"1100","productFullName":"商品全称1","productName":"商品名称","brand":"商品品牌","attribute":"商品属性","specifications":"规格型号","manufacturer":"生产厂家","expirationDate":30,"productStoreType":null,"auditStatus":"1","rank":null,"remarks":null,"delFlag":"0","categoryProducts":null,"categoryProductMaps":null,"productImageMaps":null,"imageId":"3cbb49e327b64df48e8a127f65a5213d","inventoryNum":80,"originalPrice":1000,"normalPrice":2000,"coldStoragePrice":null}]},{"productPageId":4,"pageStrategyId":1,"productPageName":"分页名称","imageId":"3cbb49e327b64df48e8a127f65a5213d","rank":null,"remarks":null,"delFlag":"0","products":[]},{"productPageId":0,"pageStrategyId":null,"productPageName":"未分类商品","imageId":null,"rank":null,"remarks":null,"delFlag":null,"products":[{"productId":2,"equipmentOperatorId":0,"barCode":"9999999","skuCode":"222222","productFullName":"傻瓜机哦双方都是","productName":"顶顶顶顶","brand":"撒大苏打","attribute":"顶顶顶顶","specifications":"90","manufacturer":"顶顶顶顶","expirationDate":30,"productStoreType":null,"auditStatus":"1","rank":null,"remarks":null,"delFlag":"0","categoryProducts":null,"categoryProductMaps":null,"productImageMaps":null,"imageId":"0892c8dd444045538f0375f66771eb67","inventoryNum":49,"originalPrice":1,"normalPrice":1,"coldStoragePrice":null}]}],"advertisementList":[{"advertisementId":5,"advertisementStrategyId":1,"advertisementName":"首页广告","advertisementType":"0","resourceId":"3cbb49e327b64df48e8a127f65a5213d","rank":null,"remarks":null,"delFlag":"0"},{"advertisementId":6,"advertisementStrategyId":1,"advertisementName":"左下角广告","advertisementType":"1","resourceId":"3cbb49e327b64df48e8a127f65a5213d","rank":null,"remarks":null,"delFlag":"0"},{"advertisementId":7,"advertisementStrategyId":1,"advertisementName":"右下角广告","advertisementType":"2","resourceId":"3cbb49e327b64df48e8a127f65a5213d","rank":null,"remarks":null,"delFlag":"0"},{"advertisementId":8,"advertisementStrategyId":1,"advertisementName":"列表页广告","advertisementType":"3","resourceId":"3cbb49e327b64df48e8a127f65a5213d","rank":null,"remarks":null,"delFlag":"0"}]}
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
        private List<ProductPagesBean> productPages;
        private List<AdvertisementListBean> advertisementList;

        public List<ProductPagesBean> getProductPages() {
            return productPages;
        }

        public void setProductPages(List<ProductPagesBean> productPages) {
            this.productPages = productPages;
        }

        public List<AdvertisementListBean> getAdvertisementList() {
            return advertisementList;
        }

        public void setAdvertisementList(List<AdvertisementListBean> advertisementList) {
            this.advertisementList = advertisementList;
        }

        public static class ProductPagesBean {
            /**
             * productPageId : 3
             * pageStrategyId : 1
             * productPageName : 分页名称1
             * imageId : 3cbb49e327b64df48e8a127f65a5213d
             * rank : null
             * remarks : null
             * delFlag : 0
             * products : [{"productId":1,"equipmentOperatorId":0,"barCode":"115588990","skuCode":"1100","productFullName":"商品全称1","productName":"商品名称","brand":"商品品牌","attribute":"商品属性","specifications":"规格型号","manufacturer":"生产厂家","expirationDate":30,"productStoreType":null,"auditStatus":"1","rank":null,"remarks":null,"delFlag":"0","categoryProducts":null,"categoryProductMaps":null,"productImageMaps":null,"imageId":"3cbb49e327b64df48e8a127f65a5213d","inventoryNum":80,"originalPrice":1000,"normalPrice":2000,"coldStoragePrice":null}]
             */

            private int productPageId;
            private int pageStrategyId;
            private String productPageName;
            private String imageId;
            private Object rank;
            private Object remarks;
            private String delFlag;
            private List<ProductsBean> products;

            public int getProductPageId() {
                return productPageId;
            }

            public void setProductPageId(int productPageId) {
                this.productPageId = productPageId;
            }

            public int getPageStrategyId() {
                return pageStrategyId;
            }

            public void setPageStrategyId(int pageStrategyId) {
                this.pageStrategyId = pageStrategyId;
            }

            public String getProductPageName() {
                return productPageName;
            }

            public void setProductPageName(String productPageName) {
                this.productPageName = productPageName;
            }

            public String getImageId() {
                return imageId;
            }

            public void setImageId(String imageId) {
                this.imageId = imageId;
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

            public List<ProductsBean> getProducts() {
                return products;
            }

            public void setProducts(List<ProductsBean> products) {
                this.products = products;
            }

            public static class ProductsBean {
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
                 * productImageMaps : null
                 * imageId : 3cbb49e327b64df48e8a127f65a5213d
                 * inventoryNum : 80
                 * originalPrice : 1000
                 * normalPrice : 2000
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
                private Object productImageMaps;
                private String imageId;
                private int inventoryNum;
                private int originalPrice;
                private int normalPrice;
                private Object coldStoragePrice;

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

                public Object getProductImageMaps() {
                    return productImageMaps;
                }

                public void setProductImageMaps(Object productImageMaps) {
                    this.productImageMaps = productImageMaps;
                }

                public String getImageId() {
                    return imageId;
                }

                public void setImageId(String imageId) {
                    this.imageId = imageId;
                }

                public int getInventoryNum() {
                    return inventoryNum;
                }

                public void setInventoryNum(int inventoryNum) {
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
            }
        }

        public static class AdvertisementListBean {
            /**
             * advertisementId : 5
             * advertisementStrategyId : 1
             * advertisementName : 首页广告
             * advertisementType : 0
             * resourceId : 3cbb49e327b64df48e8a127f65a5213d
             * rank : null
             * remarks : null
             * delFlag : 0
             */

            private int advertisementId;
            private int advertisementStrategyId;
            private String advertisementName;
            private String advertisementType;
            private String resourceId;
            private Object rank;
            private Object remarks;
            private String delFlag;

            public int getAdvertisementId() {
                return advertisementId;
            }

            public void setAdvertisementId(int advertisementId) {
                this.advertisementId = advertisementId;
            }

            public int getAdvertisementStrategyId() {
                return advertisementStrategyId;
            }

            public void setAdvertisementStrategyId(int advertisementStrategyId) {
                this.advertisementStrategyId = advertisementStrategyId;
            }

            public String getAdvertisementName() {
                return advertisementName;
            }

            public void setAdvertisementName(String advertisementName) {
                this.advertisementName = advertisementName;
            }

            public String getAdvertisementType() {
                return advertisementType;
            }

            public void setAdvertisementType(String advertisementType) {
                this.advertisementType = advertisementType;
            }

            public String getResourceId() {
                return resourceId;
            }

            public void setResourceId(String resourceId) {
                this.resourceId = resourceId;
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
