/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MyTheme

@Composable
fun TimerCircle(
    progress: Float,
    backgroundColor: Color = MaterialTheme.colors.primaryVariant,
    progressColor: Color = MaterialTheme.colors.secondary
) {
    val animateCurrentProgress = animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(durationMillis = 1000, easing = LinearEasing)
    )

    val progressDegrees = animateCurrentProgress.value * 360F

    Canvas(
        modifier = Modifier
            .width(400.dp)
            .height(400.dp)
            .padding(30.dp)
    ) {
        drawArc(
            color = backgroundColor,
            style = Stroke(width = 80.dp.value, cap = StrokeCap.Round),
            useCenter = false,
            startAngle = 270f,
            sweepAngle = 360f
        )

        drawArc(
            color = progressColor,
            style = Stroke(width = 80.dp.value, cap = StrokeCap.Round),
            useCenter = false,
            startAngle = 270f,
            sweepAngle = progressDegrees
        )
    }
}

@Preview
@Composable
fun ArcPreview() {
    MyTheme {
        TimerCircle(0.5f)
    }
}
