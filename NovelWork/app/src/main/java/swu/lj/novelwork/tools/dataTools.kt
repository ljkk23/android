package swu.lj.novelwork.tools

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.json.JSONArray
import org.json.JSONObject
import swu.lj.novelwork.R
import swu.lj.novelwork.appdatabase.AppDatabase.Companion.getDatabase
import swu.lj.novelwork.dao.*
import swu.lj.novelwork.entity.BookShell


class dataTools (context: Context){

    val db= getDatabase(context)
    val bookShellDao=db.bookShellDao()
    fun insertBookShell(bookShell: BookShell){
        bookShellDao.insert(bookShell)
        //val Items:List<BookShell> =bookShellDao.getAll()
    }
    fun getBookShell():List<BookShell>{

        return bookShellDao.getAll()
    }
    fun getAdviceCard():JSONArray{


        Log.e(TAG, "getAdviceCard: $", )
        val myjsonArray=JSONArray()
        val bookShell:BookShell =BookShell("booktltir","dsfjsaf","dfasf",4)

            val json=JSONObject()
            json.put("image", R.drawable.bookcover)
            json.put("title","恶魔地狱")
            json.put("introduction","ssdsadass搜索大师时代撒旦ssss jsd fdfs ad萨顿峰 士大夫时代萨顿峰时代方法啊缩放撒发生的asdf asd fasd fsadfaf asd ff f dsdfsdafsd")
            myjsonArray.put(i,json)

        return myjsonArray

    }
}