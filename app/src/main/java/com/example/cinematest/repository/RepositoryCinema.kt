package com.example.cinematest.repository

import com.example.cinematest.entity.ModelCinema
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


class RepositoryCinema {

    private val BASE_URL = "https://s3-eu-west-1.amazonaws.com/sequeniatesttask/"

    suspend fun getFilm(): ModelCinema {
       return apiInterfaceCinema.films()

    }

    suspend fun getResponse(){
        withContext(Dispatchers.IO) {
            val response = apiServiceResponse.response()

            if (response.isSuccessful) {
                println("Успешный ответ: ${response.code()}") // Например, 200
                val data = response // Обрабатываем тело ответа
                println(data)
            } else {
                println("Ошибка: ${response.code()}") // Например, 404 или 500
            }
        }
    }

    private val logInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

     val httpClientBuilder = OkHttpClient.Builder()
        .addInterceptor(logInterceptor)
         .build()



    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit
        .Builder()
        .client(
            OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().also {
                it.level = HttpLoggingInterceptor.Level.BODY
            }).build()
        )
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
       // .create(ApiInterfaceCinema::class.java)


    val apiServiceResponse = retrofit.create (ApiInterfaceCinemaResponse::class.java)
    val apiInterfaceCinema =retrofit.create(ApiInterfaceCinema::class.java)


    interface ApiInterfaceCinema {
        @GET("films.json")
        suspend fun films(): ModelCinema
    }

    interface ApiInterfaceCinemaResponse {
        @GET("films.json")
        suspend fun response(): Response<ModelCinema>
    }

}