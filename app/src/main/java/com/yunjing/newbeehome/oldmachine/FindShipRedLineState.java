package com.yunjing.newbeehome.oldmachine;


import android.util.Log;

import com.yunjing.newbeehome.protocol.ParamsSettingUtil;

import android_serialport_api.SerialUtilOld;


/**
 *  查询货仓红外状态
 * Created by 17710890509 on 2018/7/12.
 */

public class FindShipRedLineState {

    public static SerialUtilOld serialUtilOld;

    public static String findMachineRedLineOrder(String port,int open,int close) throws InterruptedException {
        //查询一次  初始化
        serialUtilOld = new SerialUtilOld(port,19200,0);
        //发送查询货仓红外状态指令
        serialUtilOld.setData(ParamsSettingUtil.SEND_REDLINE_STATE);
        //等待0.5s
        Thread.sleep(500);
        //获取数据
        byte[] dataByte = serialUtilOld.getDataByte();
        //截取数据
        String str = serialUtilOld.bytesToHexString(dataByte, dataByte.length);
        Log.d("xuezhiqian",str);
        if("3f".equals(str)){
            return "09";
        }
        //得到状态判断值
        String shipmentStatus = str.substring(open, close);
        //进入判断
        if(shipmentStatus.equals("00")){
            //无货
            return "00";
        }else if(shipmentStatus.equals("01")){
            //有货
            return "01";
        }else{
            //数据错位
            return "08";
        }
    }
}
