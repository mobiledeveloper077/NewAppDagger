package uz.abduvali.newappdagger.networking

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import uz.abduvali.newappdagger.models.NewData

interface ApiService {

    @GET("v2/everything?apiKey=c024a77545414618b9d7bda726bb32de")
    suspend fun getNews(
        @Query("q") query: String,
        @Query("language") language: String
    ): Response<NewData>
}