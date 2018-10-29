package com.yunjing.newbeehome.model.api;

import com.yunjing.newbeehome.base.Urls;
import com.yunjing.newbeehome.model.entity.QcodeBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 单个商品显示二维码
 * 作者：zhiyuan Xue on 2018/10/19 15:09
 * 邮箱：xzy7319@sina.com
 */

public interface OneQcodeApi {
    @GET(Urls.PAYONEQCODE)
    Call<QcodeBean> getQcode(@Query("productId") int productId,@Query("machineId") int machineId);
}
