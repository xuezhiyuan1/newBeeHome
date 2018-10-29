package com.yunjing.newbeehome.activity;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import com.yunjing.newbeehome.R;
import com.yunjing.newbeehome.base.BaseActivity;
import com.yunjing.newbeehome.base.Urls;
import com.yunjing.newbeehome.model.api.ClearShopCarApi;
import com.yunjing.newbeehome.model.entity.ClearShopCarBean;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 作者：zhiyuan Xue on 2018/9/6 15:31
 * 邮箱：xzy7319@sina.com
 */

public class ShoppingCarActivity extends BaseActivity implements View.OnClickListener{


    private LinearLayout linBack;
    private Button clearBtn;
    private ListView mList;


    @Override
    protected void layoutId() {
        setContentView(R.layout.shopping_car);
    }

    @Override
    protected void initView() {
        linBack = findViewById(R.id.back_Text);
        clearBtn = findViewById(R.id.clearShopCarBtn);
        mList = findViewById(R.id.list_item);
    }

    @Override
    protected void initData() {
      /*  Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        CheckShopCarBean shopCarBean = (CheckShopCarBean) bundle.getSerializable("checkShopCarBean");
      */  //商品总数
        //int countAll = shopCarBean.getData().getCountAll();
        //List<CheckShopCarBean.DataBean.CartsBean> carts = shopCarBean.getData().getCarts();

    }

    @Override
    protected void initListener() {
        linBack.setOnClickListener(this);
        clearBtn.setOnClickListener(this);
    }

    @Override
    protected void loadData() {

    }

    @Override
    public void in(Class tClass) {

    }

    public void out() {
        finish();
        overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_Text:{
                out();
            }
            break;
            case R.id.clearShopCarBtn:{
                clearShopCarRequest();
            }
            break;
        }
    }

    //清除购物车
    private void clearShopCarRequest() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ClearShopCarApi clearShopCarApi = retrofit.create(ClearShopCarApi.class);
        Call<ClearShopCarBean> beanCall = clearShopCarApi.clearShopCar(1);
        beanCall.enqueue(new Callback<ClearShopCarBean>() {
            @Override
            public void onResponse(Call<ClearShopCarBean> call, Response<ClearShopCarBean> response) {
                if(response.isSuccessful()){
                    Toast.makeText(ShoppingCarActivity.this,"清除成功",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(ShoppingCarActivity.this,"清除失败",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ClearShopCarBean> call, Throwable t) {
                Toast.makeText(ShoppingCarActivity.this,"网络请求失败",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
