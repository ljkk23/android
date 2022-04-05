package com.example.todolist

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class CreateActivity : AppCompatActivity() {

    lateinit var helper:DBHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)
        helper = DBHelper(this,"todo.db",1)
        findViewById<Button>(R.id.btn_save).setOnClickListener {
            saveInput()
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun saveInput() {
        val text = findViewById<EditText>( R.id.ipt_text).text.toString()

        saveInDb(text)
    }

    private fun saveInDb(text: String) {
        val db = helper.writableDatabase
        val values = ContentValues().apply {
            put(Todo.COL_CONTENT,text)
            put(Todo.COL_TIME,System.currentTimeMillis())
        }
        val rs = db.insert(Todo.TABLE,null,values)

        Toast.makeText(this,if(rs < 0) "保存失败" else "保存成功" , Toast.LENGTH_LONG).show()

    }
}