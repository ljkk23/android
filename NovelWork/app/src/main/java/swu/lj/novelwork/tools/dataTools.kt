package swu.lj.novelwork.tools

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import androidx.annotation.Nullable
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.json.JSONArray
import org.json.JSONObject
import swu.lj.novelwork.R
import swu.lj.novelwork.appdatabase.AppDatabase.Companion.getDatabase
import swu.lj.novelwork.dao.*
import swu.lj.novelwork.entity.BookShell
import swu.lj.novelwork.testDB
import java.util.*


class dataTools (context: Context){

    val db= getDatabase(context)
    val bookShellDao=db.bookShellDao()
    fun insertBookShell(bookShell: BookShell){
        bookShellDao.insert(bookShell)
        //val Items:List<BookShell> =bookShellDao.getAll()
    }
    fun getBookShell():JSONArray{

        var allbookShell:List<BookShell> = bookShellDao.getAll()
        val myjsonArray=JSONArray()

        allbookShell.forEach {item->
            val json=JSONObject()
            json.put("bookTitle", item.bookTitle)
            json.put("image", R.drawable.bookcover)
            json.put("introduction",item.introduction)
            json.put("bookAuthor",item.bookAuthor)
            json.put("readChapter",3)
            myjsonArray.put(json)
        }
        Log.e(TAG, "getBookShell:List<BookShell> $allbookShell")

        return myjsonArray
    }

    fun deleteBookShell(bookShell: BookShell){

        return bookShellDao.delete(bookShell)
    }

    fun IsBookShell(bookTitle:String): Boolean{

        return !Objects.isNull(bookShellDao.findByName(bookTitle))
    }



    fun getAdviceCard():JSONArray{
        Log.e(TAG, "getAdviceCard: $", )
        val myjsonArray=JSONArray()
        val json=JSONObject()
        json.put("bookTitle", "item.bookTitle")
        json.put("image", R.drawable.bookcover)
        json.put("introduction","item.introduction")
        json.put("coveUrl","item.coveUrl")
        json.put("bookAuthor","item.bookAuthor")
        json.put("readChapter",3)

        myjsonArray.put(json)
        return myjsonArray

    }
}