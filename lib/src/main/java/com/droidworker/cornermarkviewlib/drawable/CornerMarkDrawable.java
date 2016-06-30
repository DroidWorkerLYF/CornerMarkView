package com.droidworker.cornermarkviewlib.drawable;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import com.droidworker.cornermarkviewlib.CornerMarkLocation;
import com.droidworker.cornermarkviewlib.CornerMarkType;

/**
 * 继承自系统的android.graphics.drawable,这样可以直接设置为视图的background drawable使用,
 * 如果有特殊的角标影响到视图的大小和绘制,需要处理onMeasure和restore方法
 *
 * @author https://github.com/DroidWorkerLYF
 */
public abstract class CornerMarkDrawable extends Drawable {
    /**
     * 位置
     * Location
     */
    protected CornerMarkLocation mLocation;

    public CornerMarkDrawable() {

    }

    /**
     * 设置xml设定的参数
     * Set attributes from xml
     *
     * @param context      上下文(context)
     * @param attributeSet 参数(attribute set)
     */
    public abstract void setAttributeSet(Context context, AttributeSet attributeSet);

    /**
     * 设置位置,位置影响到个别类型的绘制
     * Set location, location will effect some kind of drawable's draw method
     *
     * @param location 位置
     */
    public void setLocation(CornerMarkLocation location) {
        if(location != null) {
            this.mLocation = location;
        }
    }

    /**
     * 获取位置
     * Get location
     *
     * @return location
     */
    public CornerMarkLocation getLocation() {
        return mLocation;
    }

    /**
     * 获取角标类型
     * Get type
     *
     * @return drawable's type
     */
    public abstract CornerMarkType getMarkType();

    /**
     * 设置颜色
     * Set color
     *
     * @param color 颜色
     */
    public abstract void setColor(int color);

    /**
     * 获取颜色
     *
     * @return color
     */
    public abstract int getColor();

    /**
     * 是否需要改变视图的大小
     *
     * @return true if this drawable need to change view's size
     */
    public boolean needChangeViewSize() {
        return false;
    }

    /**
     * 特殊角标的大小需要额外的处理,并且改变视图的大小
     * Some special mark need to handle this method to change view's size
     *
     * @param widthMeasureSpec  widthMeasureSpec
     * @param heightMeasureSpec heightMeasureSpec
     * @return a array of exactly width and height or null
     */
    public int[] onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        return null;
    }

    /**
     * 用户还原canvas, 某些类型是要有旋转平移操作来影响文字绘制的,当需要恢复canvas原有状态时覆盖此方法
     * restore canvas, some type of corner mark drawable need to rotate canvas, translate canvas and so on
     * to make the text drawing at the right place, so we need to provide a restore method to restore canvas
     * when needed.
     *
     * @param canvas canvas
     */
    public void restore(Canvas canvas) {

    }
}
