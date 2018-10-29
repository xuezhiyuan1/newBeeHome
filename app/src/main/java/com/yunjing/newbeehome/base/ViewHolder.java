package com.yunjing.newbeehome.base;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yunjing.newbeehome.R;

import java.util.HashMap;
import java.util.Map;


public class ViewHolder {

    //ViewHolder只需要创建一个对象 所以该ViewHolder是单例的

    //加载的item布局
    private View convertView;
    //key就是viewId values 就是id对应的控件（可以是TextView、ImageView、Button、LinearLayout...）
    private Map<Integer,View> views;

    private Context context;

    //第一步 构造函数私有化
    private ViewHolder(Context context, int layoutId){
        this.views = new HashMap<>();
        this.context = context;
        this.convertView = LayoutInflater.from(context).inflate(layoutId,null);
        this.convertView.setTag(this);
    }

    /**
     * 第二步 提供一个静态的公共的方法 该方法的返回值就是ViewHolder对象
     * @param context 实例化LayoutInflater所需要的参数
     * @param convertView viewHolder的优化所必须的
     * @param layoutId 加载的布局的d
     * @return
     */
    public static ViewHolder getInstance(Context context, View convertView, int layoutId){
        if(convertView == null)
            return new ViewHolder(context,layoutId);
        return (ViewHolder) convertView.getTag();
    }


    public View getConvertView() {
        return convertView;
    }

    /**
     * 根据id查找view 并且给view缓存到map集合中
     * @param viewId
     * @param <T>
     * @return
     */
    private <T extends View> T findViewById(int viewId){

        //先从内存（Map集合）中查找view是否已经存在 没有的话就findviewByid加载 并存储到集合中 否则直接从集合中取出 return 返回

        View view = views.get(viewId);
        if(view == null){
            view = convertView.findViewById(viewId);
            views.put(viewId,view);
        }
        return (T)view;
    }

    /**
     * 给Textview赋值
     * @param viewId
     * @param text
     */
    public ViewHolder setText(int viewId,String text){

        TextView tv = findViewById(viewId);
        if (tv != null)
            tv.setText(text);
        return  this;
    }

    /**
     * 加载网络图片
     * @param viewId
     * @param imgUrl
     */
    public ViewHolder setImage(int viewId,String imgUrl){

        ImageView img = findViewById(viewId);
        if(img != null)
            Glide.with(context)
                    .load(Urls.BASEIMAGEBEFORE+imgUrl+Urls.BASEIMAGEAFTER)
                    .placeholder(R.drawable.bee)
                    .crossFade()
                    .into(img);
        return this;
    }

}
