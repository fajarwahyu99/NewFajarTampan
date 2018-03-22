
/*
 * Copyright (c) By Fajar Nugraha Wahyu on 3/22/18 10:28 AM 2018.
 * All rights reserved
 */

package com.example.infolabsolution.basisdatanew;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;



public class Splashscreen extends AppCompatActivity {

    private static int splashInterval = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.splashscreen);

        new Handler().postDelayed(new Runnable() {


            @Override
            public void run() {
                Intent i = new Intent(Splashscreen.this, NavigationActivity.class);
                startActivity(i);
                this.finish();
            }

            private void finish() {

            }
        }, splashInterval);

    };

}