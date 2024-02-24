package com.example.perfectportions

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.widget.AppCompatButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val accedi = findViewById<Button>(R.id.Accedi)
        accedi.setOnClickListener {
            //Intent per passare alla schermata "Accedi"
            val intent = Intent(this, Accedi::class.java)
            startActivity(intent)
        }
    }
}