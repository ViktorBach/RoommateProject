package com.example.roommateproject.FrontPage.Components.UI

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun RoundedCornerShape(topRight: Dp, bottomRight: Dp): RoundedCornerShape {
    return RoundedCornerShape(
        topStart = 0.dp,
        topEnd = topRight,
        bottomEnd = bottomRight,
        bottomStart = 0.dp
    )
}
