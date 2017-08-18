package kr.soen.moa.ui.fragment;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import kr.soen.moa.R;
import kr.soen.moa.ui.activity.Main2Activity;

/**
 * Created by manggi on 2017. 8. 17..
 */

public class Title1Fragment extends Fragment implements View.OnClickListener{

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_POSITION = "position";
    private static final String LOG_TAG = Title1Fragment.class.getSimpleName();
    private int position;

    //fragment view controls
    private TextView titleBtn1,titleBtn2,titleBtn3;
    private Button titleGuideBtn;


    public static Title1Fragment newInstance(int position) {

        Title1Fragment f = new Title1Fragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION,position);
        f.setArguments(b);

        return f;
    }

    public Title1Fragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getArguments().getInt(ARG_POSITION);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View title1View = inflater.inflate(R.layout.title_fragment1,container,false);
        titleBtn1 = (TextView)title1View.findViewById(R.id.title_btn1);
        titleBtn1.setOnClickListener(this);

        titleBtn2 = (TextView)title1View.findViewById(R.id.title_btn2);
        titleBtn2.setOnClickListener(this);

        titleBtn3 = (TextView)title1View.findViewById(R.id.title_btn3);
        titleBtn3.setOnClickListener(this);

        titleGuideBtn = (Button)title1View.findViewById(R.id.button);
        titleGuideBtn.setOnClickListener(this);


        return title1View;
    }

    @Override
    public void onClick(View view) {
        ViewCompat.animate(view)
                .setDuration(200)
                .scaleX(0.9f)
                .scaleY(0.9f)
                .setInterpolator(new CycleInterpolator())
                .setListener(new ViewPropertyAnimatorListener() {
                    @Override
                    public void onAnimationStart(final View view) {

                    }

                    @Override
                    public void onAnimationEnd(final View view) {
                        switch (view.getId()) {
                            case R.id.title_btn1:

                            case R.id.title_btn2:

                            case R.id.title_btn3:
                                startActivity(
                                        new Intent(getActivity(), Main2Activity.class)
                                );
                                break;
                            case R.id.button:
                                startActivity(
                                        new Intent(getActivity(), Main2Activity.class)

                                );

                            default:
                                break;
                        }
                    }

                    @Override
                    public void onAnimationCancel(final View view) {

                    }
                })
                .withLayer()
                .start();
    }

    private class CycleInterpolator implements android.view.animation.Interpolator {

        private final float mCycles = 0.5f;

        @Override
        public float getInterpolation(final float input) {
            return (float) Math.sin(2.0f * mCycles * Math.PI * input);
        }
    }

}
