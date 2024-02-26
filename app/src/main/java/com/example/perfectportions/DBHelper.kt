package com.example.perfectportions

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.perfectportions.DBConstants.Companion.DB_NAME
import com.example.perfectportions.DBConstants.Companion.DB_VERSION
import com.example.perfectportions.DBConstants.Companion.Utente.COL_ID
import com.example.perfectportions.DBConstants.Companion.Utente.TABLE_NAME
import com.example.perfectportions.DBConstants.Companion.Utente.COL_USERNAME
import com.example.perfectportions.DBConstants.Companion.Utente.COL_MAIL
import com.example.perfectportions.DBConstants.Companion.Utente.COL_PASSWORD

class DBHelper(var context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(
            "CREATE TABLE" + TABLE_NAME + "(" +
                    COL_ID + "INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COL_USERNAME + "VARCHAR(32)" +
                    COL_MAIL + "VARCHAR(128)" +
                    COL_PASSWORD + "VARCHAR(128)" +
                ")"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    fun insertUser(utente: Utente): Long {
        val dbHelper = DBHelper(context)
        val db = dbHelper.writableDatabase

        val values = ContentValues().apply {
            put(COL_USERNAME, utente.username)
            put(COL_MAIL, utente.email)
            put(COL_PASSWORD, utente.password)
        }

        val newRowId = db.insert(TABLE_NAME, null, values)

        db.close()
        return newRowId
    }
}