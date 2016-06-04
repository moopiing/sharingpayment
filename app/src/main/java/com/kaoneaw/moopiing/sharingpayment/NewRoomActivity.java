package com.kaoneaw.moopiing.sharingpayment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class NewRoomActivity extends Activity {

    private ImageButton newButton;
    private EditText inputRoom;
    private EditText inputFood;
    private EditText inputDrink;
    private EditText inputDessert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newroom);

        newButton = (ImageButton) findViewById(R.id.btn_new);

        inputRoom = (EditText) findViewById(R.id.et_input_room);
        inputFood = (EditText) findViewById(R.id.et_input_food);
        inputDrink = (EditText) findViewById(R.id.et_input_drink);
        inputDessert = (EditText) findViewById(R.id.et_input_dessert);

        initComponents();
    }

    private void initComponents(){
        newButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String room = inputRoom.getText().toString();
                final String food = inputFood.getText().toString();
                final String drink = inputDrink.getText().toString();
                final String dessert = inputDessert.getText().toString();


                


                Intent intent = new Intent(NewRoomActivity.this,ChooseActivity.class);
                intent.putExtra("Room", room);
                startActivity(intent);
            }
        });
    }
}
