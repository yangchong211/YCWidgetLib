package com.yc.shadow;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import java.util.HashMap;

/**
 * <pre>
 *     @author yangchong
 *     blog  : https://github.com/yangchong211
 *     time  : 2018/7/20
 *     desc  : 自定义阴影
 *     revise:
 * </pre>
 */
public class ShadowLayout extends FrameLayout {

    /**
     * 第一种：使用CardView，但是不能设置阴影颜色
     * 第二种：采用shape叠加，存在后期UI效果不便优化
     * 第三种：自定义View
     *
     * paint.setShadowLayer(float radius, float dx, float dy, int shadowColor);
     * 这个方法可以达到这样一个效果，在使用canvas画图时给视图顺带上一层阴影效果。
     * 简单介绍一下这几个参数：
     * radius: 阴影半径，主要可以控制阴影的模糊效果以及阴影扩散出去的大小。
     * dx：阴影在X轴方向上的偏移量
     * dy: 阴影在Y轴方向上的偏移量
     * shadowColor： 阴影颜色。
     *
     * 其中涉及到几个属性，阴影的宽度，view到Viewgroup的距离，如果视图和父布局一样大的话，那阴影就不好显示，
     * 如果要能够显示出来就必须设置clipChildren=false。
     * 大部分背景都是有圆角的，比如上图中的圆角，需要达到高度还原阴影的效果就是的阴影的圆角和背景保持一致。
     */


    /**
     * 阴影颜色
     */
    private int mShadowColor;
    /**
     * 阴影的扩散范围(也可以理解为扩散程度)
     */
    private float mShadowLimit;
    /**
     * 阴影的圆角大小
     */
    private float mCornerRadius;
    /**
     * x轴的偏移量
     */
    private float mDx;
    /**
     * y轴的偏移量
     */
    private float mDy;
    /**
     * 左边是否显示阴影
     */
    private boolean leftShow;
    /**
     * 右边是否显示阴影
     */
    private boolean rightShow;
    /**
     * 上边是否显示阴影
     */
    private boolean topShow;
    /**
     * 下面是否显示阴影
     */
    private boolean bottomShow;
    private boolean mInvalidateShadowOnSizeChanged = true;
    private boolean mForceInvalidateShadow = false;
    /**
     * 缓存
     */
    private final HashMap<Key, Bitmap> cache = new HashMap<>();


    public ShadowLayout(Context context) {
        super(context);
        initView(context, null);
    }

    public ShadowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public ShadowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    @Override
    protected int getSuggestedMinimumWidth() {
        return 0;
    }

    @Override
    protected int getSuggestedMinimumHeight() {
        return 0;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (w > 0 && h > 0 && (getBackground() == null || mInvalidateShadowOnSizeChanged
                || mForceInvalidateShadow)) {
            mForceInvalidateShadow = false;
            setBackgroundCompat(w, h);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (mForceInvalidateShadow) {
            mForceInvalidateShadow = false;
            setBackgroundCompat(right - left, bottom - top);
        }
    }

    public void setInvalidateShadowOnSizeChanged(boolean invalidateShadowOnSizeChanged) {
        mInvalidateShadowOnSizeChanged = invalidateShadowOnSizeChanged;
    }

    public void invalidateShadow() {
        mForceInvalidateShadow = true;
        requestLayout();
        invalidate();
    }

    private void initView(Context context, AttributeSet attrs) {
        initAttributes(context, attrs);

        int xPadding = (int) (mShadowLimit + Math.abs(mDx));
        int yPadding = (int) (mShadowLimit + Math.abs(mDy));
        int left;
        int right;
        int top;
        int bottom;
        if (leftShow) {
            left = xPadding;
        } else {
            left = 0;
        }

        if (topShow) {
            top = yPadding;
        } else {
            top = 0;
        }


        if (rightShow) {
            right = xPadding;
        } else {
            right = 0;
        }

        if (bottomShow) {
            bottom = yPadding;
        } else {
            bottom = 0;
        }

        setPadding(left, top, right, bottom);
    }

    @SuppressWarnings("deprecation")
    private void setBackgroundCompat(int w, int h) {
        Bitmap bitmap = createShadowBitmap(w, h, mCornerRadius, mShadowLimit, mDx,
                mDy, mShadowColor, Color.TRANSPARENT);
        BitmapDrawable drawable = new BitmapDrawable(getResources(), bitmap);
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN) {
            setBackgroundDrawable(drawable);
        } else {
            setBackground(drawable);
        }
    }


