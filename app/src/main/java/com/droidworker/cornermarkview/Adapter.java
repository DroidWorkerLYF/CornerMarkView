package com.droidworker.cornermarkview;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.droidworker.cornermarkviewlib.IMarkCreator;
import com.droidworker.cornermarkviewlib.attribute.CommonAttribute;
import com.droidworker.cornermarkviewlib.attribute.RectangleAttribute;
import com.droidworker.cornermarkviewlib.attribute.TrapezoidAttribute;
import com.droidworker.cornermarkviewlib.view.CMImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author luoyanfeng@le.com
 */
public class Adapter extends BaseAdapter {
    private List<Data> mList = new ArrayList();

    public Adapter(){
        Random random = new Random();
        for(int i=0;i<100;i++){
            Data data = new Data();
            data.type = random.nextInt(2) + 1;
            data.text = "会员";//String.valueOf(i + 1);
            mList.add(data);
        }
    }

    @Override
    public int getCount() {
        return 100;
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
        if(convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item, parent, false);
        }
        Data data = mList.get(position);
        Random random = new Random();
        CMImageView cmImageView = (CMImageView)convertView;
        IMarkCreator markDrawer = cmImageView.getMarkCreator();
        if(markDrawer == null || markDrawer.getType() != data.type){
            cmImageView.setCornerMarkType(data.type);
            CommonAttribute commonAttribute = createAttribute(data.type);
            commonAttribute.setText(data.text);
            commonAttribute.setLocation(random.nextInt(4) + 1);
            cmImageView.getMarkCreator().setAttribute(commonAttribute);
        } else {
            CommonAttribute commonAttribute = markDrawer.getAttribute();
            if(commonAttribute != null){
                commonAttribute.setText(data.text);
                commonAttribute.setLocation(random.nextInt(4) + 1);
            } else {
                Log.e("lyf", "attr is null");
            }
        }

        return convertView;
    }

    private CommonAttribute createAttribute(int type){
        switch (type){
            case 1:
                default:
                return createTrapezoidAttribute();
            case 2:
                return createRectangleAttribute();
        }
    }

    private TrapezoidAttribute createTrapezoidAttribute(){
        TrapezoidAttribute trapezoidAttribute = new TrapezoidAttribute();
        trapezoidAttribute.setLongSide(120);
        trapezoidAttribute.setShortSide(45);
        trapezoidAttribute.setStrokeColor(Color.parseColor("#fe8c2f"));
        trapezoidAttribute.setBackgroundColor(Color.parseColor("#fe8c2f"));
        trapezoidAttribute.setTextColor(Color.WHITE);
        trapezoidAttribute.setTextSize(36);
        trapezoidAttribute.setStrokeWidth(9);
        return trapezoidAttribute;
    }

    private RectangleAttribute createRectangleAttribute(){
        RectangleAttribute rectangleAttribute = new RectangleAttribute();
        rectangleAttribute.setLongSide(107);
        rectangleAttribute.setShortSide(45);
        rectangleAttribute.setStrokeColor(Color.parseColor("#ef4444"));
        rectangleAttribute.setBackgroundColor(Color.parseColor("#ef4444"));
        rectangleAttribute.setTextColor(Color.WHITE);
        rectangleAttribute.setMarginVertical(24);
        rectangleAttribute.setMarginHorizontal(24);
        rectangleAttribute.setRadius(3);
        rectangleAttribute.setLeftTopRadius(true);
        rectangleAttribute.setRightTopRadius(true);
        rectangleAttribute.setLeftBottomRadius(true);
        rectangleAttribute.setRightBottomRadius(true);
        rectangleAttribute.setTextSize(36);
        rectangleAttribute.setStrokeWidth(9);
        return rectangleAttribute;
    }

    public class Data {
        int type;
        String text;
    }
}
