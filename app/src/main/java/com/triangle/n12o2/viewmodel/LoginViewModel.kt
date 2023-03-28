package com.triangle.n12o2.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.triangle.n12o2.common.ApiService
import kotlinx.coroutines.launch

/*
Описание: ViewModel для авторизации пользователя
Дата создания: 28.03.2023 09:34
Автор: Хасанов Альберт
*/
class LoginViewModel: ViewModel() {
    val message = MutableLiveData<String>()
    val token = MutableLiveData<String>()
    val isSuccess = MutableLiveData<Boolean>()

    /*
    Описание: Метод для отправки кода на email
    Дата создания: 28.03.2023 09:39
    Автор: Хасанов Альберт
    */
    fun sendCode(email: String) {
        message.value = null
        isSuccess.value = null

        val apiService = ApiService.getInstance()

        viewModelScope.launch {
            try {
                val json = apiService.sendCode(email)

                if (json.code() == 200) {
                    isSuccess.value = true
                } else {
                    message.value = json.body()?.get("errors").toString()
                }
            } catch (e: Exception) {
                message.value = e.message
            }
        }
    }
    /*
    Описание: Метод для авторизации пользователя
    Дата создания: 28.03.2023 09:39
    Автор: Хасанов Альберт
    */
    fun logIn(email: String, code: String) {
        message.value = null
        token.value = null

        val apiService = ApiService.getInstance()

        viewModelScope.launch {
            try {
                val json = apiService.signIn(email, code)

                if (json.code() == 200) {
                    token.value = json.body()?.get("token").toString()
                } else {
                    message.value = json.body()?.get("errors").toString()
                }
            } catch (e: Exception) {
                message.value = e.message
            }
        }
    }
}