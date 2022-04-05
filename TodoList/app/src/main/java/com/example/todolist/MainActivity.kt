package com.example.todolist

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {


    val TAG = "@@MAIN"
    val KEY_ITEM = "item"

    lateinit var helper:DBHelper
    lateinit var adapter: TodoAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        helper = DBHelper(this@MainActivity,"todo.db",1)


        init()
        setListener();
    }

    private fun init(){

        adapter = TodoAdapter()

        val recycler = findViewById<RecyclerView>(R.id.recycler)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter

//        readFromSp()
//        readInFile()
        readInDb()


//        findViewById<Button>(R.id.btn_save).setOnClickListener {
//            saveInput()
//        }
    }

    private fun setListener(){
        val createbtn=findViewById<Button>(R.id.create)
        createbtn.setOnClickListener {
            startActivity(Intent(this, CreateActivity::class.java))
        }
    }



    @SuppressLint("Range")
    private fun readInDb() {
        val db = helper.readableDatabase
        val cursor = db.query(Todo.TABLE,null,null,null,null,null,
            "${Todo.COL_TIME} desc ")
        val arr = arrayListOf<Todo>()
        if(cursor.moveToFirst()){
            do{
                arr.add(
                    Todo(
                        cursor.getString(cursor.getColumnIndex(Todo.COL_CONTENT)),
                        cursor.getLong(cursor.getColumnIndex(Todo.COL_TIME)),
                    ).apply {
                        id = cursor.getInt(cursor.getColumnIndex(Todo.COL_ID))
                    }
                )
                Log.i(TAG,"arr[0].content");
            }while (cursor.moveToNext())
        }

        adapter.setData(arr)
        cursor.close()
    }



    inner class TodoAdapter : RecyclerView.Adapter<TodoViewHolder>() {

        val data = arrayListOf<Todo>()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
            return TodoViewHolder(view).apply {
                id = view.findViewById(R.id.id)
                content = view.findViewById(R.id.content)
                btnUpdate = view.findViewById(R.id.btn_update)
                btnDelete = view.findViewById(R.id.btn_delete)
            }
        }

        override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
            holder.render(data[position])
        }

        override fun getItemCount(): Int {
            return data.size
        }

        fun setData(arr: ArrayList<Todo>) {
            data.addAll(arr)

            notifyDataSetChanged()
        }

    }

    inner class TodoViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var id: TextView? = null
        var content: TextView? = null

        var btnDelete: TextView? = null
        var btnUpdate: TextView? = null

        fun render(todo: Todo) {
            helper = DBHelper(this@MainActivity,"todo.db",1)
            val db = helper.writableDatabase

            id?.text = todo.id.toString()
            content?.text = todo.content

            btnDelete?.setOnClickListener {
                Log.i(TAG,"delete from "+Todo.TABLE+" where "+Todo.COL_ID+"="+todo.id )
                db.execSQL("delete from "+Todo.TABLE+" where "+Todo.COL_ID+"="+todo.id )
            }
//            btnDelete?.setOnClickListener {
//
//            }


        }


    }

}