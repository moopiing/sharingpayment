package com.kaoneaw.moopiing.sharingpayment.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.kaoneaw.moopiing.sharingpayment.Databases.DatabaseRoom;
import com.kaoneaw.moopiing.sharingpayment.R;

public class JoinRoomActivity extends Activity {

    DatabaseRoom dbRoom = new DatabaseRoom(this);

    private EditText inputRoom;
    private String username;
    private ImageButton joinButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joinroom);
        username = super.getIntent().getExtras().getString("Username");

        inputRoom = (EditText) findViewById(R.id.et_input_room);
        joinButton = (ImageButton) findViewById(R.id.btn_join);

        initComponents();
    }

    private void initComponents(){
        joinButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                final String room = inputRoom.getText().toString();

                if(room.equals(dbRoom.searchName(room)) && !dbRoom.searchFood(room).equals("0") && !dbRoom.searchDrink(room).equals("0") && !dbRoom.searchDessert(room).equals("0")){
                    Intent intent = new Intent(JoinRoomActivity.this, ChooseActivity.class);
                    intent.putExtra("Room", room);
                    intent.putExtra("Username", username);
                    startActivity(intent);
                } else {
                    Toast invalid_room = Toast.makeText(JoinRoomActivity.this, "This room is invalid!", Toast.LENGTH_SHORT);
                    invalid_room.show();
                }
            }
        });
    }
}