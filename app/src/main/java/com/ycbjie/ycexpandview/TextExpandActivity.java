package com.ycbjie.ycexpandview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ycbjie.expandlib.ExpandLayout;
import com.ycbjie.expandlib.LogUtils;

/**
 * @author yc
 */
public class TextExpandActivity extends AppCompatActivity {

    private ExpandLayout expand;
    private TextView tv;
    private LinearLayout llTagBtn;
    private ImageView ivExpand;
    private int measuredHeight;
    private static final int TAG_LAYOUT_FINISH = 521;
    private int twoLineTagHeight;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case TAG_LAYOUT_FINISH:
                    //这个是两行的高度，实际开发中看UI设置的高度，这里仅仅是假设操作
                    twoLineTagHeight = dip2px(TextExpandActivity.this, 54.0f);
                    if (measuredHeight> twoLineTagHeight){
                        //如果大于两行，则显示折叠
                        llTagBtn.setVisibility(View.VISIBLE);
                        expand.initExpand(false ,twoLineTagHeight);
                    }else {
                        //如果小于或者等于两行，则不显示折叠控件
                        llTagBtn.setVisibility(View.GONE);
                    }
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_expand);

        initView();
        initListener();
        initExpend();
    }


    private void initView() {
        expand = findViewById(R.id.expand);
        tv = findViewById(R.id.tv);
        llTagBtn = findViewById(R.id.ll_tag_btn);
        ivExpand = findViewById(R.id.iv_expand);
    }


    private void initListener() {
        llTagBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.d("点击事件");
                expand.toggleExpand();
            }
        });
        //设置动画时间
        expand.setAnimationDuration(300);
        //折叠或者展开操作后的监听
        expand.setOnToggleExpandListener(new ExpandLayout.OnToggleExpandListener() {
            @Override
            public void onToggleExpand(boolean isExpand) {
                if (isExpand){
                    ivExpand.setBackgroundResource(R.mipmap.icon_btn_collapse);
                }else {
                    ivExpand.setBackgroundResource(R.mipmap.icon_btn_expand);
                }
            }
        });
    }

    private void initExpend() {
        tv.post(new Runnable() {
            @Override
            public void run() {
                measuredHeight = tv.getMeasuredHeight();
                LogUtils.d("flowView--获取内容布局---" + measuredHeight);
                handler.sendEmptyMessage(TAG_LAYOUT_FINISH);
            }
        });
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
