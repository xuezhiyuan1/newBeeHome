package com.yunjing.newbeehome.model.api;

import com.yunjing.newbeehome.base.Urls;
import com.yunjing.newbeehome.model.entity.NewShopListBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 列表页
 * 作者：zhiyuan Xue on 2018/10/17 17:39
 * 邮箱：xzy7319@sina.com
 */

public interface ShopListApi {
    @GET(Urls.SHOPLIST)
    Call<NewShopListBean> getDatas(@Query("machineId") int machineId);
}
