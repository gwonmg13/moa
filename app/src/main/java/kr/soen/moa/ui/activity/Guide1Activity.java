package kr.soen.moa.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import kr.soen.moa.R;

public class Guide1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide1);
        setTitle("참고 사항");
    }
}
