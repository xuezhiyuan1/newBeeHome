package com.yunjing.newbeehome.model.entity;

/**
 * 作者：zhiyuan Xue on 2018/10/22 10:23
 * 邮箱：xzy7319@sina.com
 */

public class ClearShopCarBean {

    /**
     * meta : {"success":true,"message":"ok"}
     * data : null
     */

    private MetaBean meta;
    private Object data;

    public MetaBean getMeta() {
        return meta;
    }

    public void setMeta(MetaBean meta) {
        this.meta = meta;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
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
}
