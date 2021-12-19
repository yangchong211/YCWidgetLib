package com.yc.expandpager;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.lang.ref.WeakReference;


public abstract class ExpandPagerAdapter extends FragmentStatePagerAdapter {

    private WeakReference<Fragment> currentFragmentReference;

    public ExpandPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public Fragment getCurrentFragment() {
        if(currentFragmentReference != null){
            return currentFragmentReference.get();
        }
        return null;
    }

    @Override
    public void setPrimaryItem(@NonNull ViewGroup container,
                               int position, @NonNull Object object) {
        super.setPrimaryItem(container, position, object);
        if (getCurrentFragment() != object) {
            currentFragmentReference = new WeakReference<>((Fragment) object);
        }
    }

}
