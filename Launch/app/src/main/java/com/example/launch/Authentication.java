package com.example.launch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Authentication extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        View.OnClickListener listnr = new View.OnClickListener() {

            public void onClick(View v) {
                Intent i = new Intent();
                startActivity(i);
            }
        };
        Button button = (Button) findViewById(R.id.button4);
        button.setOnClickListener(listnr);
    }
}