package kr.soen.moa.ui.fragment;

import android.support.v4.app.ListFragment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kr.soen.moa.ui.adapter.MyPageViewAdapter;
import kr.soen.moa.ui.data.MyPageListViewItem;

/**
 * Created by manggi on 2017. 8. 22..
 */

public class MyPage2Fragment extends ListFragment{

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_POSITION = "position";
    private static final String LOG_TAG = Title1Fragment.class.getSimpleName();
    private int position;

    MyPageViewAdapter adapter;

    public static MyPage2Fragment newInstance(int position) {

        MyPage2Fragment f = new MyPage2Fragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION,position);
        f.setArguments(b);

        return f;
    }

    public MyPage2Fragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getArguments().getInt(ARG_POSITION);

    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {


        adapter = new MyPageViewAdapter();

        setListAdapter(adapter);

        adapter.addItem("2017-02-02","안녕안녕");
        adapter.addItem("2017-02-02","안녕안녕");
        adapter.addItem("2017-02-02","안녕안녕");
        adapter.addItem("2017-02-02","안녕안녕");
        adapter.addItem("2017-02-02","안녕안녕");
        adapter.addItem("2017-02-02","안녕안녕");



        return super.onCreateView(inflater, container, savedInstanceState);

    }


}
