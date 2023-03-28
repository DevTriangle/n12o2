package com.triangle.n12o2.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.triangle.n12o2.LoginViewModel
import com.triangle.n12o2.ui.theme.N12o2Theme
import com.triangle.n12o2.ui.theme.inputBG
import com.triangle.n12o2.R
import com.triangle.n12o2.ui.components.AppTextField
import com.triangle.n12o2.ui.theme.descriptionColor
import kotlinx.coroutines.delay

/*
Описание: Активити экрана ввода кода из email
Дата создания: 28.03.2023 08:36
Автор: Хасанов Альберт
*/
class CodeActivity : ComponentActivity() {
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
    Описание: Содержание экрана ввода кода из email
    Дата создания: 28.03.2023 08:37
    Автор: Хасанов Альберт
    */
    @Composable
    fun ScreenContent() {
        var mContext = LocalContext.current
        val viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        val sharedPreferences = this.getSharedPreferences("shared", MODE_PRIVATE)

        var code1 by rememberSaveable { mutableStateOf("") }
        var code2 by rememberSaveable { mutableStateOf("") }
        var code3 by rememberSaveable { mutableStateOf("") }
        var code4 by rememberSaveable { mutableStateOf("") }

        var timer by rememberSaveable { mutableStateOf(60) }
        val email = intent.getStringExtra("email")

        LaunchedEffect(timer) {
            delay(1000)

            if (timer > 0) {
                timer--
            } else {
                viewModel.sendCode(email!!)
                timer = 60
            }
        }

        Scaffold(
            topBar = {
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(MaterialTheme.shapes.small)
                        .background(
                            inputBG,
                        )
                        .clickable { onBackPressed() },
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = "",
                        tint = descriptionColor
                    )
                }
            }
        ) {
            Box(modifier = Modifier.padding(it)) {
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(20.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Введите код из E-mail",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AppTextField(
                            value = code1,
                            onValueChange = { code ->
                                if (code.length < 2) {
                                    code1 = code
                                }
                            },
                            modifier = Modifier
                                .size(48.dp)
                                .padding(end = 16.dp),
                            contentPadding = PaddingValues(5.dp)
                        )
                        AppTextField(
                            value = code2,
                            onValueChange = { code ->
                                if (code.length < 2) {
                                    code2 = code
                                }
                            },
                            modifier = Modifier
                                .size(48.dp)
                                .padding(end = 16.dp),
                            contentPadding = PaddingValues(5.dp)
                        )
                        AppTextField(
                            value = code3,
                            onValueChange = { code ->
                                if (code.length < 2) {
                                    code3 = code
                                }
                            },
                            modifier = Modifier
                                .size(48.dp)
                                .padding(end = 16.dp),
                            contentPadding = PaddingValues(5.dp)
                        )

                        AppTextField(
                            value = code4,
                            onValueChange = { code ->
                                if (code.length < 2) {
                                    code4 = code
                                    viewModel.logIn(email, "${code1}${code2}${code3}${code4}")
                                }
                            },
                            modifier = Modifier
                                .size(48.dp),
                            contentPadding = PaddingValues(5.dp)
                        )
                    }
                }
            }
        }
    }
}