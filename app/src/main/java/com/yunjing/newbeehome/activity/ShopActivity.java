package com.yunjing.newbeehome.activity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.yunjing.newbeehome.R;
import com.yunjing.newbeehome.adapter.HomePagerAdapter;
import com.yunjing.newbeehome.base.App;
import com.yunjing.newbeehome.base.BaseActivity;
import com.yunjing.newbeehome.base.BaseFragment;
import com.yunjing.newbeehome.base.Urls;
import com.yunjing.newbeehome.fragment.EmptyFragment;
import com.yunjing.newbeehome.model.api.CheckShopCarListApi;
import com.yunjing.newbeehome.model.api.ShopListApi;
import com.yunjing.newbeehome.model.callback.ShopNumberInter;
import com.yunjing.newbeehome.model.callback.ShopNumbers;
import com.yunjing.newbeehome.model.entity.CheckShopCarBean;
import com.yunjing.newbeehome.model.entity.NewShopListBean;
import com.yunjing.newbeehome.model.util.FileSaveUtil;
import com.yunjing.newbeehome.model.util.ReadFileUtil;

import java.util.ArrayList;
import java.util.List;

import q.rorbin.badgeview.QBadgeView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.yunjing.newbeehome.base.App.context;


/**
 * 作者：zhiyuan Xue on 2018/10/16 16:52
 * 邮箱：xzy7319@sina.com
 */

public class ShopActivity extends BaseActivity implements View.OnClickListener{

    private TabLayout titleLayout;
    private List<NewShopListBean.DataBean.ProductPagesBean> titles;
    private ViewPager mViewPager;
    private HomePagerAdapter pagerAdapter;
    private List<BaseFragment> fragments;
    private ImageView imageView;
    //,imageshopcar
    private int tagTab = 1;

    @Override
    protected void layoutId() {
        setContentView(R.layout.activity_shop);
    }

    @Override
    protected void initView() {
        imageView = findViewById(R.id.tagImage);
        //imageshopcar = findViewById(R.id.shop_car_Image);
        titleLayout = findViewById(R.id.homeTitleLayout);
        mViewPager = findViewById(R.id.homeViewPager);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.d("page",position+"onPageScrolled");
            }

            @Override
            public void onPageSelected(int position) {
                Log.d("page",position+"onPageSelected");
                tagTab = position;
                Log.d("page",tagTab+"onPageSelected");
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        titleLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        titleLayout.setTabMode(TabLayout.MODE_FIXED);
    }

    @Override
    protected void initData() {
        fragments = new ArrayList<>();
        titles = new ArrayList<>();
        pagerAdapter = new HomePagerAdapter(getSupportFragmentManager(),fragments,titles);
        mViewPager.setAdapter(pagerAdapter);
        titleLayout.setupWithViewPager(mViewPager);
    }

    @Override
    protected void initListener() {
        imageView.setOnClickListener(this);
        //imageshopcar.setOnClickListener(this);
    }

    @Override
    protected void loadData() {
        ReadFileUtil readFileUtil = new ReadFileUtil();
        String json = (String) readFileUtil.readObjFromSDCard("shoplist");
        if(TextUtils.isEmpty(json)){
            NetGetRequest();
        }else {
            Gson gson = new Gson();
            NewShopListBean newShopListBean = gson.fromJson(json, NewShopListBean.class);
            NewShopListBean.DataBean newShopListBeanData = newShopListBean.getData();
            fragmentData(newShopListBeanData);
        }
    }

    @Override
    public void in(Class tClass) {
        Intent intent = new Intent(this, tClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tagImage:{
                in(MainActivity.class);
            }
            break;
            /* case R.id.shop_car_Image:{
                 //Toast.makeText(ShopActivity.this,"暂未开通",Toast.LENGTH_SHORT).show();
                 checkShopCar();
             }
             break;*/
        }
    }

