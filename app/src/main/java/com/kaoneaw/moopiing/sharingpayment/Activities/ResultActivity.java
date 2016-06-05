package com.kaoneaw.moopiing.sharingpayment.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.kaoneaw.moopiing.sharingpayment.Models.Account;
import com.kaoneaw.moopiing.sharingpayment.Databases.DatabaseAccount;
import com.kaoneaw.moopiing.sharingpayment.Databases.DatabaseRoom;
import com.kaoneaw.moopiing.sharingpayment.R;

import java.text.DecimalFormat;

public class ResultActivity extends Activity {

    DatabaseRoom dbRoom = new DatabaseRoom(this);
    DatabaseAccount dbAccount = new DatabaseAccount(this);

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

    private int countFood_Join;
    private int countDrink_Join;
    private int countDessert_Join;
    private int countTips_Join;

    private double totalCost;
    private double totalPay;

    private Handler mHandler;

    private int num_join = 1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        mHandler = new Handler();
        for(int i=0 ; i<(int)(Math.random()*5+1); i++) {
            if(i==0){
                mHandler.postDelayed(textUpdate, 2000);
            } else {
                mHandler.postDelayed(textUpdate, 3000 * i);
            }
        }

        roomText = (TextView) findViewById(R.id.tv_string_room);
        balanceText = (TextView) findViewById(R.id.tv_string_balance);
        totalText = (TextView) findViewById(R.id.tv_string_total);
        youpayText = (TextView) findViewById(R.id.tv_string_upay);
        payButton = (ImageButton) findViewById(R.id.btn_pay);

        username = super.getIntent().getExtras().getString("Username");
        room = super.getIntent().getExtras().getString("Room");
        countFood = super.getIntent().getExtras().getInt("CountFood");
        countDrink = super.getIntent().getExtras().getInt("CountDrink");
        countDessert = super.getIntent().getExtras().getInt("CountDessert");
        countTIPs = super.getIntent().getExtras().getInt("CountTIPs");

        initComponents();
    }

    private void initComponents(){
        balanceText.setText(dbAccount.searchBalance(username));
        roomText.setText(room);

        payButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(Double.parseDouble(dbAccount.searchBalance(username)) >= totalPay) {

                    mHandler.removeCallbacksAndMessages(null);

                    Account ac = new Account();
                    ac.setUsername(username);
                    ac.setPassword(dbAccount.searchPass(username));
                    ac.setBalance(Double.parseDouble(dbAccount.searchBalance(username)) - Double.parseDouble(youpayText.getText().toString()));

                    dbAccount.updateBalance(ac);


                    dbRoom.delete(room);

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
            countFood_Join += (int)(Math.random()*2+0);
            countDrink_Join += (int)(Math.random()*2+0);
            countDessert_Join += (int)(Math.random()*2+0);
            countTips_Join += (int)(Math.random()*2+0);
            update();
            Toast user_join = Toast.makeText(ResultActivity.this, num_join + " Anonymous User Joined!", Toast.LENGTH_SHORT);
            num_join++;
            user_join.show();
        }
    };


    public void update(){
        double totalFood = Double.parseDouble(dbRoom.searchFood(room));
        double totalDrink = Double.parseDouble(dbRoom.searchDrink(room));
        double totalDessert = Double.parseDouble(dbRoom.searchDessert(room));
        double totalTips = 0.1 * (totalFood + totalDrink + totalDessert);

        totalCost = totalFood + totalDrink + totalDessert + totalTips;
        totalText.setText(REAL_FORMATTER.format(totalCost));

        double totalPFood = totalFood/(countFood + countFood_Join);
        double totalPDrink = totalDrink/(countDrink + countDrink_Join);
        double totalPDessert = totalDessert/(countDessert + countDessert_Join);
        double totalPTips = totalTips/(countTIPs + countTips_Join);

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