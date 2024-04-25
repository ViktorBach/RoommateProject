package com.example.roommateproject.ui.theme
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.roommateproject.R

// Set of Material typography styles to start with
val Typography = Typography(
    // Large body text
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    // Medium body text
    bodyMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    // Small body text
    bodySmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.sp
    ),
    // Large title text
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Thin,
        fontSize = 75.sp,
        lineHeight = 80.sp,
        letterSpacing = 3.sp
    ),
    // Medium title text
    titleMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 50.sp,
        lineHeight = 50.sp,
        letterSpacing = 0.sp,
    ),
    // Small title text
    titleSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 34.sp,
        lineHeight = 34.sp,
        letterSpacing = 0.sp
    ),
    // Large label text
    labelLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.sp
    ),
    labelMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        lineHeight = 26.sp,
        letterSpacing = 0.sp
    ),
    // Small label text
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 22.sp,
        letterSpacing = 0.sp
    )
)

val karantinaFontFamily = FontFamily(
    Font(R.font.karantina_regular, FontWeight.Normal),
)

val katibehFontFamily = FontFamily(
    Font(R.font.katibeh_regular, FontWeight.Normal)
)

val playFairDisplayFontFamily = FontFamily(
    Font(R.font.playfairdisplay_regular, FontWeight.Normal)
)

val jaldiFontFamily = FontFamily(
    Font(R.font.jaldi_bold, FontWeight.Normal)
)