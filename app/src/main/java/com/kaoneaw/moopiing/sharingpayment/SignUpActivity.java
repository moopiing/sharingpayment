package com.kaoneaw.moopiing.sharingpayment;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class SignUpActivity extends Activity {

    DatabaseHelper helper = new DatabaseHelper(this);

    private EditText input_username;
    private EditText input_password;
    private EditText input_confirm;
    private ImageButton signUpButton;

    @Override
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

                    String username = input_username.getText().toString();
                    String password = input_password.getText().toString();
                    String confirm = input_confirm.getText().toString();

                    if (!password.equals(confirm)) {
                        Toast noMatch = Toast.makeText(SignUpActivity.this, "Passwords don't match!", Toast.LENGTH_SHORT);
                        noMatch.show();
                    } else if (username.equals("")){
                        Toast empty_username = Toast.makeText(SignUpActivity.this, "Username don't empty!", Toast.LENGTH_SHORT);
                        empty_username.show();
                    }else if (password.equals("")){
                        Toast empty_password = Toast.makeText(SignUpActivity.this, "Password don't empty!", Toast.LENGTH_SHORT);
                        empty_password.show();
                    }
                    else {
                        Account ac = new Account();
                        ac.setUsername(username);
                        ac.setPassword(password);

                        helper.insertContact(ac);

                        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                }
        });
    }
}
