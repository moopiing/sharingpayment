package com.kaoneaw.moopiing.sharingpayment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ChooseActivity extends Activity {

    DatabaseRoom dbRoom = new DatabaseRoom(this);

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        foodButtom = (ImageButton) findViewById(R.id.btn_food);
        drinkButtom = (ImageButton) findViewById(R.id.btn_drink);
        dessertButtom = (ImageButton) findViewById(R.id.btn_dessert);
        tipButtom = (ImageButton) findViewById(R.id.btn_tip);
        goButtom = (ImageButton) findViewById(R.id.btn_go);

        roomText = (TextView) findViewById(R.id.tv_string_room);

        initComponents();
    }

    private void initComponents(){

        username = super.getIntent().getExtras().getString("Username");

        room = super.getIntent().getExtras().getString("Room");
        roomText.setText(room);

        foodButtom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                foodButtom.setImageResource(R.drawable.img_choose);
                countFood = countFood + 1;
            }
        });
        drinkButtom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drinkButtom.setImageResource(R.drawable.img_choose);
                countDrink = countDrink + 1;
            }
        });
        dessertButtom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dessertButtom.setImageResource(R.drawable.img_choose);
                countDessert = countDessert + 1;
            }
        });
        tipButtom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tipButtom.setImageResource(R.drawable.img_choose);
                countTIPs = countTIPs + 1;
            }
        });
        goButtom.setOnClickListener(new View.OnClickListener() {
            @Override
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
