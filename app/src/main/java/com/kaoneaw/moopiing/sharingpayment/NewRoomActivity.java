package com.kaoneaw.moopiing.sharingpayment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class NewRoomActivity extends Activity {

    DatabaseRoom dbRoom = new DatabaseRoom(this);

    private ImageButton newButton;
    private EditText inputRoom;
    private EditText inputFood;
    private EditText inputDrink;
    private EditText inputDessert;
    private String username;

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

        username = super.getIntent().getExtras().getString("Username");

        newButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String room = inputRoom.getText().toString();
                final String food = inputFood.getText().toString();
                final String drink = inputDrink.getText().toString();
                final String dessert = inputDessert.getText().toString();

                if(!room.equals("") && !food.equals("") && !drink.equals("") && !dessert.equals("")
                        && isStringDouble(food)==true  && isStringDouble(drink)==true && isStringDouble(dessert)==true){
                    Room rm = new Room();

                    rm.setName(room);
                    rm.setFood(Integer.parseInt(food));
                    rm.setDrink(Integer.parseInt(drink));
                    rm.setDessert(Integer.parseInt(dessert));

                    dbRoom.insertRoom(rm);

                    Intent intent = new Intent(NewRoomActivity.this, ChooseActivity.class);
                    intent.putExtra("Room", room);
                    intent.putExtra("Username", username);
                    startActivity(intent);
                } else if (room.equals("")){
                    Toast empty_room = Toast.makeText(NewRoomActivity.this, "Room don't empty!", Toast.LENGTH_SHORT);
                    empty_room.show();
                } else if(food.equals("")){
                    Toast empty_food = Toast.makeText(NewRoomActivity.this, "Food don't empty!", Toast.LENGTH_SHORT);
                    empty_food.show();
                } else if(drink.equals("")){
                    Toast empty_drink = Toast.makeText(NewRoomActivity.this, "Drink don't empty!", Toast.LENGTH_SHORT);
                    empty_drink.show();
                } else if(dessert.equals("")){
                    Toast empty_dessert = Toast.makeText(NewRoomActivity.this, "Dessert don't empty!", Toast.LENGTH_SHORT);
                    empty_dessert.show();
                } else if(isStringDouble(food) == false){
                    Toast invalid_room = Toast.makeText(NewRoomActivity.this, "Food must be numeric!", Toast.LENGTH_SHORT);
                    invalid_room.show();
                } else if(isStringDouble(drink) == false){
                    Toast invalid_room = Toast.makeText(NewRoomActivity.this, "Drink must be numeric!", Toast.LENGTH_SHORT);
                    invalid_room.show();
                } else if(isStringDouble(dessert) == false){
                    Toast invalid_room = Toast.makeText(NewRoomActivity.this, "Dessert must be numeric!", Toast.LENGTH_SHORT);
                    invalid_room.show();
                }
            }
        });
    }

    public boolean isStringDouble(String s)
    {
        try
        {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException ex)
        {
            return false;
        }
    }
}