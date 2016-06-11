package com.droidworker.cornermarkviewlib;

import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextPaint;

import com.droidworker.cornermarkviewlib.attribute.CommonAttribute;
import com.droidworker.cornermarkviewlib.attribute.RectangleAttribute;

/**
 * 矩形角标的创建者
 * The creator used for drawing rectangle kind of corner mark
 *
 * @author https://github.com/DroidWorkerLYF
 */
public class RectangleMarkCreator implements IMarkCreator {
    private int left;
    private int top;
    private int right;
    private int bottom;
    private RectF mRect;
    /**
     * 矩形绘制画笔
     * The paint used for drawing rect
     */
    private Paint mRectPaint;
    /**
     * 文字绘制画笔
     * The paint used for drawing text
     */
    private TextPaint mTextPaint;
    /**
     * 绘制参数
     * Attribute for drawing
     */
    private RectangleAttribute mRectangleAttribute;
    /**
     * 上一次绘制的位置,如果两次绘制位置一样,则不再重复计算顶点
     * The location of last time drawing,if it is equal to current location,then we don't
     * need to reset vertex.
     */
    private int mPreLocation;

    public RectangleMarkCreator(){
        mRectPaint = new Paint();
        mRectPaint.setAntiAlias(true);

        mTextPaint = new TextPaint();
        mTextPaint.setAntiAlias(true);

        mRect = new RectF();
    }

    @Override
    public void setAttribute(TypedArray typedArray, float density) {
        mRectangleAttribute = new RectangleAttribute(typedArray, density);
    }

    @Override
    public void setAttribute(CommonAttribute commonAttribute) {
        mRectangleAttribute = (RectangleAttribute) commonAttribute;
    }

    @Override
    public CommonAttribute getAttribute() {
        return mRectangleAttribute;
    }

    @Override
    public int getType() {
        return MarkCreatorFactory.TYPE_RECTANGLE;
    }

    @Override
    public void onDrawMark(Canvas canvas) {
        if(mRectangleAttribute == null){
            return;
        }

        setVertex(canvas);
        mRectPaint.setStrokeWidth(mRectangleAttribute.getStrokeWidth());
        if (mRectangleAttribute.getRadius() != 0) {
            drawRoundedStroke(canvas);
            drawRoundedRect(canvas);
        } else {
            drawRect(canvas);
        }
        mTextPaint.setColor(mRectangleAttribute.getTextColor());
        mTextPaint.setTextSize(mRectangleAttribute.getTextSize());
        drawText(canvas);
    }

    /**
     * 设置矩形顶点
     * Set vertex of rect
     *
     * @param canvas 画布
     */
    private void setVertex(Canvas canvas) {
        final int curLocation = mRectangleAttribute.getLocation();
        if(mPreLocation == curLocation){
            return;
        }
        final int width = canvas.getWidth();
        final int height = canvas.getHeight();
        final int longSide = mRectangleAttribute.getLongSide();
        final int shortSide = mRectangleAttribute.getShortSide();
        final int marginVertical = mRectangleAttribute.getMarginVertical();
        final int marginHorizontal = mRectangleAttribute.getMarginHorizontal();

        switch (curLocation) {
            case RectangleAttribute.LEFT_TOP:
                left = marginHorizontal;
                top = marginVertical;
                right = left + longSide;
                bottom = top + shortSide;
                break;
            case RectangleAttribute.RIGHT_TOP:
                right = width - marginHorizontal;
                top = marginVertical;
                left = right - longSide;
                bottom = top + shortSide;
                break;
            case RectangleAttribute.LEFT_BOTTOM:
                left = marginHorizontal;
                right = left + longSide;
                bottom = height - marginVertical;
                top = bottom - shortSide;
                break;
            case RectangleAttribute.RIGHT_BOTTOM:
                right = width - marginHorizontal;
                left = right - longSide;
                bottom = height - marginVertical;
                top = bottom - shortSide;
                break;
        }
        mPreLocation = curLocation;
    }

