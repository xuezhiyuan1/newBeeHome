package com.yunjing.newbeehome.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;
import com.yunjing.newbeehome.base.BaseFragment;
import com.yunjing.newbeehome.model.entity.NewShopListBean;

import java.util.List;


public class HomePagerAdapter extends FragmentPagerAdapter {

    private List<BaseFragment> fragments;
    private List<NewShopListBean.DataBean.ProductPagesBean> titles;
    public HomePagerAdapter(FragmentManager fm, List<BaseFragment> fragments, List<NewShopListBean.DataBean.ProductPagesBean> titles) {
        super(fm);
        this.fragments = fragments;
        this.titles = titles;
    }


    @Override
    public Fragment getItem(int position) {
        return fragments.get( position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position).getProductPageName();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
            //super.destroyItem(container, position, object);
    }
}
