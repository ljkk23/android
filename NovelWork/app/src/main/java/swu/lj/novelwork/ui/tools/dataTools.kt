package swu.lj.novelwork.ui.tools

import org.json.JSONArray
import org.json.JSONObject
import swu.lj.novelwork.R

class dataTools {

    fun getAdviceCard():JSONArray{
        val myjsonArray=JSONArray()
        for (i in 1..3){
            val json=JSONObject()
            json.put("image", R.drawable.bookcover)
            json.put("title","恶魔地狱")
            json.put("introduction","季什诺夫i史fsdf sd蒂夫时代姐夫f jsdfdfsadfdsdfs dafsd")
            myjsonArray.put(i,json)
        }
        return myjsonArray

    }
}