package com.kaoneaw.moopiing.sharingpayment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

public class JoinRoomActivity extends Activity {

    private EditText inputRoom;
    private String username;
    private ImageButton joinButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joinroom);

        inputRoom = (EditText) findViewById(R.id.et_input_room);
        joinButton = (ImageButton) findViewById(R.id.btn_join);

        initComponents();
    }

    private void initComponents(){

        username = super.getIntent().getExtras().getString("Username");

        joinButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                final String room = inputRoom.getText().toString();

                Intent intent = new Intent(JoinRoomActivity.this, ChooseActivity.class);
                intent.putExtra("Room", room);
                intent.putExtra("Username", username);
                startActivity(intent);
            }
        });
    }
}