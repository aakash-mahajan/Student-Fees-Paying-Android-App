package com.example.hp.studentfees;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText Id,Password;
    Button Login;
    String id;

    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Login = (Button)findViewById(R.id.btnShow);



        InsertData();

        Login.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                Id = (EditText)findViewById(R.id.editTextID);
                Password = (EditText)findViewById(R.id.editTextPassword);

                String password=Password.getText().toString();

                String id= Id.getText().toString();

                int result= checkUser(new User(id,password));

                if(result==-1)
                {
                    Toast.makeText(MainActivity.this,"Incorrect Id or Password!!",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Login Successfully!!!",Toast.LENGTH_SHORT).show();
                    Intent myIntent = new Intent(MainActivity.this, DisplayFees.class);
                    myIntent.putExtra("key", id);
                    MainActivity.this.startActivity(myIntent);
                }
            }
        });
    }

    public int checkUser(User user)
    {
        return myDb.checkUser(user);
    }

    public void InsertData() {
        myDb = new DatabaseHelper(this);
        myDb.insertDataIntoFees("1", "70000", "3000", "2000", "40000","115000");
        myDb.insertDataIntoFees("2", "70000", "2500", "1500", "35000","109500");
        myDb.insertDataIntoFees("3", "70000", "2000", "1000", "30000","103000");
        myDb.insertDataIntoFees("4", "70000", "1500", "500", "25000","97000");
        myDb.insertDataIntoUser("1","123");
        myDb.insertDataIntoUser("2","456");
        myDb.insertDataIntoUser("3","789");
        myDb.insertDataIntoUser("4","159");
        myDb.insertDataIntoFeesStatus("1","1");
        myDb.insertDataIntoFeesStatus("2","0");
        myDb.insertDataIntoFeesStatus("3","1");
        myDb.insertDataIntoFeesStatus("4","0");
        myDb.insertDataIntoFeeDedLine("1","15-10-2018","9823710556");
        myDb.insertDataIntoFeeDedLine("2","16-10-2018","8390043027");
        myDb.insertDataIntoFeeDedLine("3","19-10-2018","7898476761");
        myDb.insertDataIntoFeeDedLine("4","20-10-2018","7770871792");
    }
}
