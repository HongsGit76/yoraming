package com.example.yoraming.UI.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yoraming.BackPressedForFinish;
import com.example.yoraming.R;
import com.example.yoraming.Server.Net;
import com.example.yoraming.items.UserData;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private SignInButton btn_google;
    private Button btn_logout;
    private TextView textView;
    private static final String TAG = "LoginActivity";
    public FirebaseAuth auth; //파이어베이스 인증 객체
    public GoogleApiClient googleApiClient;
    private static final int REQ_SIGN_GOOGLE = 100; //구글로그인 했을 때 결과 코드
    private BackPressedForFinish backPressedForFinish;
    private ProgressDialog mProgressDialog;


    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        int i = intent.getIntExtra("key", 0);

        if (i == 1) {
            System.out.println(i);
            signOut();
        } else {
            System.out.println(i);
            return;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        //Intent intent = getIntent();
        //int i = intent.getIntExtra("key", 0);

        // BackPressedForFinish 객체를 생성한다.
        backPressedForFinish = new BackPressedForFinish(this);

        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
                .build();

        auth = FirebaseAuth.getInstance(); //파이어베이스 인증객체 초기화
        btn_google = findViewById(R.id.btn_google);

        textView = (TextView) btn_google.getChildAt(0);
        textView.setText("아주대 구글계정으로 로그인");
        textView.setTextColor(Color.parseColor("#000000"));

        btn_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient); //구글이 만들어낸 로직대로 인증 진행
                startActivityForResult(intent, REQ_SIGN_GOOGLE);
            }
        });
    }



    @Override
    public void onBackPressed() {

        //BackPressedForFinish 클래스의 onBackPressed() 함수를 호출한다.
        backPressedForFinish.onBackPressed();
    }

    public void signOut() {
        googleApiClient.connect();
        googleApiClient.registerConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
            @Override
            public void onConnected(@Nullable Bundle bundle) {
                auth.signOut();
                if (googleApiClient.isConnected()) {
                    Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
                        @Override
                        public void onResult(@NonNull Status status) {
                            if (status.isSuccess()) {
                                Log.v("알림", "로그아웃 성공");
                                setResult(1);
                            } else {
                                setResult(0);
                            }
                            //finish();
                        }
                    });
                }
            }

            @Override
            public void onConnectionSuspended(int i) {
                Log.v("알림", "Google API Client Connection Suspended");
                setResult(-1);
                finish();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) { //구글로그인 인증을 요청했을 때 결과값을 되돌려받는 구문
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_SIGN_GOOGLE) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                GoogleSignInAccount account = result.getSignInAccount(); //data가 다 담김
                resultLogin(account);
            }
        }
    }

    public void resultLogin(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) { //로그인이 성공했다면
                            String subemail = account.getEmail().substring(account.getEmail().lastIndexOf("@"));
                            String email = account.getEmail();

                            Log.d("test", email);
                            if (subemail.equals("@ajou.ac.kr")) {
                                UserData userData = new UserData(email,account.getDisplayName(),"");
                                Call<JsonObject> res = Net.getInstance().getuserFactory().getUser(email);
                                res.enqueue(new Callback<JsonObject>() {
                                    @Override
                                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                                        if(response.isSuccessful()){
                                            if(response.body() != null){
                                                JsonObject success = response.body();
                                                Log.d("LoginGet 통신", success.get("success").toString());
                                                if (success.get("success").toString().equals("true")){
                                                    SharedPreferences SP_user = getSharedPreferences("user", MODE_PRIVATE);
                                                    SharedPreferences.Editor editor = SP_user.edit();
                                                    editor.putString("user_id", email);
                                                    editor.putString("user_name",account.getDisplayName());
                                                    editor.commit();

                                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                                    startActivity(intent);

                                                }
                                                else if(success.get("success").toString().equals("false")){

                                                    Call<JsonObject> res1 = Net.getInstance().getuserFactory().postUser(userData);
                                                    res1.enqueue(new Callback<JsonObject>() {
                                                        @Override
                                                        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                                                            if(response.isSuccessful()) {
                                                                if (response.body() != null) {
                                                                    JsonObject success = response.body();
                                                                    Log.d("LoginPost 통신", success.get("success").toString());
                                                                    if(success.get("success").toString().equals("true")){
                                                                        SharedPreferences SP_user = getSharedPreferences("user", MODE_PRIVATE);
                                                                        SharedPreferences.Editor editor = SP_user.edit();
                                                                        editor.putString("user_id", email);
                                                                        editor.putString("user_name",account.getDisplayName());
                                                                        editor.commit();
                                                                        Intent intent = new Intent(getApplicationContext(), MainMajorActivity.class);
                                                                        startActivity(intent);
                                                                    }else{
                                                                        Toast.makeText(getApplicationContext(),"다시 시도해 주세요",Toast.LENGTH_SHORT);
                                                                    }
                                                                }else{
                                                                    Log.e("LoginPost 통신", "실패 1 response 내용이 없음");
                                                                }
                                                            }else{
                                                                Log.e("LoginPost 통신", "실패 2 서버 에러");
                                                            }
                                                        }

                                                        @Override
                                                        public void onFailure(Call<JsonObject> call, Throwable t) {
                                                            Log.e("LoginPost 통신", "실패 3 통신 에러 "+t.getLocalizedMessage());
                                                        }
                                                    });



                                                }else{
                                                    Toast.makeText(getApplicationContext(),"다시 시도해 주세요",Toast.LENGTH_SHORT);
                                                }
                                            }else{
                                                Log.e("LoginGet 통신", "실패 1 response 내용이 없음");
                                            }
                                        }else{
                                            Log.e("LoginGet 통신", "실패 2 서버 에러");
                                        }

                                    }

                                    @Override
                                    public void onFailure(Call<JsonObject> call, Throwable t) {
                                        Log.e("LoginGet 통신", "실패 3 통신 에러 "+t.getLocalizedMessage());
                                    }
                                });
