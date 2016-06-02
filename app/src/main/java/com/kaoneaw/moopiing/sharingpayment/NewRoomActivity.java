package com.kaoneaw.moopiing.sharingpayment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class NewRoomActivity extends Activity {

    private ImageButton newInNewButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newroom);

        newInNewButton = (ImageButton) findViewById(R.id.btn_new_in_new);

        initComponents();
    }

    private void initComponents(){
        newInNewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewRoomActivity.this,ChooseActivity.class);
                startActivity(intent);
            }
        });
    }
}
