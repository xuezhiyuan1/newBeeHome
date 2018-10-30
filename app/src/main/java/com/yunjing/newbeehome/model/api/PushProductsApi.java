package com.yunjing.newbeehome.model.api;

import com.yunjing.newbeehome.base.Urls;
import com.yunjing.newbeehome.model.entity.PushShopInfoBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 作者：zhiyuan Xue on 2018/10/19 16:38
 * 邮箱：xzy7319@sina.com
 */

public interface PushProductsApi {

    @GET(Urls.GETPUSHSHOPINFO)
    Call<PushShopInfoBean> getpushShopInfo(@Query("machineId") int machineId, @Query("orderId") int orderId);
}
