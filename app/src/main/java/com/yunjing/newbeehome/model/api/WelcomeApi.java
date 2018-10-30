package com.yunjing.newbeehome.model.api;


import com.yunjing.newbeehome.base.Urls;
import com.yunjing.newbeehome.model.entity.ShopListBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 *  欢迎页
 * 作者：zhiyuan Xue on 2018/9/7 10:31
 * 邮箱：xzy7319@sina.com
 */

public interface WelcomeApi {

    @GET(Urls.DATAURL)
    Call<ShopListBean> getData(@Query("machineId") int machineId);
}
