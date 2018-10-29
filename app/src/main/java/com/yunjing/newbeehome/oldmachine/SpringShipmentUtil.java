package com.yunjing.newbeehome.oldmachine;

import android.util.Log;

import android_serialport_api.SerialUtilOld;


/**
 * 弹簧机出货工具类
 * 作者：zhiyuan Xue on 2018/8/20 16:02
 * 邮箱：xzy7319@sina.com
 */


public class SpringShipmentUtil {

    public static int Calibration;
    public static SerialUtilOld serialUtilOld;

    /**
     *
     * @param port 串口号
     * @param axis  前置坐标
     * @param lineNumber  行号
     * @param columnNumber  列号
     * @throws InterruptedException
     */
    public static void sendSpringShipMentOrder(String port,int axis,int lineNumber,int columnNumber) throws InterruptedException {

        serialUtilOld = new SerialUtilOld(port,19200,0);
        synchronized (ShipmentUtil.class){
            //行号  a-f
            //byte[] cloumu_number = serialUtilOld.intToBytes(columnNumber);
            //列号  0—9
            //byte[] line_number = serialUtilOld.intToBytes(lineNumber);
            //前置电机垂直坐标
            byte[] xAxis = serialUtilOld.intToBytes(axis);

            byte[] shipmentOrder = new byte[] {(byte) 0x7E, 0x11, 0x00,
                    (byte) 0x01,0x60,0x00,0x01,xAxis[0],xAxis[1],0x02,0x00, (byte) columnNumber,0x03,0x00, (byte) lineNumber,(byte)Calibration,(byte) 0xED};

            Calibration = 0;
            for(int a = 0;a < 15;a++){
                Calibration += shipmentOrder[a];
                Calibration = Calibration &0x00ff;
            }

            int Calibration_Value;
            Calibration_Value = Calibration;
            byte []SEND_DATA_SHOP_ORDER_OK = new byte[]{(byte) 0x7E, 0x11, 0x00,
                    (byte) 0x01,0x60,0x00,0x01,xAxis[0],xAxis[1],0x02,0x00, (byte) columnNumber,0x03,0x00, (byte) lineNumber,(byte)Calibration_Value,(byte) 0xED};
            serialUtilOld.setData(SEND_DATA_SHOP_ORDER_OK);
            Thread.sleep(500);
            byte[] dataByte = serialUtilOld.getData();
            String str = serialUtilOld.bytesToHexString(dataByte, dataByte.length);
            Log.d("xuezhiyuan",str+"出货的返回");
        }
    }
}

