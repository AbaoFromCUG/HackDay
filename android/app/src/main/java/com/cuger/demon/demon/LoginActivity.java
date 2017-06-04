package com.cuger.demon.demon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";


    EditText uname;
    EditText passw;
    Button button;
    String result;
    BufferedReader in;


    String username;
    String userkey;

    // UI references.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        uname = (EditText) findViewById(R.id.login_username);
        passw = (EditText) findViewById(R.id.login_passw);
        button = (Button) findViewById(R.id.login_login);
        Log.d(TAG, "onCreate: runing");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: button click");
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
                        Map<String, String> map = null;
                        String path = "http://139.198.14.233/test.php?uname=";
                        path+=uname.getText().toString();
                        path+="&upass=";
                        path+=passw.getText().toString();
                        URL url = null;
                        try {
                            url = new URL(path);
                            // 利用HttpURLConnection对象，我们可以从网页中获取网页数据
                            HttpURLConnection conn = null;
                            conn = (HttpURLConnection) url.openConnection();

                            // 单位为毫秒，设置超时时间为5秒
                            conn.setConnectTimeout(15 * 1000);
                            // HttpURLConnection对象是通过HTTP协议请求path路径的，所以需要设置请求方式，可以不设置，因为默认为get

                            conn.setRequestMethod("GET");
                            if (conn.getResponseCode() == 200) {// 判断请求码是否200，否则为失败
                                InputStream is = conn.getInputStream(); // 获取输入流
                                //??******************************
                                ByteArrayOutputStream bout = new ByteArrayOutputStream();
                                byte[] buffer = new byte[1024];
                                int len = 0;
                                while ((len = is.read(buffer)) != -1) {
                                    bout.write(buffer, 0, len);
                                }
                                bout.close();
                                is.close();
                                byte[] data = bout.toByteArray();
                                ; // 把输入流转换成字符串组
                                String json = new String(data); // 把字符串组转换成字符串

                                // 数据形式：{uname: "HackDay",makesure: "1 }
                                JSONObject jsonObject = new JSONObject(json); // 返回的数据形式是一个Object类型，所以可以直接转换成一个Object
                                String name = jsonObject.optString("uname");
                                String status = jsonObject.optString("makesure");
                                if (status.equals("1")) {
                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                } else {
                                    //非UI线程吐司导致崩溃
                                    //Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
                                    Log.d(TAG, "run: 登陆错误，");
                                    LoginActivity.this.runOnUiThread(new Runnable() {
                                        public void run() {
                                            Toast.makeText(LoginActivity.this.getApplicationContext(), "登陆失败，密码错误或者用户不存在", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                    
                                }
                            }
                        } catch (Exception e) {

                            Log.d(TAG, "run: " + e.getMessage().toString());

                        }
                    }
                });

                thread.start();


            }


        });
    }
}