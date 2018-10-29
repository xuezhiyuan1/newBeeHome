package com.yunjing.newbeehome.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import com.yunjing.newbeehome.R;
import com.yunjing.newbeehome.base.BaseActivity;
import com.yunjing.newbeehome.oldmachine.logic.ResetMachine;

import java.util.Locale;

import static com.yunjing.newbeehome.base.App.context;

/**
 * 作者：zhiyuan Xue on 2018/10/29 15:01
 * 邮箱：xzy7319@sina.com
 */

public class ErrorActivity extends BaseActivity {


    private TextToSpeech tts;

    @Override
    protected void layoutId() {
        setContentView(R.layout.error_page);
    }

    @Override
    protected void initView() {
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
    protected void initData(){
        tts.speak("设备正在重置，请稍后", TextToSpeech.QUEUE_ADD,
                null);
    }

    @Override
    protected void initListener() {
          new Thread(new Runnable() {
              @Override
              public void run() {
                  try {
                      ResetMachine.resetMachine("com3");
                  } catch (InterruptedException e) {
                      e.printStackTrace();
                  }
              }
          }).start();
    }

    @Override
    protected void loadData() {
        in(MainActivity.class);
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
    protected void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
            Log.d("TAG", "TTS Destroyed");
        }
        super.onDestroy();
    }
}
