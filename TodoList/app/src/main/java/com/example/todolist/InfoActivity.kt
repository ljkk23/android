package com.example.todolist

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.sqrt


class InfoActivity : AppCompatActivity() {

    val TAG = "@@INFO"
    lateinit var helper:DBHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        val i = intent
        val data = i.getStringExtra("id")
        data?.let { readInDb(it) }
        var updateInfo=findViewById<Button>(R.id.update_info)
        updateInfo.setOnClickListener{
            data?.let { it1 -> updateInfo(it1) }
            startActivity(Intent(this,MainActivity::class.java))

        }


    }


    private fun updateInfo(data:String){
        helper = DBHelper(this,"todo.db",1)
        var db=helper.writableDatabase
        var content=findViewById<EditText>(R.id.info).text.toString()
//        var sql="update Todo set content="+content+"where id="+data
//        db.execSQL(sql)
//        Log.i(TAG,sql)
        Log.i(TAG,content)
        val cv = ContentValues()
        cv.put("content",content)
        db.update("Todo",cv,"id=?", arrayOf(data))
    }


    @SuppressLint("Range")
    private fun readInDb(data:String){
        helper = DBHelper(this,"todo.db",1)
        var db=helper.readableDatabase
        val cursor = db.query(
            "Todo",
            arrayOf("content"),
            "id=?",
            arrayOf(data),
            null,
            null,
            null
        )

        while (cursor.moveToNext()) {
            val content = cursor.getString(cursor.getColumnIndex("content"))
            var contentView=findViewById<EditText>(R.id.info)
            contentView.setText(content)
        }

    }
}