//                                new JSONTask().execute("https://f93d745aa940.ngrok.io/test//login");
//                                Toast.makeText(LoginActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();
//                                /*try {
//                                    String result = new JSONTask().execute("https://f93d745aa940.ngrok.io/test//login").get();
//                                    Toast.makeText(LoginActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();
//                                    if (result.equals("new user")) {
//                                        Intent intent = new Intent(getApplicationContext(), MainMajorActivity.class);
//                                        startActivity(intent);
//                                    }
//                                    else {
//                                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                                        startActivity(intent);
//                                    }
//                                } catch (ExecutionException e) {
//                                    e.printStackTrace();
//                                } catch (InterruptedException e) {
//                                    e.printStackTrace();
//                                }*/
//                                Intent intent = new Intent(getApplicationContext(), MainMajorActivity.class);
//                                startActivity(intent);

                            } else {
                                Toast.makeText(LoginActivity.this, "아주대학교 계정이 아닙니다.", Toast.LENGTH_SHORT).show();
                                signOut();
                            }
                        } else { //로그인이 실패했다면
                            Toast.makeText(LoginActivity.this, "로그인 실패", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }

    public class JSONTask extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... strings) {
            try {
                JSONObject jsonObject = new JSONObject();
                FirebaseUser user = auth.getCurrentUser();
                jsonObject.accumulate("userEmail", user.getEmail());
                jsonObject.accumulate("name", user.getDisplayName());
                jsonObject.accumulate("uid", auth.getUid());
                jsonObject.accumulate("year", "2019");
                jsonObject.accumulate("major", "미디어학과");

                HttpURLConnection con = null;
                BufferedReader reader = null;

                try {
                    URL url = new URL("https://f93d745aa940.ngrok.io/test//login");
                    con = (HttpURLConnection) url.openConnection();

                    con.setRequestMethod("POST");//POST방식으로 보냄
                    con.setRequestProperty("Cache-Control", "no-cache");//캐시 설정
                    con.setRequestProperty("Content-Type", "application/json");//application JSON 형식으로 전송
                    con.setRequestProperty("Accept", "text/html");//서버에 response 데이터를 html로 받음
                    con.setDoOutput(true);//Outstream으로 post 데이터를 넘겨주겠다는 의미
                    con.setDoInput(true);//Inputstream으로 서버로부터 응답을 받겠다는 의미
                    con.connect();

                    //서버로 보내기위해서 스트림 만듬
                    OutputStream outStream = con.getOutputStream();
                    //버퍼를 생성하고 넣음
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outStream));
                    writer.write(jsonObject.toString());
                    writer.flush();
                    writer.close(); //버퍼를 받아줌

                    //서버로 부터 데이터를 받음
                    InputStream stream = con.getInputStream();

                    reader = new BufferedReader(new InputStreamReader(stream));

                    StringBuffer buffer = new StringBuffer();

                    String line = "";
                    while ((line = reader.readLine()) != null) {
                        buffer.append(line);
                    }

                    return buffer.toString();//서버로 부터 받은 값을 리턴해줌 아마 OK!!가 들어올것임

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (con != null) {
                        con.disconnect();
                    }
                    try {
                        if (reader != null) {
                            reader.close();//버퍼를 닫아줌
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
        }
    }
}
