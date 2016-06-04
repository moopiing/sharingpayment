package com.kaoneaw.moopiing.sharingpayment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

public class ResultActivity extends Activity {

    DatabaseRoom dbRoom = new DatabaseRoom(this);
    DatabaseHelper helper = new DatabaseHelper(this);

    private static DecimalFormat REAL_FORMATTER = new DecimalFormat("0.##");

    private ImageButton payButton;
    private TextView roomText;
    private TextView totalText;
    private TextView youpayText;
    private String room;
    private String username;

    private int countFood;
    private int countDrink;
    private int countDessert;
    private int countTIPs;

    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        mHandler = new Handler();

        int temp = (int)(Math.random()*5+1);
        for(int i=0;i<temp;i++) {
            mHandler.postDelayed(textUpdate, 2500 * i);
        }

        roomText = (TextView) findViewById(R.id.tv_string_room);
        totalText = (TextView) findViewById(R.id.tv_string_total);
        youpayText = (TextView) findViewById(R.id.tv_string_upay);

        payButton = (ImageButton) findViewById(R.id.btn_pay);

        initComponents();
    }

    private void initComponents(){

        username = super.getIntent().getExtras().getString("Username");

        room = super.getIntent().getExtras().getString("Room");
        roomText.setText(room);

        countFood = super.getIntent().getExtras().getInt("CountFood");
        countDrink = super.getIntent().getExtras().getInt("CountDrink");
        countDessert = super.getIntent().getExtras().getInt("CountDessert");
        countTIPs = super.getIntent().getExtras().getInt("CountTIPs");

        payButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Account ac = new Account();
                ac.setUsername(username);
                ac.setPassword(helper.searchPass(username));
                ac.setBalance(Double.parseDouble(helper.searchPass1(username)) - Double.parseDouble(youpayText.getText().toString()));

                helper.updateBalance(ac);

                Room rm = new Room();
                rm.setName(room);
                rm.setFood(0);
                rm.setDrink(0);
                rm.setDessert(0);

                dbRoom.updateCost(rm);

                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                intent.putExtra("Username", username);
                startActivity(intent);
            }
        });

        update();
    }

    private Runnable textUpdate = new Runnable() {
        public void run() {
            countFood += (int)(Math.random()*2+0);
            countDrink += (int)(Math.random()*2+0);
            countDessert += (int)(Math.random()*2+0);
            countTIPs += (int)(Math.random()*2+0);
            update();
            Toast user_join = Toast.makeText(ResultActivity.this, "Anonymous User Joined!", Toast.LENGTH_SHORT);
            user_join.show();
        }
    };


    public void update(){
        final double totalFood = Double.parseDouble(dbRoom.searchFood(room));
        final double totalDrink = Double.parseDouble(dbRoom.searchDrink(room));
        final double totalDessert = Double.parseDouble(dbRoom.searchDessert(room));
        final double totalTips = 0.1 * (totalFood + totalDrink + totalDessert);

        totalText.setText(REAL_FORMATTER.format(totalFood + totalDrink + totalDessert + totalTips));

        double totalPFood = totalFood/countFood;
        double totalPDrink = totalDrink/countDrink;
        double totalPDessert = totalDessert/countDessert;
        double totalPTips = totalTips/countTIPs;

        if(totalPFood == Double.POSITIVE_INFINITY){
            totalPFood = 0;
        } else if (totalPDrink == Double.POSITIVE_INFINITY){
            totalPDrink = 0;
        } else if (totalPDessert == Double.POSITIVE_INFINITY){
            totalPDessert = 0;
        } else if (totalPTips == Double.POSITIVE_INFINITY){
            totalPTips = 0;
        }
        youpayText.setText(REAL_FORMATTER.format(totalPFood + totalPDrink + totalPDessert + totalPTips));
    }
}