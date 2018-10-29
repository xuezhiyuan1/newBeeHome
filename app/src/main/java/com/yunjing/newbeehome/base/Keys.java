package com.yunjing.newbeehome.base;

import android.os.Environment;

/**
 * 作者：zhiyuan Xue on 2018/8/30 20:45
 * 邮箱：xzy7319@sina.com
 */

public class Keys {
    //public static final String URL = "http://comb-machine-client.cloudmirror.cn/html/welcome.html?machineId=";
    public static final int QUEUE_NAME = 1;
    //消息队列地址
    public static final String ADDRESS = "101.201.150.111:5672";
    //消息队列 用户名 密码
    public static final String USERNAME = "admin";
    public static final String PASSWORD = "admin@123";
    public static final String VIRTUALHOST = "/";
    public static final String CONFIG = "The New properties file";
    public static final int TIME_QUEEN = 120000;
    public static final int ONE = 1;
    public static final int TWO = 2;
    public static final int THREE = 3;
    public static final int SIX = 6;
    public static final int SENVEN = 7;
    public static final int EIGHT = 8;
    public static final int NINE = 9;
    public static final int TEN = 10;
    public static final int ELEVEN = 11;
    public static final int TWELVE = 12;
    public static final int THIRTEEN = 13;

    //配置文件路径
    public static final String FILE_URI_PATH = Environment.getExternalStorageDirectory()+"/Vendor/Android/Config";
    public static final String FILE_NAME = "/config.properties";
}
