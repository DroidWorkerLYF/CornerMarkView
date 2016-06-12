package com.droidworker.cornermarkviewlib.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.droidworker.cornermarkviewlib.CornerMarkHelper;

/**
 * @author https://github.com/DroidWorkerLYF
 */
public class CornerMarkView extends View {
    private CornerMarkHelper mHelper;

    public CornerMarkView(Context context) {
        this(context, null, 0);
    }

    public CornerMarkView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CornerMarkView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mHelper = new CornerMarkHelper(context, attrs);
    }
}
