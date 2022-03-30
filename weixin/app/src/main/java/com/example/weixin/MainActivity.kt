package com.example.weixin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    val category = arrayListOf<String>()

    lateinit var adapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        initData()
    }

    private fun initData() {

        category.add("聊天记录")
        category.add("通讯录")
        category.add("朋友圈")
        category.add("个人服务")
        category.add("收藏")
        category.add("钱包")
        category.add("健康码")
        category.add("八卦3")
        category.add("八卦4")
        category.add("八卦5")

        adapter.setData(category)

    }

    private fun initView() {
        val tabLayout = findViewById<TabLayout>(R.id.tab_layout)
        val viewpager = findViewById<ViewPager2>(R.id.viewpager)

        adapter = NewsAdapter(this)
        viewpager.adapter = adapter

        TabLayoutMediator(tabLayout,viewpager){ tab,pos->
            val item = adapter.getItem(pos)

            tab.text = item
        }.attach()
    }

}