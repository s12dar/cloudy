package com.lyvetech.cloudy.core.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.lyvetech.cloudy.core.R

val fontFamilyKulim = FontFamily(
    listOf(
        Font(
            resId = R.font.kulim_park_regular
        ),
        Font(
            resId = R.font.kulim_park_light,
            weight = FontWeight.Light
        )
    )
)

val typography = Typography(
    defaultFontFamily = fontFamilyKulim,
    h1 = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
    ),
    h2 = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 17.sp,
    ),
    h3 = TextStyle(
        fontSize = 15.sp,
    ),
    body1 = TextStyle(
        fontSize = 14.sp
    ),
    button = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        letterSpacing = (1.15).sp
    ),
    caption = TextStyle(
        fontSize = 12.sp,
        letterSpacing = (1.15).sp
    ),
)