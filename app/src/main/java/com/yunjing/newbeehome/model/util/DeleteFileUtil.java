package com.yunjing.newbeehome.model.util;

import android.os.Environment;

import java.io.File;

/**
 * 作者：zhiyuan Xue on 2018/9/4 10:01
 * 邮箱：xzy7319@sina.com
 */

public class DeleteFileUtil {
    /**
     * 删除已存储的文件
     */
    public static void deletefile(String fileName) {
        try {
            // 找到文件所在的路径并删除该文件
            File file = new File(Environment.getExternalStorageDirectory(), fileName);
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
