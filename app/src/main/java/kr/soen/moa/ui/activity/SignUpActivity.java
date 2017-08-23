package kr.soen.moa.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import kr.soen.moa.R;

public class SignUpActivity extends AppCompatActivity {

    private static final int REQUEST_READ_CONTACTS = 0;


    private LoginTask LoginAuthTask = null;

    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private EditText mPasswordCheckView;
    private EditText mNameView;
    private View mProgressView;
    private View mLoginFormView;

    String sEmail, sPw, sPw_chk, sName;

    //이용약관
    TextView already_signup ;
    TextView access_term;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("회원 가입");

        already_signup = (TextView)findViewById(R.id.link_login);
        String str = "이미 회원이신가요? 로그인";
        already_signup.setText(Html.fromHtml("<u>" + str + "</u>"));

        access_term = (TextView)findViewById(R.id.access_term);
        String str2 ="이용약관 보기";
        access_term.setText(Html.fromHtml("<u>" + str2 + "</u>"));

        //이메일 입력창
        mEmailView = (AutoCompleteTextView)findViewById(R.id.email);
        //비밀번호 입력창
        mPasswordView = (EditText)findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptSignup();
                    return true;
                }
                return false;
            }
        });

        //회원가입 버튼 클릭시
        Button btn_signup = (Button) findViewById(R.id.btn_signup);
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptSignup();
            }
        });

        //비밀번호 확인
        mPasswordCheckView = (EditText)findViewById(R.id.password_confirm);
        //이름
        mNameView = (EditText)findViewById(R.id.name);

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }

    private void attemptSignup(){

        if(LoginAuthTask!=null){
            return;
        }

        //에러를 null로 세팅한다
        mEmailView.setError(null);
        mPasswordView.setError(null);//로그인 값 저장

        sEmail = mEmailView.getText().toString();
        sPw = mPasswordView.getText().toString();
        sPw_chk = mPasswordCheckView.getText().toString();
        sName = mNameView.getText().toString();

        boolean cancel = false;
        View focusView = null;
        // 비밀번호 유효성 체크
        //비밀번호가 비어있지 않고 && 비밀번호가 4글자 이상이어야 한다
        if (!TextUtils.isEmpty(sPw) && !isPasswordValid(sPw)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        if (!TextUtils.isEmpty(sPw_chk) && !isPasswordValid(sPw_chk)) {
            mPasswordCheckView.setError(getString(R.string.error_field_pw_check_required));
            focusView = mPasswordCheckView;
            cancel = true;
        }


        // 이메일 유효성 체크
        if (TextUtils.isEmpty(sEmail)) {
            //이메일 입력되어 있지 않거나
            mEmailView.setError(getString(R.string.error_field_email_required));
            focusView = mEmailView;
            cancel = true;
        }else if (!isEmailValid(sEmail)) {
            //이메일이 @을 포함하지 않거나
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }else if(!isEmailValid2(sEmail)){
            //이메일이 .을 포함하지 않거나
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }


        if (TextUtils.isEmpty(sName)) {
            mNameView.setError(getString(R.string.error_field_name_required));
            focusView = mNameView;
            cancel = true;
        }

        if (cancel) {
            //에러발생시 로그인 시도 x , 오류가 있는 필드로 포커싱
            focusView.requestFocus();
        } else {
            //패스워드 확인이 정상적으로 됨
            if(sPw.equals(sPw_chk))
            {

                try {
                    showProgress(true);

                    String result;

                    LoginAuthTask = new LoginTask();
                    result = LoginAuthTask.execute(sEmail,sPw,sName,"join").get();
                    Log.i("리턴 값 = ",result);


                    // 로그인 성공시 spinner 보여주고 로그인 시도
                    // Show a progress spinner, and kick off a background task to
                    //background 태스크 시작하기

                }catch (Exception e){

                }

            }
            else //패스워드 확인이 불일치 함
            {

                mPasswordCheckView.setError(getString(R.string.error_field_pw_check_diff));
                focusView = mPasswordCheckView;
                cancel = true;

            }


        }


    }
    private boolean isEmailValid(String email) {
        //이메일이 @을 포함하는지 확인
        return email.contains("@");
    }
    private boolean isEmailValid2(String email) {
        //이메일이 @을 포함하는지 확인
        return email.contains(".");
    }

    private boolean isPasswordValid(String password) {
        //비밀번호가 4자 이상이어야 한다
        return password.length() > 4;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
    /**
     * progress UI를 보여주고 로그인 양식을 감춘다
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }
    public void myClick(View v){
        switch (v.getId()){
            // 약관 동의 페이지 이동
            case  R.id.access_term:

                    Intent intent = new Intent(SignUpActivity.this , Access_terms.class);
                    startActivity(intent);

                break;
            // 로그인 화면으로 이동
            case R.id.link_login:
                    Intent intent2 = new Intent(SignUpActivity.this, LoginActivity.class);
                    startActivity(intent2);
                break;

        }
    }

    class LoginTask extends AsyncTask<String, Void, String> {
        String sendMsg, receiveMsg;


        @Override
        protected String doInBackground(String... strings) {

            try {
                String str;
                URL url = new URL("http://192.168.0.11:8080/login/data"); // 보낼 jsp주소
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST"); //데이터 post 방식으로 전송
                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
                sendMsg = "sEmail=" + strings[0] + "&sPw=" + strings[1] + "&sName=" + strings[2]+"&type="+strings[3];

                osw.write(sendMsg);
                osw.flush();

                //jsp와 정상적으로 통신이 된다면
                if (conn.getResponseCode() == conn.HTTP_OK) {
                    InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "EUC-KR");
                    BufferedReader reader = new BufferedReader(tmp);
                    StringBuffer buffer = new StringBuffer();

                    while ((str = reader.readLine()) != null) {
                        buffer.append(str);
                    }
                    receiveMsg = buffer.toString();
                } else {
                    Log.i("통신결과", conn.getResponseCode() + "에러");
                    //통신이 실패했을때 통신 결과 로그를 찍는다.
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //jsp로 부터 받은 리턴 값 .
            return receiveMsg;
        }


        @Override
        protected void onPostExecute(String receiveMsg) {
            LoginAuthTask = null;
            showProgress(false);

            if (receiveMsg.equals("email")) {
                mEmailView.setError(getString(R.string.error_field_email_exist));
                mEmailView.requestFocus();


            } else if(receiveMsg.equals("permit")) {

                Toast.makeText(SignUpActivity.this,"회원가입이 완료되었습니다.",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();

            }
        }

        @Override
        protected void onCancelled() {

            LoginAuthTask = null;
            showProgress(false);
        }

    }

}
