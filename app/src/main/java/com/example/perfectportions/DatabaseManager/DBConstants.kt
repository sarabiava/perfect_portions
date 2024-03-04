package com.example.perfectportions.DatabaseManager

class DBConstants {
    companion object {
        const val DB_VERSION = 1
        const val DB_NAME = "perfectportions_data.db"

        object Utente {
            const val TABLE_NAME = "utenti"
            const val COL_ID = "id"
            const val COL_USERNAME = "username"
            const val COL_MAIL = "mail"
            const val COL_PASSWORD = "mail"
        }

        object Ricetta {
            const val TABLE_NAME = "ricette"
            const val COL_ID = "id"
            const val COL_NOME = "nome"
            const val COL_INGREDIENTI = "ingredienti"
            const val COL_IMAGE_PATH = "percorso_immagine"
            const val COL_NOTE = "note"
        }

    }
}