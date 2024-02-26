package com.example.perfectportions

import android.content.Context
import android.widget.Toast;

class Message(
    val context: Context,
    val message: String,
) {
    fun showToast() {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}