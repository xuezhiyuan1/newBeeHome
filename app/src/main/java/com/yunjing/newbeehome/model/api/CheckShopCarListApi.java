package com.yunjing.newbeehome.model.api;

import com.yunjing.newbeehome.base.Urls;
import com.yunjing.newbeehome.model.entity.CheckShopCarBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 作者：zhiyuan Xue on 2018/10/22 10:22
 * 邮箱：xzy7319@sina.com
 */

public interface CheckShopCarListApi {

    @GET(Urls.SHOPCARLISTCHECK)
    Call<CheckShopCarBean> getDatas(@Query("machineId") int machineId);
}
