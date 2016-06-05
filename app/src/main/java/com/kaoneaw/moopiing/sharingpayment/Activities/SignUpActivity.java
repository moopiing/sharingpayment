package com.kaoneaw.moopiing.sharingpayment.Activities;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.kaoneaw.moopiing.sharingpayment.Models.Account;
import com.kaoneaw.moopiing.sharingpayment.Databases.DatabaseAccount;
import com.kaoneaw.moopiing.sharingpayment.R;

public class SignUpActivity extends Activity {

    DatabaseAccount dbAccount = new DatabaseAccount(this);

    private EditText input_username;
    private EditText input_password;
    private EditText input_confirm;
    private ImageButton signUpButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        input_username = (EditText) findViewById(R.id.et_input_username);
        input_password = (EditText) findViewById(R.id.et_input_password);
        input_confirm = (EditText) findViewById(R.id.et_input_comfirm_password);
        signUpButton = (ImageButton) findViewById(R.id.btn_signup);

        initComponents();
    }

    private void initComponents(){
        signUpButton.setOnClickListener(new View.OnClickListener() {
                public void onClick (View v) {
                    final String username = input_username.getText().toString();
                    final String password = input_password.getText().toString();
                    final String confirm = input_confirm.getText().toString();

                    if (!password.equals(confirm)) {
                        Toast noMatch = Toast.makeText(SignUpActivity.this, "Passwords don't match!", Toast.LENGTH_SHORT);
                        noMatch.show();
                    }else if (username.equals("")){
                        Toast empty_username = Toast.makeText(SignUpActivity.this, "Username don't empty!", Toast.LENGTH_SHORT);
                        empty_username.show();
                    }else if (password.equals("")){
                        Toast empty_password = Toast.makeText(SignUpActivity.this, "Password don't empty!", Toast.LENGTH_SHORT);
                        empty_password.show();
                    } else if (username.equals(dbAccount.searchUname(username))){
                        Toast invalid_uname = Toast.makeText(SignUpActivity.this, "This username is already taken!", Toast.LENGTH_SHORT);
                        invalid_uname.show();
                    } else {
                        Account ac = new Account();
                        ac.setUsername(username);
                        ac.setPassword(password);
                        ac.setBalance(100);

                        dbAccount.insertContact(ac);

                        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                }
        });
    }
}
