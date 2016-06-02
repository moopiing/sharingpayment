package com.kaoneaw.moopiing.sharingpayment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class NewRoomActivity extends Activity {

    private ImageButton newButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newroom);

        newButton = (ImageButton) findViewById(R.id.btn_new);

        initComponents();
    }

    private void initComponents(){
        newButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewRoomActivity.this,ChooseActivity.class);
                startActivity(intent);
            }
        });
    }
}
