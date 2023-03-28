package com.triangle.n12o2.ui.components

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Dialog

/*
Описание: Индикация загрузки
Дата создания: 28.03.2023 09:15
Автор: Хасанов Альберт
*/
@Composable
fun LoadingDialog() {
    Dialog(onDismissRequest = {}) {
        CircularProgressIndicator()
    }
}