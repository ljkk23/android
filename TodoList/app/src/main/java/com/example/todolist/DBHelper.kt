package com.example.todolist

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(
    context: Context?,
    name: String?,
    version: Int
) : SQLiteOpenHelper(context, name, null,version) {



    override fun onCreate(db: SQLiteDatabase) {

        db.execSQL("CREATE TABLE ${Todo.TABLE} (\n" +
                "\t${Todo.COL_ID} integer PRIMARY KEY autoincrement,\n" +
                "\t${Todo.COL_CONTENT} text,\n" +
                "\t${Todo.COL_TIME} REAL)")

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }
}