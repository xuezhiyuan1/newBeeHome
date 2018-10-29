package com.yunjing.newbeehome.oldmachine;

import android.util.Log;

import com.yunjing.newbeehome.protocol.ParamsSettingUtil;

import android_serialport_api.SerialUtilOld;


/**
 * 获取门限位状态
 * Created by 17710890509 on 2018/7/16.
 */

public class GetDoorLocationState {
    public static SerialUtilOld serialUtilOld;

    public static String findDoorStateOrder(String port,int open,int close) throws InterruptedException {
        //初始化串口
        serialUtilOld = new SerialUtilOld(port,19200,0);
        //发送获取门限位状态指令
        serialUtilOld.setData(ParamsSettingUtil.SEND_DOOR_STATE);
        //等待0.5s
        Thread.sleep(500);
        //得到数据
        byte[] dataByte = serialUtilOld.getDataByte();
        //截取数据
        String str = serialUtilOld.bytesToHexString(dataByte, dataByte.length);
        //打印获取门限位状态指令
        Log.d("xuezhiyuan",str+"获取门限位状态返回");
        //得到状态值
        String shipmentStatus = str.substring(open, close);
        Log.d("xuezhiyuan",shipmentStatus+"开门的返回截取");
        if(shipmentStatus.equals("00")){
            //限位未触发  门锁是关闭状态
            return "00";
        }else if(shipmentStatus.equals("01")){
            //限位触发    门锁是开启状态
            return "01";
        }else {
            //数据错位
            return "08";
        }
    }
}
