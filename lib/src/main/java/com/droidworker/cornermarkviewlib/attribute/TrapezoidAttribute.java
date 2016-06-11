package com.droidworker.cornermarkviewlib.attribute;

import android.content.res.TypedArray;

import com.droidworker.cornermarkviewlib.R;

/**
 * 绘制梯形角标的参数
 * The creator of drawing trapezoid
 *
 * @author https://github.com/DroidWorkerLYF
 */
public class TrapezoidAttribute extends CommonAttribute {
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

    public TrapezoidAttribute() {

    }

    public TrapezoidAttribute(TypedArray typedArray, float density) {
        super(typedArray, density);
    }

    @Override
    protected void initAttribute(TypedArray typedArray) {
        mLongSide = typedArray.getDimensionPixelSize(R.styleable.CornerMark_mark_long_side, dp2Px(DEFAULT_LONG_SIDE));
        mSideRatio = typedArray.getFloat(R.styleable.CornerMark_mark_side_ratio, DEFAULT_SIDE_RATIO);
        mShortSide = typedArray.getDimensionPixelSize(R.styleable.CornerMark_mark_short_side, (int) (mLongSide / mSideRatio));
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
}
