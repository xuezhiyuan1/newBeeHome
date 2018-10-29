package com.yunjing.newbeehome.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.Map;


public abstract class BaseFragment extends Fragment implements View.OnClickListener{

    protected Bundle bundle;
    private ProgressDialog progressDialog;

    protected Map<String,String> params;

    //fragment是否第一次加载
    protected boolean isFirstLoad = true;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return  inflater.inflate(getLayoutId(),container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.params = new HashMap<>();
        initView(view);
        initData();
        loadData();

    }

    @Override
    public void onResume() {
        super.onResume();
        dismiss();
        if (isFirstLoad)
            isFirstLoad = false;
    }

    /**
     * 布局id
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 初始化View
     */
    protected abstract void initView(View view);


    /**
     * 初始化数据
     */
    protected abstract void initData();


    /**
     * 加载数据（网络数据、本地数据（sharedPreference、database 、读取sdk文件））
     */
    protected abstract void loadData();

    /**
     * 页面切换传递参数
     * @param bundle
     */
    public void setParams(Bundle bundle){
        this.bundle = bundle;
    }

    /**
     * 显示等待进度条
     */
    protected void showProgressBar(){
        progressDialog = new ProgressDialog(this.getActivity());
        progressDialog.setMessage("正在加载中，请稍等......");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    /**
     * 关闭等待进度条
     */
    protected void dismiss(){
        if(progressDialog != null && progressDialog.isShowing())
            progressDialog.dismiss();
    }


}
