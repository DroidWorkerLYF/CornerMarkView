package com.droidworker.cornermarkviewlib;

import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.text.TextPaint;

import com.droidworker.cornermarkviewlib.attribute.CommonAttribute;
import com.droidworker.cornermarkviewlib.attribute.TrapezoidAttribute;

/**
 * 梯形角标的创建者
 * The creator used for drawing trapezoid kind of corner mark
 *
 * @author https://github.com/DroidWorkerLYF
 */
public class TrapezoidMarkCreator implements IMarkCreator {
    /**
     * 绘制参数
     * Attribute for drawing
     */
    private TrapezoidAttribute mTrapezoidAttribute;
    /**
     * 梯形的画笔
     * The paint used for drawing rect
     */
    private Paint mRectPaint;
    /**
     * 文字的画笔
     * The paint used for drawing text
     */
    private TextPaint mTextPaint;
    /**
     * 梯形的绘制路径
     * The drawing path
     */
    private Path mPath;
    /**
     * 上一次绘制的位置,如果位置和当前位置相同,则不在重新设置path
     * The location of last time drawing,if it is equal to current location,then we don't
     * need to reset path.
     */
    private int mPreLocation;

    public TrapezoidMarkCreator(){
        mRectPaint = new Paint();
        mRectPaint.setAntiAlias(true);

        mTextPaint = new TextPaint();
        mTextPaint.setAntiAlias(true);

        mPath = new Path();
    }

    @Override
    public void setAttribute(TypedArray typedArray, float density) {
        mTrapezoidAttribute = new TrapezoidAttribute(typedArray, density);
    }

    @Override
    public void setAttribute(CommonAttribute commonAttribute) {
        mTrapezoidAttribute = (TrapezoidAttribute) commonAttribute;
    }

    @Override
    public CommonAttribute getAttribute() {
        return mTrapezoidAttribute;
    }

    @Override
    public int getType() {
        return MarkCreatorFactory.TYPE_TRAPEZOID;
    }

    @Override
    public void onDrawMark(Canvas canvas) {
        if(mTrapezoidAttribute == null){
            return;
        }

        setPath(canvas.getWidth(), canvas.getHeight());
        mRectPaint.setStyle(Paint.Style.STROKE);
        mRectPaint.setStrokeWidth(mTrapezoidAttribute.getStrokeWidth());
        mRectPaint.setColor(mTrapezoidAttribute.getStrokeColor());
        canvas.drawPath(mPath, mRectPaint);
        mRectPaint.setStyle(Paint.Style.FILL);
        mRectPaint.setColor(mTrapezoidAttribute.getBackgroundColor());
        canvas.drawPath(mPath, mRectPaint);
        mTextPaint.setColor(mTrapezoidAttribute.getTextColor());
        mTextPaint.setTextSize(mTrapezoidAttribute.getTextSize());
        drawText(canvas);
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
        final int longSide = mTrapezoidAttribute.getLongSide();
        final int shortSide = mTrapezoidAttribute.getShortSide();
        final int curLocation = mTrapezoidAttribute.getLocation();
        if(mPreLocation == curLocation){
            return;
        }
        mPath.reset();
        switch (mTrapezoidAttribute.getLocation()){
            case TrapezoidAttribute.LEFT_TOP:
                mPath.moveTo(shortSide, 0);
                mPath.lineTo(longSide, 0);
                mPath.lineTo(0, longSide);
                mPath.lineTo(0, shortSide);
                mPath.lineTo(shortSide, 0);
                break;
            case TrapezoidAttribute.RIGHT_TOP:
                mPath.moveTo(width - shortSide, 0);
                mPath.lineTo(width - longSide, 0);
                mPath.lineTo(width, longSide);
                mPath.lineTo(width, shortSide);
                mPath.lineTo(width - shortSide, 0);
                break;
            case TrapezoidAttribute.LEFT_BOTTOM:
                mPath.moveTo(0, height - longSide);
                mPath.lineTo(longSide, height);
                mPath.lineTo(shortSide, height);
                mPath.lineTo(0, height - shortSide);
                mPath.lineTo(0, height - longSide);
                break;
            case TrapezoidAttribute.RIGHT_BOTTOM:
                mPath.moveTo(width, height - longSide);
                mPath.lineTo(width - longSide, height);
                mPath.lineTo(width - shortSide, height);
                mPath.lineTo(width, height - shortSide);
                mPath.lineTo(width, height - longSide);
                break;
        }
        mPath.close();
        mPreLocation = curLocation;
    }

    /**
     * 绘制文字
     * Draw text
     *
     * @param canvas 画布
     */
    private void drawText(Canvas canvas){
        final Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        if(fontMetrics == null){
            return;
        }
        final int width = canvas.getWidth();
        final int height = canvas.getHeight();
        final String text = mTrapezoidAttribute.getText();
        final int textHeight = (int) Math.ceil(fontMetrics.bottom - fontMetrics.top);
        final int textWidth = (int) mTextPaint.measureText(text);
        final int longSide = mTrapezoidAttribute.getLongSide();
        final int shortSide = mTrapezoidAttribute.getShortSide();
        float centerX = (shortSide + (longSide - shortSide) /2 ) / 2;
        //noinspection SuspiciousNameCombination
        float centerY = centerX;
        canvas.save();
        switch (mTrapezoidAttribute.getLocation()){
            case TrapezoidAttribute.LEFT_TOP:
                canvas.rotate(-45, centerX, centerY);
                break;
            case TrapezoidAttribute.RIGHT_TOP:
                centerX = width - centerX;
                canvas.rotate(45, centerX, centerY);
                break;
            case TrapezoidAttribute.LEFT_BOTTOM:
                centerY = height - centerY;
                canvas.rotate(45, centerX, centerY);
                break;
            case TrapezoidAttribute.RIGHT_BOTTOM:
                centerX = width - centerX;
                centerY = height - centerY;
                canvas.rotate(-45, centerX, centerY);
                break;
        }
        canvas.drawText(text, centerX - textWidth/2, centerY + textHeight/2-fontMetrics.descent,
                mTextPaint);
        canvas.restore();
    }
}
