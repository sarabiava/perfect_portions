package com.example.perfectportions

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar

class Accedi : AppCompatActivity() {
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.accedi)

        setupOnBackPressed()
        setupOnRegistratiPressed()
        setupOnAvantiPressed()
    }

    /**
     * Porta alla schermata di registrazione quando viene premuta la scritta "registrati".
     */
    private fun setupOnRegistratiPressed() {
        val clickableText = findViewById<TextView>(R.id.textView3)
        clickableText.setOnClickListener {
            startActivity(Intent(this, Registrati::class.java))
        }
    }

    private fun setupOnAvantiPressed() {
        val avanti = findViewById<Button>(R.id.Avanti)
        avanti.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
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
                startActivity(Intent(this@Accedi, Logger::class.java))
            }
        }
        onBackPressedDispatcher.addCallback(this, callback)
    }

    /**
     * Gestisce il clic sulla freccia indietro nella toolbar.
     */
    override fun onSupportNavigateUp(): Boolean {
        // Gestisce il clic sulla freccia indietro nella toolbar
        startActivity(Intent(this@Accedi, Logger::class.java))
        return true
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