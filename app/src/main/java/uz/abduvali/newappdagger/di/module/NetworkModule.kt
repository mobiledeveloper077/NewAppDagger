package uz.abduvali.newappdagger.di.module

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.abduvali.newappdagger.networking.ApiService
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideBaseUrl(): String = "https://newsapi.org/"

    @Provides
    @Singleton
    fun provideGsonConverter(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideRetrofit(
        baseUrl: String,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(gsonConverterFactory)
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)
}