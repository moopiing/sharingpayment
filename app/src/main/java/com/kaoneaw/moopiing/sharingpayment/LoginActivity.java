package com.kaoneaw.moopiing.sharingpayment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class LoginActivity extends Activity {

    private ImageButton loginButton;
    private ImageButton signUpButton;
    private ImageButton guestButton;
    private TextView usernameTextView;
    private TextView passwordTextView;
    private EditText usernameInputText;
    private EditText passwordInputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = (ImageButton) findViewById(R.id.btn_login);
        signUpButton = (ImageButton) findViewById(R.id.btn_menu_signup);
        guestButton = (ImageButton) findViewById(R.id.btn_menu_guest);

        usernameTextView = (TextView) findViewById(R.id.tv_username);
        passwordTextView = (TextView) findViewById(R.id.tv_password);

        usernameInputText = (EditText) findViewById(R.id.et_input_username);
        passwordInputText = (EditText) findViewById(R.id.et_input_password);

        initComponents();
    }

    private void initComponents(){
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
        guestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}