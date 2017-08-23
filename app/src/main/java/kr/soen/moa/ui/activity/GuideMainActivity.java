package kr.soen.moa.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import butterknife.BindView;
import butterknife.OnClick;
import kr.soen.moa.R;

public class GuideMainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_main);
    }

    public void myClick(View v){
        switch (v.getId()){
            case R.id.guide1:
                startActivity(new Intent(this,Guide1Activity.class));
                break;
            case R.id.guide2:
                startActivity(new Intent(this,Guide2Activity.class));
                break;
            case R.id.guide3:
                startActivity(new Intent(this,Guide3Activity.class));
                break;
        }
    }
}
