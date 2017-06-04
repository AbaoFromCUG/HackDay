package com.cuger.demon.demon;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CalculationActivity extends AppCompatActivity {
    private static final String TAG = "CalculationActivity";
    String str;

    TextView score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        str="0";

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculation);
        score= (TextView) findViewById(R.id.scoreText);
        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                List<Map<String, String>> list = new ArrayList<Map<String, String>>();
                Map<String, String> map = null;
                String path = "http://139.198.14.233/post.php?id=1";

                URL url = null;
                while(true){
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {
                        url = new URL(path);
                        // 利用HttpURLConnection对象，我们可以从网页中获取网页数据
                        HttpURLConnection conn = null;
                        conn = (HttpURLConnection) url.openConnection();

                        // 单位为毫秒，设置超时时间为15秒
                        conn.setConnectTimeout(15 * 1000);

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
                            JSONArray jsonArray = new JSONArray(json);

                            // JSONObject jsonObject = new JSONObject(json); // 返回的数据形式是一个Object类型，所以可以直接转换成一个Object
                            if(str.equals(jsonArray.getString(0))){
                                final String name = "击杀数"+jsonArray.getString(0);
                                Log.d(TAG, "run: "+name);
                                CalculationActivity.this.runOnUiThread(new Runnable() {
                                    public void run() {
                                        Toast.makeText(CalculationActivity.this.getApplicationContext(),"runring",Toast.LENGTH_SHORT).show();
                                        CalculationActivity.this.score.setText(name);
                                    }
                                });
                            }



                        }

                    } catch (Exception e) {

                        Log.d(TAG, "run: " + e.getMessage().toString());

                    }
                }
            }
        });

        thread.start();
    }
}
