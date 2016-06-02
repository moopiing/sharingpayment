package com.kaoneaw.moopiing.sharingpayment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ChooseActivity extends Activity {

    private ImageButton foodButtom;
    private ImageButton drinkButtom;
    private ImageButton dessertButtom;
    private ImageButton tipButtom;
    private ImageButton goButtom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        foodButtom = (ImageButton) findViewById(R.id.btn_food);
        drinkButtom = (ImageButton) findViewById(R.id.btn_drink);
        dessertButtom = (ImageButton) findViewById(R.id.btn_dessert);
        tipButtom = (ImageButton) findViewById(R.id.btn_tip);
        goButtom = (ImageButton) findViewById(R.id.btn_go);

        initComponents();
    }

    private void initComponents(){
        foodButtom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        drinkButtom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        dessertButtom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tipButtom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        goButtom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseActivity.this,ResultActivity.class);
                startActivity(intent);
            }
        });
    }
}
