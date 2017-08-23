package kr.soen.moa.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.textclassifier.TextClassification;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import kr.soen.moa.R;
import kr.soen.moa.ui.activity.talk_list_Info;

/**
 * Created by hong on 2017-08-19.
 */

public class talk_list_adapter extends BaseAdapter {
    ArrayList<talk_list_Info> data = new ArrayList<talk_list_Info>();
    Context context;
    public talk_list_adapter(ArrayList<talk_list_Info> data, Context context) {
        this.data = data;
        this.context = context;
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.talk_list, null);
        }
        TextView textView = (TextView)view.findViewById(R.id.textView);
        textView.setText(data.get(i).text);
        if(data.get(i).check==true){
            textView.setTextColor(Color.argb(255, 125, 196, 255));
        }else{
            textView.setTextColor(Color.argb(255, 96, 96, 96));
        }
        return view;
    }
}
