package com.yunjing.newbeehome.model.util;

import android.speech.tts.TextToSpeech;
import android.util.Log;

import java.util.Locale;

import static com.yunjing.newbeehome.base.App.context;

/**
 * 作者：zhiyuan Xue on 2018/10/29 14:31
 * 邮箱：xzy7319@sina.com
 */

public class TtsUtil {

    private static TtsUtil instance = null;
    private TtsUtil(){}
    private TextToSpeech tts;

    public static synchronized TtsUtil getInstance(){
        if(instance == null){
            instance = new TtsUtil();
        }
        return instance;
    }

    public TextToSpeech instanceTts(){
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
        return tts;
    }



}
