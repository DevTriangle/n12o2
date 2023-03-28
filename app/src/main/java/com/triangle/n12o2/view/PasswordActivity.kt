package com.triangle.n12o2.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.triangle.n12o2.ui.theme.N12o2Theme

/*
Описание: Активити экрана создания пароля
Дата создания: 28.03.2023 08:36
Автор: Хасанов Альберт
*/
class PasswordActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            N12o2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ScreenContent()
                }
            }
        }
    }

    /*
    Описание: Содержание экрана создания пароля
    Дата создания: 28.03.2023 08:37
    Автор: Хасанов Альберт
    */
    @Composable
    fun ScreenContent() {

    }
}