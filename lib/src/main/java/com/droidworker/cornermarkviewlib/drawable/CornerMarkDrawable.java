package com.droidworker.cornermarkviewlib.drawable;

import android.graphics.Canvas;

import com.droidworker.cornermarkviewlib.CornerMarkLocation;
import com.droidworker.cornermarkviewlib.CornerMarkType;

/**
 * @author https://github.com/DroidWorkerLYF
 */
public abstract class CornerMarkDrawable extends android.graphics.drawable.GradientDrawable{
    /**
     * 位置
     */
    private CornerMarkLocation mLocation;

    /**
     * 设置位置,位置影响到绘制,但是实际视图的位置仍然是视图设定的
     * Set location
     * @param location 位置
     */
    public void setLocation(CornerMarkLocation location){
        this.mLocation = location;
    }

    public CornerMarkLocation getLocation(){
        return mLocation;
    }

    /**
     * 角标类型
     * @return type
     */
    public abstract CornerMarkType getMarkType();

    /**
     * 有些角标的大小需要额外的处理,改变视图的大小
     * @param widthMeasureSpec widthMeasureSpec
     * @param heightMeasureSpec heightMeasureSpec
     * @return a array of exactly width and height or null
     */
    public int[] onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        return null;
    }

    /**
     * 用户还原canvas
     * @param canvas canvas
     */
    public void restore(Canvas canvas){

    }
}
