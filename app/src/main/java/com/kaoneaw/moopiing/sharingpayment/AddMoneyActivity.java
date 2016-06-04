package com.kaoneaw.moopiing.sharingpayment;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.DecimalFormat;

public class AddMoneyActivity extends Activity {

    private static DecimalFormat REAL_FORMATTER = new DecimalFormat("0.##");

    DatabaseHelper helper = new DatabaseHelper(this);

    private String username;
    private TextView balance;
    private ImageButton addButton;
    private EditText inputBalance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmoney);

        balance = (TextView) findViewById(R.id.tv_string_balance);
        inputBalance = (EditText) findViewById(R.id.et_input_amount);
        addButton = (ImageButton) findViewById(R.id.btn_add);

        initComponents();
    }

    private void initComponents(){

        username = super.getIntent().getExtras().getString("Username");
        balance.setText(helper.searchPass1(username));


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String temp = inputBalance.getText().toString();

                Account ac = new Account();
                ac.setUsername(username);
                ac.setPassword(helper.searchPass(username));
                ac.setBalance(Integer.parseInt(temp) + Integer.parseInt(helper.searchPass1(username)));

                helper.updateBalance(ac);

                Intent intent = new Intent(AddMoneyActivity.this,MainActivity.class);
                intent.putExtra("Username", username);
                startActivity(intent);
            }
        });
    }
}
