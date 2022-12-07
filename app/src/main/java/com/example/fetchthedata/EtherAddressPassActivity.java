package com.example.fetchthedata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EtherAddressPassActivity extends AppCompatActivity {

    private static String value;
    public static String getValue() {
        return value;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ether_address_pass);

        final EditText editText = findViewById(R.id.etAddress);
        Button btn_for_ether = findViewById(R.id.btnGoForEther);

        btn_for_ether.setOnClickListener(view -> {
            String url = "https://etherscan.io/tx/0xf7cedccfcebc8724e6b9c47e777b7be98d66bc2aaeb00233a27f8d283da4ad68";
            Intent urlIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(urlIntent);
        });


        btn_for_ether.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value = editText.getText().toString().trim();
                Intent intent = new Intent(EtherAddressPassActivity.this, EtherActivity.class);
                startActivity(intent);
            }
        });

    }
}