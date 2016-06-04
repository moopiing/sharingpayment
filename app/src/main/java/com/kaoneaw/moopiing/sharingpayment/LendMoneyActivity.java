package com.kaoneaw.moopiing.sharingpayment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.DecimalFormat;

public class LendMoneyActivity extends Activity {

    DatabaseHelper helper = new DatabaseHelper(this);

    private static DecimalFormat REAL_FORMATTER = new DecimalFormat("0.##");

    private TextView balance;
    private EditText inputUsername;
    private EditText inputBalance;
    private ImageButton lendButton;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lendmoney);

        balance = (TextView) findViewById(R.id.tv_string_balance);
        inputUsername = (EditText) findViewById(R.id.et_input_username);
        inputBalance = (EditText) findViewById(R.id.et_input_amount);
        lendButton = (ImageButton) findViewById(R.id.btn_lend);

        initComponents();
    }

    private void initComponents(){

        username = super.getIntent().getExtras().getString("Username");
        balance.setText(helper.searchPass1(username));

        lendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String uname = inputUsername.getText().toString();
                final String amount = inputBalance.getText().toString();


                Account ac1 = new Account();
                ac1.setUsername(username);
                ac1.setPassword(helper.searchPass(username));
                ac1.setBalance(Double.parseDouble(helper.searchPass1(username)) + Double.parseDouble(amount));

                helper.updateBalance(ac1);



                Account ac2 = new Account();
                ac2.setUsername(uname);
                ac2.setPassword(helper.searchPass(uname));
                ac2.setBalance(Double.parseDouble(helper.searchPass1(uname)) - Double.parseDouble(amount));

                helper.updateBalance(ac2);

                Intent intent = new Intent(LendMoneyActivity.this,MainActivity.class);
                intent.putExtra("Username", username);
                startActivity(intent);
            }
        });
    }
}
