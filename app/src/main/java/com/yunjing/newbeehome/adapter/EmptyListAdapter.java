package com.yunjing.newbeehome.adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.yunjing.newbeehome.R;
import com.yunjing.newbeehome.base.App;
import com.yunjing.newbeehome.base.CommonAdapter;
import com.yunjing.newbeehome.base.Urls;
import com.yunjing.newbeehome.base.ViewHolder;
import com.yunjing.newbeehome.model.api.AddShopCarApi;
import com.yunjing.newbeehome.model.entity.AddShopCarBean;
import com.yunjing.newbeehome.model.entity.NewShopListBean;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 作者：zhiyuan Xue on 2018/8/31 17:13
 * 邮箱：xzy7319@sina.com
 */

public class EmptyListAdapter extends CommonAdapter<NewShopListBean.DataBean.ProductPagesBean.ProductsBean> {


    public EmptyListAdapter(Context context, List<NewShopListBean.DataBean.ProductPagesBean.ProductsBean> data, int layoutId) {
        super(context, data, layoutId);
    }


    @Override
    public void display(ViewHolder holder, final NewShopListBean.DataBean.ProductPagesBean.ProductsBean shopContentBeans) {
            holder.setText(R.id.shop_content_info, shopContentBeans.getProductFullName());
            holder.setImage(R.id.image_info, shopContentBeans.getImageId());
            TextView textView_original = holder.getConvertView().findViewById(R.id.original_price);
            textView_original.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            String valueOf_original = String.valueOf(shopContentBeans.getOriginalPrice());
            double original = Double.parseDouble(valueOf_original);
            double originals = original / 100;

            NumberFormat nf = new DecimalFormat("##.##");
            String str = nf.format(originals);
            textView_original.setText("￥"+str);
            TextView textView_current = holder.getConvertView().findViewById(R.id.current_price);
            String valueOf_current = String.valueOf(shopContentBeans.getNormalPrice());
            double current = Double.parseDouble(valueOf_current);
            double currents = current / 100;
            String str2 = nf.format(currents);
            textView_current.setText("￥"+str2);
           /* ImageView imageView = holder.getConvertView().findViewById(R.id.shopping_btn);
            imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                *//*int currentQuan = shopContentBeans.getInventoryNum();
                Toast.makeText(App.context,"库存还有"+currentQuan+"个",Toast.LENGTH_SHORT).show();*//*
                Log.d("xuezhiyuan",shopContentBeans.getProductId()+"");
                NetGetWorkShopCar(1,shopContentBeans.getProductId());
              }
            });*/
            if(shopContentBeans.getInventoryNum() == 0){
                @SuppressLint("WrongViewCast") LinearLayout linearLayout = holder.getConvertView().findViewById(R.id.tagImages);
                linearLayout.setClickable(false);
                linearLayout.setOnClickListener(null);
                linearLayout.setBackgroundResource(R.drawable.shape_corner_up);
                linearLayout.setPadding(20,20,20,20);
             }
    }

    private void NetGetWorkShopCar(int machineId,int productId){
        Gson gson = new Gson();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AddShopCarApi addShopCarApi = retrofit.create(AddShopCarApi.class);
        HashMap<String,Integer> map = new HashMap<>();
        map.put("machineId",machineId);
        map.put("productId",productId);
        String json = gson.toJson(map);
        RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), json);
        Call<AddShopCarBean> call = addShopCarApi.getNumber(body);
        call.enqueue(new Callback<AddShopCarBean>() {
            @Override
            public void onResponse(Call<AddShopCarBean> call, Response<AddShopCarBean> response) {
                if(response.isSuccessful()){
                    Toast.makeText(App.context,"已加入",Toast.LENGTH_SHORT).show();
                }else{
                    Log.d("xuezhiyuan","500");
                }
            }
            @Override
            public void onFailure(Call<AddShopCarBean> call, Throwable t) {
                Log.d("xuezhiyuan","网络请求失败");
            }
        });
    }

}
