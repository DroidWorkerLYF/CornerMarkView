package com.droidworker.cornermarkviewlib.attribute;

import android.content.res.TypedArray;

import com.droidworker.cornermarkviewlib.R;

/**
 * 绘制矩形的参数,支持圆角矩形(各角半径相同)
 * The creator of drawing rectangle, support rounded rect
 *
 * @author https://github.com/DroidWorkerLYF
 */
public class RectangleAttribute extends CommonAttribute {
    /**
     * 默认长边长度
     * Default long side's length
     */
    private static final int DEFAULT_LONG_SIDE = 1;
    /**
     * 默认长短边比例
     * Default long side/short side 's ratio
     */
    private static final int DEFAULT_SIDE_RATIO = 1;
    /**
     * 长边长度
     * Long side's length
     */
    private int mLongSide;
    /**
     * 长边/短边
     * long side/short side
     */
    private float mSideRatio;
    /**
     * 短边
     * Short side's length
     */
    private int mShortSide;
    /**
     * 弧度半径
     * Radius of corner
     */
    private int mRadius;
    /**
     * 左上角是否绘制圆角
     * Whether draw rounded corner at left top
     */
    private boolean mLeftTopRadius;
    /**
     * 右上角是否绘制圆角
     * Whether draw rounded corner at right top
     */
    private boolean mRightTopRadius;
    /**
     * 左下角是否绘制圆角
     * Whether draw rounded corner at left bottom
     */
    private boolean mLeftBottomRadius;
    /**
     * 右下角是否绘制圆角
     * Whether draw rounded corner at right bottom
     */
    private boolean mRightBottomRadius;
    /**
     * 距离最近的横边的距离
     * Distance between long side and the nearest horizontal edge
     */
    private int mMarginVertical;
    /**
     * 距离最近的竖边的距离
     * Distance between short side and the nearest vertical edge
     */
    private int mMarginHorizontal;

    public RectangleAttribute() {

    }

    public RectangleAttribute(TypedArray typedArray, float density) {
        super(typedArray, density);
    }

    @Override
    protected void initAttribute(TypedArray typedArray) {
        mLongSide = typedArray.getDimensionPixelSize(R.styleable.CornerMark_mark_long_side, dp2Px(DEFAULT_LONG_SIDE));
        mSideRatio = typedArray.getFloat(R.styleable.CornerMark_mark_side_ratio, DEFAULT_SIDE_RATIO);
        mShortSide = typedArray.getDimensionPixelSize(R.styleable.CornerMark_mark_short_side, (int) (mLongSide / mSideRatio));
        mRadius = typedArray.getDimensionPixelSize(R.styleable.CornerMark_mark_radius, 0);
        mLeftTopRadius = typedArray.getBoolean(R.styleable.CornerMark_mark_left_top_radius, false);
        mRightTopRadius = typedArray.getBoolean(R.styleable.CornerMark_mark_right_top_radius, false);
        mLeftBottomRadius = typedArray.getBoolean(R.styleable.CornerMark_mark_left_bottom_radius, false);
        mRightBottomRadius = typedArray.getBoolean(R.styleable.CornerMark_mark_right_bottom_radius, false);
        mMarginVertical = typedArray.getDimensionPixelSize(R.styleable.CornerMark_mark_margin_vertical, 0);
        mMarginHorizontal = typedArray.getDimensionPixelSize(R.styleable.CornerMark_mark_margin_horizontal, 0);
    }

    public int getLongSide() {
        return mLongSide;
    }

    public void setLongSide(int longSide) {
        this.mLongSide = longSide;
    }

    public float getSideRatio() {
        return mSideRatio;
    }

    public void setSideRatio(float sideRatio) {
        this.mSideRatio = sideRatio;
    }

    public int getShortSide() {
        return mShortSide;
    }

    public void setShortSide(int shortSide) {
        this.mShortSide = shortSide;
    }

    public int getRadius() {
        return mRadius;
    }

    public void setRadius(int mRadius) {
        this.mRadius = mRadius;
    }

    public boolean isLeftTopRadius() {
        return mLeftTopRadius;
    }

    public void setLeftTopRadius(boolean mLeftTopRadius) {
        this.mLeftTopRadius = mLeftTopRadius;
    }

    public boolean isRightTopRadius() {
        return mRightTopRadius;
    }

    public void setRightTopRadius(boolean mRightTopRadius) {
        this.mRightTopRadius = mRightTopRadius;
    }

    public boolean isLeftBottomRadius() {
        return mLeftBottomRadius;
    }

    public void setLeftBottomRadius(boolean mLeftBottomRadius) {
        this.mLeftBottomRadius = mLeftBottomRadius;
    }

    public boolean isRightBottomRadius() {
        return mRightBottomRadius;
    }

    public void setRightBottomRadius(boolean mRightBottomRadius) {
        this.mRightBottomRadius = mRightBottomRadius;
    }

    public int getMarginVertical() {
        return mMarginVertical;
    }

    public void setMarginVertical(int mMarginVertical) {
        this.mMarginVertical = mMarginVertical;
    }

    public int getMarginHorizontal() {
        return mMarginHorizontal;
    }

    public void setMarginHorizontal(int mMarginHorizontal) {
        this.mMarginHorizontal = mMarginHorizontal;
    }
}
