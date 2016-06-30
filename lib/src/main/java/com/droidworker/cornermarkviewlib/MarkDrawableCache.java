package com.droidworker.cornermarkviewlib;

import android.util.SparseArray;

import com.droidworker.cornermarkviewlib.drawable.CornerMarkDrawable;

import java.util.ArrayList;

/**
 * 角标Drawable的缓存,在列表中,有可能存在多种类型及颜色的角标,缓存已介绍创建过多drawable
 * Cache for holding exist corner mark drawable to prevent creating too much object
 *
 * @author https://github.com/DroidWorkerLYF
 */
public class MarkDrawableCache {
    private static final MarkDrawableCache sInstance = new MarkDrawableCache();
    /**
     * 使用CornerMarkType的类型作为key
     * Use CornerMarkType#getType as key.
     */
    private SparseArray<ArrayList<CornerMarkDrawable>> recycledDrawable = new SparseArray<>();

    private MarkDrawableCache(){

    }

    /**
     * 获取单例
     * @return single instance
     */
    public static MarkDrawableCache getInstance(){
        return sInstance;
    }

    /**
     * 将drawable放入缓存中
     * Put a corner mark drawable into cache
     *
     * @param key 缓存的key
     * @param drawable 角标drawable
     */
    public void put(int key, CornerMarkDrawable drawable){
        ArrayList<CornerMarkDrawable> arrayList;
        if(recycledDrawable.get(key) == null){
            arrayList = new ArrayList<>();
            recycledDrawable.put(key, arrayList);
        } else {
            arrayList = recycledDrawable.get(key);
        }
        arrayList.add(drawable);
    }

    /**
     * 根据指定的key取出缓存的Drawable
     *
     * @param key 缓存的key
     * @return an exist corner mark drawable
     */
    public CornerMarkDrawable get(int key){
        ArrayList<CornerMarkDrawable> arrayList = recycledDrawable.get(key);
        if(arrayList == null || arrayList.size() <= 0){
            return null;
        }
        CornerMarkDrawable cornerMarkDrawable = arrayList.get(0);
        arrayList.remove(cornerMarkDrawable);
        return cornerMarkDrawable;
    }
}
