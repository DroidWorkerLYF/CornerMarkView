package com.droidworker.cornermarkviewlib.drawable;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;

import com.droidworker.cornermarkviewlib.CornerMarkType;
import com.droidworker.cornermarkviewlib.R;

/**
 * 支持圆角的矩形drawable(支持圆角)
 * This drawable will draw a rectangle(support rounded corners)
 *
 * @author https://github.com/DroidWorkerLYF
 */
public class RectangleMarkDrawable extends CornerMarkDrawable {
    /**
     * 矩形的画笔
     * The paint used for drawing rect
     */
    private Paint mRectPaint;
    /**
     * 绘制路径
     * The drawing path
     */
    private Path mPath;
    /**
     * 包含圆角半径的数组
     * 4 pairs of X and Y radius for each corner, specified in pixels.
     * The length of this array must be 8
     */
    private float[] mRadiusArray;
    /**
     * 大小
     * Size
     */
    private RectF mRectF;
    /**
     * 填充颜色
     * Full fill color
     */
    private int mColor;

    public RectangleMarkDrawable() {
        mRectPaint = new Paint();
        mRectPaint.setAntiAlias(true);
        mPath = new Path();
        mRectF = new RectF();
    }

    @Override
    public void setAttributeSet(Context context, AttributeSet attributeSet) {
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.CornerMark_Rectangle);
        mColor = typedArray.getColor(R.styleable.CornerMark_Rectangle_rectangle_background_color, Color.WHITE);
        final int corner = typedArray.getDimensionPixelSize(R.styleable.CornerMark_Rectangle_corner, 0);
        final int leftTopCorner = typedArray.getDimensionPixelSize(R.styleable.CornerMark_Rectangle_left_top_corner, corner);
        final int rightTopCorner = typedArray.getDimensionPixelSize(R.styleable.CornerMark_Rectangle_right_top_corner, corner);
        final int leftBottomCorner = typedArray.getDimensionPixelSize(R.styleable.CornerMark_Rectangle_left_bottom_corner, corner);
        final int rightBottomCorner = typedArray.getDimensionPixelSize(R.styleable.CornerMark_Rectangle_right_bottom_corner, corner);
        typedArray.recycle();
        mRadiusArray = new float[]{leftTopCorner, leftTopCorner, rightTopCorner, rightTopCorner, rightBottomCorner, rightBottomCorner, leftBottomCorner, leftBottomCorner};
    }

    @Override
    public CornerMarkType getMarkType() {
        return CornerMarkType.TYPE_RECTANGLE;
    }

    @Override
    public void setColor(int color) {
        mColor = color;
    }

    @Override
    public int getColor() {
        return mColor;
    }

    @Override
    public void draw(Canvas canvas) {
        mPath.reset();
        mRectF.set(getBounds());
        if (Build.VERSION.SDK_INT >= 21) {
            setPathV21();
        } else {
            setPath();
        }
        mRectPaint.setColor(mColor);
        mRectPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawPath(mPath, mRectPaint);
    }

    /**
     * API 21及以上提供了设置方法,可以直接使用
     * Set path for api level at 21 and higher
     */
    private void setPathV21() {
        mPath.addRoundRect(mRectF, mRadiusArray, Path.Direction.CW);
    }

    /**
     * API 21及以下,设置绘制路径
     * Set path for api level lower than 21
     */
    private void setPath() {
        final float halfX = mRectF.centerX();
        final float halfY = mRectF.centerY();
        float cornerX = setRadiusValue(mRadiusArray[0], halfX);
        float cornerY = setRadiusValue(mRadiusArray[1], halfY);
        setPath(cornerX, cornerY, 0, cornerY, 0, 0, cornerX, 0);

        cornerX = setRadiusValue(mRadiusArray[2], halfX);
        cornerY = setRadiusValue(mRadiusArray[3], halfY);
        setPath(cornerX, cornerY, mRectF.right - cornerX, 0, mRectF.right, 0, mRectF.right, cornerY);

        cornerX = setRadiusValue(mRadiusArray[4], halfX);
        cornerY = setRadiusValue(mRadiusArray[5], halfY);
        setPath(cornerX, cornerY, mRectF.right, mRectF.bottom - cornerY, mRectF.right, mRectF.bottom, mRectF.right - cornerX, mRectF.bottom);

        cornerX = setRadiusValue(mRadiusArray[6], halfX);
        cornerY = setRadiusValue(mRadiusArray[7], halfY);
        setPath(cornerX, cornerY, cornerX, mRectF.bottom, 0, mRectF.bottom, 0, mRectF.bottom - cornerY);

        cornerX = setRadiusValue(mRadiusArray[0], halfX);
        cornerY = setRadiusValue(mRadiusArray[1], halfY);
        setPath(cornerX, cornerY, 0, cornerY, 0, 0, cornerX, 0);
        mPath.close();
    }

    /**
     * 确保半径不会大于宽度或者高度的一半
     * Make sure the radius will not larger than half width or half height of view
     *
     * @param userValue 用户设定的值 user set value
     * @param half      一半的宽或者高 view's value
     * @return 半径
     */
    private float setRadiusValue(float userValue, float half) {
        return userValue > half ? half : userValue;
    }

    /**
     * 设定绘制路径
     * Set drawing path
     *
     * @param cornerX 圆角水平半径(X radius)
     * @param cornerY 圆角竖直半径(Y radius)
     * @param x1      贝塞尔曲线的起点X(quadratic bezier's start x)
     * @param y1      贝塞尔曲线的起点Y(quadratic bezier's start y)
     * @param x2      贝塞尔曲线的控制点X(quadratic bezier's control x)
     * @param y2      贝塞尔曲线的控制点Y(quadratic bezier's control y)
     * @param x3      贝塞尔曲线的终点X(quadratic bezier's end x)
     * @param y3      贝塞尔曲线的终点Y(quadratic bezier's end y)
     */
    private void setPath(float cornerX, float cornerY, float x1, float y1, float x2, float y2, float x3, float y3) {
        if (cornerX != 0 && cornerY != 0) {
            mPath.moveTo(x1, y1);
            mPath.quadTo(x2, y2, x3, y3);
        } else {
            mPath.moveTo(x2, y2);
        }
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

    /**
     * 设置圆角半径
     * Specify radius array for each of the 4 corners. For each corner, the array
     * contains 2 values, [cornerX, cornerY]. The corners are ordered
     * left-top, right-top, right-bottom, left-bottom
     *
     * @param radiusArray 包含圆角半径的数组
     *                    4 pairs of X and Y radius for each corner, specified in pixels.
     *                    The length of this array must be 8
     */
    public void setRadiusArray(float[] radiusArray) {
        if (radiusArray == null || radiusArray.length != 8) {
            throw new IllegalArgumentException("radius array can not be null or it's length must be 8");
        }
        this.mRadiusArray = radiusArray;
    }
}
