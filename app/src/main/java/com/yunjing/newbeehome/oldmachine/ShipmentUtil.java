package com.yunjing.newbeehome.oldmachine;


import android.os.Environment;

import com.yunjing.newbeehome.model.util.MakeFileUtils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

import android_serialport_api.SerialUtilOld;


/**
 * 三代机出货工具类
 * Created by 17710890509 on 2018/6/26.
 */

public class ShipmentUtil {

    public static int Calibration;
    public static SerialUtilOld serialUtilOld;

    /**
     * 出货指令
     * @param xAxis 后置电机水平坐标
     * @param yAxis 后置电机垂直坐标
     * @param zAxis 前置电机垂直坐标
     * @param deliverySpeed 出货速度0 ~ 3000
     * @return
     */
    public static void sendShipMentOrder(String port,int xAxis,int yAxis,int zAxis,int deliverySpeed) throws InterruptedException, IOException {

        BufferedWriter out;
        SimpleDateFormat formatter = new   SimpleDateFormat   ("yyyy年MM月dd日   HH:mm:ss");
        java.util.Date curDate = new java.util.Date(System.currentTimeMillis());
        String time = formatter.format(curDate);
        MakeFileUtils.getInstance().makeFilePath(Environment.getExternalStorageDirectory()+"/Vendor/Log/","cloudMirrorShipmentReturn"+".txt");
        out = new BufferedWriter(new FileWriter(Environment.getExternalStorageDirectory()+"/Vendor/Log/"+"cloudMirrorShipmentReturn.txt",true));
        serialUtilOld = new SerialUtilOld(port,19200,0);
        synchronized (ShipmentUtil.class){
            //后置电机水平坐标
            byte[] byte_x_behind_shop = serialUtilOld.intToBytes(xAxis);
            //后置电机垂直坐标
            byte[] byte_y_behind_shop = serialUtilOld.intToBytes(yAxis);
            //前置电机垂直坐标
            byte[] byte_x_front_shop = serialUtilOld.intToBytes(zAxis);
            //出货速度
            byte[] byte_go_Shop_Sv = serialUtilOld.intToBytes(deliverySpeed);

            byte[] shipmentOrder = new byte[] {(byte) 0x7E, 0x13, 0x00,
                    (byte) 0x01,0x37,0x00,0x01,byte_x_front_shop[0],byte_x_front_shop[1],0x02,byte_y_behind_shop[0],byte_y_behind_shop[1],0x03,byte_x_behind_shop[0],byte_x_behind_shop[1],byte_go_Shop_Sv[0],byte_go_Shop_Sv[1],(byte)Calibration,(byte) 0xED};

            Calibration = 0;
            for(int a = 0;a < 17;a++){
                Calibration += shipmentOrder[a];
                Calibration = Calibration &0x00ff;
            }
            int Calibration_Value;
            Calibration_Value = Calibration;
            byte []SEND_DATA_SHOP_ORDER_OK = new byte[]{(byte) 0x7E, 0x13, 0x00,
                    (byte) 0x01,0x37,0x00,0x01,byte_x_front_shop[0],byte_x_front_shop[1],0x02,byte_y_behind_shop[0],byte_y_behind_shop[1],0x03,byte_x_behind_shop[0],byte_x_behind_shop[1],byte_go_Shop_Sv[0],byte_go_Shop_Sv[1],(byte)Calibration_Value,(byte) 0xED};
            serialUtilOld.setData(SEND_DATA_SHOP_ORDER_OK);
            Thread.sleep(500);
            byte[] dataByte = serialUtilOld.getData();
            String str = serialUtilOld.bytesToHexString(dataByte, dataByte.length);
            out.write(time+"=出货的返回："+str+"\r\n"+"\r\n");
            out.flush(); // 把缓存区内容压入文件
            out.close(); // 最后记得关闭文件
            //String shipmentStatus = str.substring(30, 32);
            //Log.d("xuezhiyuan",shipmentStatus+"指令执行成败的回复");
        }
    }
}
