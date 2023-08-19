package com.yc.customwidget;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import androidx.annotation.NonNull;
import android.text.style.ReplacementSpan;

/**
 * <pre>
 *     @author yangchong
 *     blog  :
 *     time  : 2018/6/11
 *     desc  : SpannableString 设置文字圆角背景
 *     revise:
 * </pre>
 */
public class RoundedBackgroundSpan extends ReplacementSpan {


    private static final int CORNER_RADIUS = 8;
    private int backgroundColor = 0;
    private int textColor = 0;

    public RoundedBackgroundSpan(int backgroundColor, int textColor) {
        super();
        this.backgroundColor = backgroundColor;
        this.textColor = textColor;
    }

    @Override
    public void draw(Canvas canvas, CharSequence text, int start, int end, float x,
                     int top, int y, int bottom, @NonNull Paint paint) {
        Paint.FontMetrics fm = paint.getFontMetrics();
        // fm.bottom - fm.top 解决设置行距（android:lineSpacingMultiplier="1.2"）时背景色高度问题
        RectF rect = new RectF(x, top, x + measureText(paint, text, start, end),
                fm.bottom - fm.top );
        paint.setColor(backgroundColor);
        canvas.drawRoundRect(rect, CORNER_RADIUS, CORNER_RADIUS, paint);
        paint.setColor(textColor);
        canvas.drawText(text, start, end, x, y, paint);
    }

    @Override
    public int getSize(@NonNull Paint paint, CharSequence text,
                       int start, int end, Paint.FontMetricsInt fm){
        return Math.round(paint.measureText(text, start, end));
    }

    private float measureText(Paint paint, CharSequence text, int start, int end) {
        return paint.measureText(text, start, end);
    }

}
