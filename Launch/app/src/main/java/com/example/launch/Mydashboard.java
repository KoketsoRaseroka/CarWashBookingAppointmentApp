package com.example.launch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Mydashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mydashboard);
        TextView lblGreeting = findViewById(R.id.textV1);


        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("userdetails",0);

        lblGreeting.setText(sharedPreferences.getString("email","0"));
        View.OnClickListener listnr = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Avvailability.class);
                startActivity(i);
            }
        };
        TextView Calendarv = (TextView) findViewById(R.id.ctv);
        Calendarv.setOnClickListener(listnr);



        View.OnClickListener listnr2 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Notificationss.class);
                startActivity(i);
            }
        };
        TextView notificationss = (TextView) findViewById(R.id.Ntv);
        notificationss.setOnClickListener(listnr2);



        View.OnClickListener listnr3 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), bookslot.class);
                startActivity(i);
            }
        };
        TextView booksl = (TextView) findViewById(R.id.btv);
        booksl.setOnClickListener(listnr3);
    }
}