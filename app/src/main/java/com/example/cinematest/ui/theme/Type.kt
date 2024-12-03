package com.example.cinematest.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.LoadedFontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.cinematest.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,
        color = Color.White
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

object MyTextStyles {
    val myTextStyleHeader = TextStyle(
        fontSize = 18.sp,
        lineHeight = 22.sp,
        fontFamily = FontFamily(Font(R.font.roboto)),
        fontWeight = FontWeight(500),
        letterSpacing = 0.15.sp,
        textAlign = TextAlign.Center,

       // color = Color(0xFF000000)
    )

    val myTextStyleHeaderSecond = TextStyle(
        fontSize = 20.sp,
        lineHeight = 22.sp,
        fontFamily = FontFamily(Font(R.font.roboto)),
        fontWeight = FontWeight(700),
        letterSpacing = 0.1.sp,
        //color = Color(0xFF000000)
       // color = Color(0xFF4B4B4B)
    )
    val myTextStyleGenreItem = TextStyle(
        fontSize = 16.sp,
        lineHeight = 20.sp,
        fontFamily = FontFamily(Font(R.font.roboto)),
        fontWeight = FontWeight(400),
        letterSpacing = 0.1.sp,
        //color = Color(0xFF000000)
    )
    val myTextStyleFilmItem = TextStyle(
        fontSize = 16.sp,
        lineHeight = 20.sp,
        fontFamily = FontFamily(Font(R.font.roboto)),
        fontWeight = FontWeight(700),
        letterSpacing = 0.1.sp,
        //color = Color(0xFF000000)
       // color = Color(0xFF000000)
    )

    val myTextStyleFilmName = TextStyle(
        fontSize = 26.sp,
        lineHeight = 32.sp,
        fontFamily = FontFamily(Font(R.font.roboto)),
        fontWeight = FontWeight(700),
        letterSpacing = 0.1.sp,
        color = Color(0xFF000000)
        // color = Color(0xFF000000)
    )

    val myTextStyleRating = TextStyle(
        fontSize = 24.sp,
        lineHeight = 28.sp,
        fontFamily = FontFamily(Font(R.font.roboto)),
        fontWeight = FontWeight(700),
        letterSpacing = 0.1.sp,
        color = Color(0xFF0E3165)
        // color = Color(0xFF000000)
    )

    val myTextStyleSource = TextStyle(
        fontSize = 16.sp,
        lineHeight = 16.sp,
        fontFamily = FontFamily(Font(R.font.roboto)),
        fontWeight = FontWeight(500),
        letterSpacing = 0.1.sp,
        color = Color(0xFF0E3165)
        //color = Color(0xFF0E3165)
        // color = Color(0xFF000000)
    )

    val myTextStyleDescription = TextStyle(
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontFamily = FontFamily(Font(R.font.roboto)),
        fontWeight = FontWeight(400),
        letterSpacing = 0.1.sp,
       // color = Color(0xFF000000)
        //color = Color(0xFF0E3165)
        // color = Color(0xFF000000)
    )


}