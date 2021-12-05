package uz.abduvali.newappdagger.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import uz.abduvali.newappdagger.database.entity.TopicEntity

@Dao
interface TopicDao {

    @Insert(onConflict = REPLACE)
    fun addTopic(topicEntity: TopicEntity)

    @Update
    fun updateTopic(topicEntity: TopicEntity)

    @Query("select * from topicentity")
    fun getTopics(): List<TopicEntity>
}