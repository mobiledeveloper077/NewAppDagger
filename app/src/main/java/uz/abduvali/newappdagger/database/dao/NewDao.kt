package uz.abduvali.newappdagger.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import uz.abduvali.newappdagger.database.entity.NewEntity

@Dao
interface NewDao {

    @Insert(onConflict = REPLACE)
    suspend fun addNew(newEntity: NewEntity)

    @Insert(onConflict = REPLACE)
    suspend fun addNews(list: List<NewEntity>)

    @Query("select * from newentity")
    suspend fun getNews(): List<NewEntity>
}