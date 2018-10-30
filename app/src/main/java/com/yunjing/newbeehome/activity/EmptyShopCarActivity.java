package com.yunjing.newbeehome.activity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.yunjing.newbeehome.R;
import com.yunjing.newbeehome.base.BaseActivity;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 作者：zhiyuan Xue on 2018/10/22 15:26
 * 邮箱：xzy7319@sina.com
 */

public class EmptyShopCarActivity extends BaseActivity implements View.OnClickListener {
    private Timer timer = new Timer();
    private int time = 120;
    private TextView textViewValue;
    private LinearLayout linBack,shopCarLin;
    private Button backBtn;
    private ImageView imageView;

    @Override
    protected void layoutId() {
        setContentView(R.layout.empty_shopping_car);
        timer.schedule(task, time, 1000);
    }

    @Override
    protected void initView() {
        imageView = findViewById(R.id.shopCar_image);
        backBtn = findViewById(R.id.back_list_shop);
        textViewValue = findViewById(R.id.value_Text);
        linBack = findViewById(R.id.back_Text);
        shopCarLin = findViewById(R.id.line_shopCar);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        linBack.setOnClickListener(this);
        backBtn.setOnClickListener(this);
        imageView.setOnClickListener(this);
    }

    @Override
    protected void loadData() {

    }

    @Override
    public void in(Class tClass) {

    }

    TimerTask task = new TimerTask() {
        @Override public void run() {
            runOnUiThread(new Runnable() {
                @Override public void run() {
                    time--;
                    textViewValue.setText(String.valueOf(time)+"s");
                    textViewValue.setEnabled(false);
                    if (time <= 0) {
                        out();
                    }
                }
            });
        }
    };


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

    public void out() {
        finish();
        overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_Text: {
                stopTimer();
                out();
            }
            break;
            case R.id.back_list_shop: {
                stopTimer();
                execShell("am broadcast -a android.intent.action.hidenavigation --ez enable false" +
                        "\n");
                out();
            }
            break;
            case R.id.shopCar_image:{
                execShell("am broadcast -a android.intent.action.hidenavigation --ez enable true" +
                        "\n");
            }
            break;
        }
    }



    public void execShell(String cmd){
        try{
            //权限设置
            Process p = Runtime.getRuntime().exec("su");
            //获取输出流
            OutputStream outputStream = p.getOutputStream();
            DataOutputStream dataOutputStream=new DataOutputStream(outputStream);
            //将命令写入
            dataOutputStream.writeBytes(cmd);
            //提交命令
            dataOutputStream.flush();
            //关闭流操作
            dataOutputStream.close();
            outputStream.close();
        }
        catch(Throwable t)
        {
            t.printStackTrace();
        }
    }



}
