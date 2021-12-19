package com.yc.ycexpandview.pager;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.yc.expandpager.ExpandPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MyViewPagerAdapter extends ExpandPagerAdapter {

    List<PagerBean> travels;

    public MyViewPagerAdapter(FragmentManager fm) {
        super(fm);
        travels = new ArrayList<>();
    }

    public void addAll(List<PagerBean> travels){
        this.travels.addAll(travels);
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        PagerBean travel = travels.get(position);
        return MyExpandingFragment.newInstance(travel);
    }

    @Override
    public int getCount() {
        return travels.size();
    }

}
