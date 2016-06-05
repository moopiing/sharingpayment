package com.kaoneaw.moopiing.sharingpayment.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.kaoneaw.moopiing.sharingpayment.Databases.DatabaseAccount;
import com.kaoneaw.moopiing.sharingpayment.Databases.DatabaseRoom;
import com.kaoneaw.moopiing.sharingpayment.R;

public class MainActivity extends Activity {

    DatabaseAccount dbAccount = new DatabaseAccount(this);
    DatabaseRoom dbRoom = new DatabaseRoom(this);

    private TextView balance;
    private ImageButton newRoomButtom;
    private ImageButton joinRoomButtom;
    private ImageButton addMoneyButtom;
    private ImageButton lendMoneyButtom;
    private ImageButton viewHistoryButtom;
    private ImageButton logOutButton;

    private String username;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = super.getIntent().getExtras().getString("Username");

        balance = (TextView) findViewById(R.id.tv_string_balance);
        newRoomButtom = (ImageButton) findViewById(R.id.btn_menu_new_room);
        joinRoomButtom = (ImageButton) findViewById(R.id.btn_menu_join_room);
        addMoneyButtom = (ImageButton) findViewById(R.id.btn_menu_add_money);
        lendMoneyButtom = (ImageButton) findViewById(R.id.btn_menu_lend_money);
        viewHistoryButtom = (ImageButton) findViewById(R.id.btn_menu_history);
        logOutButton = (ImageButton) findViewById(R.id.btn_menu_logout);

        initComponents();
    }

    private void initComponents(){
        balance.setText(dbAccount.searchBalance(username));

        newRoomButtom.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewRoomActivity.class);
                intent.putExtra("Username", username);
                startActivity(intent);
            }
        });
        joinRoomButtom.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,JoinRoomActivity.class);
                intent.putExtra("Username", username);
                startActivity(intent);
            }
        });
        addMoneyButtom.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AddMoneyActivity.class);
                intent.putExtra("Username", username);
                startActivity(intent);
            }
        });
        lendMoneyButtom.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,LendMoneyActivity.class);
                intent.putExtra("Username", username);
                startActivity(intent);
            }
        });
        viewHistoryButtom.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,InfoActivity.class);
                intent.putExtra("Username", username);
                startActivity(intent);
            }
        });
        logOutButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(username.equals("guest")){
                    dbAccount.delete("guest");
                }
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
