package uz.abduvali.newappdagger.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import uz.abduvali.newappdagger.database.entity.BookmarkEntity

@Dao
interface BookmarkDao {

    @Insert(onConflict = REPLACE)
    fun addBookmark(bookmarkEntity: BookmarkEntity)

    @Delete
    fun deleteBookmark(bookmarkEntity: BookmarkEntity)

    @Query("select * from bookmarkentity")
    fun getBookmarks(): List<BookmarkEntity>
}