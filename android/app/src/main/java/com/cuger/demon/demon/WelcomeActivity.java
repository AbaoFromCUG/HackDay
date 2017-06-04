package com.cuger.demon.demon;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;

public class WelcomeActivity extends AppCompatActivity {
    private static final String TAG = "WelcomeActivity";
    WelcomeVideoView videoView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_welcome);
        initView();



    }

    protected void initView(){
        videoView= (WelcomeVideoView) findViewById(R.id.welcome_videoview);
        videoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.init));
        videoView.start();
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                Log.d(TAG, "onCompletion: video over");
                startActivity(new Intent(WelcomeActivity.this,LoginActivity.class));
            }
        });
    }


}
