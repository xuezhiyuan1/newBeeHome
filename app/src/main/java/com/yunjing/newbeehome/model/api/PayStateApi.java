package com.yunjing.newbeehome.model.api;

import com.yunjing.newbeehome.base.Urls;
import com.yunjing.newbeehome.model.entity.PayStateBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 作者：zhiyuan Xue on 2018/10/19 16:23
 * 邮箱：xzy7319@sina.com
 */

public interface PayStateApi {
    @GET(Urls.CHECKONESBUY)
    Call<PayStateBean> getPayState(@Query("orderId") int orderId);
}
