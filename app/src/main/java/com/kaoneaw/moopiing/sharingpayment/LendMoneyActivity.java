package com.kaoneaw.moopiing.sharingpayment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class LendMoneyActivity extends Activity {

    private ImageButton lendInLendMoneyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lendmoney);

        lendInLendMoneyButton = (ImageButton) findViewById(R.id.btn_lend_in_lendmoney);

        initComponents();
    }

    private void initComponents(){
        lendInLendMoneyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LendMoneyActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
