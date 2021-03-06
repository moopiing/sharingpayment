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

public class LoginActivity extends Activity {

    DatabaseAccount dbAccount = new DatabaseAccount(this);

    private ImageButton loginButton;
    private ImageButton signUpButton;
    private ImageButton guestButton;
    private EditText input_username;
    private EditText input_password;

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

            public void onClick(View v) {

                final String username = input_username.getText().toString();
                final String password = input_password.getText().toString();
                final String searchPass = dbAccount.searchPass(username);

                if(!username.equals("") && !password.equals("")) {
                    if (password.equals(searchPass)) {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("Username", username);

                        startActivity(intent);
                    } else {
                        Toast nomatch = Toast.makeText(LoginActivity.this, "Username or Password is incorrect!", Toast.LENGTH_SHORT);
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
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
        guestButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Account guest = new Account();
                guest.setUsername("guest");
                guest.setPassword("guest");
                guest.setBalance(0);

                dbAccount.insertContact(guest);

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("Username", "guest");
                startActivity(intent);
            }
        });
    }

}