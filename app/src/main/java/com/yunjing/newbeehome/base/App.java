package com.yunjing.newbeehome.base;

import android.app.Activity;
import android.support.multidex.MultiDexApplication;
/**
 * 作者：zhiyuan Xue on 2018/8/30 18:28
 * 邮箱：xzy7319@sina.com
 */

public class App extends MultiDexApplication {
    public static Activity context;
    @Override
    public void onCreate() {
        super.onCreate();
    }
}
