package com.triangle.n12o2.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.triangle.n12o2.ui.components.AppTextButton
import com.triangle.n12o2.ui.theme.N12o2Theme
import com.triangle.n12o2.R
import com.triangle.n12o2.common.DataSaver
import com.triangle.n12o2.ui.components.OnboardComponent
import kotlinx.coroutines.flow.collect

/*
Описание: Активити приветственного экрана
Дата создания: 28.03.2023 08:35
Автор: Хасанов Альберт
*/
class OnboardActivity : ComponentActivity() {
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
    Описание: Содержание приветственного экрана
    Дата создания: 28.03.2023 08:35
    Автор: Хасанов Альберт
    */
    @OptIn(ExperimentalPagerApi::class)
    @Composable
    fun ScreenContent() {
        val mContext = LocalContext.current
        val sharedPreferences = this.getSharedPreferences("shared", MODE_PRIVATE)
        val pagerState = rememberPagerState()

        var skipText by rememberSaveable { mutableStateOf("Пропустить") }

        val screenList = listOf(
            mapOf(
                "title" to "Анализы",
                "desc" to "Экспресс сбор и получение проб",
                "image" to painterResource(id = R.drawable.onboard_1)
            ),
            mapOf(
                "title" to "Уведомления",
                "desc" to "Вы быстро узнаете о результатах",
                "image" to painterResource(id = R.drawable.onboard_2)
            ),
            mapOf(
                "title" to "Мониторинг",
                "desc" to "Наши врачи всегда наблюдают за вашими показателями здоровья",
                "image" to painterResource(id = R.drawable.onboard_3)
            ),
        )

        LaunchedEffect(pagerState) {
            snapshotFlow { pagerState.currentPage }.collect() {
                if (it < 2) {
                    skipText = "Пропустить"
                } else {
                    skipText = "Завершить"
                }
            }
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                AppTextButton(
                    modifier = Modifier.padding(start = 30.dp),
                    onClick = {
                        DataSaver().saveSecondLaunch(sharedPreferences)

                        val intent = Intent(mContext, LoginActivity::class.java)
                        startActivity(intent)
                    },
                    label = skipText,
                )
                Image(painter = painterResource(id = R.drawable.onboard_logo), contentDescription = "")
            }
            HorizontalPager(
                count = 3,
                state = pagerState
            ) { page ->
                OnboardComponent(info = screenList[page], currentIndex = page)
            }
        }
    }
}