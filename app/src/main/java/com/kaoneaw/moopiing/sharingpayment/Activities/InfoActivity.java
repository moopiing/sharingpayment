package com.kaoneaw.moopiing.sharingpayment.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;

import com.kaoneaw.moopiing.sharingpayment.R;

public class InfoActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_info);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .85), (int) (height * .7));
    }
}
