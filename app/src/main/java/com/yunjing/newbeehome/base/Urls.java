package com.yunjing.newbeehome.base;

/**
 * 作者：zhiyuan Xue on 2018/8/30 20:43
 * 邮箱：xzy7319@sina.com
 */

public class Urls {
    //测试服务器地址
    public static final String BASEURL = "http://192.168.1.111:8762/";
    //欢迎页地址
    public static final String DATAURL = "eureka-C/main/welcome";
    //图片前缀地址
    public static final String BASEIMAGEBEFORE = "http://192.168.1.111:8762/eureka-C/file/";
    //图片后缀地址
    public static final String BASEIMAGEAFTER = "/stream";
    //列表页接口GET
    public static final String SHOPLIST = "eureka-C/main/list?";
    //单个商品详情
    public static final String ONSHOPINFO = "eureka-C/main/productDetail";
    //生成单个商品支付二维码
    public static final String PAYONEQCODE = "eureka-C/main/getProductQcode";
    //单个购买时查看商品支付状态
    public static final String CHECKONESBUY = "eureka-C/main/commonPayState";
    //获取出货信息
    public static final String GETPUSHSHOPINFO = "eureka-C/main/pushProducts";
    //（外）购物车新增商品  POST
    public static final String ADDSHOPTOSHOPCAR = "eureka-C/cart/saveCart";
    //（内）购物车商品数量变更或者删除单个购物车    POST
    public static final String UPDATACAR = "eureka-C/cart/updateCart";
    //清空购物车
    public static final String CLEARSHOPCAR = "eureka-C/cart/removeCart";
    //购物车列表查询
    public static final String SHOPCARLISTCHECK = "eureka-C/cart/queryCart";
    //预支付
    public static final String PREPAYURL = "machineAdmin/main/prePay";
    //货舱有货  打开货舱门
    public static final String OPENDOOR = "machineAdmin/main/getProduct";

}
