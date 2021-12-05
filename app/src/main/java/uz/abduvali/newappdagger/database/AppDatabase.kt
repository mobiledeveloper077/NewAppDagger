package uz.abduvali.newappdagger.database

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.abduvali.newappdagger.database.dao.BookmarkDao
import uz.abduvali.newappdagger.database.dao.NewDao
import uz.abduvali.newappdagger.database.dao.TopicDao
import uz.abduvali.newappdagger.database.entity.BookmarkEntity
import uz.abduvali.newappdagger.database.entity.NewEntity
import uz.abduvali.newappdagger.database.entity.TopicEntity

@Database(
    entities = [NewEntity::class, BookmarkEntity::class, TopicEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun newDao(): NewDao

    abstract fun bookmarkDao(): BookmarkDao

    abstract fun topicDao(): TopicDao
}