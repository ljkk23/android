package com.example.myapplication1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListActivity : AppCompatActivity() {

    val players= arrayListOf<player>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        initData()

        val recycler=findViewById<RecyclerView>(R.id.recycler)
        recycler.layoutManager=LinearLayoutManager(this)
        recycler.adapter=PlayerAdapter(players)

    }
    private fun initData(){
        for (i in 0..5){
            players.add(player().apply {
                name = "jordan"
                description ="迈克尔·杰弗里·乔丹（英语：Michael Jeffrey Jordan，1963年2月17日－），非裔美国人，已退役的前男子职业篮球运动员，现任夏洛特黄蜂董事长及主要股东。乔丹身高6英尺6英寸（1.98米），体重216磅（98公斤），主打得分后卫位置，球衣号码为23号"
                avatar = R.mipmap.jordan2
            })
            players.add(player().apply {
                name = "詹姆斯"
                description ="是美国职业篮球运动员，目前效力于NBA洛杉矶湖人。詹姆斯被公认是NBA历史上最伟大球员之一，并经常在史上最伟大篮球员的争论中被拿来与迈克尔·乔丹相互比较[1]。詹姆斯曾效力过克利夫兰骑士和迈阿密热火，是NBA历史上第一位率领三支不同球队夺冠，且都拿下NBA总决赛最有价值球员的球员[2]。詹姆斯曾挺进NBA总决赛十次，其中从2011年到2018年连续八年率领球队挺进总决"
                avatar = R.mipmap.james
            })
            players.add(player().apply {
                name = "kobe"
                description ="是美国职业篮球运动员，目前效力于NBA洛杉矶湖人。詹姆斯被公认是NBA历史上最伟大球员之一，并经常在史上最伟大篮球员的争论中被拿来与迈克尔·乔丹相互比较[1]。詹姆斯曾效力过克利夫兰骑士和迈阿密热火，是NBA历史上第一位率领三支不同球队夺冠，且都拿下NBA总决赛最有价值球员的球员[2]。詹姆斯曾挺进NBA总决赛十次，其中从2011年到2018年连续八年率领球队挺进总决"
                avatar = R.mipmap.kobe
            })
        }
    }
}