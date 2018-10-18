package com.example.hp.studentfees;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Payment extends AppCompatActivity {

    String key;

    DatabaseHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        Button btnByu = (Button)findViewById(R.id.buttonbuy);

        Intent intent = getIntent();
        key = intent.getStringExtra("key");

        btnByu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mydb = new DatabaseHelper(Payment.this);
                boolean result = mydb.UpdateStatus(key);
                if(result)
                {
                    Intent myIntent = new Intent(Payment.this, DonePayment.class);
                    Payment.this.startActivity(myIntent);
                }
                else {
                    Intent myIntent = new Intent(Payment.this, Payment.class);
                    Payment.this.startActivity(myIntent);
                }
            }
        });
    }
}
