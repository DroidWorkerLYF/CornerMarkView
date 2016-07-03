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
 * 书签样式
 *
 * @author https://github.com/DroidWorkerLYF
 */
public class BookMarkDrawable extends CornerMarkDrawable {
    private Path mPath;
    private Paint mPaint;
    private int mFillColor;
    private int angle;
    private int horizontalSide;
    private int verticalSide;
    private int[] mSize;

    public BookMarkDrawable(){
        mPath = new Path();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    @Override
    public void setAttributeSet(Context context, AttributeSet attributeSet) {
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.CornerMark_BookMark);
        mFillColor = typedArray.getColor(R.styleable.CornerMark_BookMark_bookmark_background_color, Color.WHITE);
        angle = typedArray.getInt(R.styleable.CornerMark_BookMark_angle, 0);
        horizontalSide = typedArray.getDimensionPixelSize(R.styleable
                .CornerMark_BookMark_horizontal_side, 0);
        verticalSide = typedArray.getDimensionPixelSize(R.styleable
                .CornerMark_BookMark_vertical_side, 0);
        typedArray.recycle();
    }

    @Override
    public CornerMarkType getMarkType() {
        return CornerMarkType.TYPE_BOOKMARK;
    }

    @Override
    public void setColor(int color) {
        mFillColor = color;
    }

    @Override
    public int getColor() {
        return mFillColor;
    }

    @Override
    public void draw(Canvas canvas) {
        setPath(canvas.getWidth(), canvas.getHeight());
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setColor(mFillColor);
        canvas.drawPath(mPath, mPaint);
        canvas.save();
        canvas.translate(0, -calculateVertex()/2);
    }

    private void setPath(int width, int height){
        mPaint.reset();
        int vertex = calculateVertex();
        switch (mLocation.getLocation()){
            case 1:
            case 2:
                mPath.moveTo(0, 0);
                mPath.lineTo(width, 0);
                mPath.lineTo(width, height);
                mPath.lineTo(width/2, height - vertex);
                mPath.lineTo(0, height);
                mPath.lineTo(0, 0);
                break;
            case 3:
            case 4:
                break;
        }
        mPath.close();
    }

    private int calculateVertex(){
        int halfAngle = angle/2;
        return (int) (getBounds().width()/2/Math.tan(halfAngle * Math.PI/360));
    }

    @Override
    public boolean needChangeViewSize() {
        return true;
    }

    @Override
    public int[] onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        return mSize;
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

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    public int getHorizontalSide() {
        return horizontalSide;
    }

    public void setHorizontalSide(int horizontalSide) {
        this.horizontalSide = horizontalSide;
        onSizeChanged();
    }

    public int getVerticalSide() {
        return verticalSide;
    }

    public void setVerticalSide(int verticalSide) {
        this.verticalSide = verticalSide;
        onSizeChanged();
    }

    public void onSizeChanged(){
        if (mSize == null) {
            mSize = new int[2];
        }
        mSize[0] = horizontalSide;
        mSize[1] = verticalSide;
    }
}
