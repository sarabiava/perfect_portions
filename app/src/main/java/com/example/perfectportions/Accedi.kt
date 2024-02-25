package com.example.perfectportions

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.ViewTreeObserver
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar

class Accedi : AppCompatActivity() {
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.accedi)

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                startActivity(Intent(this@Accedi, MainActivity::class.java))
            }
        }
        onBackPressedDispatcher.addCallback(this, callback)

        val clickableText = findViewById<TextView>(R.id.textView3)
        clickableText.setOnClickListener {
            startActivity(Intent(this, Registrati::class.java))
        }

        // Riferimento alla radice del layout
        val rootView = findViewById<View>(android.R.id.content)
        // Aggiunta di un listener per osservare il cambiamento nella gerarchia della vista
        rootView.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                // Nasconde la tastiera quando la vista viene toccata
                rootView.setOnTouchListener { v, event ->
                    hideKeyboard(v)
                    false
                }
                // Rimozione listener una volta che è stato utilizzato
                rootView.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        // Gestisce il clic sulla freccia indietro nella toolbar
        startActivity(Intent(this@Accedi, MainActivity::class.java))
        return true
    }

    private fun hideKeyboard(view: View) {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

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