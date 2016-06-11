package com.droidworker.cornermarkviewlib;

/**
 * 用来创建所需的实现了{@code IMarkCreator}接口的Drawer,如需要增加新的类型,再次定义好type,在create方法中
 * 返回对应的Drawer即可
 * @author https://github.com/DroidWorkerLYF
 */
public class MarkCreatorFactory {
    /**
     * 梯形类型
     * Trapezoid type
     */
    public static final int TYPE_TRAPEZOID = 1;
    /**
     * 矩形类型
     * Rectangle type
     */
    public static final int TYPE_RECTANGLE = 2;

    private MarkCreatorFactory(){

    }

    /**
     * 根据指定的类型,返回对应的Drawer
     * Return a creator according to the given type
     *
     * @param type 角标类型 type of corner mark
     * @return creator
     */
    public static IMarkCreator create(int type){
        switch (type) {
            case TYPE_RECTANGLE:
                return new RectangleMarkCreator();
            case TYPE_TRAPEZOID:
            default:
                return new TrapezoidMarkCreator();
        }
    }
}
