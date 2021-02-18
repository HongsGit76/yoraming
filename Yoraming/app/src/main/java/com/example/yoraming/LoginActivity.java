package com.example.yoraming;

import androidx.annotation.Dimension;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.AsyncQueryHandler;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
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
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

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

import android.view.WindowManager;

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private SignInButton btn_google;
    private Button btn_logout;
    private TextView tvData, textView;
    private FirebaseAuth auth; //파이어베이스 인증 객체
    private GoogleApiClient googleApiClient;
    private static final int REQ_SIGN_GOOGLE = 100; //구글로그인 했을 때 결과 코드
    private BackPressedForFinish backPressedForFinish;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

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

        /*btn_logout = findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alt_bld = new AlertDialog.Builder(v.getContext());
                alt_bld.setMessage("로그아웃 하시겠습니까?").setCancelable(false)
                        .setPositiveButton("네",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        // 네 클릭
                                        // 로그아웃 함수 call
                                        signOut();
                                    }
                                }).setNegativeButton("아니오",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // 아니오 클릭. dialog 닫기.
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = alt_bld.create();
                // 대화창 클릭시 뒷 배경 어두워지는 것 막기
                //alert.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                // 대화창 제목 설정
                alert.setTitle("로그아웃");
                // 대화창 아이콘 설정
                alert.setIcon(R.drawable.common_google_signin_btn_icon_dark);
                // 대화창 배경 색 설정
                alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(255, 62, 79, 92)));
                alert.show();
            }
        });*/
    }

    @Override
    public void onBackPressed() {

        //BackPressedForFinish 클래스의 onBackPressed() 함수를 호출한다.
        backPressedForFinish.onBackPressed();
    }

    private void signOut() {
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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) { //구글로그인 인증을 요청했을 때 결과값을 되돌려받는 구문
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_SIGN_GOOGLE) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                GoogleSignInAccount account = result.getSignInAccount(); //data가 다 담김
                resultLogin(account);
            }
        }
    }

    private void resultLogin(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) { //로그인이 성공했다면
                            String email = account.getEmail().substring(account.getEmail().lastIndexOf("@"));
                            Log.d("test", email);
                            if (email.equals("@ajou.ac.kr")) {
                                //FirebaseUser user = auth.getCurrentUser();
                                new JSONTask().execute("https://f93d745aa940.ngrok.io/test//login");

                                /*String cu = auth.getUid();
                                String name = user.getDisplayName();
                                String userEmail = user.getEmail();*/

                                Toast.makeText(LoginActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);

                                /*UserData userData = new UserData(userEmail, name, cu);
                                mDatabase.child("users").setValue(userData);*/
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
            tvData.setText(result);//서버로 부터 받은 값을 출력해주는 부분
        }
    }
}
