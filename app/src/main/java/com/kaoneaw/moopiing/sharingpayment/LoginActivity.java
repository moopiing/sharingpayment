package com.kaoneaw.moopiing.sharingpayment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {

    DatabaseHelper helper = new DatabaseHelper(this);

    private ImageButton loginButton;
    private ImageButton signUpButton;
    private ImageButton guestButton;
    private EditText input_username;
    private EditText input_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = (ImageButton) findViewById(R.id.btn_login);
        signUpButton = (ImageButton) findViewById(R.id.btn_menu_signup);
        guestButton = (ImageButton) findViewById(R.id.btn_menu_guest);

        input_username = (EditText) findViewById(R.id.et_input_username);
        input_password = (EditText) findViewById(R.id.et_input_password);

        initComponents();
    }

    private void initComponents(){
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = input_username.getText().toString();
                String password = input_password.getText().toString();

                String searchPass = helper.searchPass(username);

                if(!username.equals("") && !password.equals("")) {
                    if (password.equals(searchPass)) {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("Username", username);
                        startActivity(intent);
                    } else {
                        Toast nomatch = Toast.makeText(LoginActivity.this, "Username and Password don't match!", Toast.LENGTH_SHORT);
                        nomatch.show();
                    }
                } else if (username.equals("")){
                    Toast empty_username = Toast.makeText(LoginActivity.this, "Username don't empty!", Toast.LENGTH_SHORT);
                    empty_username.show();
                } else if (password.equals("")){
                    Toast empty_password = Toast.makeText(LoginActivity.this, "Password don't empty!", Toast.LENGTH_SHORT);
                    empty_password.show();
                }
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