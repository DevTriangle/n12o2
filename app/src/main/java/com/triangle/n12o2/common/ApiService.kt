package com.triangle.n12o2.common

import android.provider.ContactsContract.CommonDataKinds.Email
import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

/*
Описание: Интерфейс для обращения к API
Дата создания: 28.03.2023 09:29
Автор: Хасанов Альберт
*/
interface ApiService {
    @POST("sendCode")
    @Headers(
        "accept: application/json"
    )
    suspend fun sendCode(@Header("email") email: String): Response<JsonObject>

    @POST("signin")
    @Headers(
        "accept: application/json"
    )
    suspend fun signIn(@Header("email") email: String, @Header("code") code: String): Response<JsonObject>

    companion object {
        var apiService: ApiService? = null

        /*
        Описание: Инициализация Retrofit
        Дата создания: 28.03.2023 09:29
        Автор: Хасанов Альберт
        */
        fun getInstance(): ApiService {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl("https://medic.madskill.ru/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ApiService::class.java)
            }

            return apiService!!
        }
    }
}