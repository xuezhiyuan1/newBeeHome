package com.yunjing.newbeehome.model.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 17710890509 on 2018/8/13.
 */

public class DBManager {

    private int shopSum = 0;
    private SQLiteDatabase db;
    private  MyHelper helper;
    private Context context;

    public DBManager(Context context){
        this.context = context;
        helper = MyHelper.getInstance(context);
        db = this.helper.getWritableDatabase();
    }

    public void createDatabase() // 创建或者打开数据库
    {
        helper = new MyHelper(context);

  /*
   * 调用getWritabelDatabase方法或者
   * getReadableDatabase方法时，如果数据库文
   * 件中不存在（注意一个数据库中可以存在多个表格），
   * 那么会回调MyHelper类的onCreate方法新建一个数据库文
   * 件并且在这个数据库文件中新建一
   * 个book表格
   */
        helper.getWritableDatabase();
    }

    /**
     * 插入数据
     * @param shopId 商品ID
     * @param number 商品库存
     * @param machineId  机器ID
     */
    public void insertDatabase(int shopId,int number,int machineId) // 向数据库中插入新数据
    {
        if(helper == null)
        {
            helper = new MyHelper(context);
        }
        db = helper.getWritableDatabase();
        ContentValues cV = new ContentValues();
        cV.put(DatabaseStatic.MACHINEID, machineId);
        cV.put(DatabaseStatic.ID, ++shopSum);
        cV.put(DatabaseStatic.SHOPID, shopId);
        cV.put(DatabaseStatic.NUMBER,number);
        db.insert(DatabaseStatic.TABLE_NAME, null, cV);
        Toast.makeText(context, "插入数据成功", Toast.LENGTH_SHORT).show();
    }

    //更新数据
    public void updateDatabase(int number,int shopId) // 更新数据
    {
        if(helper == null)
        {
            helper = new MyHelper(context);
        }
        db = helper.getWritableDatabase();

        ContentValues cV = new ContentValues();
        cV.put(DatabaseStatic.NUMBER, number);
        db.update(DatabaseStatic.TABLE_NAME, cV, DatabaseStatic.SHOPID + "= ?", new String[]{String.valueOf(shopId)});

        Toast.makeText(context, "数据更新成功", Toast.LENGTH_SHORT).show();
    }

    //暂不修改
    public void deleteDatabase(String time) // 数据库中删除数据
    {
        if(helper == null)
        {
            helper = new MyHelper(context);
        }
        db = helper.getWritableDatabase();

  /*
   * 调用 delete 方法删除数据库中的数据
   * 对应的SQL语句：
   * database.execSQL("delete from " +
   * DatabaseStatic.TABLE_NAME + " where " +
   * DatabaseStatic.BOOK_NAME + " = ?", new
   * String[]{"C Language"});
   */
        db.delete(DatabaseStatic.TABLE_NAME, DatabaseStatic.MACHINEID + " = ? ",
                new String[]{time});

        Toast.makeText(context, "数据删除成功", Toast.LENGTH_SHORT).show();
    }

    // 表名
    // 查询字段
    // 查询条件
    // 满足查询的值
    // 分组
    // 分组筛选关键字
    // 排序
    public DbShopInfoBean searchList(int shopId){
        if(helper == null)
        {
            helper = new MyHelper(context);
        }
        db = helper.getWritableDatabase();
        Cursor cursor = db.query(DatabaseStatic.TABLE_NAME,new String[]{DatabaseStatic.NUMBER,DatabaseStatic.MACHINEID},DatabaseStatic.SHOPID + " = ?",
                new String[]{String.valueOf(shopId)},null, null,null);
        DbShopInfoBean dbShopInfoBean = new DbShopInfoBean();
        while (cursor.moveToNext()){
            dbShopInfoBean.setNumber(Integer.parseInt(cursor.getString(cursor.getColumnIndex(DatabaseStatic.NUMBER))));
            dbShopInfoBean.setMachineId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(DatabaseStatic.MACHINEID))));
        }
        return dbShopInfoBean;
    }

    public List<String> searchDatabase() // 查询数据库中的数据
    {
        if(helper == null)
        {
            helper = new MyHelper(context);
        }
        db = helper.getWritableDatabase();
  /*
   * 调用database的query方法，第一个参数是要查询的表名，
   * 后面的参数是一些查询的约束条件，对应于SQL语句的一些参
   * 数， 这里全为null代表查询表格中所有的数据
   * 查询的结果返回一个 Cursor对象
   * 对应的SQL语句：
   * Cursor cursor = database.rawQuery("select * from book", null);
   */
        Cursor cursor = db.query(DatabaseStatic.TABLE_NAME, null, null, null, null, null, null);
        List<String> data = new ArrayList<>();
        StringBuilder str = new StringBuilder();
        if(cursor.moveToFirst()) {
            // 显示数据库的内容
            for(; !cursor.isAfterLast(); cursor.moveToNext()) // 获取查询游标中的数据
            {
                str.append(cursor.getString(cursor.getColumnIndex(DatabaseStatic.ID)) + " ");
                str.append(cursor.getString(cursor.getColumnIndex(DatabaseStatic.MACHINEID)) + " ");
                str.append(cursor.getString(cursor.getColumnIndex(DatabaseStatic.SHOPID)) + " ");
                data.add(cursor.getString(cursor.getColumnIndex(DatabaseStatic.NUMBER)));
            }
        }
        cursor.close(); // 记得关闭游标对象
        if(str.toString().equals("")) {
            str.append("数据库为空！");
        } else {
            Log.d("xuezhiyuan",str.toString());
            //Toast.makeText(context,str.toString(),Toast.LENGTH_SHORT).show();
            str.append("数据库有数据！");
        }
        return data;
    }


    public void closeDb(){
        db.close();
    }

}
