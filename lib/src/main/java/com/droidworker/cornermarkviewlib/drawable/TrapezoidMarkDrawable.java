package com.droidworker.cornermarkviewlib.drawable;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.util.AttributeSet;

import com.droidworker.cornermarkviewlib.CornerMarkType;
import com.droidworker.cornermarkviewlib.R;

/**
 * 梯形角标背景
 * This drawable will draw a trapezoid
 *
 * @author https://github.com/DroidWorkerLYF
 */
public class TrapezoidMarkDrawable extends CornerMarkDrawable {
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
     * 填充颜色
     * Full fill color
     */
    private int mColor;
    /**
     * 依附的视图的宽度
     * The view which this drawable attached 's width
     */
    private int width;
    /**
     * 依附的视图的高度
     * The view which this drawable attached 's height
     */
    private int height;
    /**
     * 包含宽度和高度,用于设定视图大小的数组
     * This array contains width and height.It will be used for setting view's size
     */
    private int[] mSize;

    public TrapezoidMarkDrawable() {
        mRectPaint = new Paint();
        mRectPaint.setAntiAlias(true);
        mPath = new Path();
    }

    @Override
    public void setAttributeSet(Context context, AttributeSet attributeSet) {
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.CornerMark_Trapezoid);
        mColor = typedArray.getColor(R.styleable.CornerMark_Trapezoid_trapezoid_background_color, Color.WHITE);
        mLongSide = typedArray.getDimensionPixelSize(R.styleable.CornerMark_Trapezoid_long_side, 0);
        mShortSide = typedArray.getDimensionPixelSize(R.styleable.CornerMark_Trapezoid_short_side, 0);
        typedArray.recycle();
        onSizeChanged();
    }

    @Override
    public void draw(Canvas canvas) {
        //绘制背景后,需要平移,旋转视图来将文字绘制到正确的位置,如果后续再视图中还有其他绘制,需要先调用restore方法
        //In order to draw text at the right place, we need to translate and rotate the canvas.Call
        //restore before you drawing other things.
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
     * @param width  画布的宽
     *               the width of canvas
     * @param height 画布的高
     *               the height of canvas
     */
    private void setPath(int width, int height) {
        mPath.reset();
        switch (mLocation.getLocation()) {
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

    /**
     * 旋转画布
     * Rotate canvas
     *
     * @param canvas 画布
     */
    public void rotate(Canvas canvas) {
        final int centerX = width / 2;
        final int centerY = height / 2;
        switch (mLocation.getLocation()) {
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

    /**
     * 平移画布
     * Translate canvas
     *
     * @param canvas 画布
     */
    public void translate(Canvas canvas) {
        int centerX = width / 2;
        int centerY = height / 2;
        int x = (mShortSide + (mLongSide - mShortSide) / 2) / 2;
        switch (mLocation.getLocation()) {
            case 1:
                canvas.translate(-(centerX - x), -(centerY - x));
                break;
            case 2:
                canvas.translate(centerX - x, -(centerY - x));
                break;
            case 3:
                canvas.translate(-(centerX - x), centerY - x);
                break;
            case 4:
                canvas.translate(centerX - x, centerY - x);
                break;
        }
    }

    @Override
    public void restore(Canvas canvas) {
        canvas.restore();
    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.OPAQUE;
    }

    @Override
    public CornerMarkType getMarkType() {
        return CornerMarkType.TYPE_TRAPEZOID;
    }

    @Override
    public boolean needChangeViewSize() {
        return true;
    }

    @Override
    public int[] onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        return mSize;
    }

    /**
     * 宽度或者高度改变时,重新设置数组大小
     * Reset mSize when long side or short side changed
     */
    private void onSizeChanged() {
        width = mLongSide;
        height = mLongSide;
        if (mSize == null) {
            mSize = new int[2];
        }
        mSize[0] = width;
        mSize[1] = height;
    }

    @Override
    public void setColor(int color) {
        this.mColor = color;
    }

    @Override
    public int getColor() {
        return mColor;
    }

    public void setLongSide(int longSide) {
        mLongSide = longSide;
        onSizeChanged();
    }

    public void setShortSide(int shortSide) {
        mShortSide = shortSide;
        onSizeChanged();
    }
}
