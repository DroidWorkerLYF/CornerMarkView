package com.droidworker.cornermarkviewlib.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.droidworker.cornermarkviewlib.CornerMarkHelper;
import com.droidworker.cornermarkviewlib.IMarkCreator;
import com.droidworker.cornermarkviewlib.MarkCreatorFactory;
import com.droidworker.cornermarkviewlib.attribute.CommonAttribute;

/**
 * 带有角标的ImageView
 * An ImageView support corner mark
 *
 * @author https://github.com/DroidWorkerLYF
 */
public class CMImageView extends ImageView {
    /**
     * 角标绘制Helper
     * CornerMarkHelper
     */
    private CornerMarkHelper mHelper;

    public CMImageView(Context context) {
        this(context, null);
    }

    public CMImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //初始化
        mHelper = new CornerMarkHelper(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mHelper != null) {
            mHelper.onDraw(canvas);
        }
    }

    /**
     * 设置角标的类型,根据指定的类型创建一个对应的drawer
     * Set creator according to the given type
     *
     * @param type 角标的类型
     */
    public void setCornerMarkType(int type) {
        if (mHelper != null) {
            IMarkCreator creator = MarkCreatorFactory.create(type);
            if(creator != null){
                mHelper.setMarkCreator(creator);
            }
        }
    }

    /**
     * 设置绘制参数
     * Set attribute
     *
     * @param commonAttribute 绘制参数
     */
    public void setAttribute(CommonAttribute commonAttribute) {
        if (mHelper != null) {
            mHelper.setAttribute(commonAttribute);
            invalidate();
        }
    }

    /**
     * @return 绘制参数 attribute for drawing
     */
    public CommonAttribute getAttribute() {
        if (mHelper == null) {
            return null;
        }
        return mHelper.getAttribute();
    }

    /**
     * 获取creator
     *
     * @return creator
     */
    public IMarkCreator getMarkCreator() {
        if (mHelper == null) {
            return null;
        }
        return mHelper.getMarkCreator();
    }
}
