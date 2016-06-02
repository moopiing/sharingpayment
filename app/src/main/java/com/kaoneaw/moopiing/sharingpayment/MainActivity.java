package com.kaoneaw.moopiing.sharingpayment;

        import android.app.Activity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.ImageButton;

public class MainActivity extends Activity {

    private ImageButton newRoomButtom;
    private ImageButton joinRoomButtom;
    private ImageButton addMoneyButtom;
    private ImageButton lendMoneyButtom;
    private ImageButton viewHistoryButtom;
    private ImageButton logOutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newRoomButtom = (ImageButton) findViewById(R.id.btn_new_room);
        joinRoomButtom = (ImageButton) findViewById(R.id.btn_join_room);
        addMoneyButtom = (ImageButton) findViewById(R.id.btn_add_money);
        lendMoneyButtom = (ImageButton) findViewById(R.id.btn_lend_money);
        viewHistoryButtom = (ImageButton) findViewById(R.id.btn_history);
        logOutButton = (ImageButton) findViewById(R.id.btn_logout);

        initComponents();
    }

    private void initComponents(){

        newRoomButtom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,NewRoomActivity.class);
                startActivity(intent);
            }
        });
        joinRoomButtom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,JoinRoomActivity.class);
                startActivity(intent);
            }
        });
        addMoneyButtom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AddMoneyActivity.class);
                startActivity(intent);
            }
        });
        lendMoneyButtom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,LendMoneyActivity.class);
                startActivity(intent);
            }
        });
        viewHistoryButtom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,HistoryActivity.class);
                startActivity(intent);
            }
        });
        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });



    }
}
