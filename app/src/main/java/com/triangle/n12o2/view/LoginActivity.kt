package com.triangle.n12o2.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Space
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.triangle.n12o2.LoginViewModel
import com.triangle.n12o2.ui.theme.N12o2Theme
import com.triangle.n12o2.R
import com.triangle.n12o2.ui.components.AppButton
import com.triangle.n12o2.ui.components.AppTextButton
import com.triangle.n12o2.ui.components.AppTextField
import com.triangle.n12o2.ui.components.LoadingDialog
import com.triangle.n12o2.ui.theme.captionColor
import com.triangle.n12o2.ui.theme.descriptionColor
import com.triangle.n12o2.ui.theme.inputStroke

/*
Описание: Активити экрана авторизации
Дата создания: 28.03.2023 08:36
Автор: Хасанов Альберт
*/
class LoginActivity : ComponentActivity() {
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
    Описание: Содержание экрана авторизации
    Дата создания: 28.03.2023 08:36
    Автор: Хасанов Альберт
    */
    @Composable
    fun ScreenContent() {
        val mContext = LocalContext.current
        val viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        var email by rememberSaveable { mutableStateOf("") }
        var isLoading by rememberSaveable { mutableStateOf(false) }
        var isError by rememberSaveable { mutableStateOf(false) }

        val errorMessage by viewModel.message.observeAsState()
        LaunchedEffect(errorMessage) {
            if (errorMessage != null) {
                isError = true
                isLoading = false
            }
        }

        val isSuccess by viewModel.isSuccess.observeAsState()
        LaunchedEffect(isSuccess) {
            if (isSuccess != null) {
                isLoading = false

                val intent = Intent(mContext, CodeActivity::class.java)
                startActivity(intent)
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 60.dp, start = 20.dp, end = 20.dp, bottom = 56.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_hello),
                        contentDescription = "",
                        modifier = Modifier
                            .size(32.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = "Добро пожаловать!",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(23.dp))
                Text(text = "Войдите, чтобы пользоваться функциями приложения", fontSize = 15.sp)
                Spacer(modifier = Modifier.height(60.dp))
                AppTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = email,
                    onValueChange = {
                        email = it
                    },
                    label = {
                        Text(
                            text = "Вход по E-mail",
                            fontSize = 14.sp,
                            color = descriptionColor,
                        )
                    },
                    placeholder = {
                        Text(
                            text = "example@mail.ru",
                            color = Color.Black.copy(0.5f),
                            fontSize = 15.sp
                        )
                    }
                )
                Spacer(modifier = Modifier.height(32.dp))
                AppButton(
                    onClick = {
                        if (Regex("^[a-zA-Z0-9]*@[a-zA-Z0-9]*\\.[a-zA-Z0-9]{2,}$").matches(email)) {
                            viewModel.sendCode(email)
                            isLoading = true
                        } else {
                            Toast.makeText(mContext, "Неправильный формат E-Mail", Toast.LENGTH_LONG).show()
                        }
                    },
                    label = "Далее",
                    textStyle = TextStyle(
                        fontSize = 17.sp
                    ),
                    enabled = email.isNotBlank()
                )
            }
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Или войдите с помощью",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    color = captionColor
                )
                Spacer(modifier = Modifier.height(16.dp))
                AppButton(
                    onClick = {},
                    label = "Войти с Яндекс",
                    textStyle = TextStyle(
                        fontSize = 17.sp
                    ),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.White,
                        contentColor = Color.Black
                    ),
                    border = BorderStroke(1.dp, inputStroke)
                )
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