package com.droidworker.cornermarkviewlib.attribute;

import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.TextUtils;

/**
 * 角标需要的基本参数
 *
 * @author https://github.com/DroidWorkerLYF
 */
public abstract class CommonAttribute {
    /**
     * 左上角
     * Location:LEFT_TOP
     */
    public static final int LEFT_TOP = 1;
    /**
     * 右上角
     * Location:RIGHT_TOP
     */
    public static final int RIGHT_TOP = 2;
    /**
     * 左下角
     * Location:LEFT_BOTTOM
     */
    public static final int LEFT_BOTTOM = 3;
    /**
     * 右下角
     * Location:RIGHT_BOTTOM
     */
    public static final int RIGHT_BOTTOM = 4;
    /**
     * 默认边框宽度
     * Default stroke width
     */
    private static final int DEFAULT_STROKE_WIDTH = 1;
    /**
     * 默认边框颜色
     * Default stroke color
     */
    private static final int DEFAULT_STROKE_COLOR = Color.WHITE;
    /**
     * 默认背景色
     * Default background color
     */
    private static final int DEFAULT_BG_COLOR = Color.BLACK;
    /**
     * 默认文字颜色
     * Default text color
     */
    private static final int DEFAULT_TEXT_COLOR = Color.WHITE;
    /**
     * 默认文字大小
     * Default text size
     */
    private static final int DEFAULT_TEXT_SIZE = 12;
    /**
     * 边框宽度
     * Stroke width
     */
    private int mStrokeWidth;
    /**
     * 边框颜色
     * Stroke color
     */
    private int mStrokeColor;
    /**
     * 背景色
     * Background color
     */
    private int mBackgroundColor;
    /**
     * 文字颜色
     * Text color
     */
    private int mTextColor;
    /**
     * 文字大小
     * Text size
     */
    private int mTextSize;
    /**
     * 文字内容
     * The text needed to draw
     */
    private String mText;
    /**
     * 角标位置
     * The location of corner mark
     */
    private int mLocation;
    /**
     * 缩放比例
     * Scaling factor
     */
    private float mDensity;

    public CommonAttribute() {

    }

    public CommonAttribute(TypedArray typedArray, float density) {
        if (typedArray == null) {
            return;
        }
//        mDensity = density;
//        mStrokeWidth = typedArray.getDimensionPixelSize(R.styleable.CornerMark_mark_stroke_width, dp2Px(DEFAULT_STROKE_WIDTH));
//        mStrokeColor = typedArray.getColor(R.styleable.CornerMark_mark_stroke_color,
//                DEFAULT_STROKE_COLOR);
//        mBackgroundColor = typedArray.getColor(R.styleable.CornerMark_mark_background_color,
//                DEFAULT_BG_COLOR);
//        mTextColor = typedArray.getColor(R.styleable.CornerMark_mark_text_color,
//                DEFAULT_TEXT_COLOR);
//        mTextSize = typedArray.getDimensionPixelSize(R.styleable.CornerMark_mark_text_size, dp2Px(DEFAULT_TEXT_SIZE));
//        mText = typedArray.getString(R.styleable.CornerMark_mark_text);
//        mLocation = typedArray.getInteger(R.styleable.CornerMark_mark_location, LEFT_TOP);
//        initAttribute(typedArray);
        typedArray.recycle();
    }

    /**
     * 因为TypedArray需要回收,所以在回收前,调用子类的解析,而不是在子类的构造方法中处理
     * Subclass init their unique attribute in this method
     *
     * @param typedArray 参数
     */
    protected abstract void initAttribute(TypedArray typedArray);

    public int getStrokeWidth() {
        return mStrokeWidth;
    }

    public void setStrokeWidth(int strokeWidth) {
        mStrokeWidth = strokeWidth;
    }

    public int getStrokeColor() {
        return mStrokeColor;
    }

    public void setStrokeColor(int strokeColor) {
        mStrokeColor = strokeColor;
    }

    public int getBackgroundColor() {
        return mBackgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        mBackgroundColor = backgroundColor;
    }

    public int getTextColor() {
        return mTextColor;
    }

    public void setTextColor(int textColor) {
        mTextColor = textColor;
    }

    public int getTextSize() {
        return mTextSize;
    }

    public void setTextSize(int textSize) {
        mTextSize = textSize;
    }

    public String getText() {
        return TextUtils.isEmpty(mText) ? "" : mText;
    }

    public void setText(String text) {
        mText = text;
    }

    public int getLocation() {
        return mLocation;
    }

    public void setLocation(int location) {
        mLocation = location;
    }

    /**
     * dp转px
     * @param dp 需要被转换的参数(the parameter which need to convert)
     * @return px
     */
    public int dp2Px(float dp) {
        return (int) (dp * mDensity + 0.5f);
    }
}
