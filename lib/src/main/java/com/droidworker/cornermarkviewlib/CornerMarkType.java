package com.droidworker.cornermarkviewlib;

import com.droidworker.cornermarkviewlib.drawable.RectangleMarkDrawable;
import com.droidworker.cornermarkviewlib.drawable.TrapezoidMarkDrawable;

/**
 * 角标drawable的类型
 * Corner mark's type
 *
 * @author https://github.com/DroidWorkerLYF
 */
public enum CornerMarkType {
    /**
     * 梯形类型
     * Trapezoid type
     */
    TYPE_TRAPEZOID(10, TrapezoidMarkDrawable.class.getName()),
    /**
     * 支持圆角,渐变色等等,就是android.graphics.drawable.GradientDrawable
     * Rectangle type
     */
    TYPE_RECTANGLE(20, RectangleMarkDrawable.class.getName());

    private int type;
    private String clazz;

    CornerMarkType(int type, String clazz){
        this.type = type;
        this.clazz = clazz;
    }

    public int getType(){
        return type;
    }

    public String getClazz(){
        return clazz;
    }

    /**
     * 将int值转为对应类型
     *
     * @param type 类型对应的int值
     * @return corresponding {@code CornerMarkType}
     */
    public static CornerMarkType convert2Type(int type){
        switch (type){
            case 10:
                return TYPE_TRAPEZOID;
            case 20:
                return TYPE_RECTANGLE;
        }
        return null;
    }
}
