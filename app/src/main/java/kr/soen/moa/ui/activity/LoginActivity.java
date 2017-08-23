package kr.soen.moa.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
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
import java.util.ArrayList;
import java.util.List;

import kr.soen.moa.R;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * 이메일 비밀번호를 통한 로그인을 제공하는 로그인 스크린
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements LoaderCallbacks<Cursor> {

    /**
     * READ_CONTACTS 승인요청을 식별
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    /**
     * 알려진 사용자 이름과 비밀번호가 포함된 더미 인증 저장
     * A dummy authentication store containing known user names and passwords.
     * 실제 인증 시스템에 연결된 후 제거
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };
    /**
     * 요청이 있는 경우 그것을 취소할 수 있게 하기 위해 로그인 태스크를 추척해야한다
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserLoginTask LoginAuthTask = null;

    // 이메일 비밀번호 기입창.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;

    private View mProgressView;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle("로그인");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // 이메일 입력창
        mEmailView = (AutoCompleteTextView)findViewById(R.id.email);
        //전화부 접근 권한 허가
        populateAutoComplete();

        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        /**
         * 로그인 버튼 클릭시
         */
        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    //이메일 입력
    private void populateAutoComplete() {
        if (!mayRequestContacts()) {
            return;
        }

        getLoaderManager().initLoader(0, null, this);
    }


    /**
     * READ_CONTACTS 권한 허가 요청할 때
     */
    private boolean mayRequestContacts() {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            //단말기 os버전 체크
            return true;
        }
        if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
            Snackbar.make(mEmailView, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, new OnClickListener() {
                        @Override
                        @TargetApi(Build.VERSION_CODES.M)
                        public void onClick(View v) {
                            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
                        }
                    });
        } else {
            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
        }
        return false;
    }

    /**
     * 권한허가 요청 후 결과 가져오기
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_READ_CONTACTS) {
            //권한허가
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                populateAutoComplete();
            }
        }
    }


    /**
     * 로그인이나 회원가입 시 만약 ( 유효하지 않은 이메일 항복이 비어있거나 , )
     * 에러발생하고 로그인 시도가 되지 않음
     */
    private void attemptLogin() {
        if (LoginAuthTask != null) {
            return;
        }

        // 에러를 null로 세팅
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // 로그인 값 저장
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;



        // 이메일 유효성 체크
        if (TextUtils.isEmpty(email)) {
            //이메일 입력되어 있지 않거나
            mEmailView.setError(getString(R.string.error_field_email_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            //이메일이 @을 포함하지 않거나
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }else if(!isEmailValid2(email)){
            //이메일이 .을 포함하지 않거나
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }
// 비밀번호 유효성 체크
        //비밀번호가 비어있지 않고 && 비밀번호가 4글자 이상이어야 한다
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }
        if (cancel) {
            //에러발생시 로그인 시도 x , 오류가 있는 필드로 포커싱
            focusView.requestFocus();
        } else {
            // 로그인 성공시 spinner 보여주고 로그인 시도
            // Show a progress spinner, and kick off a background task to


            try {
                showProgress(true);

                String result;
                LoginAuthTask = new UserLoginTask();
                result = LoginAuthTask.execute(email,password,"login").get();
                Log.i("리턴 값 = ",result);


                // 로그인 성공시 spinner 보여주고 로그인 시도
                // Show a progress spinner, and kick off a background task to
                //background 태스크 시작하기

            }catch (Exception e){

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

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,
                //장치 사용자의 '프로필'컨택트에 관한 데이터 행을 검색한다
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},

                //기본 이메일 주소를 보여준다 사용자가 지정하지 않으면 기본 이메일 주소가 없다
                // Show primary email addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<String> emails = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            emails.add(cursor.getString(ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }

        addEmailsToAutoComplete(emails);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }

    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
        //autoCompleteTextView에게
        //dropdown list에서 무엇을 보여줄지 말하기 위해 어댑터를 만든다
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(LoginActivity.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

        mEmailView.setAdapter(adapter);
    }


    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<String, Void, String> {

        String sendMsg, receiveMsg;

        private  String mEmail;
        private  String mPassword;

        UserLoginTask(){

        }
        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected String doInBackground(String... strings) {

            try {
                String str;
                URL url = new URL("http://192.168.0.11:8080/login/data"); // 보낼 jsp주소
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
                conn.setRequestMethod("POST"); //데이터 post 방식으로 전송
                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
                sendMsg = "sEmail="+strings[0]+"&sPw="+strings[1]+"&type="+strings[2];

                osw.write(sendMsg);
                osw.flush();

                //jsp와 정상적으로 통신이 된다면
                if(conn.getResponseCode() == conn.HTTP_OK){
                    InputStreamReader tmp = new InputStreamReader(conn.getInputStream(),"EUC-KR");
                    BufferedReader reader = new BufferedReader(tmp);
                    StringBuffer buffer = new StringBuffer();

                    while((str = reader.readLine())!=null){
                        buffer.append(str);
                    }
                    receiveMsg = buffer.toString();
                }else{
                    Log.i("통신결과",conn.getResponseCode()+"에러");
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

            if (receiveMsg.equals("true")) {
                Toast.makeText(LoginActivity.this,"로그인 성공",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
                finish();

            }else if(receiveMsg.equals("false")){

                mPasswordView.setError(getString(R.string.error_field_pw_check_diff));
                mPasswordView.requestFocus();

            }else if(receiveMsg.equals("nonexist")){
                mEmailView.setError(getString(R.string.error_field_email_nonexist));
                mEmailView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {

            LoginAuthTask = null;
            showProgress(false);
        }
    }
}

