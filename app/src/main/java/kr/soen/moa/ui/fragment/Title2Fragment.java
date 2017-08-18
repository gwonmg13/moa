package kr.soen.moa.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import kr.soen.moa.R;

/**
 * Created by manggi on 2017. 8. 17..
 */

public class Title2Fragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_POSITION = "position";
    private static final String LOG_TAG = Title1Fragment.class.getSimpleName();
    private int position;

    //fragment view


    public static Title2Fragment newInstance(int position) {

        Title2Fragment fragment2 = new Title2Fragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION,position);
        fragment2.setArguments(b);

        return fragment2;
    }

    public Title2Fragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getArguments().getInt(ARG_POSITION);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View title1View = inflater.inflate(R.layout.title_fragment2,null,false);

        return title1View;
    }

}
