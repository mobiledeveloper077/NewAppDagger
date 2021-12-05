package uz.abduvali.newappdagger.repository

import kotlinx.coroutines.flow.flow
import uz.abduvali.newappdagger.database.dao.NewDao
import uz.abduvali.newappdagger.database.entity.NewEntity
import uz.abduvali.newappdagger.networking.ApiService
import javax.inject.Inject

class NewDataRepository @Inject constructor(
    private val apiService: ApiService,
    private val newDao: NewDao
) {

    suspend fun getNews(query: String, language: String) =
        flow { emit(apiService.getNews(query, language)) }

    suspend fun addNews(list: List<NewEntity>) = newDao.addNews(list)

    suspend fun addNew(list: NewEntity) = newDao.addNew(list)

    suspend fun getDbNews() = flow { emit(newDao.getNews()) }
}