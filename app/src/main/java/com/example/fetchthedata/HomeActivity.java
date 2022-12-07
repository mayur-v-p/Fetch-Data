package com.example.fetchthedata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button btn = findViewById(R.id.btn_ether_scan);
        Button pass_tron = findViewById(R.id.btn_tron_scan);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, EtherAddressPassActivity.class);
                startActivity(intent);
            }
        });

        pass_tron.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent TronIntent = new Intent(getApplicationContext(), TronActivity.class);
                startActivity(TronIntent);
            }
        });


    }
}