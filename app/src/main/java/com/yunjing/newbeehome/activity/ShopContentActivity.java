package com.yunjing.newbeehome.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.yunjing.newbeehome.R;
import com.yunjing.newbeehome.base.App;
import com.yunjing.newbeehome.base.BaseActivity;
import com.yunjing.newbeehome.base.Urls;
import com.yunjing.newbeehome.model.api.PayStateApi;
import com.yunjing.newbeehome.model.api.PushProductsApi;
import com.yunjing.newbeehome.model.entity.PayStateBean;
import com.yunjing.newbeehome.model.entity.PushShopInfoBean;
import com.yunjing.newbeehome.model.util.ZxingUtils;
import com.yunjing.newbeehome.oldmachine.logic.OldMachineShipMent;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.yunjing.newbeehome.base.App.context;


/**
 * 作者：zhiyuan Xue on 2018/9/3 17:24
 * 邮箱：xzy7319@sina.com
 */

public class ShopContentActivity extends BaseActivity implements View.OnClickListener{

    private ImageView imageView;
    private Timer timer = new Timer();
    private int time = 120;
    //倒计时
    private TextView textViewValue;
    //返回键
    private LinearLayout linBack;
    //大图
    private ImageView imageViewInfo;
    //商品名
    private TextView textViewShopName,textViewShopNameLitter,originalPrice,currentPrice1,currentPrice2;
    private Bitmap bitmap;
    //订单状态
    private String state = "1";

    private int orderId;
    private String link;
    private int originalPrice1;
    private int normalPrice;
    private String shopName;
    private String imageId;
    private String str2;
    private String str;


    @Override
    protected void layoutId() {
        setContentView(R.layout.activity_shop_content);
        timer.schedule(task, time, 1000);
    }

    @Override
    protected void initView() {
        //  接收 数据
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("Message");
        orderId = bundle.getInt("orderId");
        link = bundle.getString("link");
        originalPrice1 = bundle.getInt("originalPrice");
        normalPrice = bundle.getInt("normalPrice");
        shopName = bundle.getString("shopName");
        imageId = bundle.getString("imageId");
        imageViewInfo =  findViewById(R.id.image_info);
        textViewShopName =  findViewById(R.id.shop_content_info);
        linBack =  findViewById(R.id.back_Text);
        textViewValue = findViewById(R.id.value_Text);
        imageView = findViewById(R.id.image_recode);
        textViewShopNameLitter =  findViewById(R.id.shop_Name2);
        this.originalPrice = findViewById(R.id.original_price);
        currentPrice1 = findViewById(R.id.current_price1);
        currentPrice2 = findViewById(R.id.current_price2);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
         linBack.setOnClickListener(this);
    }

    @Override
    protected void loadData() {
        loadImage(imageViewInfo,imageId);
        textViewShopName.setText(shopName);
        textViewShopNameLitter.setText("暂无");
        originalPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        double original = Double.parseDouble(originalPrice1+"");
        double originals = original / 100;
        NumberFormat nf = new DecimalFormat("##.##");
        str = nf.format(originals);

        double current = Double.parseDouble(normalPrice+"");
        double currents = current / 100;
        str2 = nf.format(currents);
        originalPrice.setText("市场价：￥"+ str);
        currentPrice1.setText("折扣价：￥"+ str2);
        currentPrice2.setText("需支付：￥"+ str2);
        String wXbase= link.replace("\"", "");
        Log.d("xuehouye","https://"+wXbase+"/eureka-WX/order/getOrderList?orderid="+orderId);
        bitmap = ZxingUtils.createBitmap("https://"+wXbase+"/eureka-WX/order/getOrderList?orderid="+orderId);
        imageView.setImageBitmap(bitmap);
    }

    @Override
    public void in(Class tClass) {

    }

    TimerTask task = new TimerTask() {
        @Override public void run() {
            runOnUiThread(new Runnable() {
                private String stateStr;
                private int stateInt;
                @Override public void run() {
                    time--;
                    textViewValue.setText(String.valueOf(time)+"s");
                    textViewValue.setEnabled(false);
                    if(time > 0){
                        stateStr = findOrderPayState(orderId);
                        if("2".equals(stateStr)){
                            time = 0;
                            stopTimer();
                        }
                        stateInt = Integer.parseInt(stateStr);
                        if(stateInt == 2){
                            Bundle bundle = new Bundle();
                            bundle.putString("original",str);
                            bundle.putString("current",str2);
                            bundle.putString("imgUrl",imageId);
                            bundle.putString("shopName1",shopName);
                            bundle.putString("shopName2","暂无");
                            bundle.putInt("orderId",orderId);
                            bundle.putInt("state",stateInt);
                            inAct(ShopFinishActivity.class,bundle);
                        }else {
                            stateStr = null;
                        }
                    }else {
                        stateStr = null;
                        out();
                    }
                }
            });
        }
    };

    public void out() {
        finish();
        overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
    }

    private void stopTimer(){

        if (task != null) {
            task.cancel();
            task = null;
        }

        if (task != null) {
            task.cancel();
            task = null;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_Text:{
                stopTimer();
                out();
            }
            break;
        }
    }

    //加载图片
    public int loadImage(ImageView imageView,String imgUrl){
        if(context !=null){
            Glide.with(context).load(Urls.BASEIMAGEBEFORE+imgUrl+Urls.BASEIMAGEAFTER).into(imageView);
            return 1;
        }else{
            return 0;
        }
    }


    //查看订单支付状态
    private String findOrderPayState(int orderId){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PayStateApi payStateApi = retrofit.create(PayStateApi.class);
        Call<PayStateBean> call = payStateApi.getPayState(orderId);
        call.enqueue(new Callback<PayStateBean>() {

            @Override
            public void onResponse(Call<PayStateBean> call, Response<PayStateBean> response) {
                if(response.isSuccessful()){
                    state = response.body().getData();
                    Log.d("xuezhiyuan", state);
                }else{
                    Log.d("xuezhiyuan","请求数据失败");
                }
            }
            @Override
            public void onFailure(Call<PayStateBean> call, Throwable t) {
                Log.d("xuezhiyuan","请求数据失败");
            }
        });
        return state;
    }





    //跳转Activity
    public void inAct(Class tClass,Bundle bundle) {
        Intent intent = new Intent(context, tClass);
        intent.putExtra("finish",bundle);
        startActivity(intent);
        finish();
        context.overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
    }

}
