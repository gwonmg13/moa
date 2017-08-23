package kr.soen.moa.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import kr.soen.moa.R;
import kr.soen.moa.ui.activity.LoginActivity;
import kr.soen.moa.ui.activity.random_play;

/**
 * Created by hong on 2017-08-21.
 */

public class IndicatorFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section-icon";
    private static final String ARG_SECTION_COLOR = "section-color";
    private static final String ARG_SECTION_STR1 = "section-str1";
    private static final String ARG_SECTION_STR2 = "section-str2";
    private static final String ARG_SECTION_INDEX = "section_index";

    public static IndicatorFragment newInstance(int color, int icon,String str1,String str2, int i) {
        int index=i;

        IndicatorFragment fragment = new IndicatorFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, icon);
        args.putInt(ARG_SECTION_COLOR, color);
        args.putString(ARG_SECTION_STR1,str1);
        args.putString(ARG_SECTION_STR2,str2);
        args.putInt(ARG_SECTION_INDEX,i);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.start_view, container, false);
        rootView.setBackgroundColor(ContextCompat.getColor(getContext(), getArguments().getInt(ARG_SECTION_COLOR)));
        ImageView image = (ImageView) rootView.findViewById(R.id.imageView);
        image.setImageResource(getArguments().getInt(ARG_SECTION_NUMBER));
        TextView textView1 = (TextView) rootView.findViewById(R.id.textView);
        textView1.setText(getArguments().getString(ARG_SECTION_STR1));
        TextView textView2 = (TextView) rootView.findViewById(R.id.textView2);
        textView2.setText(getArguments().getString(ARG_SECTION_STR2));
        Button button = (Button)rootView.findViewById(R.id.button);
        int index = getArguments().getInt(ARG_SECTION_INDEX);
        if(index==4){
            button.setVisibility(View.VISIBLE);
            button.setClickable(true);
        }else{
            button.setVisibility(View.INVISIBLE);
            button.setClickable(false);
        }
        button.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        //여기에 이벤트를 적어주세요
                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                        startActivity(intent);
                    }
                }
        );

        return rootView;
    }


}