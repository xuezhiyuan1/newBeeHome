package com.yunjing.newbeehome.model.api;

import com.yunjing.newbeehome.base.Urls;
import com.yunjing.newbeehome.model.entity.AddShopCarBean;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * 作者：zhiyuan Xue on 2018/10/20 10:00
 * 邮箱：xzy7319@sina.com
 */

public interface AddShopCarApi {
    @POST(Urls.ADDSHOPTOSHOPCAR)
    Call<AddShopCarBean> getNumber(@Body RequestBody route);
}
