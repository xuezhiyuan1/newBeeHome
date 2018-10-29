package com.yunjing.newbeehome.model.util;

import java.io.File;

/**
 * Created by 17710890509 on 2018/4/14.
 */

public class MakeFileUtils {

    private static MakeFileUtils instance = null;
    private File file;
    private MakeFileUtils(){}
    public static synchronized MakeFileUtils getInstance(){
        if(instance == null){
            instance = new MakeFileUtils();
        }
        return instance;
    }

    //创建文件夹
    public void createPath(String path){
        file = new File(path);
        if(!file.exists()){
            file.mkdirs();
        }
    }

    //创建文件
    public File makeFilePath(String filePath,String fileName){
        file = null;
        createPath(filePath);
        try {
            file = new File(filePath+fileName);
            if(!file.exists()){
                file.createNewFile();
            }
        }catch (Exception e){
            e.getMessage();
        }
        return file;
    }
}
