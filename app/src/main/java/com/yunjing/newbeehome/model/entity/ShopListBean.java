package com.yunjing.newbeehome.model.entity;



import java.io.Serializable;
import java.util.List;


/**
 * Created by Administrator on 2018/8/24.
 */

public class ShopListBean implements Serializable{

    /**
     * meta : {"success":true,"message":"ok"}
     * data : [{"advertisementId":25,"advertisementStrategyId":1,"advertisementName":"首页广告","advertisementType":"0","resourceId":"3cbb49e327b64df48e8a127f65a5213d","rank":null,"remarks":null,"delFlag":"0"},{"advertisementId":26,"advertisementStrategyId":1,"advertisementName":"广告名称","advertisementType":"0","resourceId":"3dc91b3f6d724102be5e2da3e02129ae","rank":null,"remarks":null,"delFlag":"0"},{"advertisementId":27,"advertisementStrategyId":1,"advertisementName":null,"advertisementType":null,"resourceId":null,"rank":null,"remarks":null,"delFlag":"0"},{"advertisementId":28,"advertisementStrategyId":1,"advertisementName":null,"advertisementType":null,"resourceId":null,"rank":null,"remarks":null,"delFlag":"0"},{"advertisementId":29,"advertisementStrategyId":1,"advertisementName":"列表页广告","advertisementType":"3","resourceId":"3cbb49e327b64df48e8a127f65a5213d","rank":null,"remarks":null,"delFlag":"0"}]
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
         * advertisementId : 25
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
