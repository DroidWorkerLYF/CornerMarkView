package com.droidworker.cornermarkview;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

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
    private List<Data> mList = new ArrayList();
    private Random random = new Random();
    private TrapezoidDrawable trapezoidDrawable;
    private GradientDrawable gradientDrawable;

    public Adapter(){
        for(int i=0;i<100;i++){
            Data data = new Data();
            data.type = random.nextInt(2) + 1;
            data.text = String.valueOf(i + 100);
            mList.add(data);
        }

        trapezoidDrawable = new TrapezoidDrawable();
        trapezoidDrawable.setColor(Color.parseColor("#fe8c2f"));
        trapezoidDrawable.setLongSide(120);
        trapezoidDrawable.setShortSide(45);

        gradientDrawable= new GradientDrawable();
        gradientDrawable.setCornerRadii(new float[]{0, 0, 6, 6, 0, 0, 0, 0});
        gradientDrawable.setColor(Color.parseColor("#5895ed"));
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
        viewHolder.cornerMarkView.setMarkBackground(gradientDrawable);
        viewHolder.cornerMarkView.setText(data.text);
        return convertView;
    }

    public class ViewHolder {
        ImageView imageView;
        CornerMarkView cornerMarkView;
    }

    public class Data {
        int type;
        String text;
    }
}
