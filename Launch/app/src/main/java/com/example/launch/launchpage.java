package com.example.launch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class launchpage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launchpage);



        View.OnClickListener listnr = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Mylogin.class);
                startActivity(i);
            }
        };
        Button button = (Button) findViewById(R.id.Loginbtn);
        button.setOnClickListener(listnr);


        View.OnClickListener listnr2 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Mysignup.class);
                startActivity(i);
            }
        };
        Button button2 = (Button) findViewById(R.id.Registerbtn);
        button2.setOnClickListener(listnr2);
    }
}