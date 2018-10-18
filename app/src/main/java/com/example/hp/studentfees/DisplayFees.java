package com.example.hp.studentfees;


import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DisplayFees extends Activity {

    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;

    DatabaseHelper mydb;
    TextView TutionFees;
    TextView StpFees;
    TextView ExtraActivityFees;
    TextView HostelFees;
    TextView Id;
    TextView TotalFees,FeesStatus;
    Button buttonbuy,button1;
    String key,id;

    List<Student> showFees;
    List<Status> getStatus;
    List<DedLine> getDate;

    Intent myIntent;
    int temp;

    String curr_date,ded_line,MobileNo,msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_fees);

        mydb = new DatabaseHelper(this);

        Intent intent = getIntent();
        key = intent.getStringExtra("key");

        TutionFees = (TextView) findViewById(R.id.tutionfess_tv);
        StpFees = (TextView) findViewById(R.id.stpfees_tv);
        ExtraActivityFees = (TextView) findViewById(R.id.extraactivityfees_tv);
        HostelFees = (TextView) findViewById(R.id.hostelfees_tv);
        Id = (TextView)findViewById(R.id.id_tv);
        TotalFees = (TextView)findViewById(R.id.totalfees_tv);
        button1 = (Button)findViewById(R.id.calculatefee);
        buttonbuy = (Button)findViewById(R.id.buttonBuy);
        FeesStatus = (TextView)findViewById(R.id.textViewStatus);

        showFees=new ArrayList<>();
        getStatus=new ArrayList<>();
        getDate = new ArrayList<>();

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showFees = mydb.getFees(key);

                getStatus = mydb.getStatus(key);

                getDate = mydb.getDedLine(key);

                ded_line = getDate.get(0).getCheckDate();
                MobileNo = "+91"+getDate.get(0).getMobileNo();
                curr_date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

                temp = ded_line.compareTo( curr_date );

                Id.setText(showFees.get(0).getId());
                TutionFees.setText(showFees.get(0).getTution_Fees());
                StpFees.setText(showFees.get(0).getSTP_Fees());
                ExtraActivityFees.setText(showFees.get(0).getExtraActivity_Fees());
                HostelFees.setText(showFees.get(0).getHostel_Fees());
                TotalFees.setText(showFees.get(0).getTotal_Fees());

                id = getStatus.get(0).getId();
                String status = getStatus.get(0).getStatus();


               if(status.equals("1")) {
                   FeesStatus.setText("!!!!FEES PAID!!!!");
                   buttonbuy.setEnabled(false);
                   msg = "Fees paid successfully";
                   send();
               }
                else
               {
                   if ( temp < 0 )
                   {
                       msg = "Your fees is pending complete it before "+ded_line+" otherwise you may not sit in your final exam";
                       send();
                   }
                   else if ( temp > 0 )
                   {
                       msg = "You miss your fees deposite dedline.You are not allow to sit in the final exam";
                       send();
                   }
                   else
                   {
                       msg = "Today is the last day to pay your fess otherwise you may not sit in your final exam";
                       send();
                   }
                    FeesStatus.setText("!!!!FEES DUE!!!!");
               }
            }
        });


        findViewById(R.id.buttonBuy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myIntent = new Intent(DisplayFees.this,Payment.class);
                myIntent.putExtra("key", id);
                DisplayFees.this.startActivity(myIntent);
            }
        });
    }

    protected void send() {

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SEND_SMS)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(MobileNo, null, msg, null, null);
                    // smsManager.sendTextMessage(phoneNo, null, message, null, null);
                    Toast.makeText(getApplicationContext(), "SMS sent.",
                            Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(getApplicationContext(),
                            "SMS failed, please try again.", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }
    }
}



