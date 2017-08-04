package com.rifki.pustakav20;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;


public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
       

        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/font.ttf");
        TextView tv = (TextView) findViewById(R.id.tview);
        TextView tv2 = (TextView) findViewById(R.id.tview2);
      //  TextView tv = (TextView) findViewById(R.id.diberdayakan);
       tv.setTypeface(tf);
        tv2.setTypeface(tf);

        Thread loading = new Thread() {
            public void run() {
                try {
                    sleep(7000);
                    Intent main = new Intent(SplashActivity.this, SliderActivity.class);
                    startActivity(main);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    finish();
                }
            }
        };

        loading.start();
    }
       /* final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        },1000);
    }*/
}
