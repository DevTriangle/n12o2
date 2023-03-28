package com.triangle.n12o2.common

import android.content.SharedPreferences

/*
Описание: Класс для сохранения данных на устройстве
Дата создания: 28.03.2023 09:11
Автор: Хасанов Альберт
*/
class DataSaver {
    /*
    Описание: Метод для сохранения информации о прозождении приветственного экрана на устройстве
    Дата создания: 28.03.2023 09:11
    Автор: Хасанов Альберт
    */
    fun saveSecondLaunch(sharedPreferences: SharedPreferences) {
        with(sharedPreferences.edit()) {
            putBoolean("isFirstLaunch", false)
            apply()
        }
    }

    /*
    Описание: Метод для безопасного сохранения токена на устройстве
    Дата создания: 28.03.2023 09:11
    Автор: Хасанов Альберт
    */
    fun saveToken(sharedPreferences: SharedPreferences, token: String) {
        with(sharedPreferences.edit()) {
            putString("token", token)
            apply()
        }
    }
}