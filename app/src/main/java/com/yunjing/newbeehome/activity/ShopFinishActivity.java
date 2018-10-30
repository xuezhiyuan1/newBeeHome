package com.yunjing.newbeehome.activity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yunjing.newbeehome.R;
import com.yunjing.newbeehome.base.App;
import com.yunjing.newbeehome.base.BaseActivity;
import com.yunjing.newbeehome.base.Keys;
import com.yunjing.newbeehome.base.Urls;
import com.yunjing.newbeehome.model.api.PayStateApi;
import com.yunjing.newbeehome.model.api.PushProductsApi;
import com.yunjing.newbeehome.model.entity.PayStateBean;
import com.yunjing.newbeehome.model.entity.PushShopInfoBean;
import com.yunjing.newbeehome.model.util.DeleteFileUtil;
import com.yunjing.newbeehome.model.util.PropertiesUtils;
import com.yunjing.newbeehome.model.util.TtsUtil;
import com.yunjing.newbeehome.oldmachine.CloseDoorOrder;
import com.yunjing.newbeehome.oldmachine.OpenDoorLafterBoxState;
import com.yunjing.newbeehome.oldmachine.logic.OldMachineShipMent;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.yunjing.newbeehome.base.App.context;

/**
 * 作者：zhiyuan Xue on 2018/9/11 18:05
 * 邮箱：xzy7319@sina.com
 */

public class ShopFinishActivity extends BaseActivity {

    private TextView textViewName,textViewLitterName,textViewOriginal,textViewCurrent,textViewResult;
    private ImageView imageView_finish;
    private String imgUrl;
    private int orderId;
    private String shipment;
    private int deliverySpeed;
    private int zAxis;
    private int yAxis;
    private int xAxis;
    private Lock lock;
    private TextToSpeech tts;
    private Properties prop;
    private String machineId;

    @Override
    protected void layoutId() {
         setContentView(R.layout.shop_finish_activity);
    }

    @Override
    protected void initView() {
        prop = PropertiesUtils.propertiesUtils().properties(Keys.FILE_URI_PATH + Keys.FILE_NAME);
        machineId = prop.getProperty("QUEUE_NAME");
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("finish");
        String shopName1 = bundle.getString("shopName1");
        String shopName2 = bundle.getString("shopName2");
        String priceCurrent = bundle.getString("current");
        String priceOriginal = bundle.getString("original");
        orderId = bundle.getInt("orderId");
        imgUrl = bundle.getString("imgUrl");

        imageView_finish = findViewById(R.id.image_info_finish);
        textViewName = findViewById(R.id.shop_content_info_finish);
        textViewLitterName = findViewById(R.id.shop_Name_finish);
        textViewOriginal= findViewById(R.id.original_price_finish);
        textViewCurrent = findViewById(R.id.current_price_finish);
        textViewResult = findViewById(R.id.textResult);

        Glide.with(App.context).load(Urls.BASEIMAGEBEFORE+ imgUrl +Urls.BASEIMAGEAFTER).into(imageView_finish);
        textViewName.setText(shopName1);
        textViewLitterName.setText(shopName2);
        textViewOriginal.setText("市场价：￥"+priceOriginal);
        textViewCurrent.setText("已支付：￥"+priceCurrent);

    }

    @Override
    protected void initData() {
        lock = new ReentrantLock();
        // 如果装载TTS引擎成功
        // 设置使用美式英语朗读
        // 如果不支持所设置的语言
        tts = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                // 如果装载TTS引擎成功
                if (status == TextToSpeech.SUCCESS) {
                    // 设置使用美式英语朗读
                    int result = tts.setLanguage(Locale.CHINESE);
                    // 如果不支持所设置的语言
                    if (result != TextToSpeech.LANG_COUNTRY_AVAILABLE
                            && result != TextToSpeech.LANG_AVAILABLE) {
                        Log.d("xuezhiyuan","TTS暂时不支持这种语言的朗读！");
                    }
                }
            }
        });
    }

    @Override
    protected void initListener() {

    }




    @Override
    protected void loadData() {
        getLocatio(orderId);
    }

    @Override
    public void in(Class tClass) {
       Intent intent = new Intent(this,tClass);
       startActivity(intent);
       finish();
       overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
    }


    //获取出货坐标
    private String getLocatio(int orderId){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PushProductsApi pushProductsApi = retrofit.create(PushProductsApi.class);
        Call<PushShopInfoBean> call = pushProductsApi.getpushShopInfo(Integer.parseInt(machineId), orderId);
        call.enqueue(new Callback<PushShopInfoBean>() {



            @Override
            public void onResponse(Call<PushShopInfoBean> call, Response<PushShopInfoBean> response) {
                if(response.isSuccessful()){
                    PushShopInfoBean body = response.body();
                    List<PushShopInfoBean.DataBean> data = body.getData();
                    xAxis = data.get(0).getXAxis();
                    yAxis = data.get(0).getYAxis();
                    zAxis = data.get(0).getZAxis();
                    deliverySpeed = data.get(0).getDeliverySpeed();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            lock.lock();
                            shipment = OldMachineShipMent.shipment(xAxis, yAxis, zAxis, deliverySpeed);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if ("06".equals(shipment)){
                                        //静止-出货成功
                                        textViewResult.setText("出货成功");
                                        tts.speak("出货成功！请 上提出货仓门 取走商品", TextToSpeech.QUEUE_ADD,
                                                null);
                                        try {
                                            //静止开门后查询设备状态的返回   30S
                                            String port = prop.getProperty(machineId);
                                            String machineBoxState = OpenDoorLafterBoxState.findMachineOrder(port, 26, 28, 3000);
                                            if (machineBoxState.equals("07")) {
                                                //关门  查询门限位
                                                CloseDoorOrder.findMachineOrder(port, 12, 14, 100);
                                                DeleteFileUtil.deletefile("shoplist.out");
                                                in(MainActivity.class);
                                            } else if (machineBoxState.equals("06")) {
                                                //取货超时
                                                CloseDoorOrder.findMachineOrder(port, 12, 14, 1000);
                                                DeleteFileUtil.deletefile("shoplist.out");
                                                in(MainActivity.class);
                                            } else {
                                                //静止  08  数据错位  复位
                                                CloseDoorOrder.findMachineOrder(port, 12, 14, 1000);
                                                textViewResult.setText("数据错位");
                                                DeleteFileUtil.deletefile("shoplist.out");
                                                in(ErrorActivity.class);
                                            }
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }else if("08".equals(shipment)){
                                        //数据错位   复位
                                        textViewResult.setText("数据错位");
                                        DeleteFileUtil.deletefile("shoplist.out");
                                        in(ErrorActivity.class);
                                    }else if("09".equals(shipment)){
                                        //推空
                                        textViewResult.setText("推空超时");
                                        DeleteFileUtil.deletefile("shoplist.out");
                                        in(ErrorActivity.class);
                                    }
                                }
                            });
                            lock.unlock();
                        }
                    }).start();
                }else {
                    Log.d("xuezhiyuan","500");
                    //服务器返回结果null-500
                    //textViewResult.setText("服务器返回");
                    in(ErrorActivity.class);
                }
            }

            @Override
            public void onFailure(Call<PushShopInfoBean> call, Throwable t) {
                Log.d("xuezhiyuan","请求数据失败");
                //网络请求失败-400
                //textViewResult.setText("网络请求失败");
                in(ErrorActivity.class);
            }
        });
        return shipment;
    }

    @Override
    protected void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
            Log.d("TAG", "TTS Destroyed");
        }
        super.onDestroy();
    }
}
