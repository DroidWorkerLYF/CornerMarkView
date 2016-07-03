package com.droidworker.cornermarkview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.droidworker.cornermarkviewlib.CornerMarkLocation;
import com.droidworker.cornermarkviewlib.CornerMarkType;
import com.droidworker.cornermarkviewlib.drawable.BookMarkDrawable;
import com.droidworker.cornermarkviewlib.drawable.CornerMarkDrawable;
import com.droidworker.cornermarkviewlib.drawable.RectangleMarkDrawable;
import com.droidworker.cornermarkviewlib.drawable.TrapezoidMarkDrawable;
import com.droidworker.cornermarkviewlib.view.CornerMarkView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author https://github.com/DroidWorkerLYF
 */
public class Adapter extends BaseAdapter {
    private static final String TAG = Adapter.class.getSimpleName();
    private List<Data> mList = new ArrayList<>();
    private int[] text = new int[]{R.string.text_1, R.string.text_2, R.string.text_3, R.string.text_4,
            R.string.text_5,R.string.text_6,R.string.text_7,R.string.text_8,R.string.text_9,
            R.string.text_10,R.string.text_11,R.string.text_12, R.string.text_13, R.string.text_14};

    public Adapter(Context context){
        final int blue = context.getResources().getColor(R.color.blue);
        final int green = context.getResources().getColor(R.color.green);
        final int red = context.getResources().getColor(R.color.red);
        final int orange = context.getResources().getColor(R.color.orange);
        for(int i=0;i<100;i++){
            Data data = new Data();
            if(i % 2 == 0){
                data.color = blue;
            } else if(i %3 == 0){
                data.color = green;
            } else {
                data.color = red;
            }
            data.textId = text[i % 14];
            if(data.textId == R.string.text_13 || data.textId == R.string.text_14){
                data.type = CornerMarkType.TYPE_TRAPEZOID.getType();
                data.color = orange;
            } else if(i % 10 == 0){
                data.type = CornerMarkType.TYPE_BOOKMARK.getType();
                data.textId = R.string.text_12;
            } else {
                data.type = CornerMarkType.TYPE_RECTANGLE.getType();
            }
            mList.add(data);
        }
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.image);
            viewHolder.cornerMarkView = (CornerMarkView) convertView.findViewById(R.id.corner_mark);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Data data = mList.get(position);

        CornerMarkType cornerMarkType = viewHolder.cornerMarkView.getMarkType();
        int type = -1;
        if(cornerMarkType != null){
            type = cornerMarkType.getType();
        }
        if(type == data.type){
            CornerMarkDrawable cornerMarkDrawable = viewHolder.cornerMarkView
                    .getMarkBackground();
            cornerMarkDrawable.setColor(data.color);
        } else {
            CornerMarkDrawable drawable = viewHolder.cornerMarkView.getMarkDrawable(data.type,
                    CornerMarkLocation.RIGHT_TOP);
            if(drawable != null){
                drawable.setColor(data.color);
            } else if(data.type == CornerMarkType.TYPE_TRAPEZOID.getType()){
                viewHolder.cornerMarkView.setMarkBackground(createTrapezoid(data.color));
            } else if(data.type == CornerMarkType.TYPE_RECTANGLE.getType()){
                viewHolder.cornerMarkView.setMarkBackground(createGradient(data.color));
            } else if(data.type == CornerMarkType.TYPE_BOOKMARK.getType()){
                viewHolder.cornerMarkView.setMarkBackground(createBookMark(data.color));
            }
        }
        viewHolder.cornerMarkView.setText(data.textId);
        return convertView;
    }

    public TrapezoidMarkDrawable createTrapezoid(int color){
        Log.e(TAG, "createTrapezoid");
        TrapezoidMarkDrawable trapezoidMarkDrawable = new TrapezoidMarkDrawable();
        trapezoidMarkDrawable.setColor(color);
        trapezoidMarkDrawable.setLongSide(120);
        trapezoidMarkDrawable.setShortSide(45);
        return trapezoidMarkDrawable;
    }

    public RectangleMarkDrawable createGradient(int color){
        Log.e(TAG, "createGradient");
        RectangleMarkDrawable rectangleMarkDrawable= new RectangleMarkDrawable();
        rectangleMarkDrawable.setRadiusArray(new float[]{0, 0, 6, 6, 0, 0, 0, 0});
        rectangleMarkDrawable.setColor(color);
        return rectangleMarkDrawable;
    }

    public BookMarkDrawable createBookMark(int color){
        Log.e(TAG, "createBookMark");
        BookMarkDrawable bookMarkDrawable = new BookMarkDrawable();
        bookMarkDrawable.setColor(color);
        bookMarkDrawable.setAngle(160);
        bookMarkDrawable.setHorizontalSide(60);
        bookMarkDrawable.setVerticalSide(120);
        return bookMarkDrawable;
    }

    public class ViewHolder {
        ImageView imageView;
        CornerMarkView cornerMarkView;
    }

    public class Data {
        int color;
        int type;
        int textId;
    }
}
