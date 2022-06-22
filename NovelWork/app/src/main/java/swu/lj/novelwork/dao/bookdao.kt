package swu.lj.novelwork.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import swu.lj.novelwork.entity.BookShell

@Dao
interface BookShellDao {
    @Query("SELECT * FROM Book_shell")
    fun getAll(): List<BookShell>

    @Query("SELECT * FROM Book_shell WHERE bookTitle LIKE :bookTitle LIMIT 1")
    fun findByName(bookTitle: String): BookShell

    @Insert
    fun insert(bookShell: BookShell)

    @Delete
    fun delete(bookShell: BookShell)
}