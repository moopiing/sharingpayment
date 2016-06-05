package com.kaoneaw.moopiing.sharingpayment.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.kaoneaw.moopiing.sharingpayment.Models.Account;
import com.kaoneaw.moopiing.sharingpayment.Databases.DatabaseAccount;
import com.kaoneaw.moopiing.sharingpayment.R;

public class LendMoneyActivity extends Activity {

    DatabaseAccount dbAccount = new DatabaseAccount(this);

    private TextView balance;
    private EditText inputUsername;
    private EditText inputBalance;
    private ImageButton lendButton;
    private String username;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lendmoney);
        username = super.getIntent().getExtras().getString("Username");

        balance = (TextView) findViewById(R.id.tv_string_balance);
        inputUsername = (EditText) findViewById(R.id.et_input_username);
        inputBalance = (EditText) findViewById(R.id.et_input_amount);
        lendButton = (ImageButton) findViewById(R.id.btn_lend);

        initComponents();
    }

    private void initComponents(){
        balance.setText(dbAccount.searchBalance(username));

        lendButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                final String uname = inputUsername.getText().toString();
                final String amount = inputBalance.getText().toString();

                if (isStringDouble(amount)) {
                    if (uname.equals(dbAccount.searchUname(uname))) {

                        Account ac1 = new Account();
                        ac1.setUsername(username);
                        ac1.setPassword(dbAccount.searchPass(username));
                        ac1.setBalance(Double.parseDouble(dbAccount.searchBalance(username)) + Double.parseDouble(amount));

                        dbAccount.updateBalance(ac1);

                        Account ac2 = new Account();
                        ac2.setUsername(uname);
                        ac2.setPassword(dbAccount.searchPass(uname));
                        ac2.setBalance(Double.parseDouble(dbAccount.searchBalance(uname)) - Double.parseDouble(amount));

                        dbAccount.updateBalance(ac2);

                        Intent intent = new Intent(LendMoneyActivity.this, MainActivity.class);
                        intent.putExtra("Username", username);
                        startActivity(intent);
                    } else {
                        Toast invalid_uname = Toast.makeText(LendMoneyActivity.this, "This username is invalid!", Toast.LENGTH_SHORT);
                        invalid_uname.show();
                    }
                } else {
                    Toast invalid_amount = Toast.makeText(LendMoneyActivity.this, "Amount must be numeric!", Toast.LENGTH_SHORT);
                    invalid_amount.show();
                }
            }
        });
    }
    public boolean isStringDouble(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
}
