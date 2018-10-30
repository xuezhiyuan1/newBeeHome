package com.yunjing.newbeehome.oldmachine;

import android.util.Log;

import com.yunjing.newbeehome.protocol.ParamsSettingUtil;

import android_serialport_api.SerialUtilOld;


/**
 * 弹簧机状态查询
 * 作者：zhiyuan Xue on 2018/8/20 16:16
 * 邮箱：xzy7319@sina.com
 */


public class SpringMachineStateUtil {

    public static SerialUtilOld serialUtilOld;
    private static String state;

    public static String findSpringMachineOrder(String port,int open,int close) throws InterruptedException {
        //查询一次  初始化
        serialUtilOld = new SerialUtilOld(port,19200,0);
        //发送查询弹簧机设备状态指令
        serialUtilOld.setData(ParamsSettingUtil.SEND_SPRING_STATE_FIND);
        //等待3s
        Thread.sleep(3000);
        //获取数据
        byte[] dataByte = serialUtilOld.getDataByte();
        //截取数据
        String str = serialUtilOld.bytesToHexString(dataByte, dataByte.length);
        //得到状态判断值
        String shipmentStatus = str.substring(open, close);
        Log.d("xuezhiyuan",shipmentStatus+"查询结果---111111");
        //进入判断
        if(shipmentStatus.equals("01")){
            Log.d("xuezhiyuan",shipmentStatus+"查询结果---222222");
            boolean orderState = true;
            while (orderState){
                serialUtilOld.setData(ParamsSettingUtil.SEND_SPRING_STATE_FIND);
                Thread.sleep(3000);
                byte[] dataByte_while = serialUtilOld.getDataByte();
                String errorStr = serialUtilOld.bytesToHexString(dataByte_while, dataByte_while.length);
                Log.d("xuezhiyuan",errorStr+"-----------没有收到数据");
                if(errorStr.equals("3f")){
                    Thread.sleep(6000);
                    dataByte_while = serialUtilOld.getDataByte();
                    Log.d("xuezhiyuan",shipmentStatus+"错误数据等待6s后---222222");
                }
                String str_while = serialUtilOld.bytesToHexString(dataByte_while, dataByte_while.length);
                state = str_while.substring(open, close);
                Log.d("xuezhiyuan", state +"---333333");
                if(state.equals("00")){
                    Log.d("6767","成功");
                    orderState = false;
                }
                if(state.equals("02")){
                    Log.d("6767","出货超时");
                    orderState = false;
                }
                if(state.equals("03")){
                    Log.d("6767","机器故障");
                    orderState = false;
                }
            }
            return state;
        }else{
            //数据错位
            return "08";
        }
    }
}
