package com.kaoneaw.moopiing.sharingpayment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class AddMoneyActivity extends Activity {

    private ImageButton addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmoney);

        addButton = (ImageButton) findViewById(R.id.btn_add);

        initComponents();
    }

    private void initComponents(){
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddMoneyActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
