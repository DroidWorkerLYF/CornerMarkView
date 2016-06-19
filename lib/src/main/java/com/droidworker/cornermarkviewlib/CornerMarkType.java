package com.droidworker.cornermarkviewlib;

/**
 * @author https://github.com/DroidWorkerLYF
 */
public enum CornerMarkType {
    /**
     * 梯形类型
     * Trapezoid type
     */
    TYPE_TRAPEZOID(1),
    /**
     * 矩形类型
     * Rectangle type
     */
    TYPE_GRADIENT(2);

    private int type;

    CornerMarkType(int type){
        this.type = type;
    }

    public int getType(){
        return this.type;
    }

    public static CornerMarkType convert2Type(int type){
        switch (type){
            case 1:
                return TYPE_TRAPEZOID;
            case 2:
                return TYPE_GRADIENT;
        }
        return null;
    }
}
