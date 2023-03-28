package com.triangle.n12o2.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.triangle.n12o2.R
import com.triangle.n12o2.ui.theme.N12o2Theme
import kotlinx.coroutines.launch

/*
Описание: Активити Splash-экрана
Дата создания: 28.03.2023 08:33
Автор: Хасанов Альберт
*/
@SuppressLint("CustomSplashScreen")
class SplashActivity : ComponentActivity() {
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val sharedPreferences = this.getSharedPreferences("shared", MODE_PRIVATE)
            val scope = rememberCoroutineScope()
            val mContext = LocalContext.current

            val isFirstLaunch = sharedPreferences.getBoolean("isFirstLaunch", true)
            val token = sharedPreferences.getString("token", "")

            N12o2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ScreenContent()

                    scope.launch {
                        if (isFirstLaunch) {
                            val intent = Intent(mContext, OnboardActivity::class.java)
                            startActivity(intent)
                        } else {
                            if (token != "") {
                                val intent = Intent(mContext, PasswordActivity::class.java)
                                startActivity(intent)
                            } else {
                                val intent = Intent(mContext, LoginActivity::class.java)
                                startActivity(intent)
                            }
                        }
                    }
                }
            }
        }
    }

    /*
    Описание: Содержание Splash-экрана
    Дата создания: 28.03.2023 08:35
    Автор: Хасанов Альберт
    */
    @Composable
    fun ScreenContent() {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.splash_bg),
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
            Image(
                painter = painterResource(id = R.drawable.splash_logo),
                contentDescription = "",
                modifier = Modifier.align(Alignment.Center),
            )
        }
    }
}