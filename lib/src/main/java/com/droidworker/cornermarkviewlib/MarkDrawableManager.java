package com.droidworker.cornermarkviewlib;

import android.util.SparseArray;

import com.droidworker.cornermarkviewlib.drawable.CornerMarkDrawable;

import java.util.ArrayList;

/**
 * @author https://github.com/DroidWorkerLYF
 */
public class MarkDrawableManager {
    private static final MarkDrawableManager sInstance = new MarkDrawableManager();
    private SparseArray<ArrayList<CornerMarkDrawable>> recycledDrawable = new SparseArray<>();

    private MarkDrawableManager(){

    }

    public static MarkDrawableManager getInstance(){
        return sInstance;
    }

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
