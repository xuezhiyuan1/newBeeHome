package com.yunjing.newbeehome.oldmachine;

import android.os.Environment;
import android.util.Log;

import com.yunjing.newbeehome.model.util.MakeFileUtils;
import com.yunjing.newbeehome.protocol.ParamsSettingUtil;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

import android_serialport_api.SerialUtilOld;


/**
 * 动态查询设备状态指令
 * Created by 17710890509 on 2018/7/12.
 */

public class FindMachineStateOrder {

    public static SerialUtilOld serialUtilOld;
    private static String state;
    private static String str_while;

    public static String findMachineOrder(String port,int open,int close) throws InterruptedException, IOException {

        BufferedWriter out;
        SimpleDateFormat formatter = new   SimpleDateFormat   ("yyyy年MM月dd日   HH:mm:ss");
        java.util.Date curDate = new java.util.Date(System.currentTimeMillis());
        String time = formatter.format(curDate);
        MakeFileUtils.getInstance().makeFilePath(Environment.getExternalStorageDirectory()+"/Vendor/Log/","motionState"+".txt");
        out = new BufferedWriter(new FileWriter(Environment.getExternalStorageDirectory()+"/Vendor/Log/"+"motionState.txt",true));
        //查询一次  初始化
        serialUtilOld = new SerialUtilOld(port,19200,0);
        //发送查询设备状态指令
        serialUtilOld.setData(ParamsSettingUtil.SEND_DATA_SHOP_STATE);
        //等待3s
        Thread.sleep(3000);
        //获取数据
        byte[] dataByte = serialUtilOld.getDataByte();
        //截取数据
        String str = serialUtilOld.bytesToHexString(dataByte, dataByte.length);
        out.write(time+"=首次获取状态值："+str+"\r\n");
        //得到状态判断值
        String shipmentStatus = str.substring(open, close);
        Log.d("xuezhiyuan",shipmentStatus+"查询结果---111111");
        //进入判断
        if(shipmentStatus.equals("02") || shipmentStatus.equals("03") || shipmentStatus.equals("05")){
            Log.d("xuezhiyuan",shipmentStatus+"查询结果---222222");
            boolean orderState = true;
            while (orderState){
                serialUtilOld.setData(ParamsSettingUtil.SEND_DATA_SHOP_STATE);
                Thread.sleep(3000);
                byte[] dataByte_while = serialUtilOld.getDataByte();
                String errorStr = serialUtilOld.bytesToHexString(dataByte_while, dataByte_while.length);
                out.write(time+"=while获取状态值："+errorStr+"\r\n");
                Log.d("xuezhiyuan",errorStr+"-----------没有收到数据");
                if(errorStr.equals("3f")){
                    Thread.sleep(6000);
                    dataByte_while = serialUtilOld.getDataByte();
                    str_while = serialUtilOld.bytesToHexString(dataByte_while, dataByte_while.length);
                    out.write(time+"=while获取状态值2："+str_while+"\r\n");
                    Log.d("xuezhiyuan",shipmentStatus+"错误数据等待6s后---222222");
                }else {
                    str_while = errorStr;
                }
                state = str_while.substring(open, close);
                Log.d("xuezhiyuan", state +"---333333");
                if(state.equals("06")){
                    Log.d("xuezhiyuan","成功");
                    orderState = false;
                }
                if(state.equals("00")){
                    Log.d("xuezhiyuan","红外检测出错");
                    orderState = false;
                }
                if(state.equals("09")){
                    Log.d("xuezhiyuan","用户已经将商品拿走");
                    orderState = false;
                }
                if(state.equals("a0")){
                    Log.d("xuezhiyuan","推空超时返回");
                    orderState = false;
                }
            }
            out.flush(); // 把缓存区内容压入文件
            out.close(); // 最后记得关闭文件
            return state;
        }else{
            //数据错位
            out.flush(); // 把缓存区内容压入文件
            out.close(); // 最后记得关闭文件
            return "08";
        }
    }
}