    /**
     * 绘制直角矩形
     * Draw a rect without radius
     *
     * @param canvas 画布
     */
    private void drawRect(Canvas canvas) {
        mRectPaint.setStyle(Paint.Style.STROKE);
        mRectPaint.setColor(mRectangleAttribute.getStrokeColor());
        canvas.drawRect(left, top, right, bottom, mRectPaint);
        mRectPaint.setStyle(Paint.Style.FILL);
        mRectPaint.setColor(mRectangleAttribute.getBackgroundColor());
        canvas.drawRect(left, top, right, bottom, mRectPaint);
    }

    /**
     * 绘制圆角矩形
     * Draw rounded rect
     *
     * @param canvas 画布
     */
    private void drawRoundedRect(Canvas canvas, Paint paint) {
        final int radius = mRectangleAttribute.getRadius();
        canvas.drawRect(left + radius, top + radius, right - radius, bottom - radius, paint);
        canvas.drawRect(left + radius, top, right - radius, top + radius, paint);
        canvas.drawRect(left + radius, bottom - radius, right - radius, bottom, paint);
        canvas.drawRect(left, top + radius, left + radius, bottom - radius, paint);
        canvas.drawRect(right - radius, top + radius, right, bottom - radius, paint);

        if (mRectangleAttribute.isLeftTopRadius()) {
            mRect.set(left, top, left + 2 * radius, top + 2 * radius);
            canvas.drawArc(mRect, 180, 90, true, paint);
        } else {
            canvas.drawRect(left, top, left + radius, top + radius, paint);
        }
        if (mRectangleAttribute.isRightTopRadius()) {
            mRect.set(right - 2 * radius, top, right, top + 2 * radius);
            canvas.drawArc(mRect, 270, 90, true, paint);
        } else {
            canvas.drawRect(right - radius, top, right, right + radius, paint);
        }
        if (mRectangleAttribute.isRightBottomRadius()) {
            mRect.set(right - 2 * radius, bottom - 2 * radius, right, bottom);
            canvas.drawArc(mRect, 0, 90, true, paint);
        } else {
            canvas.drawRect(right - radius, bottom - radius, right, bottom, paint);
        }
        if (mRectangleAttribute.isLeftBottomRadius()) {
            mRect.set(left, bottom - 2 * radius, left + 2 * radius, bottom);
            canvas.drawArc(mRect, 90, 90, true, paint);
        } else {
            canvas.drawRect(left, bottom - radius, left + radius, bottom, paint);
        }
    }

    /**
     * 绘制圆角矩形描边
     * Draw rounded rect's stroke
     *
     * @param canvas 画布
     */
    private void drawRoundedStroke(Canvas canvas){
        mRectPaint.setStyle(Paint.Style.STROKE);
        mRectPaint.setColor(mRectangleAttribute.getStrokeColor());
        drawRoundedRect(canvas, mRectPaint);
    }

    /**
     * 绘制圆角矩形
     * Draw rounded rect's background
     *
     * @param canvas 画布
     */
    private void drawRoundedRect(Canvas canvas){
        mRectPaint.setStyle(Paint.Style.FILL);
        mRectPaint.setColor(mRectangleAttribute.getBackgroundColor());
        drawRoundedRect(canvas, mRectPaint);
    }

    /**
     * 绘制文字
     * Draw text
     *
     * @param canvas 画布
     */
    private void drawText(Canvas canvas) {
        final Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        if (fontMetrics == null) {
            return;
        }
        final String text = mRectangleAttribute.getText();
        final int textHeight = (int) Math.ceil(fontMetrics.bottom - fontMetrics.top);
        final int textWidth = (int) mTextPaint.measureText(text);
        final float centerX = left + (right - left) / 2;
        final float centerY = top + (bottom - top) / 2;
        canvas.drawText(mRectangleAttribute.getText(), centerX - textWidth / 2, centerY + textHeight / 2 - fontMetrics.descent, mTextPaint);
    }
}
