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

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androiddevchallenge.components.ActionButton
import com.example.androiddevchallenge.components.ClockContainer
import com.example.androiddevchallenge.components.Input
import com.example.androiddevchallenge.ui.theme.MyTheme

class MainActivity : AppCompatActivity() {

    val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp(mainViewModel = mainViewModel)
            }
        }
    }
}

@Composable
fun MyApp(mainViewModel: MainViewModel) {

    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier.fillMaxHeight()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                TimerCircle(progress = mainViewModel.getPercentageComplete())
                ClockContainer(
                    mainViewModel.clockMinutes,
                    mainViewModel.clockSeconds,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            Row {
                Input(
                    modifier = Modifier.weight(1f),
                    label = "Minutes",
                    value = mainViewModel.inputMinutes,
                    onValueChanged = mainViewModel::onInputMinutesChanged
                )
                Input(
                    modifier = Modifier.weight(1f),
                    label = "Seconds",
                    value = mainViewModel.inputSeconds,
                    onValueChanged = mainViewModel::onInputSecondsChanged
                )
            }
            ActionButton { mainViewModel.startCountdown() }
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp(viewModel())
    }
}
