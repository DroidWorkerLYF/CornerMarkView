package com.droidworker.cornermarkviewlib.drawable;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import com.droidworker.cornermarkviewlib.CornerMarkLocation;
import com.droidworker.cornermarkviewlib.CornerMarkType;

/**
 * @author https://github.com/DroidWorkerLYF
 */
public interface ICornerMarkDrawable {

    void setLocation(CornerMarkLocation location);

    /**
     * 获取用于绘制的BackgroundDrawable
     * @return drawable
     */
    Drawable getDrawable();

    /**
     * 获取当前角标背景的类型
     *
     * @return the type of drawable
     */
    CornerMarkType getMarkType();

    /**
     * 有些角标的大小需要额外的处理
     * @param widthMeasureSpec widthMeasureSpec
     * @param heightMeasureSpec heightMeasureSpec
     * @return a array of exactly width and height or null
     */
    int[] onMeasure(int widthMeasureSpec, int heightMeasureSpec);

    /**
     * 用户还原canvas
     * @param canvas canvas
     */
    void restore(Canvas canvas);
}
