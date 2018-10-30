package com.yunjing.newbeehome.model.api;

import com.yunjing.newbeehome.base.Urls;
import com.yunjing.newbeehome.model.entity.QcodeBean;
import com.yunjing.newbeehome.model.entity.ShopInfoBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 作者：zhiyuan Xue on 2018/10/22 17:01
 * 邮箱：xzy7319@sina.com
 */

public interface OneShopInfoApi {

    @GET(Urls.ONSHOPINFO)
    Call<ShopInfoBean> getShopInfo(@Query("productId") int productId, @Query("machineId") int machineId);
}
