package com.kaoneaw.moopiing.sharingpayment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class LoadingActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_loading);

        Thread welcomeThread = new Thread() {

            @Override
            public void run() {
                try {
                    super.run();
                    sleep(0000);  //Delay of 4 seconds
                } catch (Exception e) {

                } finally {

//                    Intent i = new Intent(LoadingActivity.this, LoginActivity.class);

                    //temp
                    Intent i = new Intent(LoadingActivity.this, NewRoomActivity.class);

                    startActivity(i);
                    finish();
                }
            }
        };
        welcomeThread.start();
    }
}
