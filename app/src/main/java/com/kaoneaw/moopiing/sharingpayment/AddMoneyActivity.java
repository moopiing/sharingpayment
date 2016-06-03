package com.kaoneaw.moopiing.sharingpayment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class AddMoneyActivity extends Activity {

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





//                Intent intent = new Intent(AddMoneyActivity.this,MainActivity.class);
//                startActivity(intent);

            }
        });
    }
}
