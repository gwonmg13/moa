package kr.soen.moa.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import kr.soen.moa.R;

public class StartingActivity extends AppCompatActivity {
    TextView account_forgot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("회원가입");
        setContentView(R.layout.activity_starting);
        account_forgot = (TextView)findViewById(R.id.account_forgot);
        String str = "계정을 잊어버리셨나요?";
        account_forgot.setText(Html.fromHtml("<u>" + str + "</u>"));
    }

    public void myClick(View v){
        switch(v.getId())
        {

            case R.id.signup_button:
                Intent intent = new Intent(StartingActivity.this , SignUpActivity.class);
                startActivity(intent);
                break;
            case R.id.login_button:
                Intent intent2 = new Intent(StartingActivity.this , LoginActivity.class);
                startActivity(intent2);
                break;
            case R.id.account_forgot:
                Intent intent3 = new Intent(StartingActivity.this , MainActivity.class);
                startActivity(intent3);
                break;
        }
    }
}
