package swu.lj.novelwork.tools

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import okhttp3.*
import okio.IOException
import org.json.JSONArray
import org.json.JSONObject
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import swu.lj.novelwork.R

class NetWorktools {
    val client = OkHttpClient()
    //联网获推荐的书籍
    fun getAdvBooks(): JSONArray {
        val url = "https://www.exiaoshuo.com"
        val lis: Elements? = getDocument(url).select("div[class=item]")
        val msgs = JSONArray()
        if (lis != null) {
            for (index in lis.indices) {
                val element = lis[index]
                val msg1 = JSONObject()
                msg1.put("bookTitle", element.select("div[class=p10] > dl > dt >a").text())
                msg1.put("bookAuthor", element.select("div[class=p10] > dl > dt > span").text())
                msg1.put("introduction", element.select("div[class=p10] > dl > dd").text())
                msg1.put("image", R.drawable.home)
                msg1.put("readChapter", 0)
                msg1.put("url", url+element.select("div[class=p10] > dl > dt > a").attr("href"))
                msgs.put(msg1)
            }
        }
        Log.e(TAG, "getAdvBooks: $msgs")
        return msgs
    }


    //联网获分类的书籍
    fun getsortBooks(sort:String): JSONArray {
        val url = "https://www.exiaoshuo.com/$sort/"
        val lis: Elements? = getDocument(url).select("div[class=item]")
        val msgs = JSONArray()
        if (lis != null) {
            for (index in lis.indices) {
                val element = lis[index]
                val msg1 = JSONObject()
                msg1.put("bookTitle", element.select("div[class=p10] > dl > dt >span").text())
                msg1.put("bookAuthor", element.select("div[class=p10] > dl > dt > a").text())
                msg1.put("introduction", element.select("div[class=p10] > dl > dd").text())
                msg1.put("image", R.drawable.home)
                msg1.put("url", element.select("div[class=p10] > dl > dt > a").attr("src"))
                msg1.put("readChapter", 0)
                msgs.put(msg1)
            }
        }
        return msgs
    }

    //联网获取章目录
    fun getBookChapterAndReadUrl(url:String): JSONArray {
        val url = url
        val lis: Elements? = getDocument(url).select("div[class=listmain] > dl > dd")
        val msg1 = JSONArray()
        if (lis != null) {
            for (i in 0..50) {
                val element1 = lis[i]
                val msg2 = JSONObject()
                msg2.put("title", element1.select("a").text())
                msg2.put("url", "https://www.exiaoshuo.com" + element1.select("a").attr("href"))
                msg1.put(msg2)
            }
        }
        return msg1
    }


    //联网获取小说内容,传入目录列表以及获取章节
    fun getBody(url:String): String {
        val url = url
        val body = getDocument(url)!!.select("div[id=content]").get(0).text()
        Log.e(TAG, "getBody: $body", )
        return body
    }




    fun getDocument(url: String): Document {
        var body1=""
        try {
            val request = Request.Builder().get()
                .url(url)
                .build()
            var response =client.newCall(request).execute()
            body1 =response.body!!.string()
            //Log.d("UPDATE", "OnResponse: " + response.body?.string())
            response.body!!.close()
        }catch (e:Exception) {
            Log.e("UPDATE ERROR:", "", e)
        }
        //解析html

        return Jsoup.parse(body1);
        }
}