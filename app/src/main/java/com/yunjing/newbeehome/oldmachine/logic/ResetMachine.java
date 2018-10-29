package com.yunjing.newbeehome.oldmachine.logic;

import android.os.Environment;
import android.util.Log;

import com.yunjing.newbeehome.model.util.PropertiesUtils;
import com.yunjing.newbeehome.protocol.ParamsSettingUtil;

import java.util.Properties;
import java.util.concurrent.locks.Lock;

import android_serialport_api.SerialUtilOld;

/**
 * 复位
 * 作者：zhiyuan Xue on 2018/10/29 17:51
 * 邮箱：xzy7319@sina.com
 */

public class ResetMachine {
    public static SerialUtilOld serialUtilOld;

    public static void resetMachine(String port) throws InterruptedException {
        //初始化串口
        serialUtilOld = new SerialUtilOld(port, 19200, 0);
        //发送开门指令
        serialUtilOld.setData(ParamsSettingUtil.SEND_DATA_RESET);
        //等待0.5s
        Thread.sleep(500);
        //得到数据
        byte[] dataByte = serialUtilOld.getDataByte();
        Log.d("xuezhiyuan",dataByte.toString());
    }
}
