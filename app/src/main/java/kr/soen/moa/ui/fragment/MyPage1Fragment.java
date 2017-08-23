package kr.soen.moa.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kr.soen.moa.R;
import kr.soen.moa.ui.adapter.MyPageViewAdapter;
import kr.soen.moa.ui.adapter.TitleListViewAdapter;

/**
 * Created by manggi on 2017. 8. 22..
 */

public class MyPage1Fragment extends ListFragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_POSITION = "position";
    private static final String LOG_TAG = Title1Fragment.class.getSimpleName();
    private int position;


    MyPageViewAdapter adapter;
    public static MyPage1Fragment newInstance(int position) {

        MyPage1Fragment f = new MyPage1Fragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION,position);
        f.setArguments(b);

        return f;
    }

    public MyPage1Fragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getArguments().getInt(ARG_POSITION);

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View title1View = inflater.inflate(R.layout.mypage_fragment1,null,false);

        adapter = new MyPageViewAdapter();

        setListAdapter(adapter);
        return title1View;
    }
}
