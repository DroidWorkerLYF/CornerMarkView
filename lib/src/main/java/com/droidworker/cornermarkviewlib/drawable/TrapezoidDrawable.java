package com.droidworker.cornermarkviewlib.drawable;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;

import com.droidworker.cornermarkviewlib.CornerMarkLocation;
import com.droidworker.cornermarkviewlib.CornerMarkType;

/**
 * 梯形角标背景
 *
 * @author https://github.com/DroidWorkerLYF
 */
public class TrapezoidDrawable extends Drawable implements ICornerMarkDrawable{
    /**
     * 梯形的画笔
     * The paint used for drawing rect
     */
    private Paint mRectPaint;
    /**
     * 梯形的绘制路径
     * The drawing path
     */
    private Path mPath;
    /**
     * 长边长度
     * Long side's length
     */
    private int mLongSide;
    /**
     * 短边
     * Short side's length
     */
    private int mShortSide;
    /**
     * 位置
     */
    private CornerMarkLocation mLocation;
    /**
     * 填充颜色
     */
    private int mColor;
    /**
     * 宽
     */
    private int width;
    /**
     * 高
     */
    private int height;

    public TrapezoidDrawable(){
        mRectPaint = new Paint();
        mRectPaint.setAntiAlias(true);
        mPath = new Path();
    }

    @Override
    public void draw(Canvas canvas) {

        setPath(canvas.getWidth(), canvas.getHeight());
        mRectPaint.setStyle(Paint.Style.FILL);
        mRectPaint.setColor(mColor);
        canvas.drawPath(mPath, mRectPaint);
        canvas.save();
        translate(canvas);
        rotate(canvas);
    }

    /**
     * 设置梯形绘制路径
     * Set drawing path
     *
     * @param width 画布的宽
     *              the width of canvas
     * @param height 画布的高
     *               the height of canvas
     */
    private void setPath(int width, int height){
        mPath.reset();
        switch (mLocation.getLocation()){
            case 1:
                mPath.moveTo(mShortSide, 0);
                mPath.lineTo(mLongSide, 0);
                mPath.lineTo(0, mLongSide);
                mPath.lineTo(0, mShortSide);
                mPath.lineTo(mShortSide, 0);
                break;
            case 2:
                mPath.moveTo(width - mShortSide, 0);
                mPath.lineTo(width - mLongSide, 0);
                mPath.lineTo(width, mLongSide);
                mPath.lineTo(width, mShortSide);
                mPath.lineTo(width - mShortSide, 0);
                break;
            case 3:
                mPath.moveTo(0, height - mLongSide);
                mPath.lineTo(mLongSide, height);
                mPath.lineTo(mShortSide, height);
                mPath.lineTo(0, height - mShortSide);
                mPath.lineTo(0, height - mLongSide);
                break;
            case 4:
                mPath.moveTo(width, height - mLongSide);
                mPath.lineTo(width - mLongSide, height);
                mPath.lineTo(width - mShortSide, height);
                mPath.lineTo(width, height - mShortSide);
                mPath.lineTo(width, height - mLongSide);
                break;
        }
        mPath.close();
    }

    public void rotate(Canvas canvas) {
        final int centerX = width /2;
        final int centerY = height/2;
        switch (mLocation.getLocation()){
            case 1:
                canvas.rotate(-45, centerX, centerY);
                break;
            case 2:
                canvas.rotate(45, centerX, centerY);
                break;
            case 3:
                canvas.rotate(45, centerX, centerY);
                break;
            case 4:
                canvas.rotate(-45, centerX, centerY);
                break;
        }
    }

    public void translate(Canvas canvas){
        int centerX = width /2;
        int centerY = height/2;
        int x = (mShortSide + (mLongSide - mShortSide) /2 ) / 2;
        int y = x;
        switch (mLocation.getLocation()){
            case 1:
                canvas.translate(-(centerX- x), -(centerY - y));
                break;
            case 2:
                canvas.translate(centerX - x, -(centerY - y));
                break;
            case 3:
                canvas.translate(-(centerX- x), centerY - y);
                break;
            case 4:
                canvas.translate(centerX - x, centerY - y);
                break;
        }
    }

    @Override
    public void setAlpha(int alpha) {
        if(mRectPaint != null){
            mRectPaint.setAlpha(alpha);
        }
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        if(mRectPaint != null){
            mRectPaint.setColorFilter(colorFilter);
        }
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSPARENT;
    }

    @Override
    public void setLocation(CornerMarkLocation location) {
        if(location == null){
            return;
        }
        this.mLocation = location;
    }

    @Override
    public Drawable getDrawable() {
        return this;
    }

    @Override
    public CornerMarkType getMarkType() {
        return CornerMarkType.TYPE_TRAPEZOID;
    }

    @Override
    public int[] onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        width = (int) Math.sqrt(Math.pow(mLongSide, 2) * 2);
        height = (int) ((mLongSide - mShortSide) * Math.cos(45 * Math.PI / 180)) * 3;
        return new int[]{width, height};
    }

    @Override
    public void restore(Canvas canvas) {
        canvas.restore();
    }

    public int getLongSide() {
        return mLongSide;
    }

    public void setLongSide(int longSide) {
        this.mLongSide = longSide;
    }

    public int getShortSide() {
        return mShortSide;
    }

    public void setShortSide(int shortSide) {
        this.mShortSide = shortSide;
    }

    public CornerMarkLocation getLocation() {
        return mLocation;
    }

    public int getColor() {
        return mColor;
    }

    public void setColor(int color) {
        this.mColor = color;
    }
}
