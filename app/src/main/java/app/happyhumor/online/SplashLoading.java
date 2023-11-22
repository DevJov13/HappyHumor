package app.happyhumor.online;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.VideoView;

public class SplashLoading extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_loading);


        getWindow().setFlags(1024,1024);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.hide();

        UniversalConf.setupRemoteConfig(this, this, true);

        VideoView splash=findViewById(R.id.videoView);
        Uri splashFile =Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.loading);
        splash.setVideoURI(splashFile);


        splash.start();
        splash.setOnCompletionListener(mediaPlayer -> {
            splash.stopPlayback();
            splash.setVisibility(View.GONE);

            Intent intent = new Intent(SplashLoading.this, appContent.class);
            startActivity(intent); // Use 'intent' instead of 'Intent'
            finish();
        });
    }
}