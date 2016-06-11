package com.droidworker.cornermarkviewlib;

import android.content.res.TypedArray;
import android.graphics.Canvas;

import com.droidworker.cornermarkviewlib.attribute.CommonAttribute;

/**
 * 实现此接口,用来绘制角标
 * A creator should implements this creator
 *
 * @author https://github.com/DroidWorkerLYF
 */
public interface IMarkCreator {

    /**
     * 设置绘制参数,在视图的构造方法中使用,读取{@code AttributeSet}中的参数
     * Set drawing attribute,used in constructor
     *
     * @param typedArray drawing attribute
     * @param density    scaling factor
     */
    void setAttribute(TypedArray typedArray, float density);

    /**
     * 设置绘制参数,此方法需要一个完整参数集合,用于在设置了新的Drawer后,没有参数时,设定使用
     * Set drawing attribute
     *
     * @param commonAttribute 绘制参数
     */
    void setAttribute(CommonAttribute commonAttribute);

    /**
     * 获取当前绘制参数,如果需要更新当前绘制参数,可以通过获取当前的参数update
     *
     * @return current drawing attribute
     */
    CommonAttribute getAttribute();

    /**
     * 获取当前的类型
     *
     * @return current mark's type
     */
    int getType();

    /**
     * 绘制
     * Draw mark
     *
     * @param canvas 画布
     */
    void onDrawMark(Canvas canvas);
}
