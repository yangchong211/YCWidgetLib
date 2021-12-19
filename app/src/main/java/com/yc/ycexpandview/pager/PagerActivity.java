package com.yc.ycexpandview.pager;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.transition.Explode;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.viewpager.widget.ViewPager;

import com.yc.expandpager.ExpandFragment;
import com.yc.expandpager.ExpandPagerFactory;
import com.yc.expandpager.OnExpandClickListener;
import com.yc.ycexpandview.R;

import java.util.ArrayList;
import java.util.List;

public class PagerActivity extends AppCompatActivity implements OnExpandClickListener {

    private RelativeLayout back;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        back = findViewById(R.id.back);
        viewPager = findViewById(R.id.viewPager);
        setupWindowAnimations();
        MyViewPagerAdapter adapter = new MyViewPagerAdapter(getSupportFragmentManager());
        adapter.addAll(generateTravelList());
        viewPager.setAdapter(adapter);


        ExpandPagerFactory.setupViewPager(viewPager);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                ExpandFragment expandingFragment = ExpandPagerFactory.getCurrentFragment(viewPager);
                if(expandingFragment != null && expandingFragment.isOpenend()){
                    expandingFragment.close();
                }
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        if(!ExpandPagerFactory.onBackPressed(viewPager)){
            super.onBackPressed();
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setupWindowAnimations() {
        Explode slideTransition = new Explode();
        getWindow().setReenterTransition(slideTransition);
        getWindow().setExitTransition(slideTransition);
    }

    private List<PagerBean> generateTravelList(){
        List<PagerBean> travels = new ArrayList<>();
        for(int i=0;i<5;++i){
            travels.add(new PagerBean("图片3", R.drawable.image3));
            travels.add(new PagerBean("图片4", R.drawable.image4));
            travels.add(new PagerBean("图片1", R.drawable.image1));
            travels.add(new PagerBean("图片2", R.drawable.image2));
        }
        return travels;
    }
    @SuppressWarnings("unchecked")
    private void startInfoActivity(View view, PagerBean travel) {
        Activity activity = this;
        ActivityCompat.startActivity(activity,
                InfoActivity.newInstance(activity, travel),
                ActivityOptionsCompat.makeSceneTransitionAnimation(
                        activity)
                        .toBundle());
    }

    @Override
    public void onExpandClick(View v) {
        View view = v.findViewById(R.id.image);
        PagerBean travel = generateTravelList().get(viewPager.getCurrentItem());
        startInfoActivity(view,travel);
    }
}
