package com.diogo.soundwave.screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.diogo.soundwave.ui.theme.musicBar
import com.diogo.soundwave.ui.theme.musicBarBackground

@Composable
fun MusicBar(
    width: Dp = 300.dp,
    heigth: Dp = 10.dp,
    indicatorValue: Float = 0f,
    maxIndicatorValue: Float = 5f,
    backgroundIndicatorColor: Color = musicBarBackground,
    foregroundIndicatorColor: Color = musicBar,
    circleIndicatorColor: Color = musicBar
){

    Column(
        modifier = Modifier
            .size(width, heigth)
            .drawBehind {

                val percentage = indicatorValue / maxIndicatorValue

                val backgroundWidth = size.width * 9/10
                val foregroundWidth = backgroundWidth * percentage
                val startX = (size.width - backgroundWidth) / 2

                backgroundIndicator(
                    width = backgroundWidth,
                    heigth = size.height / 2,
                    indicatorColor = backgroundIndicatorColor
                )
                foregroundIndicator(
                    width = foregroundWidth,
                    heigth = size.height / 2,
                    indicatorColor = foregroundIndicatorColor,
                    startX = startX
                )
                circleIndicator(
                    indicatorColor = circleIndicatorColor,
                    center = Offset(foregroundWidth + startX, size.height / 2)
                )
            },
    ){

    }

}

fun DrawScope.backgroundIndicator(
    width: Float,
    heigth: Float,
    indicatorColor: Color,
){

    drawRoundRect(
        color = indicatorColor,
        topLeft = Offset((size.width - width) / 2, (size.height - heigth) / 2),
        size = Size(width, heigth),
        cornerRadius = CornerRadius(10f, 10f)
    )

}

fun DrawScope.foregroundIndicator(
    width: Float,
    heigth: Float,
    startX: Float,
    indicatorColor: Color,
    ){

    drawRoundRect(
        color = indicatorColor,
        topLeft = Offset(startX, (size.height - heigth) / 2),
        size = Size(width, heigth),
        cornerRadius = CornerRadius(10f, 10f)
    )

}

fun DrawScope.circleIndicator(
    indicatorColor: Color,
    center: Offset
){

    drawCircle(
        color = indicatorColor,
        center = center,
        radius = 12f
    )

}

@Preview(showBackground = true)
@Composable
fun MusicBarPreview(){
    MusicBar(
        indicatorValue = 0.33f
    )
}