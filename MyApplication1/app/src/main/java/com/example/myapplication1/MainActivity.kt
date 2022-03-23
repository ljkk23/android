package com.example.myapplication1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import java.util.*

class MainActivity : AppCompatActivity() {
    val TAG = "@mainActivity"

    private var username: String? = null;
    private var password: String? = null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val iptUsername = findViewById<EditText>(R.id.ipt_username)
        val iptPassword = findViewById<EditText>(R.id.ipt_password)

        val usernameWatcher = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Log.i(TAG, "beforchange:$p0")
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Log.i(TAG, "onTextChanged:$p0")
            }

            override fun afterTextChanged(p0: Editable?) {
                Log.i(TAG, "afterTextChanged:$p0")
                username = p0?.toString();
            }
        }
        val passwordWatcher = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Log.i(TAG, "beforechange:$p0")
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Log.i(TAG, "onTextChanged:$p0")
            }

            override fun afterTextChanged(p0: Editable?) {
                Log.i(TAG, "afterTextChanged:$p0")
                password = p0?.toString()
            }
        }

        iptUsername.addTextChangedListener(usernameWatcher)
        iptPassword.addTextChangedListener(passwordWatcher)
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart")
    }

    fun onClick(view: View) {
        Log.d(TAG, "username = $username")
        Log.d(TAG, "password = $password")

        AlertDialog.Builder(this)
            .setTitle("请确认")
            .setMessage("确定登陆吗")
            .setNegativeButton("取消") { d, w ->
                Toast.makeText(this, "已取消", Toast.LENGTH_LONG).show()
            }
            .setPositiveButton("确定") { d, w -> toLogin() }
            .show()
    }

    private fun toLogin() {
        val process = findViewById<ProgressBar>(R.id.progress)
        process.visibility = View.VISIBLE
        process.progress = 0
        val timer = Timer()
        val context = this
        val task = object : TimerTask() {
            override fun run() {
                Log.d(TAG, "progress=${process.progress}")
                process.progress = process.progress + 1
                if (process.progress == 10) {
                    timer.cancel()
                    Handler(Looper.getMainLooper()).post {
                        process.visibility = View.GONE
                        startActivity(Intent(context, ListActivity::class.java))
                    }
                }

            }
        }
        timer.schedule(task,0,1000)
    }
}