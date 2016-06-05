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

public class AddMoneyActivity extends Activity {

    DatabaseAccount dbAccount = new DatabaseAccount(this);

    private String username;
    private TextView balance;
    private ImageButton addButton;
    private EditText inputBalance;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmoney);
        username = super.getIntent().getExtras().getString("Username");

        balance = (TextView) findViewById(R.id.tv_string_balance);
        inputBalance = (EditText) findViewById(R.id.et_input_amount);
        addButton = (ImageButton) findViewById(R.id.btn_add);

        initComponents();
    }

    private void initComponents(){
        balance.setText(dbAccount.searchBalance(username));

        addButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                final String balanceStr = inputBalance.getText().toString();

                if (isStringDouble(balanceStr)) {
                    Account ac = new Account();
                    ac.setUsername(username);
                    ac.setPassword(dbAccount.searchPass(username));
                    ac.setBalance(Double.parseDouble(dbAccount.searchBalance(username)) + Double.parseDouble(balanceStr));

                    dbAccount.updateBalance(ac);

                    Intent intent = new Intent(AddMoneyActivity.this, MainActivity.class);
                    intent.putExtra("Username", username);
                    startActivity(intent);
                } else {
                    Toast invalid_amount = Toast.makeText(AddMoneyActivity.this, "Balance must be numeric!", Toast.LENGTH_SHORT);
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
