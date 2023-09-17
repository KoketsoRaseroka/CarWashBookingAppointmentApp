package com.example.launch;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Mylogin extends AppCompatActivity {
    LinearLayout lvparent;
    EditText edtEmailAddress, edtPassword;
    Button btnLogin;
    LinearLayout lvparent2;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mylogin);

        View.OnClickListener listnr = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Mysignup.class);
                startActivity(i);
            }
        };
        TextView Signuptxt = (TextView) findViewById(R.id.textV4);
        Signuptxt.setOnClickListener(listnr);



        View.OnClickListener listnr2 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),forgot.class);
                startActivity(i);
            }
        };
        TextView forgotPtxt = (TextView) findViewById(R.id.textView2);
        forgotPtxt.setOnClickListener(listnr2);

    }

    private class DoLoginForUser extends AsyncTask<String, Void, String> {
        String emailId, password;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            emailId = edtEmailAddress.getText().toString();
            password = edtPassword.getText().toString();
            progressBar.setVisibility(View.VISIBLE);
            btnLogin.setVisibility(View.GONE);
        }
        @Override
        protected String doInBackground(String... params) {


            try {
                ConnectionHelper con = new ConnectionHelper();
                Connection connect = ConnectionHelper.CONN();

                String query = "Select * from Users where id='" + emailId + "'";
                PreparedStatement ps = connect.prepareStatement(query);

                Log.e("query",query);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    String passcode = rs.getString("password");
                    connect.close();
                    rs.close();
                    ps.close();
                    if (passcode != null && !passcode.trim().equals("") && passcode.equals(password))
                        return "success";
                    else
                        return "Invalid Credentials";

                } else
                    return "User does not exists.";
            } catch (SQLException e) {

                return "Error:" + e.getMessage().toString();
            } catch (Exception e) {
                return "Error:" + e.getMessage().toString();
            }
        }

        @Override
        protected void onPostExecute(String result) {

            //Toast.makeText(signup.this, result, Toast.LENGTH_SHORT).show();
            ShowSnackBar(result);
            progressBar.setVisibility(View.GONE);
            btnLogin.setVisibility(View.VISIBLE);
            if (result.equals("success")) {
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("userdetails",0);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString("email",edtEmailAddress.getText().toString());

                editor.commit();

                Intent i = new Intent(Mylogin.this, Mydashboard.class);
                startActivity(i);

            } else {
                ShowSnackBar(result);
            }
        }
    }

    public void ShowSnackBar(String message) {
        Snackbar.make(lvparent2, message, Snackbar.LENGTH_LONG)
                .setAction("CLOSE", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                })
                .setActionTextColor(getResources().getColor(android.R.color.holo_red_light))
                .show();
    }

    public void DoLogin(View v)
    {
        DoLoginForUser login = new DoLoginForUser();
        login.execute("");
    }
}
