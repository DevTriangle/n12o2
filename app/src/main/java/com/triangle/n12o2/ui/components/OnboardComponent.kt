package com.triangle.n12o2.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.triangle.n12o2.ui.theme.captionColor
import com.triangle.n12o2.ui.theme.green
import com.triangle.n12o2.ui.theme.primaryVariant

/*
Описание: Компонент приветственного экрана
Дата создания: 28.03.2023 08:59
Автор: Хасанов Альберт
*/
@Composable
fun OnboardComponent(
    info: Map<String, Any>,
    currentIndex: Int
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 60.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = info["title"].toString(),
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
                color = green,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(29.dp))
            Text(
                text = info["desc"].toString(),
                fontSize = 14.sp,
                color = captionColor,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(60.dp))
            DotsIndicator(count = 3, currentIndex = currentIndex)
        }
        Image(
            painter = info["image"] as Painter,
            contentDescription = "",
            modifier = Modifier.height(270.dp),
            contentScale = ContentScale.FillHeight,
        )
    }
}

/*
Описание: Компонент индикации номера экрана
Дата создания: 28.03.2023 09:00
Автор: Хасанов Альберт
*/
@Composable
private fun DotsIndicator(
    count: Int,
    currentIndex: Int
) {
    Row {
        for (i in 0 until count) {
            Box(modifier = Modifier
                .padding(4.dp)
                .size(14.dp)
                .clip(CircleShape)
                .border(1.dp, primaryVariant, CircleShape)
                .background(if (currentIndex == i) primaryVariant else Color.White))
        }
    }
}