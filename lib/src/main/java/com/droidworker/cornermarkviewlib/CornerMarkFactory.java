package com.droidworker.cornermarkviewlib;

import android.content.Context;
import android.util.AttributeSet;

import com.droidworker.cornermarkviewlib.drawable.CornerMarkDrawable;


/**
 * 创建{@code CornerMarkDrawable}的工厂
 * According to the given{@code CornerMarkType} create a {@code CornerMarkDrawable}
 *
 * @author https://github.com/DroidWorkerLYF
 */
public class CornerMarkFactory {

    /**
     * 根据指定的类型和参数创建一个{@code CornerMarkDrawable}
     * According to the given{@code CornerMarkType} and attribute set create a {@code CornerMarkDrawable}
     *
     * @param cornerMarkType 类型(type of mark)
     * @param context 上下文(context)
     * @param attributeSet 参数(attribute set)
     * @return an instance of {@code CornerMarkDrawable}
     */
    public static CornerMarkDrawable create(CornerMarkType cornerMarkType, Context context, AttributeSet attributeSet) {
        try {
            CornerMarkDrawable cornerMarkDrawable = (CornerMarkDrawable) Class.forName(cornerMarkType.getClazz()).newInstance();
            cornerMarkDrawable.setAttributeSet(context, attributeSet);
            return cornerMarkDrawable;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
