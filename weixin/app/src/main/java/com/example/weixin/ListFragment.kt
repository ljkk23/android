package com.example.weixin
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

val ARG_CATEGORY = "category"

class NewsListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var category: String? = null
    val items= arrayListOf<item>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            category = it.getString(ARG_CATEGORY)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData(category)

        view.findViewById<RecyclerView>(R.id.recycler).adapter=itemAdapter(items)
        view.findViewById<RecyclerView>(R.id.recycler).layoutManager=LinearLayoutManager(context)
    }


    private fun initData(type:String?){
        if (type.equals("聊天记录")) {
            for (i in 0..5) {
                items.add(item().apply {
                    name = "jordan"
                    description =
                        "你什么时候来nba"
                    avatar = R.mipmap.jordan2
                })
                items.add(item().apply {
                    name = "詹姆斯"
                    description =
                        "我到中国了，你在哪里？"
                    avatar = R.mipmap.james
                })
                items.add(item().apply {
                    name = "拼多多"
                    description =
                        "你有未使用的优惠卷"
                    avatar = R.mipmap.pin
                })
                items.add(item().apply {
                    name = "淘宝"
                    description =
                        "你有未使用的优惠卷"
                    avatar = R.mipmap.taobao
                })
                items.add(item().apply {
                    name = "拼多多"
                    description =
                        "你有未使用的优惠卷"
                    avatar = R.mipmap.kobe
                })


                items.add(item().apply {
                    name = "老师"
                    description =
                        "你这学期获得了国家奖学金"
                    avatar = R.mipmap.laoshi
                })
                items.add(item().apply {
                    name = "家人姐姐"
                    description =
                        "什么时候回来吃饭"
                    avatar = R.mipmap.jiejie
                })
                items.add(item().apply {
                    name = "拼多多"
                    description =
                        "你有未使用的优惠卷"
                    avatar = R.mipmap.kobe
                })
            }
        }else if (type.equals("通讯录")){
            for (i in 0..5) {
                items.add(item().apply {
                    name = "张三"
                    description ="个性签名:积极向上"
                    avatar = R.mipmap.zhangsan
                })
                items.add(item().apply {
                    name = "李四"
                    description ="个性签名:努力未来"
                    avatar = R.mipmap.lisi
                })
                items.add(item().apply {
                    name = "王五"
                    description ="个性签名:不懈努力"
                    avatar = R.mipmap.wang
                })
                items.add(item().apply {
                    name = "kobe"
                    description =
                        "是美国职业篮球运动员，目前效力于NBA洛杉矶湖人。詹姆斯被公认是NBA历史上最伟大球员之一，并经常在史上最伟大篮球员的争论中被拿来与迈克尔·乔丹相互比较[1]。詹姆斯曾效力过克利夫兰骑士和迈阿密热火，是NBA历史上第一位率领三支不同球队夺冠，且都拿下NBA总决赛最有价值球员的球员[2]。詹姆斯曾挺进NBA总决赛十次，其中从2011年到2018年连续八年率领球队挺进总决"
                    avatar = R.mipmap.kobe
                })
                items.add(item().apply {
                    name = "kobe"
                    description ="个性签名:积极向上"
                    avatar = R.mipmap.kobe
                })
                items.add(item().apply {
                    name = "kobe"
                    description ="个性签名:积极向上"
                    avatar = R.mipmap.kobe
                })
                items.add(item().apply {
                    name = "kobe"
                    description ="个性签名:积极向上"
                    avatar = R.mipmap.kobe
                })
                items.add(item().apply {
                    name = "kobe"
                    description ="个性签名:积极向上"
                    avatar = R.mipmap.kobe
                })
                items.add(item().apply {
                    name = "拼多多"
                    description ="个性签名:积极向上"
                    avatar = R.mipmap.pin
                })
            }
        }else if (type.equals("朋友圈")){
            for (i in 0..5) {
                items.add(item().apply {
                    name = "张三"
                    description ="个性签名:积极向上"
                    avatar = R.mipmap.zhangsan
                })
                items.add(item().apply {
                    name = "李四"
                    description ="个性签名:努力未来"
                    avatar = R.mipmap.lisi
                })
                items.add(item().apply {
                    name = "王五"
                    description ="个性签名:不懈努力"
                    avatar = R.mipmap.wang
                })
                items.add(item().apply {
                    name = "kobe"
                    description =
                        "是美国职业篮球运动员，目前效力于NBA洛杉矶湖人。詹姆斯被公认是NBA历史上最伟大球员之一，并经常在史上最伟大篮球员的争论中被拿来与迈克尔·乔丹相互比较[1]。詹姆斯曾效力过克利夫兰骑士和迈阿密热火，是NBA历史上第一位率领三支不同球队夺冠，且都拿下NBA总决赛最有价值球员的球员[2]。詹姆斯曾挺进NBA总决赛十次，其中从2011年到2018年连续八年率领球队挺进总决"
                    avatar = R.mipmap.kobe
                })
                items.add(item().apply {
                    name = "kobe"
                    description ="个性签名:积极向上"
                    avatar = R.mipmap.kobe
                })
                items.add(item().apply {
                    name = "kobe"
                    description ="个性签名:积极向上"
                    avatar = R.mipmap.kobe
                })
                items.add(item().apply {
                    name = "kobe"
                    description ="个性签名:积极向上"
                    avatar = R.mipmap.kobe
                })
                items.add(item().apply {
                    name = "kobe"
                    description ="个性签名:积极向上"
                    avatar = R.mipmap.kobe
                })
                items.add(item().apply {
                    name = "拼多多"
                    description ="个性签名:积极向上"
                    avatar = R.mipmap.pin
                })
            }
        }else if (type.equals("个人服务")){
            for (i in 0..5) {
                items.add(item().apply {
                    name = "张三"
                    description ="个性签名:积极向上"
                    avatar = R.mipmap.zhangsan
                })
                items.add(item().apply {
                    name = "李四"
                    description ="个性签名:努力未来"
                    avatar = R.mipmap.lisi
                })
                items.add(item().apply {
                    name = "王五"
                    description ="个性签名:不懈努力"
                    avatar = R.mipmap.wang
                })
                items.add(item().apply {
                    name = "kobe"
                    description =
                        "是美国职业篮球运动员，目前效力于NBA洛杉矶湖人。詹姆斯被公认是NBA历史上最伟大球员之一，并经常在史上最伟大篮球员的争论中被拿来与迈克尔·乔丹相互比较[1]。詹姆斯曾效力过克利夫兰骑士和迈阿密热火，是NBA历史上第一位率领三支不同球队夺冠，且都拿下NBA总决赛最有价值球员的球员[2]。詹姆斯曾挺进NBA总决赛十次，其中从2011年到2018年连续八年率领球队挺进总决"
                    avatar = R.mipmap.kobe
                })
                items.add(item().apply {
                    name = "kobe"
                    description ="个性签名:积极向上"
                    avatar = R.mipmap.kobe
                })
                items.add(item().apply {
                    name = "kobe"
                    description ="个性签名:积极向上"
                    avatar = R.mipmap.kobe
                })
                items.add(item().apply {
                    name = "kobe"
                    description ="个性签名:积极向上"
                    avatar = R.mipmap.kobe
                })
                items.add(item().apply {
                    name = "kobe"
                    description ="个性签名:积极向上"
                    avatar = R.mipmap.kobe
                })
                items.add(item().apply {
                    name = "拼多多"
                    description ="个性签名:积极向上"
                    avatar = R.mipmap.pin
                })
            }
        }else if (type.equals("收藏")){
            for (i in 0..5) {
                items.add(item().apply {
                    name = "张三"
                    description ="个性签名:积极向上"
                    avatar = R.mipmap.zhangsan
                })
                items.add(item().apply {
                    name = "李四"
                    description ="个性签名:努力未来"
                    avatar = R.mipmap.lisi
                })
                items.add(item().apply {
                    name = "王五"
                    description ="个性签名:不懈努力"
                    avatar = R.mipmap.wang
                })
                items.add(item().apply {
                    name = "kobe"
                    description =
                        "是美国职业篮球运动员，目前效力于NBA洛杉矶湖人。詹姆斯被公认是NBA历史上最伟大球员之一，并经常在史上最伟大篮球员的争论中被拿来与迈克尔·乔丹相互比较[1]。詹姆斯曾效力过克利夫兰骑士和迈阿密热火，是NBA历史上第一位率领三支不同球队夺冠，且都拿下NBA总决赛最有价值球员的球员[2]。詹姆斯曾挺进NBA总决赛十次，其中从2011年到2018年连续八年率领球队挺进总决"
                    avatar = R.mipmap.kobe
                })
                items.add(item().apply {
                    name = "kobe"
                    description ="个性签名:积极向上"
                    avatar = R.mipmap.kobe
                })
                items.add(item().apply {
                    name = "kobe"
                    description ="个性签名:积极向上"
                    avatar = R.mipmap.kobe
                })
                items.add(item().apply {
                    name = "kobe"
                    description ="个性签名:积极向上"
                    avatar = R.mipmap.kobe
                })
                items.add(item().apply {
                    name = "kobe"
                    description ="个性签名:积极向上"
                    avatar = R.mipmap.kobe
                })
                items.add(item().apply {
                    name = "拼多多"
                    description ="个性签名:积极向上"
                    avatar = R.mipmap.pin
                })
            }
        }
    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param  Parameter 1.
         * @return A new instance of fragment NewsListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(category :String) =
            NewsListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_CATEGORY, category)
                }
            }
    }
}