package com.example.perfectportions

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.example.perfectportions.DatabaseManager.DBHelper
import com.example.perfectportions.DatabaseManager.Utente
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.textfield.TextInputEditText

class Registrati : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registrati)

        setupOnBackPressed()
        setupOnAvantiPressed()
    }

    /**
     * Ritorno alla schermata iniziale quando viene premuta la freccia "indietro".
     */
    private fun setupOnBackPressed() {
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                startActivity(Intent(this@Registrati, Logger::class.java))
            }
        }
        onBackPressedDispatcher.addCallback(this, callback)
    }

    /**
     * Gestisce il clic sulla freccia indietro nella toolbar.
     */
    override fun onSupportNavigateUp(): Boolean {
        startActivity(Intent(this@Registrati, Logger::class.java))
        return true
    }

    /**
     * Effettua la registrazione e l'inserimento dell'utente nel database con i dati forniti nei campi di input.
     */
    private fun setupOnAvantiPressed() {

        val avanti = findViewById<Button>(R.id.Avanti)
        avanti.setOnClickListener {

            // Recupera i dati di registrazione inseriti dall'utente
            val username = findViewById<TextInputEditText>(R.id.username).text.toString()
            val email = findViewById<TextInputEditText>(R.id.email).text.toString()
            val password = findViewById<TextInputEditText>(R.id.password).text.toString()

            if(username.isEmpty() || email.isEmpty() || password.isEmpty()){
                Message(this, "Completa tutti i campi")
            }
            else {
                // Creazione di un oggetto Utente
                val newUser = Utente(username = username, email = email, password = password)

                val dbHelper = DBHelper(this)

                // Inserimento del nuovo utente nel database
                val newRow = dbHelper.insertUser(newUser)
                if (newRow != -1L) {
                    // Inserimento riuscito
                } else {
                    // Inserimento non riuscito
                }
            }
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    /**
     * Nasconde la tastiera, quando aperta, nel momento in cui si tocca un qualsiasi punto della schermata che non contiene un campo di input.
     */
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        currentFocus?.let { focus ->
            val x = ev?.x?.toInt() ?: 0
            val y = ev?.y?.toInt() ?: 0
            val outRect = Rect()
            focus.getGlobalVisibleRect(outRect)
            if (!outRect.contains(x, y)) {
                val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(focus.windowToken, 0)
                focus.clearFocus()
            }
        }
        return super.dispatchTouchEvent(ev)
    }
}