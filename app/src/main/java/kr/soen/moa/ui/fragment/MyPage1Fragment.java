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
        adapter.addItem("2017-02-14","여러분이 살아 오면서 겪었던 가장 슬픈일은 무엇인가요? 그때 어떤 기분이 들었나 요? 그 슬픔을 어떻게 극복했나요?");
        adapter.addItem("2017-03-22","내가 어린이라는 사실이 참 싫다고 느낄 때는 언제였나요?");
        adapter.addItem("2017-05-16","이웃 아주머니께서 우리 집에 맛있는 빵을 선물해주셨어요. 그런데 빵이 부족 해서 우리 가족이 모두 먹을 수 없어요. 어떻게 하면 좋을까요?");
        adapter.addItem("2017-06-27","대대로 이어져 오는 여러분 가족의 전통은 무엇인가요?");
        adapter.addItem("2017-07-30","콩콩이는 착한 개미 친구에요. 콩콩이의 말을 들을 수 있는건 여러분밖에 없어요! 콩콩이를 위해서 무 엇을 해줄 수 있을까요?");
        adapter.addItem("2017-08-02","단 하루동안 유명해질 수 있다면 무엇을 할 건가요?");

        return title1View;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
    }


}
