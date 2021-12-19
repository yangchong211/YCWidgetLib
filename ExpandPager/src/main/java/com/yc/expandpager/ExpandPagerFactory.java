package com.yc.expandpager;

import android.app.Activity;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;


public final class ExpandPagerFactory {

    public static ExpandFragment getCurrentFragment(ViewPager viewPager){
        if (viewPager.getAdapter() instanceof ExpandPagerAdapter) {
            ExpandPagerAdapter adapter = (ExpandPagerAdapter) viewPager.getAdapter();
            Fragment fragment = adapter.getCurrentFragment();
            if (fragment instanceof ExpandFragment) {
                return (ExpandFragment)fragment;
            }
        }
        return null;
    }

    public static void setupViewPager(final ViewPager viewPager) {
        ViewGroup.LayoutParams layoutParams = viewPager.getLayoutParams();
        layoutParams.width = ((Activity) viewPager.getContext())
                .getWindowManager().getDefaultDisplay().getWidth() / 7 * 5;
        layoutParams.height = (int) ((layoutParams.width / 0.75));
        viewPager.setOffscreenPageLimit(2);
        if (viewPager.getParent() instanceof ViewGroup) {
            ViewGroup viewParent = ((ViewGroup) viewPager.getParent());
            viewParent.setClipChildren(false);
            viewPager.setClipChildren(false);
        }
        viewPager.setPageTransformer(true,
                new ExpandPagerTransformer());
    }

    public static boolean onBackPressed(ViewPager viewPager) {
        ExpandFragment expandingFragment = getCurrentFragment(viewPager);
        if(expandingFragment != null && expandingFragment.isOpenend()){
            expandingFragment.close();
            return true;
        }
        return false;
    }
}
