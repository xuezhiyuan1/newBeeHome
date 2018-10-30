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
 * 静止状态下查询
 * Created by 17710890509 on 2018/7/13.
 */

public class OpenDoorLafterBoxState {

    public static SerialUtilOld serialUtilOld;

    public static String findMachineOrder(String port,int open,int close,int time) throws InterruptedException, IOException {
        BufferedWriter out;
        SimpleDateFormat formatter = new   SimpleDateFormat   ("yyyy年MM月dd日   HH:mm:ss");
        java.util.Date curDate = new java.util.Date(System.currentTimeMillis());
        String times = formatter.format(curDate);
        MakeFileUtils.getInstance().makeFilePath(Environment.getExternalStorageDirectory()+"/Vendor/Log/","staticState"+".txt");
        out = new BufferedWriter(new FileWriter(Environment.getExternalStorageDirectory()+"/Vendor/Log/"+"staticState.txt",true));
        //查询一次  初始化
        serialUtilOld = new SerialUtilOld(port,19200,0);
        //发送查询设备状态指令
        serialUtilOld.setData(ParamsSettingUtil.SEND_DATA_SHOP_STATE);
        //等待3s
        Thread.sleep(time);
        //获取数据
        byte[] dataByte = serialUtilOld.getDataByte();
        //截取数据
        String str = serialUtilOld.bytesToHexString(dataByte, dataByte.length);
        out.write(times+"=首次获取状态值："+str+"\r\n");
        //得到状态判断值
        String shipmentStatus = str.substring(open, close);
        Log.d("xuezhiyuan",shipmentStatus+"-haha");
        //进入判断
        if(shipmentStatus.equals("06")){
            boolean orderState = true;
            int num = 0;
            while (orderState) {
                serialUtilOld.setData(ParamsSettingUtil.SEND_DATA_SHOP_STATE);
                Thread.sleep(time);
                byte[] dataByte_while = serialUtilOld.getDataByte();
                String str_while = serialUtilOld.bytesToHexString(dataByte_while, dataByte_while.length);
                out.write(times+"=while获取状态值-静止："+str_while+"\r\n");
                String state = str_while.substring(open, close);
                Log.d("xuezhiyuan", state);
                if (state.equals("07")) {
                    Log.d("xuezhiyuan", "货仓已无商品可以关门");
                    orderState = false;
                }
                num ++;
                if(num == 10){
                    orderState = false;
                }
            }


            if(num == 10){
                out.flush(); // 把缓存区内容压入文件
                out.close(); // 最后记得关闭文件
                return "06";
            }
            out.flush(); // 把缓存区内容压入文件
            out.close(); // 最后记得关闭文件
            return "07";
        }else if(shipmentStatus.equals("07")){
            //货仓商品已取走
            out.flush(); // 把缓存区内容压入文件
            out.close(); // 最后记得关闭文件
            return "07";
        }else{
            //数据错位
            out.flush(); // 把缓存区内容压入文件
            out.close(); // 最后记得关闭文件
            return "08";
        }
    }
}
