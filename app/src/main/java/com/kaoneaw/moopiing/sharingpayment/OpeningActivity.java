package com.kaoneaw.moopiing.sharingpayment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class OpeningActivity extends Activity {

    private ImageButton openningButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_opening);

        openningButton = (ImageButton) findViewById(R.id.btn_opening);

        initComponents();
    }

    private void initComponents(){

        openningButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OpeningActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}
