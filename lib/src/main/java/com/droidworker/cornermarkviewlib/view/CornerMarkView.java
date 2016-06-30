package com.droidworker.cornermarkviewlib.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

import com.droidworker.cornermarkviewlib.CornerMarkFactory;
import com.droidworker.cornermarkviewlib.CornerMarkLocation;
import com.droidworker.cornermarkviewlib.CornerMarkType;
import com.droidworker.cornermarkviewlib.MarkDrawableCache;
import com.droidworker.cornermarkviewlib.R;
import com.droidworker.cornermarkviewlib.drawable.CornerMarkDrawable;

/**
 * 支持设置角标drawable的View
 * A custom view that support use {@code CornerMarkDrawable} as background;
 *
 * @author https://github.com/DroidWorkerLYF
 */
public class CornerMarkView extends TextView {
    private CornerMarkDrawable mBackground;
    private CornerMarkLocation mLocation;

    public CornerMarkView(Context context) {
        this(context, null, 0);
    }

    public CornerMarkView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CornerMarkView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CornerMark);
        int location = typedArray.getInt(R.styleable.CornerMark_mark_location, 0);
        int type = typedArray.getInt(R.styleable.CornerMark_mark_type, 0);
        typedArray.recycle();
        mLocation = CornerMarkLocation.convert2Location(location);
        CornerMarkType markType = CornerMarkType.convert2Type(type);
        if(markType != null){
            setMarkBackground(CornerMarkFactory.create(markType, context, attrs));
        }
    }

    /**
     * 设置角标drawable
     *
     * @param background 角标drawable
     */
    public void setMarkBackground(CornerMarkDrawable background){
        if(background == null ||mBackground == background){
            return;
        }
        mBackground = background;
        mBackground.setLocation(mLocation);
        if(Build.VERSION.SDK_INT > 16){
            super.setBackground(background);
        } else {
            //noinspection deprecation
            super.setBackgroundDrawable(background);
        }
    }

    /**
     * 返回现在用于绘制的角标Drawable
     * @return current background
     */
    public CornerMarkDrawable getMarkBackground(){
        return mBackground;
    }

    /**
     * 从缓存中获取给定类型的CornerMarkDrawable,并把当前的放入缓存
     * Put current background drawable to cache and get a drawable according to the type and location from cache,
     * if we get a null,then we need create a new drawable.
     *
     * @param type 角标类型
     * @return CornerMarkDrawable
     */
    public CornerMarkDrawable getMarkDrawable(int type, CornerMarkLocation location){
        CornerMarkDrawable background = getMarkBackground();
        if(background != null){
            MarkDrawableCache.getInstance().put(background.getMarkType().getType() + mLocation.getLocation(), background);
        }
        setLocation(location);
        CornerMarkDrawable recycled = MarkDrawableCache.getInstance().get(type + location.getLocation());
        if(recycled == null){
            return null;
        }
        setMarkBackground(recycled);
        return recycled;
    }

    /**
     * 获取当前角标drawable的类型
     *
     * @return current type of background
     */
    public CornerMarkType getMarkType(){
        if(mBackground != null){
            return mBackground.getMarkType();
        }
        return null;
    }

    /**
     * 设置位置
     * Set location
     *
     * @param location 位置
     */
    public void setLocation(CornerMarkLocation location){
        if(location == null || mLocation == location){
            return;
        }
        mLocation = location;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if(mBackground == null){
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        } else {
            if(mBackground.needChangeViewSize()){
                int[] size = mBackground.onMeasure(widthMeasureSpec, heightMeasureSpec);
                if(size != null){
                    setMeasuredDimension(size[0], size[1]);
                }
            } else {
                super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(mBackground == null){
            return;
        }
        mBackground.draw(canvas);
        super.onDraw(canvas);
        mBackground.restore(canvas);
    }
}
