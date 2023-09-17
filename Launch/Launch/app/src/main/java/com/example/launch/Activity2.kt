package com.example.launch

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView

class Activity2 : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)

                val Signuptxt = findViewById<TextView>(R.id.textV4)
                Signuptxt.setOnClickListener {
                    val Intent = Intent(this, Activity3::class.java)

                    startActivity(Intent)
                }
                val forgotPtxt = findViewById<TextView>(R.id.textView2)
                forgotPtxt.setOnClickListener {
                    val Intent = Intent(this, ForgotPasswordScreen::class.java)

                    startActivity(Intent)
                }
                val Loginbtn1 = findViewById<Button>(R.id.Loginbutton)
                Loginbtn1.setOnClickListener {
                    val Intent = Intent(this, Dashboard::class.java)

                    startActivity(Intent)
                }

                var actionBar = supportActionBar

                if (actionBar != null) {
                    actionBar.setDisplayHomeAsUpEnabled(true)

                }

            }

            override fun onContextItemSelected(item: MenuItem): Boolean {
                when (item.itemId) {
                    android.R.id.home -> {
                        finish()
                        return true
                    }

                }
                return super.onContextItemSelected(item)
            }
        }


