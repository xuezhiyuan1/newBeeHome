package com.yunjing.newbeehome.oldmachine;

import android.util.Log;

import com.yunjing.newbeehome.protocol.ParamsSettingUtil;

import android_serialport_api.SerialUtilOld;


/**
 * 开门指令
 * Created by 17710890509 on 2018/7/12.
 */

public class OpenDoorOrder {

    public static SerialUtilOld serialUtilOld;

    public static String findMachineOrder(String port,int open,int close,int time) throws InterruptedException {
        //初始化串口
        serialUtilOld = new SerialUtilOld(port,19200,0);
        //发送开门指令
        serialUtilOld.setData(ParamsSettingUtil.SEND_DOOR_LOCK_OPEN);
        //等待0.5s
        Thread.sleep(time);
        //得到数据
        byte[] dataByte = serialUtilOld.getDataByte();
        //截取数据
        String str = serialUtilOld.bytesToHexString(dataByte, dataByte.length);
        //打印开门指令
        Log.d("xuezhiyuan",str+"开门的返回");
        //得到状态值
        String shipmentStatus = str.substring(open, close);
        Log.d("xuezhiyuan",shipmentStatus+"开门的返回截取");
        if(shipmentStatus.equals("00")){
            //门锁已开
            return "00";
        }else if(shipmentStatus.equals("01")){
            //门锁已关
            return "01";
        }else {
            //数据错位
            return "08";
        }
    }
}
