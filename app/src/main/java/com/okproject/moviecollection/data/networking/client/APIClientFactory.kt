package com.okproject.moviecollection.data.networking.client

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.okproject.moviecollection.data.networking.JSON_CONTENT_TYPE
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

object APIClientFactory {
    private const val BASE_MOVIEDB_URL = "https://api.themoviedb.org/3/"

    private val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        .apply {
            redactHeader("Authorization")
        }
    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(HeaderInterceptor())
        .build()

    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }
    fun createClient() =
        Retrofit.Builder()
            .baseUrl(BASE_MOVIEDB_URL)
            .addConverterFactory(json.asConverterFactory(JSON_CONTENT_TYPE.toMediaType()))
            .client(okHttpClient)
            .build()
}