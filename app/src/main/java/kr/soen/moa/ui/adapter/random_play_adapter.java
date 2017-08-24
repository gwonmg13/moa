package kr.soen.moa.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import kr.soen.moa.R;
import kr.soen.moa.ui.data.talk_list_Info;

/**
 * Created by hong on 2017-08-23.
 */

public class random_play_adapter extends BaseAdapter{
    public ArrayList<talk_list_Info> data = new ArrayList<>();
    public Context context;
    public random_play_adapter(ArrayList<talk_list_Info> data, Context context) {
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
            view = LayoutInflater.from(context).inflate(R.layout.random_list, null);
        }
        TextView textView = (TextView)view.findViewById(R.id.textView);
        textView.setText(data.get(i).text);
        return view;

    }
}
