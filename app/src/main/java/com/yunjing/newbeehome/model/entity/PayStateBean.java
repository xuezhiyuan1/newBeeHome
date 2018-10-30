package com.yunjing.newbeehome.model.entity;

/**
 * 作者：zhiyuan Xue on 2018/10/19 16:27
 * 邮箱：xzy7319@sina.com
 */

public class PayStateBean {


    /**
     * meta : {"success":true,"message":"ok"}
     * data : 1
     */

    private MetaBean meta;
    private String data;

    public MetaBean getMeta() {
        return meta;
    }

    public void setMeta(MetaBean meta) {
        this.meta = meta;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
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
