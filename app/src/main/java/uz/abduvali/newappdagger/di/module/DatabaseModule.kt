package uz.abduvali.newappdagger.di.module

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import uz.abduvali.newappdagger.database.AppDatabase
import uz.abduvali.newappdagger.database.dao.BookmarkDao
import uz.abduvali.newappdagger.database.dao.NewDao
import uz.abduvali.newappdagger.database.dao.TopicDao
import javax.inject.Singleton

@Module
class DatabaseModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideContext(): Context = context

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "my_db")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun provideNewDao(appDatabase: AppDatabase): NewDao = appDatabase.newDao()

    @Provides
    @Singleton
    fun provideBookmarkDao(appDatabase: AppDatabase): BookmarkDao = appDatabase.bookmarkDao()

    @Provides
    @Singleton
    fun provideTopicDao(appDatabase: AppDatabase): TopicDao = appDatabase.topicDao()
}