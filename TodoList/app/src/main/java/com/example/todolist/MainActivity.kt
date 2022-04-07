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
                        cursor.getString(cursor.getColumnIndex(Todo.COL_TIME)),
                        cursor.getString(cursor.getColumnIndex(Todo.COL_DONE))
                    ).apply {
                        id = cursor.getInt(cursor.getColumnIndex(Todo.COL_ID))
                    }
                )
                Log.i(TAG,cursor.getString(cursor.getColumnIndex(Todo.COL_DONE))
                )
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
                date=view.findViewById(R.id.date)
                content = view.findViewById(R.id.content)
                btnUpdate = view.findViewById(R.id.btn_update)
                btnDelete = view.findViewById(R.id.btn_delete)
                btnDone=view.findViewById<Button>(R.id.done)
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


        fun replaceItem(id: Int?, item: Todo) {
            val idx = findIdx(id)

            if(idx >= 0) {
                data.set(idx, item)
                notifyItemChanged(idx)
            }

        }

        private fun findIdx(id: Int?): Int {
            var idx = -1
            data.forEachIndexed { index, todo ->
                if(todo.id == id){
                    idx = index
                }
            }
            return idx
        }

        fun itemDeleted(id: Int?) {
            val idx = findIdx(id)
            if(idx >= 0){
                data.removeAt(idx)
                notifyItemRemoved(idx)
            }
        }

    }

    inner class TodoViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var id: TextView? = null
        var content: TextView? = null
        var date:TextView?=null

        var btnDelete: TextView? = null
        var btnUpdate: TextView? = null
        var btnDone:TextView?=null

        fun render(todo: Todo) {
//            helper = DBHelper(this@MainActivity,"todo.db",1)
            val db = helper.writableDatabase

            id?.text = todo.id.toString()
            content?.text = todo.content
            date?.text= todo.createTime
            Log.i(TAG,todo.createTime.toString())
            btnDone?.text = todo.done


            btnDelete?.setOnClickListener {
                Log.i(TAG,"delete from "+Todo.TABLE+" where "+Todo.COL_ID+"="+todo.id )
                db.execSQL("delete from "+Todo.TABLE+" where "+Todo.COL_ID+"="+todo.id )
                adapter.itemDeleted(todo.id)
            //startActivity(Intent(this@MainActivity,this@MainActivity::class.java))
            }
            btnUpdate?.setOnClickListener {
                var intent=Intent()
                intent.setClass(this@MainActivity,InfoActivity::class.java)
                intent.putExtra("id",todo.id.toString())
                startActivity(intent)
            }
            btnDone?.setOnClickListener{
                helper = DBHelper(this@MainActivity,"todo.db",1)
                var db=helper.writableDatabase
                val cv = ContentValues()
                cv.put("done","已完成")
                db.update("Todo",cv,"id=?", arrayOf(todo.id.toString()))
                //btnDone?.text = "已完成"
                val item = Todo(todo.content,todo.createTime,"已完成").apply { id=todo.id }
                adapter.replaceItem(todo.id,item)
            }


            }
        }


    }

