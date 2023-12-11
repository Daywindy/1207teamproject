package com.tu.a1207teamproject

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import kotlin.math.log

class TeamDatabase(
    context: Context
) : SQLiteOpenHelper(context, "siheung_database", null, 3) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE Siheung(location TEXT, category TEXT, address TEXT, explanation TEXT, longitude TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS Siheung")
        onCreate(db)
    }

    fun insert(location: String, category: String, address: String, explanation: String, longitude: String) {
        writableDatabase.run {
            execSQL("INSERT INTO Siheung Values(?, ?, ?, ?, ?)", arrayOf(location, category, address, explanation, longitude))
        }
    }

    fun delete(location: String) {
        writableDatabase.run {
            execSQL("DELETE Siheung Where location=$location")
        }
    }

    fun getResult(): List<Siheung> {
        val result = mutableListOf<Siheung>()
        readableDatabase.run {
            val cursor: Cursor = rawQuery("SELECT * FROM Siheung", null)
            while (cursor.moveToNext()) {
                result.add(Siheung(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4)
                ))
            }
        }
        return result
    }
}