package com.kaoneaw.moopiing.sharingpayment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class JoinRoomActivity extends Activity {

    private ImageButton joinInJoinButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joinroom);

        joinInJoinButton = (ImageButton) findViewById(R.id.btn_join_in_join);

        initComponents();
    }

    private void initComponents(){
        joinInJoinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JoinRoomActivity.this,ChooseActivity.class);
                startActivity(intent);
            }
        });
    }
}
