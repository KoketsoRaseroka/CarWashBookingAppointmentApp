package com.example.launch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Mysignup extends AppCompatActivity {
    EditText edtEmailAddress, edtMobile, edtFullName, edtPassword, edtConfirmPassword;
    Button btnSignUp;
    ProgressBar progressBar;
    LinearLayout lvparent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mysignup);
        View.OnClickListener listnr = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Mydashboard.class);
                startActivity(i);
            }
        };
        Button button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(listnr);


        View.OnClickListener listnr2 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Mylogin.class);
                startActivity(i);
            }
        };
        TextView textView3 = (TextView) findViewById(R.id.textView4);
        textView3.setOnClickListener(listnr2);
            edtEmailAddress = findViewById(R.id.editTxt1);
            edtFullName= findViewById(R.id.editTxt2);
            edtMobile = findViewById(R.id.editTxt3);
            edtPassword = findViewById(R.id.editTxt4);
            edtConfirmPassword = findViewById(R.id.editTxt5);
            btnSignUp = findViewById(R.id.button1);
            progressBar = findViewById(R.id.progressBar2);
            lvparent = findViewById(R.id.lvparent);


            btnSignUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (isEmpty(edtEmailAddress.getText().toString()) ||
                            isEmpty(edtPassword.getText().toString()) ||
                            isEmpty(edtFullName.getText().toString()) ||
                            isEmpty(edtMobile.getText().toString())||
                            isEmpty(edtConfirmPassword.getText().toString()))
                        ShowSnackBar("Please enter all fields");
                    else if (!edtPassword.getText().toString().equals(edtConfirmPassword.getText().toString()))
                        ShowSnackBar("Passwords do not match");
                    else {
                        AddUsers addUsers = new AddUsers();
                        addUsers.execute("");
                    }


                }
            });
        }

        public void ShowSnackBar(String message) {
            Snackbar.make(lvparent, message, Snackbar.LENGTH_LONG)
                    .setAction("CLOSE", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    })
                    .setActionTextColor(getResources().getColor(android.R.color.holo_red_light))
                    .show();
        }

        public Boolean isEmpty(String strValue) {
            if (strValue == null || strValue.trim().equals(("")))
                return true;
            else
                return false;
        }

        private class AddUsers extends AsyncTask<String, Void, String> {
            String emailId, password;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                emailId = edtEmailAddress.getText().toString();
                password = edtPassword.getText().toString();
                progressBar.setVisibility(View.VISIBLE);
                btnSignUp.setVisibility(View.GONE);
            }

            @Override
            protected String doInBackground(String... params) {

                try {
                    ConnectionHelper con = new ConnectionHelper();
                    Connection connect = ConnectionHelper.CONN();

                    String queryStmt = "Insert into Users " +
                            " (id,Uname,UPassword) values "
                            + "('"
                            + emailId
                            + "','"
                            + password
                            + "','User')";

                    PreparedStatement preparedStatement = connect.prepareStatement(queryStmt);

                    preparedStatement.executeUpdate();

                    preparedStatement.close();
                    return "Added successfully";

                } catch (SQLException e) {
                    e.printStackTrace();
                    return e.getMessage().toString();
                } catch (Exception e) {
                    return e.getMessage().toString();
                }
            }

            @Override
            protected void onPostExecute(String result) {

                //Toast.makeText(signup.this, result, Toast.LENGTH_SHORT).show();
                ShowSnackBar(result);
                progressBar.setVisibility(View.GONE);
                btnSignUp.setVisibility(View.VISIBLE);
                if (result.equals("Added successfully")) {
                    // Clear();
                }

            }

        }

        public void Login(View v) {
            Intent i = new Intent(Mysignup.this, Mydashboard.class);
            startActivity(i);
        }

    }




