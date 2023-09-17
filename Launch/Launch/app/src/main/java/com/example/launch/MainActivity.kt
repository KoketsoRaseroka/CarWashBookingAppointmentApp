package com.example.launch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val Loginclickbtn = findViewById<Button>(R.id.Loginbtn)
        Loginclickbtn.setOnClickListener {
            val Intent = Intent(this, Activity2::class.java)

            startActivity(Intent)


            }
        }
    }

