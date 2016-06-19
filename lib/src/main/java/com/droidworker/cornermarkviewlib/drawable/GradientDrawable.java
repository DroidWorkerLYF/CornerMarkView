package com.droidworker.cornermarkviewlib.drawable;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.droidworker.cornermarkviewlib.CornerMarkLocation;
import com.droidworker.cornermarkviewlib.CornerMarkType;

/**
 * @author https://github.com/DroidWorkerLYF
 */
public class GradientDrawable extends android.graphics.drawable.GradientDrawable implements ICornerMarkDrawable {

    @Override
    public void setBounds(int left, int top, int right, int bottom) {
        super.setBounds(left, top, right, bottom);
    }

    @Override
    public void setLocation(CornerMarkLocation location) {

    }

    @Override
    public Drawable getDrawable() {
        return this;
    }

    @Override
    public CornerMarkType getMarkType() {
        return CornerMarkType.TYPE_GRADIENT;
    }

    @Override
    public int[] onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int width = View.MeasureSpec.getSize(widthMeasureSpec);
        final int height = View.MeasureSpec.getSize(heightMeasureSpec);
        setBounds(0,0, width, height);
        return null;
    }

    @Override
    public void restore(Canvas canvas) {

    }

}
