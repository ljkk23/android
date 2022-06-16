package swu.lj.novelwork.ui.tools

import org.json.JSONArray
import org.json.JSONObject
import swu.lj.novelwork.R

class dataTools {

    fun getAdviceCard():JSONArray{
        val myjsonArray=JSONArray()
        for (i in 1..7){
            val json=JSONObject()
            json.put("image", R.drawable.bookcover)
            json.put("title","恶魔地狱")
            json.put("introduction","ssdsadass搜索大师时代撒旦ssss jsd fdfs ad萨顿峰 士大夫时代萨顿峰时代方法啊缩放撒发生的asdf asd fasd fsadfaf asd ff f dsdfsdafsd")
            myjsonArray.put(i,json)
        }
        return myjsonArray

    }
}