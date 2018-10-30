package com.yunjing.newbeehome.activity;


import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.yunjing.newbeehome.R;
import com.yunjing.newbeehome.base.Keys;
import com.yunjing.newbeehome.base.Urls;
import com.yunjing.newbeehome.model.api.WelcomeApi;
import com.yunjing.newbeehome.model.entity.ShopListBean;
import com.yunjing.newbeehome.model.util.DeleteFileUtil;
import com.yunjing.newbeehome.model.util.FileSaveUtil;
import com.yunjing.newbeehome.model.util.MakeFileUtils;
import com.yunjing.newbeehome.model.util.ReadFileUtil;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Properties;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends Activity implements View.OnClickListener {


    //参数
    private String queue_name;
    //控件
    ImageView imageViewBig,imageViewBee,imageViewGoTo;
    //type
    private String type;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createFile(Keys.FILE_URI_PATH, Keys.FILE_NAME);
        writeFile();
        imageViewBig =  findViewById(R.id.image);
        imageViewBee =  findViewById(R.id.bee);
        imageViewGoTo =  findViewById(R.id.goTo);
        imageViewBig.setOnClickListener(this);
        imageViewBee.setOnClickListener(this);
        imageViewGoTo.setOnClickListener(this);
        try {
            loadData();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    //加载网络数据
    public void loadData() throws MalformedURLException {
        ReadFileUtil readFileUtil = new ReadFileUtil();
        String json = (String) readFileUtil.readObjFromSDCard("welcome");
        if(TextUtils.isEmpty(json)){
            NetGetRequest();
        }else {
            Gson gson = new Gson();
            ShopListBean shopListBean = gson.fromJson(json,ShopListBean.class);
            List<ShopListBean.DataBean> data = shopListBean.getData();
            for(int i=0;i<data.size();i++){
                type = data.get(i).getAdvertisementType();
                if("0".equals(type)){
                    //首页广告
                    Glide.with(MainActivity.this).load(Urls.BASEIMAGEBEFORE+data.get(0).getResourceId()+Urls.BASEIMAGEAFTER).into(imageViewBig);
                }else if("1".equals(type)){
                    //左下角
                    Glide.with(MainActivity.this).load(Urls.BASEIMAGEBEFORE+data.get(1).getResourceId()+Urls.BASEIMAGEAFTER).into(imageViewBee);
                }else if("2".equals(type)){
                    //右下角
                    Glide.with(MainActivity.this).load(Urls.BASEIMAGEBEFORE+data.get(2).getResourceId()+Urls.BASEIMAGEAFTER).into(imageViewGoTo);
                }
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.image:{
                in(ShopActivity.class);
            }
            break;
            case R.id.goTo:{
                in(ShopActivity.class);
            }
            break;
            case R.id.bee:{
                in(ShopActivity.class);
            }
            break;
        }
    }

    //跳转Activity
    public void in(Class tClass) {
        Intent intent = new Intent(this, tClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
        //finish();
    }

       //请求数据
     private void NetGetRequest(){
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(Urls.BASEURL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                final WelcomeApi welcomeApi = retrofit.create(WelcomeApi.class);
                Call<ShopListBean> call = welcomeApi.getData(Integer.parseInt(queue_name));
                call.enqueue(new Callback<ShopListBean>() {

                    @Override
                    public void onResponse(Call<ShopListBean> call, Response<ShopListBean> response) {
                        if(response.isSuccessful()){
                            //数据请求成功
                            ShopListBean shopListBean = response.body();
                            Gson gson = new Gson();
                            String json = gson.toJson(shopListBean);
                            Log.d("xuezhiyuan",json);
                            FileSaveUtil fileSaveUtil = new FileSaveUtil();
                            fileSaveUtil.fileSave2SDCard(json,"welcome.out");
                            Log.d("xuezhiyuan","保存完成");
                            List<ShopListBean.DataBean> data = shopListBean.getData();
                            for(int i=0;i<data.size();i++){
                                type = data.get(i).getAdvertisementType();
                                if("0".equals(type)){
                                    //首页广告
                                    Glide.with(MainActivity.this).load(Urls.BASEIMAGEBEFORE+data.get(0).getResourceId()+Urls.BASEIMAGEAFTER).into(imageViewBig);
                                }else if("1".equals(type)){
                                    //左下角
                                    Glide.with(MainActivity.this).load(Urls.BASEIMAGEBEFORE+data.get(1).getResourceId()+Urls.BASEIMAGEAFTER).into(imageViewBee);
                                }else if("2".equals(type)){
                                    //右下角
                                    Glide.with(MainActivity.this).load(Urls.BASEIMAGEBEFORE+data.get(2).getResourceId()+Urls.BASEIMAGEAFTER).into(imageViewGoTo);
                                }
                            }
                        }else {
                            Log.d("xuezhiyuan","无数据返回");
                        }
                    }

                    @Override
                    public void onFailure(Call<ShopListBean> call, Throwable t) {
                            Log.d("xuezhiyuan","网络请求失败");
                    }
                });
    }


    //下面这些要留着，如果没这里的这些判断，它会发生：不管你在百度那个网页里，一个返回键就全退出了。
    //现在是一个页面一个页面的退出，即返回上一页
    public boolean onKeyDown(int keyCode, KeyEvent event) {//捕捉返回键
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            ConfirmExit();//按了返回键，但已经不能返回，则执行退出确认
            Log.i("zys", "返回键");
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    //返回键 提示框推出程序
    public void ConfirmExit() {//退出确认
        android.app.AlertDialog.Builder ad = new android.app.AlertDialog.Builder(this);
        ad.setTitle("退出");
        ad.setMessage("是否退出蜂巢客户端?");
        ad.setPositiveButton("是", new DialogInterface.OnClickListener() {//退出按钮
            @Override
            public void onClick(DialogInterface dialog, int i) {
                Toast.makeText(MainActivity.this,"删除文件",Toast.LENGTH_SHORT).show();
                DeleteFileUtil.deletefile("welcome.out");
                DeleteFileUtil.deletefile("shoplist.out");
                // TODO Auto-generated method stub
                finish();//关闭activity
            }
        });
        ad.setNegativeButton("否", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                //不退出不用执行任何操作
            }
        });
        ad.show();//显示对话框
    }


    /**
     * 创建文件  文件夹
     *
     * @param filePath 文件路径
     * @param fileName 文件夹名字
     */
    private void createFile(String filePath, String fileName) {
        MakeFileUtils.getInstance().makeFilePath(filePath, fileName);
    }

    /**
     * 写入文件
     */
    private void writeFile() {
        Properties prop = new Properties();
        try {
            InputStream in = new BufferedInputStream(new FileInputStream(Keys.FILE_URI_PATH + Keys.FILE_NAME));
            prop.load(in);
            if (prop.isEmpty()) {
                FileOutputStream oFile = new FileOutputStream(Keys.FILE_URI_PATH + Keys.FILE_NAME, true);
                prop.setProperty("QUEUE_NAME", Keys.QUEUE_NAME+"");
                prop.setProperty("address", Keys.ADDRESS);
                prop.setProperty("userName", Keys.USERNAME);
                prop.setProperty("passWord", Keys.PASSWORD);
                prop.setProperty("virtualHost", Keys.VIRTUALHOST);
                prop.store(oFile, Keys.CONFIG);
                oFile.close();
            }
            queue_name = prop.getProperty("QUEUE_NAME");
            in.close();
        } catch (Exception e) {
            e.getMessage();
        }
    }
}
