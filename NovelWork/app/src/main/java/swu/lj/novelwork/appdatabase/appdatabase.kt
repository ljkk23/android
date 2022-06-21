package swu.lj.novelwork.appdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import swu.lj.novelwork.dao.BookShellDao
import swu.lj.novelwork.entity.BookShell

@Database(entities = [BookShell::class], version = 2,exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookShellDao(): BookShellDao
    //单例模式
    companion object {
        private var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE =
                        Room.databaseBuilder(
                            context.applicationContext,
                            AppDatabase::class.java,
                            "contact_database"
                        )
                            .allowMainThreadQueries()
                            .build()
                }
            }
            return INSTANCE as AppDatabase
        }
    }
}