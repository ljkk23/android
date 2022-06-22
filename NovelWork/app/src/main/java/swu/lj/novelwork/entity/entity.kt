package swu.lj.novelwork.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Book_shell")
data class BookShell(

    @PrimaryKey val bookTitle: String,

    @ColumnInfo val image: Int,
    @ColumnInfo val introduction: String,
    @ColumnInfo(name = "book_author") val bookAuthor: String,
    @ColumnInfo(name = "read_chapter") val readChapter: Int?=0,
    @ColumnInfo val url: String,
)



