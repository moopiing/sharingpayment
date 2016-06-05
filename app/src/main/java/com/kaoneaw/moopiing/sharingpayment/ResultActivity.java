package com.kaoneaw.moopiing.sharingpayment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class ResultActivity extends Activity {

    DatabaseRoom dbRoom = new DatabaseRoom(this);
    DatabaseHelper helper = new DatabaseHelper(this);

    private static DecimalFormat REAL_FORMATTER = new DecimalFormat("0.##");

    private ImageButton payButton;
    private TextView roomText;
    private TextView balanceText;
    private TextView totalText;
    private TextView youpayText;
    private String room;
    private String username;

    private int countFood;
    private int countDrink;
    private int countDessert;
    private int countTIPs;

    private double totalCost;
    private double totalPay;

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
        balanceText = (TextView) findViewById(R.id.tv_string_balance);
        totalText = (TextView) findViewById(R.id.tv_string_total);
        youpayText = (TextView) findViewById(R.id.tv_string_upay);

        payButton = (ImageButton) findViewById(R.id.btn_pay);

        initComponents();
    }

    private void initComponents(){

        username = super.getIntent().getExtras().getString("Username");
        balanceText.setText(helper.searchPass1(username));

        room = super.getIntent().getExtras().getString("Room");
        roomText.setText(room);

        countFood = super.getIntent().getExtras().getInt("CountFood");
        countDrink = super.getIntent().getExtras().getInt("CountDrink");
        countDessert = super.getIntent().getExtras().getInt("CountDessert");
        countTIPs = super.getIntent().getExtras().getInt("CountTIPs");

        payButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                if(Double.parseDouble(helper.searchPass1(username)) >= totalPay) {

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
                } else {
                    Toast no_money = Toast.makeText(ResultActivity.this, "Your balance is not enough!", Toast.LENGTH_SHORT);
                    no_money.show();
                }
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

        totalCost = totalFood + totalDrink + totalDessert + totalTips;

        totalText.setText(REAL_FORMATTER.format(totalCost));

        double totalPFood = totalFood/countFood;
        double totalPDrink = totalDrink/countDrink;
        double totalPDessert = totalDessert/countDessert;
        double totalPTips = totalTips/countTIPs;

        if(totalFood == 0 || countFood == 0){
            totalPFood = 0;
        }
        if (totalDrink == 0 || countDrink == 0){
            totalPDrink = 0;
        }
        if (totalDessert == 0 || countDessert == 0){
            totalPDessert = 0;
        }
        if (totalTips == 0 || countTIPs == 0){
            totalPTips = 0;
        }

        totalPay = totalPFood + totalPDrink + totalPDessert + totalPTips;

        youpayText.setText(REAL_FORMATTER.format(totalPay));
    }
}