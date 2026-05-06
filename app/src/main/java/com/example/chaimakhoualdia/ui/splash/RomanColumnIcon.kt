package com.example.chaimakhoualdia.ui.splash

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

/** White Roman column drawn using Compose vector graphics. */
val RomanColumnIcon: ImageVector = ImageVector.Builder(
    name = "RomanColumnIcon",
    defaultWidth = 96.dp,
    defaultHeight = 96.dp,
    viewportWidth = 96f,
    viewportHeight = 96f
).apply {
    path(fill = androidx.compose.ui.graphics.SolidColor(Color.White), pathFillType = PathFillType.NonZero) {
        moveTo(32f, 10f)
        lineTo(64f, 10f)
        lineTo(64f, 20f)
        lineTo(32f, 20f)
        close()

        moveTo(38f, 22f)
        lineTo(58f, 22f)
        lineTo(58f, 74f)
        lineTo(38f, 74f)
        close()

        moveTo(28f, 74f)
        lineTo(68f, 74f)
        lineTo(68f, 84f)
        lineTo(28f, 84f)
        close()
    }
}.build()



