package com.yunjing.newbeehome.fragment;


import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.yunjing.newbeehome.R;
import com.yunjing.newbeehome.activity.ShopContentActivity;
import com.yunjing.newbeehome.adapter.EmptyListAdapter;
import com.yunjing.newbeehome.base.App;
import com.yunjing.newbeehome.base.BaseFragment;
import com.yunjing.newbeehome.base.Keys;
import com.yunjing.newbeehome.base.Urls;
import com.yunjing.newbeehome.model.api.OneQcodeApi;
import com.yunjing.newbeehome.model.entity.NewShopListBean;
import com.yunjing.newbeehome.model.entity.QcodeBean;
import com.yunjing.newbeehome.model.util.PropertiesUtils;
import com.yunjing.newbeehome.oldmachine.FindShipRedLineState;
import com.yunjing.newbeehome.oldmachine.logic.OldMachineTakeShop;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.yunjing.newbeehome.base.App.context;


@SuppressLint("ValidFragment")
public class EmptyFragment extends BaseFragment {

    private GridView gridView;
    private TextToSpeech tts;
    private EmptyListAdapter emptyListAdapter;
    private List<NewShopListBean.DataBean.ProductPagesBean.ProductsBean> list = new ArrayList<>();
    private Properties prop;
    private Bundle bundle;
    private String machineId;

    @SuppressLint("ValidFragment")
    public EmptyFragment(List<NewShopListBean.DataBean.ProductPagesBean.ProductsBean> list){
        this.list = list;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.shop_content_fragment;
    }

    @Override
    protected void initView(View view) {
        gridView = view.findViewById(R.id.contentGridView);
    }

    @Override
    protected void initData() {
        emptyListAdapter = new EmptyListAdapter(getActivity(),list,R.layout.shop_content);
        gridView.setAdapter(emptyListAdapter);
        emptyListAdapter.notifyDataSetChanged();
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
    protected void loadData() {
        prop = PropertiesUtils.propertiesUtils().properties(Keys.FILE_URI_PATH + Keys.FILE_NAME);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    machineId = prop.getProperty("QUEUE_NAME");
                    String port = prop.getProperty(machineId);
                    String machineRedLineOrder = FindShipRedLineState.findMachineRedLineOrder(port,12,14);
                    if(machineRedLineOrder.equals("00")){
                        //no have  //跳转详情
                        bundle = new Bundle();
                        String productFullName = list.get(i).getProductFullName();
                        int originalPrice = list.get(i).getOriginalPrice();
                        int normalPrice = list.get(i).getNormalPrice();
                        String imageId = list.get(i).getImageId();
                        bundle.putString("shopName",productFullName);
                        bundle.putString("imageId",imageId);
                        bundle.putInt("originalPrice",originalPrice);
                        bundle.putInt("normalPrice",normalPrice);
                        int productId = list.get(i).getProductId();
                        NetGetRequestQcode(productId);
                        //Toast.makeText(App.context,"正在跳转",Toast.LENGTH_SHORT).show();
                    }else if(machineRedLineOrder.equals("01")){
                        //have     //弹出框  取走商品
                        //dialogAlent("温馨提示","已开门，请取走商品后购买");
                        tts.speak("门锁已开 请取走商品后购买", TextToSpeech.QUEUE_ADD,
                                null);
                        OldMachineTakeShop.takeShop();
                    }else if(machineRedLineOrder.equals("09")){
                        //09  串口未插入
                        tts.speak("请检查串口", TextToSpeech.QUEUE_ADD,
                                null);
                        Toast.makeText(App.context,"串口未插入,请检查串口",Toast.LENGTH_SHORT).show();
                    }else {
                        //08       //暂无考虑
                        tts.speak("抱歉机器故障", TextToSpeech.QUEUE_ADD,
                                null);
                        Toast.makeText(App.context,"数据错位",Toast.LENGTH_SHORT).show();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }



    //跳转Activity
    public void in(Class tClass,Bundle bundle) {
        Intent intent = new Intent(context, tClass);
        intent.putExtra("Message",bundle);
        startActivity(intent);
        context.overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
    }


    @Override
    public void onClick(View view) {

    }

    private void NetGetRequestQcode(int productId){
        showProgressBar();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        OneQcodeApi oneQcodeApi = retrofit.create(OneQcodeApi.class);
        Call<QcodeBean> qcode = oneQcodeApi.getQcode(productId, Integer.parseInt(machineId));
        qcode.enqueue(new Callback<QcodeBean>() {
            @Override
            public void onResponse(Call<QcodeBean> call, Response<QcodeBean> response) {
                if(response.isSuccessful()){
                    dismiss();
                    QcodeBean body = response.body();
                    QcodeBean.DataBean data = body.getData();
                    //订单ID
                    int orderId = data.getMachineOrder().getMachineOrderId();
                    //二维码
                    String link = data.getMachineOrder().getLink();
                    bundle.putInt("orderId",orderId);
                    bundle.putString("link",link);
                    in(ShopContentActivity.class, bundle);
                    tts.speak("请用微信扫码支付", TextToSpeech.QUEUE_ADD,
                            null);
                }else {
                    dismiss();
                    Log.d("xuezhiyuan","请求数据失败1");
                }
            }

            @Override
            public void onFailure(Call<QcodeBean> call, Throwable t) {
                dismiss();
                if(!(context).isFinishing()) {
                    dialogAlent("温馨提示","网络请求失败");
                    tts.speak("网络请求失败, 请联系客服处理", TextToSpeech.QUEUE_ADD,
                            null);
                }
                Log.d("xuezhiyuan","请求数据失败2");
            }
        });
    }

    //网络连接失败   弹出框
    private void dialogAlent(String title,String content){
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(content)
                .setCancelable(false)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create().show();
    }

    @Override
    public void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
            Log.d("TAG", "TTS Destroyed");
        }
        super.onDestroy();
    }


}
