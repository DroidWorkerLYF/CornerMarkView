package com.droidworker.cornermarkview;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.droidworker.cornermarkviewlib.CornerMarkType;
import com.droidworker.cornermarkviewlib.MarkDrawableManager;
import com.droidworker.cornermarkviewlib.drawable.CornerMarkDrawable;
import com.droidworker.cornermarkviewlib.drawable.GradientDrawable;
import com.droidworker.cornermarkviewlib.drawable.TrapezoidDrawable;
import com.droidworker.cornermarkviewlib.view.CornerMarkView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author https://github.com/DroidWorkerLYF
 */
public class Adapter extends BaseAdapter {
    private List<Data> mList = new ArrayList<>();
    private static final int BLUE = Color.parseColor("#5895ed");
    private static final int GREEN = Color.parseColor("#a2c21d");
    private static final int RED = Color.parseColor("#EF534e");
    private static final int ORANGE = Color.parseColor("#fe8c2f");

    public Adapter(){
        Random random = new Random();
        for(int i=0;i<100;i++){
            Data data = new Data();
            if(i % 2 == 0){
                data.color = ORANGE;
            } else if(i %3 == 0){
                data.color = BLUE;
            } else if(i % 7 == 0){
                data.color = GREEN;
            } else {
                data.color = RED;
            }
            data.type = random.nextInt(2) + 1;
            data.text = String.valueOf(i + 100);
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
        if(cornerMarkType == null){
            if(data.type == CornerMarkType.TYPE_TRAPEZOID.getType()){
                viewHolder.cornerMarkView.setMarkBackground(createTrapezoid(data.color));
            } else if(data.type == CornerMarkType.TYPE_GRADIENT.getType()){
                viewHolder.cornerMarkView.setMarkBackground(createGradient(data.color));
            }
        } else {
            final int type = cornerMarkType.getType();
            if(type == data.type){
                if(data.type == CornerMarkType.TYPE_TRAPEZOID.getType()){
                    TrapezoidDrawable drawable = (TrapezoidDrawable) viewHolder.cornerMarkView.getMarkBackground();
                    drawable.setColor(data.color);
                } else if(data.type == CornerMarkType.TYPE_GRADIENT.getType()){
                    GradientDrawable drawable = (GradientDrawable) viewHolder.cornerMarkView.getMarkBackground();
                    drawable.setColor(data.color);
                }
            } else if(data.type == CornerMarkType.TYPE_TRAPEZOID.getType()){
                CornerMarkDrawable drawable = viewHolder.cornerMarkView.getMarkBackground();
                MarkDrawableManager.getInstance().put(type, drawable);
                TrapezoidDrawable recycled = (TrapezoidDrawable) MarkDrawableManager.getInstance().get(data.type);
                if(recycled != null){
                    recycled.setColor(data.color);
                    viewHolder.cornerMarkView.setMarkBackground(recycled);
                } else {
                    viewHolder.cornerMarkView.setMarkBackground(createTrapezoid(data.color));
                }

            } else if(data.type == CornerMarkType.TYPE_GRADIENT.getType()){
                CornerMarkDrawable drawable = viewHolder.cornerMarkView.getMarkBackground();
                MarkDrawableManager.getInstance().put(type, drawable);
                GradientDrawable recycled = (GradientDrawable) MarkDrawableManager.getInstance().get(data.type);
                if(recycled != null){
                    viewHolder.cornerMarkView.setMarkBackground(recycled);
                } else {
                    viewHolder.cornerMarkView.setMarkBackground(createGradient(data.color));
                }
            }
        }
        viewHolder.cornerMarkView.setText(data.text);
        return convertView;
    }

    public TrapezoidDrawable createTrapezoid(int color){
        Log.e("lyf", "createTrapezoid");
        TrapezoidDrawable trapezoidDrawable = new TrapezoidDrawable();
        trapezoidDrawable.setColor(color);
        trapezoidDrawable.setLongSide(120);
        trapezoidDrawable.setShortSide(45);
        return trapezoidDrawable;
    }

    public GradientDrawable createGradient(int color){
        Log.e("lyf", "createGradient");
        GradientDrawable gradientDrawable= new GradientDrawable();
        gradientDrawable.setCornerRadii(new float[]{0, 0, 6, 6, 0, 0, 0, 0});
        gradientDrawable.setColor(color);
        return gradientDrawable;
    }

    public class ViewHolder {
        ImageView imageView;
        CornerMarkView cornerMarkView;
    }

    public class Data {
        int color;
        int type;
        String text;
    }
}
