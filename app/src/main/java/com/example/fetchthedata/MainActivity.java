package com.example.fetchthedata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        TextView from_ = findViewById(R.id.tv_showData);
        TextView To_ = findViewById(R.id.tv_to);
//        TextView block_number_ = findViewById(R.id.tv_block_no);
//        TextView token_name_ = findViewById(R.id.tv_token_name);
//        TextView token_symbol_ = findViewById(R.id.tv_token_symbol);
//        TextView token_amount_ = findViewById(R.id.tv_token_amount);
//        TextView timestamp_ = findViewById(R.id.tv_timestamp);
        TextView hash_ = findViewById(R.id.tv_hash);
        Button button = findViewById(R.id.btn_fetch_data);

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            String API_KEY = "V4ER6WPYVWQY8BQ1GG1AUF4Q59SW8VCBXT";
            String url = "https://api.etherscan.io/api?module=account&action=tokentx&address=0x6dfc34609a05bC22319fA4Cce1d1E2929548c0D7&page=1&offset=10&startblock=0&endblock=27025780&sort=desc&apikey=V4ER6WPYVWQY8BQ1GG1AUF4Q59SW8VCBXT";

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    try {
                        JSONArray jsonArray = response.getJSONArray("result");

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String from = jsonObject.getString("from");
                            String to = jsonObject.getString("to");
                            String blockNumber = jsonObject.getString("blockNumber");
                            String tokenName = jsonObject.getString("tokenName");
                            String tokenSymbol = jsonObject.getString("tokenSymbol");


                            String value = jsonObject.getString("value");
                            String tokenDecimal = jsonObject.getString("tokenDecimal");

                            double val = Double.parseDouble(value);
                            double tokD = Double.parseDouble(tokenDecimal);

                            tokD = Math.pow(10, tokD);
                            val = val / tokD;
                            String TokenAmount = Double.toString(val);


                            long timeStamp = jsonObject.getLong("timeStamp");
                            Calendar cal = Calendar.getInstance(Locale.ENGLISH);
                            cal.setTimeInMillis(timeStamp * 1000L);
                            String date = DateFormat.format("dd-MM-yyyy hh:mm:ss", cal).toString();

                            String hash = jsonObject.getString("hash");

                         //   textView.append("From : " + from + "\n" + "To : " + to + "\n" + "Block No : " + blockNumber + "\n" + "Token Name : " + tokenName + "\n" + "Token Symbol : " + tokenSymbol + "\n" + "Token Amount : " + TokenAmount + "\n" + "TimeStamp : " + date + "\n" + "Hash : " + hash + "\n" + "\n \n");


                            displayData(to, from);


                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                private void displayData(String from, String to) {
                    hash_.append("From :"+from+"\n");

                    To_.append("To :"+to);

                }

            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            requestQueue.add(jsonObjectRequest);
        }
        });
    }
}