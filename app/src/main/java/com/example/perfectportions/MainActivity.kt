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

        // Passare alla schermata "Accedi"
        val accedi = findViewById<Button>(R.id.Accedi)
        accedi.setOnClickListener {
            startActivity(Intent(this, Accedi::class.java))
        }

        // Passare alla schermata "Registrati"
        val registrati = findViewById<Button>(R.id.Registrati)
        registrati.setOnClickListener {
            startActivity(Intent(this, Registrati::class.java))
        }
    }
}