    private void initAttributes(Context context, AttributeSet attrs) {
        TypedArray attr = getTypedArray(context, attrs, R.styleable.ShadowLayout);
        if (attr == null) {
            return;
        }

        try {
            //默认是显示
            leftShow = attr.getBoolean(R.styleable.ShadowLayout_yc_leftShow, true);
            rightShow = attr.getBoolean(R.styleable.ShadowLayout_yc_rightShow, true);
            bottomShow = attr.getBoolean(R.styleable.ShadowLayout_yc_bottomShow, true);
            topShow = attr.getBoolean(R.styleable.ShadowLayout_yc_topShow, true);

            mCornerRadius = attr.getDimension(R.styleable.ShadowLayout_yc_cornerRadius, 0);
            mShadowLimit = attr.getDimension(R.styleable.ShadowLayout_yc_shadowLimit, 0);
            mDx = attr.getDimension(R.styleable.ShadowLayout_yc_dx, 0);
            mDy = attr.getDimension(R.styleable.ShadowLayout_yc_dy, 0);
            mShadowColor = attr.getColor(R.styleable.ShadowLayout_yc_shadowColor,
                    getResources().getColor(R.color.default_shadow_color));
        } finally {
            attr.recycle();
        }
    }

    private TypedArray getTypedArray(Context context, AttributeSet attributeSet, int[] attr) {
        return context.obtainStyledAttributes(attributeSet, attr, 0, 0);
    }

    private Bitmap createShadowBitmap(int shadowWidth, int shadowHeight, float cornerRadius,
                                      float shadowRadius, float dx, float dy,
                                      int shadowColor, int fillColor) {

        Key key = new Key("bitmap", shadowWidth, shadowHeight);
        Bitmap output = cache.get(key);
        if (output == null) {
            //根据宽高创建bitmap背景
            output = Bitmap.createBitmap(shadowWidth, shadowHeight, Bitmap.Config.ARGB_4444);
            cache.put(key, output);
            //Log.v("bitmap对象-----","----直接创建对象，然后存入缓存之中---");
        } else {
            //Log.v("bitmap对象-----","----从缓存中取出对象---");
        }
        int size = cache.size();
        //Log.v("bitmap对象-----","----缓存数量---"+size);

        Canvas canvas = new Canvas(output);

        RectF shadowRect = new RectF(shadowRadius, shadowRadius,
                shadowWidth - shadowRadius, shadowHeight - shadowRadius);

        if (dy > 0) {
            shadowRect.top += dy;
            shadowRect.bottom -= dy;
        } else if (dy < 0) {
            shadowRect.top += Math.abs(dy);
            shadowRect.bottom -= Math.abs(dy);
        }

        if (dx > 0) {
            shadowRect.left += dx;
            shadowRect.right -= dx;
        } else if (dx < 0) {
            shadowRect.left += Math.abs(dx);
            shadowRect.right -= Math.abs(dx);
        }

        //创建画笔，设置画笔的颜色，风格
        Paint shadowPaint = new Paint();
        shadowPaint.setAntiAlias(true);
        shadowPaint.setColor(fillColor);
        shadowPaint.setStyle(Paint.Style.FILL);

        if (!isInEditMode()) {
            shadowPaint.setShadowLayer(shadowRadius, dx, dy, shadowColor);
        }
        canvas.drawRoundRect(shadowRect, cornerRadius, cornerRadius, shadowPaint);
        return output;
    }


    public class Key {

        private final String name;
        private final int width;
        private final int height;

        public Key(String name, int width, int height) {
            this.name = name;
            this.width = width;
            this.height = height;
        }

        public String getName() {
            return name;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Key key = (Key) o;
            if (width != key.width) {
                return false;
            }
            if (height != key.height) {
                return false;
            }
            return name != null ? name.equals(key.name) : key.name == null;
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + width;
            result = 31 * result + height;
            return result;
        }
    }


}
