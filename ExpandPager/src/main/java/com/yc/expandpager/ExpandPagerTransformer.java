package com.yc.expandpager;

import android.os.Build;
import android.view.View;

import androidx.viewpager.widget.ViewPager;

/**
 * @author: 杨充
 * @email : yangchong211@163.com
 * @blog  : https://github.com/yangchong211/YCWidgetLib
 * @time : 2018/06/11
 * @desc : 转化
 * @revise :
 */
public class ExpandPagerTransformer implements ViewPager.PageTransformer {

    public static final float MAX_SCALE = 0.9f;
    public static final float MIN_SCALE = 0.8f;

    @Override
    public void transformPage(View page, float position) {
        position = position < -1 ? -1 : position;
        position = position > 1 ? 1 : position;
        float tempScale = position < 0 ? 1 + position : 1 - position;
        float slope = (MAX_SCALE - MIN_SCALE) / 1;
        float scaleValue = MIN_SCALE + tempScale * slope;
        page.setScaleX(scaleValue);
        page.setScaleY(scaleValue);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            page.getParent().requestLayout();
        }
    }
}
