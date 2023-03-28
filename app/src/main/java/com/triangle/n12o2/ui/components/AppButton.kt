package com.triangle.n12o2.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.triangle.n12o2.ui.theme.primary
import com.triangle.n12o2.ui.theme.primaryVariant

/*
Описание: Текстовая кнопка
Дата создания: 28.03.2023 08:49
Автор: Хасанов Альберт
*/
@Composable
@NonRestartableComposable
fun AppTextButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    elevation: ButtonElevation? = ButtonDefaults.elevation(0.dp, 0.dp, 0.dp, 0.dp, 0.dp),
    shape: Shape = MaterialTheme.shapes.medium,
    border: BorderStroke? = null,
    colors: ButtonColors = ButtonDefaults.textButtonColors(
        contentColor = primaryVariant
    ),
    contentPadding: PaddingValues = ButtonDefaults.TextButtonContentPadding,
    label: String,
    textStyle: TextStyle = LocalTextStyle.current
) = Button(
    onClick = onClick,
    modifier = modifier,
    enabled = enabled,
    interactionSource = interactionSource,
    elevation = elevation,
    shape = shape,
    border = border,
    colors = colors,
    contentPadding = contentPadding,
    content = {
        Text(text = label, style = textStyle.copy(fontWeight = FontWeight.SemiBold))
    }
)

/*
Описание: Кнопка приложнения
Дата создания: 28.03.2023 08:50
Автор: Хасанов Альберт
*/
@Composable
@NonRestartableComposable
fun AppButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    elevation: ButtonElevation? = ButtonDefaults.elevation(0.dp, 0.dp, 0.dp, 0.dp, 0.dp),
    shape: Shape = MaterialTheme.shapes.medium,
    border: BorderStroke? = null,
    colors: ButtonColors = ButtonDefaults.buttonColors(
        contentColor = Color.White,
        disabledContentColor = Color.White,
        backgroundColor = primary,
        disabledBackgroundColor = Color(0xFFC9D4FB)
    ),
    contentPadding: PaddingValues = PaddingValues(16.dp),
    label: String,
    textStyle: TextStyle = LocalTextStyle.current
) = Button(
    onClick = onClick,
    modifier = modifier.fillMaxWidth(),
    enabled = enabled,
    interactionSource = interactionSource,
    elevation = elevation,
    shape = shape,
    border = border,
    colors = colors,
    contentPadding = contentPadding,
    content = {
        Text(text = label, style = textStyle.copy(fontWeight = FontWeight.SemiBold))
    },
)
