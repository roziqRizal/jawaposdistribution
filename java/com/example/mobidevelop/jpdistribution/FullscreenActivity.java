package com.example.mobidevelop.jpdistribution;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.io.IOException;
import java.io.InputStream;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends AppCompatActivity {
    private Tracker mTracker;
    String apkversion="/~9926";


    synchronized public Tracker getDefaultTracker(){
        if (mTracker == null) {
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
            // To enable debug logging use: adb shell setprop log.tag.GAv4 DEBUG
            mTracker = analytics.newTracker(R.xml.global_tracker);
        }
        return mTracker;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);

        ImageView imageView15 = (ImageView)findViewById(R.id.imageView15);

        Context context = getApplicationContext();
        appPrefs appPrefs = new appPrefs(context);
        String first_name = appPrefs.getFirstname();
        String last_name = appPrefs.getLastname();

        FullscreenActivity application = FullscreenActivity.this;
        mTracker = application.getDefaultTracker();

        Log.i("Image View", "Setting screen name: " + first_name+" "+last_name);
        String uid = Build.MODEL;
        mTracker.setScreenName("ImageView~"+""+uid+"/"+ first_name+"_"+last_name+"_"+apkversion);
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());

        Intent myIntent = getIntent();
        String m = myIntent.getStringExtra("stringimage");
        if (m!=null){
            byte[] avatar2 = Base64.decode(m, Base64.DEFAULT);
            Bitmap bmp = BitmapFactory.decodeByteArray(avatar2,0,avatar2.length);
            imageView15.setImageBitmap(bmp);
        }

        else if (m==null){
            try {
                InputStream ims = getAssets().open("home.png");
                Drawable d = Drawable.createFromStream(ims, null);
                imageView15.setImageDrawable(d);
            }
            catch(IOException ex) {
                return;
            }
        }

        ImageView imageView16 = (ImageView)findViewById(R.id.imageView16);
        imageView16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
