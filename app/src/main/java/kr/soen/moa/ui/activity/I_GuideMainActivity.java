package kr.soen.moa.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import kr.soen.moa.R;

public class I_GuideMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide2_main);
    }
    public void myClick(View v){
        switch (v.getId()){
            case R.id.i_guide1:
                startActivity(new Intent(this,I_Guide1Activity.class));
                break;
            case R.id.i_guide2:
                startActivity(new Intent(this,I_Guide2Activity.class));
                break;
            case R.id.i_guide3:
                startActivity(new Intent(this,I_Guide3Activity.class));
                break;
        }
    }
}
