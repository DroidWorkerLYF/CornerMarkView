package com.droidworker.cornermarkviewlib.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TextView;

import com.droidworker.cornermarkviewlib.CornerMarkLocation;
import com.droidworker.cornermarkviewlib.CornerMarkType;
import com.droidworker.cornermarkviewlib.R;
import com.droidworker.cornermarkviewlib.drawable.ICornerMarkDrawable;

/**
 * @author https://github.com/DroidWorkerLYF
 */
public class CornerMarkView extends TextView {
    private ICornerMarkDrawable mBackground;
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
    }

    public void setMarkBackground(ICornerMarkDrawable background){
        if(mBackground == background){
            return;
        }
        mBackground = background;
        mBackground.setLocation(CornerMarkLocation.convert2Location(mLocation));
//        if(Build.VERSION.SDK_INT > 16){
//            super.setBackground(background.getDrawable());
//        } else {
//            //noinspection deprecation
//            super.setBackgroundDrawable(background.getDrawable());
//        }
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
                mBackground.onMeasure(MeasureSpec.makeMeasureSpec(getMeasuredWidth(), MeasureSpec
                        .EXACTLY), MeasureSpec.makeMeasureSpec(getMeasuredHeight(), MeasureSpec
                        .EXACTLY));
            }
//            int[] size = mBackground.onMeasure(widthMeasureSpec, heightMeasureSpec);
//            if(size == null || size.length != 2){
//                super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//            } else {
//                setMeasuredDimension(size[0], size[1]);
//            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(mBackground == null){
            return;
        }
        mBackground.getDrawable().draw(canvas);
        super.onDraw(canvas);
        mBackground.restore(canvas);
    }
}
