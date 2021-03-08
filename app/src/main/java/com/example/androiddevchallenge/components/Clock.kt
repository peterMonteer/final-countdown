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
package com.example.androiddevchallenge.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import java.text.DecimalFormat

@Composable
fun ClockContainer(
    elapsedMinutes: Long,
    elapsedSeconds: Long,
    modifier: Modifier = Modifier
) {

    Clock(
        elapsedMinutes = elapsedMinutes,
        elapsedSeconds = elapsedSeconds,
        modifier = modifier
    )
}

@Composable
fun Clock(elapsedMinutes: Long, elapsedSeconds: Long, modifier: Modifier = Modifier) {
    val formattedMinutes = DecimalFormat("00").format(elapsedMinutes)
    val formattedSeconds = DecimalFormat("00").format(elapsedSeconds)
    Text(
        text = "$formattedMinutes : $formattedSeconds",
        fontSize = 50.sp,
        modifier = modifier,
    )
}
