package com.droidworker.cornermarkviewlib.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

import com.droidworker.cornermarkviewlib.CornerMarkLocation;
import com.droidworker.cornermarkviewlib.CornerMarkType;
import com.droidworker.cornermarkviewlib.MarkDrawableCache;
import com.droidworker.cornermarkviewlib.R;
import com.droidworker.cornermarkviewlib.drawable.CornerMarkDrawable;

/**
 * @author https://github.com/DroidWorkerLYF
 */
public class CornerMarkView extends TextView {
    private CornerMarkDrawable mBackground;
    private int mLocation;

    public CornerMarkView(Context context) {
        this(context, null, 0);
    }

    public CornerMarkView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CornerMarkView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CornerMark);
        mLocation = typedArray.getInt(R.styleable.CornerMark_mark_location, 1);
        typedArray.recycle();
    }

    public void setMarkBackground(CornerMarkDrawable background){
        if(mBackground == background){
            return;
        }
        mBackground = background;
        mBackground.setLocation(CornerMarkLocation.convert2Location(mLocation));
        if(Build.VERSION.SDK_INT > 16){
            super.setBackground(background);
        } else {
            //noinspection deprecation
            super.setBackgroundDrawable(background);
        }
    }

    /**
     * 返回现在用于绘制的CornerMarkDrawable
     * @return
     */
    public CornerMarkDrawable getMarkBackground(){
        return mBackground;
    }

    /**
     * 从缓存中获取给定类型的CornerMarkDrawable,并把当前的放入缓存
     * @param type 角标类型
     * @return CornerMarkDrawable
     */
    public CornerMarkDrawable getMarkDrawable(int type){
        CornerMarkDrawable background = getMarkBackground();
        MarkDrawableCache.getInstance().put(background.getMarkType().getType(), background);
        CornerMarkDrawable recycled = MarkDrawableCache.getInstance().get(type);
        if(recycled == null){
            return null;
        }
        setMarkBackground(recycled);
        return recycled;
    }

    public CornerMarkType getMarkType(){
        if(mBackground != null){
            return mBackground.getMarkType();
        }
        return null;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if(mBackground == null){
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        } else {
            if(mBackground.getMarkType() == CornerMarkType.TYPE_TRAPEZOID){
                int[] size = mBackground.onMeasure(widthMeasureSpec, heightMeasureSpec);
                setMeasuredDimension(size[0], size[1]);
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
