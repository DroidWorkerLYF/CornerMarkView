package com.droidworker.cornermarkviewlib;

/**
 * 位置的枚举
 * Enum of location
 *
 * @author https://github.com/DroidWorkerLYF
 */
public enum CornerMarkLocation {
    /**
     * 左上角
     * Location:LEFT_TOP
     */
    LEFT_TOP(1),
    /**
     * 右上角
     * Location:RIGHT_TOP
     */
    RIGHT_TOP(2),
    /**
     * 左下角
     * Location:LEFT_BOTTOM
     */
    LEFT_BOTTOM(3),
    /**
     * 右下角
     * Location:RIGHT_BOTTOM
     */
    RIGHT_BOTTOM(4);

    private int mLocation;

    CornerMarkLocation(int location) {
        mLocation = location;
    }

    public int getLocation() {
        return mLocation;
    }

    /**
     * 把参数转换为位置
     * Convert the given parameter to location
     *
     * @param location location
     * @return 对应的位置
     */
    public static CornerMarkLocation convert2Location(int location) {
        switch (location) {
            case 1:
                return LEFT_TOP;
            case 2:
                return RIGHT_TOP;
            case 3:
                return LEFT_BOTTOM;
            case 4:
                return RIGHT_BOTTOM;
        }
        return null;
    }
}
