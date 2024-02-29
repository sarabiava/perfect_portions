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

        setupOnAccediPressed()
        setupOnRegistratiPressed()
    }

    /**
     * Porta alla schermata di accesso quando viene premuto il pulsante "Accedi".
     */
    private fun setupOnAccediPressed() {
        val accedi = findViewById<Button>(R.id.Accedi)
        accedi.setOnClickListener {
            startActivity(Intent(this, Accedi::class.java))
        }
    }

    /**
     * Porta alla schermata di registrazione quando viene premuto il pulsante "Registrati".
     */
    private fun setupOnRegistratiPressed() {
        val registrati = findViewById<Button>(R.id.Registrati)
        registrati.setOnClickListener {
            startActivity(Intent(this, Registrati::class.java))
        }
    }
}