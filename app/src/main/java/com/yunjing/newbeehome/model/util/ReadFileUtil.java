package com.yunjing.newbeehome.model.util;

import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * 作者：zhiyuan Xue on 2018/8/31 14:33
 * 邮箱：xzy7319@sina.com
 */

public class ReadFileUtil {
    public Object readFileFromLocal(String fileName) {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            fileInputStream = new FileInputStream(fileName + ".out");
            objectInputStream = new ObjectInputStream(fileInputStream);
            Object obj = objectInputStream.readObject();

            return obj;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileInputStream != null) fileInputStream.close();
                if (objectInputStream != null) objectInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public Object readObjFromSDCard(String fileName) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Object obj;
            File sdCardDir = Environment.getExternalStorageDirectory(); //获取SDCard目录
            File sdFile = new File(sdCardDir, fileName + ".out");
            FileInputStream fis = null;
            ObjectInputStream ois = null;
            try {
                fis = new FileInputStream(sdFile); //获得输入流
                ois = new ObjectInputStream(fis);
                obj = ois.readObject();
                return obj;
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (fis != null) fis.close();
                    if (ois != null) ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
