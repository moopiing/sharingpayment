package com.kaoneaw.moopiing.sharingpayment.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.kaoneaw.moopiing.sharingpayment.R;

public class LoadingActivity extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_loading);

        Thread welcomeThread = new Thread() {

            public void run() {
                try {
                    super.run();
                    sleep(4000);
                } catch (Exception e) {

                } finally {
                    Intent i = new Intent(LoadingActivity.this, LoginActivity.class);

                    startActivity(i);
                    finish();
                }
            }
        };
        welcomeThread.start();
    }
}
