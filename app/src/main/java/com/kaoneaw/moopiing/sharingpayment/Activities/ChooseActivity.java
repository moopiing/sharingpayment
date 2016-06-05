package com.kaoneaw.moopiing.sharingpayment.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.kaoneaw.moopiing.sharingpayment.R;

public class ChooseActivity extends Activity {

    private ImageButton foodButtom;
    private ImageButton drinkButtom;
    private ImageButton dessertButtom;
    private ImageButton tipButtom;
    private ImageButton goButtom;

    private TextView roomText;
    private String room;
    private String username;

    private int countFood = 0;
    private int countDrink = 0;
    private int countDessert = 0;
    private int countTIPs = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        username = super.getIntent().getExtras().getString("Username");
        room = super.getIntent().getExtras().getString("Room");

        foodButtom = (ImageButton) findViewById(R.id.btn_food);
        drinkButtom = (ImageButton) findViewById(R.id.btn_drink);
        dessertButtom = (ImageButton) findViewById(R.id.btn_dessert);
        tipButtom = (ImageButton) findViewById(R.id.btn_tip);
        goButtom = (ImageButton) findViewById(R.id.btn_go);
        roomText = (TextView) findViewById(R.id.tv_string_room);

        initComponents();
    }

    private void initComponents(){
        roomText.setText(room);

        foodButtom.setOnClickListener(new View.OnClickListener() {
            boolean isPress = true;

            public void onClick(View v) {
                if (isPress) {
                    foodButtom.setImageResource(R.drawable.img_choose);
                    countFood = countFood + 1;
                }
                isPress = false;
            }
        });
        drinkButtom.setOnClickListener(new View.OnClickListener() {
            boolean isPress = true;

            public void onClick(View v) {
                if (isPress) {
                    drinkButtom.setImageResource(R.drawable.img_choose);
                    countDrink = countDrink + 1;
                }
                isPress = false;
            }
        });
        dessertButtom.setOnClickListener(new View.OnClickListener() {
            boolean isPress = true;
            public void onClick(View v) {
                if(isPress) {
                    dessertButtom.setImageResource(R.drawable.img_choose);
                    countDessert = countDessert + 1;
                }
                isPress = false;
            }
        });
        tipButtom.setOnClickListener(new View.OnClickListener() {
            boolean isPress = true;
            public void onClick(View v) {
                if(isPress) {
                    tipButtom.setImageResource(R.drawable.img_choose);
                    countTIPs = countTIPs + 1;
                }
                isPress = false;
            }
        });
        goButtom.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ChooseActivity.this,ResultActivity.class);
                intent.putExtra("Room", room);
                intent.putExtra("Username",username);
                intent.putExtra("CountFood",countFood);
                intent.putExtra("CountDrink",countDrink);
                intent.putExtra("CountDessert",countDessert);
                intent.putExtra("CountTIPs",countTIPs);
                startActivity(intent);
            }
        });
    }
}
