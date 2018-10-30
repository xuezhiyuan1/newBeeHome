package com.yunjing.newbeehome.oldmachine.logic;

import android.util.Log;

import com.yunjing.newbeehome.base.Keys;
import com.yunjing.newbeehome.model.util.PropertiesUtils;
import com.yunjing.newbeehome.oldmachine.CloseDoorOrder;
import com.yunjing.newbeehome.oldmachine.FindMachineStateOrder;
import com.yunjing.newbeehome.oldmachine.OpenDoorLafterBoxState;
import com.yunjing.newbeehome.oldmachine.ShipmentUtil;
import java.io.IOException;
import java.util.Properties;

/**
 * 作者：zhiyuan Xue on 2018/10/23 17:08
 * 邮箱：xzy7319@sina.com
 */

public class OldMachineShipMent {

    static Properties prop = PropertiesUtils.propertiesUtils().properties(Keys.FILE_URI_PATH + Keys.FILE_NAME);
    static String machineCloseDoor;


    public static String shipment(int x,int y,int z,int deliverySpeed){
        try {
            String machineId = prop.getProperty("QUEUE_NAME");
            String port = prop.getProperty(machineId);
            //出货指令
            ShipmentUtil.sendShipMentOrder(port, x, y, z, deliverySpeed);
            //运动过程查询设备状态指令
            String machineFindOrder = FindMachineStateOrder.findMachineOrder(port, 26, 28);
            //Log.d("vbnm",machineFindOrder+"okm");
            if(machineFindOrder.equals("06")) {
                machineCloseDoor = "06";
            }else if(machineFindOrder.equals("a0")){
                //运动过程中a0  数据错位  推空
                machineCloseDoor = "09";
            }else {
                //运动过程中08  数据错位
                CloseDoorOrder.findMachineOrder(port, 12, 14, 1000);
                machineCloseDoor = "08";
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("xuezhiyuan","运动-静止："+machineCloseDoor);
        return machineCloseDoor;
    }

















    /*








    **/
}