    //购物车列表页查询
    private void checkShopCar() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CheckShopCarListApi checkShopCarListApi = retrofit.create(CheckShopCarListApi.class);
        Call<CheckShopCarBean> call = checkShopCarListApi.getDatas(1);
        call.enqueue(new Callback<CheckShopCarBean>() {
            @Override
            public void onResponse(Call<CheckShopCarBean> call, Response<CheckShopCarBean> response) {
                if(response.isSuccessful()){
                    CheckShopCarBean checkShopCarBean = response.body();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("checkShopCarBean", checkShopCarBean);
                    Intent intent;
                    if(checkShopCarBean.getData().getCountAll() == 0){
                         intent = new Intent(ShopActivity.this,EmptyShopCarActivity.class);
                    }else{
                         intent = new Intent(ShopActivity.this,ShoppingCarActivity.class);
                    }
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
                    Toast.makeText(ShopActivity.this,"success",Toast.LENGTH_SHORT).show();
                }else{
                    Log.d("xuezhiyuan","无数据返回");
                }
            }

            @Override
            public void onFailure(Call<CheckShopCarBean> call, Throwable t) {
                Toast.makeText(ShopActivity.this,"网络请求失败",Toast.LENGTH_SHORT).show();
                Log.d("xuezhiyuan","网络请求失败");
            }
        });
    }


    //请求数据
    private void NetGetRequest(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final ShopListApi shopListApi = retrofit.create(ShopListApi.class);
        Call<NewShopListBean> call = shopListApi.getDatas(1);
        call.enqueue(new Callback<NewShopListBean>() {
            @Override
            public void onResponse(Call<NewShopListBean> call, Response<NewShopListBean> response) {
                int code = response.code();
                Log.d("xuezhiyuan",code+"");
                if (response.isSuccessful()){
                    Gson gson = new Gson();
                    Log.d("xuezhiyuan","success");
                    NewShopListBean newShopListBean = response.body();
                    NewShopListBean.DataBean dataBean = newShopListBean.getData();
                    String json = gson.toJson(newShopListBean);
                    Log.d("xuezhiyuan",json);
                    FileSaveUtil fileSaveUtil = new FileSaveUtil();
                    fileSaveUtil.fileSave2SDCard(json,"shoplist.out");
                    fragmentData(dataBean);
                }else {
                    Log.d("xuezhiyuan","无数据返回");
                }
            }
            @Override
            public void onFailure(Call<NewShopListBean> call, Throwable t) {
                Log.d("xuezhiyuan","网络请求失败");
            }
        });
    }


    private void fragmentData(NewShopListBean.DataBean dataBean){
        int size = dataBean.getAdvertisementList().size();
        for(int i=0;i<size;i++){
            String type = dataBean.getAdvertisementList().get(i).getAdvertisementType();
            if("3".equals(type)){
                Glide.with(context).load(Urls.BASEIMAGEBEFORE+dataBean.getAdvertisementList().get(3).getResourceId()+Urls.BASEIMAGEAFTER).into(imageView);
            }
        }
        List<NewShopListBean.DataBean.ProductPagesBean> productPages = dataBean.getProductPages();
        if(productPages.size() > 5 || productPages.size() == 0){
            //弹出提示框
            Log.d("xuezhiyuan","数据超出范围");
        }else {
            switch (productPages.size()){
                case 1:{
                    List<NewShopListBean.DataBean.ProductPagesBean.ProductsBean> machineProductList = productPages.get(0).getProducts();
                    EmptyFragment fragment = new EmptyFragment(machineProductList);
                    titles.addAll(productPages);
                    fragments.add(fragment);
                    pagerAdapter.notifyDataSetChanged();
                }
                break;
                case 2:{
                    for(int i = 0;i<productPages.size();i++){
                        List<NewShopListBean.DataBean.ProductPagesBean.ProductsBean> machineProductList = productPages.get(i).getProducts();
                        EmptyFragment fragment = new EmptyFragment(machineProductList);
                        titles.addAll(productPages);
                        fragments.add(fragment);
                    }
                    pagerAdapter.notifyDataSetChanged();
                }
                break;
                case 3:{
                    for(int i = 0;i<productPages.size();i++){
                        List<NewShopListBean.DataBean.ProductPagesBean.ProductsBean> machineProductList = productPages.get(i).getProducts();
                        EmptyFragment fragment = new EmptyFragment(machineProductList);
                        titles.addAll(productPages);
                        fragments.add(fragment);
                    }
                    pagerAdapter.notifyDataSetChanged();
                }
                break;
                case 4:{
                    for(int i = 0;i<productPages.size();i++){
                        List<NewShopListBean.DataBean.ProductPagesBean.ProductsBean> machineProductList = productPages.get(i).getProducts();
                        EmptyFragment fragment = new EmptyFragment(machineProductList);
                        titles.addAll(productPages);
                        fragments.add(fragment);
                    }
                    pagerAdapter.notifyDataSetChanged();
                }
                break;
                case 5:{
                    for(int i = 0;i<productPages.size();i++){
                        List<NewShopListBean.DataBean.ProductPagesBean.ProductsBean> machineProductList = productPages.get(i).getProducts();
                        EmptyFragment fragment = new EmptyFragment(machineProductList);
                        titles.addAll(productPages);
                        fragments.add(fragment);
                    }
                    pagerAdapter.notifyDataSetChanged();
                }
                break;
            }
        }
    }
}
