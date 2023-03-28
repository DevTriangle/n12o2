package com.triangle.n12o2.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.triangle.n12o2.viewmodel.LoginViewModel
import com.triangle.n12o2.ui.theme.N12o2Theme
import com.triangle.n12o2.ui.theme.inputBG
import com.triangle.n12o2.R
import com.triangle.n12o2.common.DataSaver
import com.triangle.n12o2.ui.components.AppTextButton
import com.triangle.n12o2.ui.components.AppTextField
import com.triangle.n12o2.ui.components.LoadingDialog
import com.triangle.n12o2.ui.theme.captionColor
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

        var isLoading by rememberSaveable { mutableStateOf(false) }
        var isError by rememberSaveable { mutableStateOf(false) }

        val errorMessage by viewModel.message.observeAsState()
        LaunchedEffect(errorMessage) {
            if (errorMessage != null) {
                isError = true
                isLoading = false
            }
        }

        val token by viewModel.token.observeAsState()
        LaunchedEffect(token) {
            if (token != null) {
                isLoading = false

                DataSaver().saveToken(sharedPreferences, token!!)

                val intent = Intent(mContext, PasswordActivity::class.java)
                startActivity(intent)
            }
        }

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
                        .padding(20.dp)
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
                        tint = descriptionColor,
                        modifier = Modifier.align(Alignment.Center)
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
                        horizontalArrangement = Arrangement.Center,
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
                                .padding(end = 16.dp)
                                .size(48.dp),
                            contentPadding = PaddingValues(10.dp),
                            textStyle = TextStyle(
                                fontSize = 20.sp,
                                lineHeight = 20.sp,
                                textAlign = TextAlign.Center
                            )
                        )
                        AppTextField(
                            value = code2,
                            onValueChange = { code ->
                                if (code.length < 2) {
                                    code2 = code
                                }
                            },
                            modifier = Modifier
                                .padding(end = 16.dp)
                                .size(48.dp),
                            contentPadding = PaddingValues(10.dp),
                            textStyle = TextStyle(
                                fontSize = 20.sp,
                                lineHeight = 20.sp,
                                textAlign = TextAlign.Center
                            )
                        )
                        AppTextField(
                            value = code3,
                            onValueChange = { code ->
                                if (code.length < 2) {
                                    code3 = code
                                }
                            },
                            modifier = Modifier
                                .padding(end = 16.dp)
                                .size(48.dp),
                            contentPadding = PaddingValues(10.dp),
                            textStyle = TextStyle(
                                fontSize = 20.sp,
                                lineHeight = 20.sp,
                                textAlign = TextAlign.Center
                            )
                        )

                        AppTextField(
                            value = code4,
                            onValueChange = { code ->
                                if (code.length < 2) {
                                    code4 = code
                                    viewModel.logIn(email!!, "${code1}${code2}${code3}${code4}")
                                    isLoading = true
                                }
                            },
                            modifier = Modifier
                                .size(48.dp),
                            contentPadding = PaddingValues(10.dp),
                            textStyle = TextStyle(
                                fontSize = 20.sp,
                                lineHeight = 20.sp,
                                textAlign = TextAlign.Center
                            )
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Отправить код повторно можно будет через $timer секунд",
                        color = captionColor,
                        fontSize = 15.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        if (isError) {
            AlertDialog(
                onDismissRequest = { isError = false },
                title = {
                    Text(text = "Ошибка", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
                },
                text = {
                    Text(text = errorMessage.toString())
                },
                buttons = {
                    AppTextButton(onClick = { isError = false }, label = "Ок")
                }
            )
        }

        if (isLoading) {
            LoadingDialog()
        }
    }
}