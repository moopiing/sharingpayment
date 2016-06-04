package com.kaoneaw.moopiing.sharingpayment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class ResultActivity extends Activity {

    DatabaseRoom dbRoom = new DatabaseRoom(this);
    DatabaseHelper helper = new DatabaseHelper(this);

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

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

        countFood =  super.getIntent().getExtras().getInt("CountFood");
        countDrink =  super.getIntent().getExtras().getInt("CountDrink");
        countDessert =  super.getIntent().getExtras().getInt("CountDessert");
        countTIPs =  super.getIntent().getExtras().getInt("CountTIPs");

        final double totalCost = (Integer.parseInt(dbRoom.searchFood(room)) +
                Integer.parseInt(dbRoom.searchDrink(room)) +
                Integer.parseInt(dbRoom.searchDessert(room)));

        final double totalTips = totalCost * 0.1;

        totalText.setText(String.valueOf(totalCost+totalTips));

        final double totalPay = Integer.parseInt(dbRoom.searchFood(room))/countFood +
                Integer.parseInt(dbRoom.searchDrink(room))/countDrink +
                Integer.parseInt(dbRoom.searchDessert(room))/countDessert +
                totalTips/countTIPs;

        youpayText.setText(String.valueOf(totalPay));

        payButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Account ac = new Account();
                ac.setUsername(username);
                ac.setPassword(helper.searchPass(username));
                ac.setBalance((int)(Double.parseDouble(helper.searchPass1(username)) - Double.parseDouble(youpayText.getText().toString())));

                helper.updateBalance(ac);

                Intent intent = new Intent(ResultActivity.this,MainActivity.class);
                intent.putExtra("Username", username);
                startActivity(intent);
            }
        });
    }
}
