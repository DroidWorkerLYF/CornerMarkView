package com.droidworker.cornermarkviewlib;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;

import com.droidworker.cornermarkviewlib.attribute.CommonAttribute;

/**
 * 角标绘制的Helper,需要实现角标功能的视图中增加一个此类的实例,调用相应方法即可,
 * 绘制一个角标,需要一个继承自{@code CommonAttribute}的绘制参数类(XxxxAttribute),
 * 一个实现了{@code IMarkCreator}的绘制者,在{@code MarkCreatorFactory}中为此类型角
 * 标定义好type以及相应的返回值,即可.
 * Helper for drawing corner mark.
 *
 * @author https://github.com/DroidWorkerLYF
 */
public class CornerMarkHelper {
    /**
     * 上下文
     */
    private Context mContext;
    /**
     * 绘制者
     */
    private IMarkCreator mIMarkCreator;

    public CornerMarkHelper(Context context, AttributeSet attrs) {
        this.mContext = context;

        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.CornerMark);
        final int type = typedArray.getInt(R.styleable.CornerMark_mark_type,
                MarkCreatorFactory.TYPE_TRAPEZOID);
        IMarkCreator creator = MarkCreatorFactory.create(type);
        if(creator != null){
            setMarkCreator(creator);
        }
        typedArray.recycle();
        this.mIMarkCreator.setAttribute(mContext.obtainStyledAttributes(attrs, R.styleable.CornerMark), mContext.getResources().getDisplayMetrics().density);
    }

    /**
     * 设置Creator
     * Set mark creator
     *
     * @param markCreator 绘制者
     */
    public void setMarkCreator(IMarkCreator markCreator) {
        mIMarkCreator = markCreator;
    }

    /**
     * 设置绘制参数
     * Set attribute
     *
     * @param attribute 绘制参数
     */
    public void setAttribute(CommonAttribute attribute) {
        if (mIMarkCreator == null) {
            return;
        }
        this.mIMarkCreator.setAttribute(attribute);
    }

    /**
     * 获取绘制参数
     * Get attribute
     *
     * @return 绘制参数
     */
    public CommonAttribute getAttribute() {
        if (mIMarkCreator == null) {
            return null;
        }
        return mIMarkCreator.getAttribute();
    }

    /**
     * 获取当前的creator
     *
     * @return current creator
     */
    public IMarkCreator getMarkCreator() {
        return mIMarkCreator;
    }

    /**
     * 绘制
     * Draw the corner mark
     *
     * @param canvas 画布
     */
    public void onDraw(Canvas canvas) {
        if (mIMarkCreator == null) {
            return;
        }
        mIMarkCreator.onDrawMark(canvas);
    }
}
