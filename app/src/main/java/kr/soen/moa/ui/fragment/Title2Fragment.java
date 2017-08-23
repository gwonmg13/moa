package kr.soen.moa.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import kr.soen.moa.R;
import kr.soen.moa.ui.activity.GuideMainActivity;
import kr.soen.moa.ui.activity.I_GuideMainActivity;
import kr.soen.moa.ui.activity.Main2Activity;

/**
 * Created by manggi on 2017. 8. 17..
 */

public class Title2Fragment extends Fragment implements View.OnClickListener{
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_POSITION = "position";
    private static final String LOG_TAG = Title1Fragment.class.getSimpleName();
    private int position;

    //fragment view
    private Button I_titleGuideBtn;


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

        I_titleGuideBtn = (Button)title1View.findViewById(R.id.button);
        I_titleGuideBtn.setOnClickListener(this);


        return title1View;
    }

    @Override
    public void onClick(View view) {

        ViewCompat.animate(view)
                .setDuration(200)
                .scaleX(0.9f)
                .scaleY(0.9f)
                .setInterpolator(new Title2Fragment.CycleInterpolator())
                .setListener(new ViewPropertyAnimatorListener() {
                    @Override
                    public void onAnimationStart(final View view) {

                    }

                    @Override
                    public void onAnimationEnd(final View view) {
                        switch (view.getId()) {
                            case R.id.button:
                                startActivity(
                                        new Intent(getActivity(), I_GuideMainActivity.class)

                                );
                                break;

                            case R.id.title_btn1:

                            case R.id.title_btn2:

                            case R.id.title_btn3:

                                break;

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
