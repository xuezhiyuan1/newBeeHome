package com.yunjing.newbeehome.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by 17710890509 on 2018/8/13.
 */
/*
 * 在这个类的构造函数里面我们调用了父类的构造方法用来创建数据库文
 * 件，第二个构造方法只是为了方便构造（不用些那么多的参数）
 * 这个类继承了 SQLiteOpenHelper 类，并且重写了父类里面的onCreate方法和 onUpgrade方法，
 * onCreate方法当数据库文件不存在的时候会被调用来创建一个新的数
 * 据库文件
 */
public class MyHelper extends SQLiteOpenHelper {

    private static MyHelper helper;
    public static String CREATE_TABLE = "create table "+ DatabaseStatic.TABLE_NAME +"(" +
            DatabaseStatic.MACHINEID + " real, " +
            DatabaseStatic.NUMBER + " real, " +
            //设置主键自增 Integer primary key autoincrement
            DatabaseStatic.ID + " long, " +
            DatabaseStatic.SHOPID + " real)"; // 用于创建表的SQL语句
    private Context myContext = null;

    public MyHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DatabaseStatic.DATABASE_NAME, null, DatabaseStatic.DATABASE_VERSION);
    }

    public MyHelper(Context context) {
        super(context, DatabaseStatic.DATABASE_NAME, null, DatabaseStatic.DATABASE_VERSION);
        myContext = context;
    }

    //将自定义的数据库创建类单例。 synchronize单例 防止多线程同时那啥  双重锁定
    public static MyHelper getInstance(Context context) {
        if (helper == null) {
            synchronized (MyHelper.class) {
                if (helper == null)
                    helper = new MyHelper(context);//数据库名称为create_db。
            }
        }
        return helper;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.i("UseDatabase", "创建数据库");
        Toast.makeText(myContext, "创建数据库", Toast.LENGTH_SHORT).show();
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    //升级数据库
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //switch (oldVersion) {
        //   case 1:
        //        db.execSQL(CREATE_CUSTOMER);
        //       APP.mToast("更新数据库，旧版本是" + oldVersion);
        //   case 2:
        //       db.execSQL(CREATE_CUSTOMER);
        //        APP.mToast("更新数据库，旧版本是" + oldVersion);
        //    default:
        // }
        //sqLiteDatabase.execSQL("drop table if exists "+DatabaseStatic.TABLE_NAME); // 如果存在表Book，则删除该表
        //onCreate(sqLiteDatabase); // 重新调用onCreate()，创建表
    }
}
