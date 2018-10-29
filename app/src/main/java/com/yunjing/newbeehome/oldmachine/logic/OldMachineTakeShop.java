package com.yunjing.newbeehome.oldmachine.logic;

import android.util.Log;

import com.yunjing.newbeehome.base.Keys;
import com.yunjing.newbeehome.model.util.PropertiesUtils;
import com.yunjing.newbeehome.oldmachine.CloseDoorOrder;
import com.yunjing.newbeehome.oldmachine.OpenDoorLafterBoxState;
import com.yunjing.newbeehome.oldmachine.OpenDoorOrder;

import java.io.IOException;
import java.util.Properties;

/**
 * 作者：zhiyuan Xue on 2018/10/25 16:14
 * 邮箱：xzy7319@sina.com
 */

public class OldMachineTakeShop {

    static Properties prop = PropertiesUtils.propertiesUtils().properties(Keys.FILE_URI_PATH + Keys.FILE_NAME);
    static String machineCloseDoor;

    public static String takeShop(){
        try {
            String port = prop.getProperty("1");
            String machineOpenDoor = OpenDoorOrder.findMachineOrder(port, 12, 14, 1000);
            if(machineOpenDoor.equals("00")){
                //门锁已开  开始查询
                String machineBoxState = OpenDoorLafterBoxState.findMachineOrder(port, 26, 28, 3000);
                if(machineBoxState.equals("07")){
                    CloseDoorOrder.findMachineOrder(port, 12, 14, 1000);
                    machineCloseDoor = "07";
                }else if(machineBoxState.equals("06")){
                    CloseDoorOrder.findMachineOrder(port, 12, 14, 1000);
                    machineCloseDoor = "06";
                }else {
                    CloseDoorOrder.findMachineOrder(port, 12, 14, 1000);
                    machineCloseDoor = "08";
                }
            }else if(machineOpenDoor.equals("01")){
                //门锁指令错误
                machineCloseDoor = "08";
            }else if(machineOpenDoor.equals("08")){
                //数据错位
                machineCloseDoor = "08";
            }else {
                //ok
                machineCloseDoor = "08";
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("xuezhiyuan","yyy-"+machineCloseDoor);
        return machineCloseDoor;
    }
}
