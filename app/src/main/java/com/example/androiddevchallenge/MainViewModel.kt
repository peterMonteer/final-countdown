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

import android.os.CountDownTimer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    var inputMinutes: String by mutableStateOf("")
        private set

    var inputSeconds: String by mutableStateOf("")
        private set

    var clockMinutes: Long by mutableStateOf(0L)
    var clockSeconds: Long by mutableStateOf(0L)

    var remainingMillis: Long by mutableStateOf(0L)
        private set

    var initialMillis: Long by mutableStateOf(0L)

    fun getPercentageComplete(): Float {
        if (initialMillis == 0L) return 1F
        return (remainingMillis.toFloat() / initialMillis.toFloat())
    }

    fun onInputMinutesChanged(minutes: String) {
        inputMinutes = minutes
    }

    fun onInputSecondsChanged(seconds: String) {
        inputSeconds = seconds
    }

    fun startCountdown() {
        val initialMinutes = inputMinutes.toLongOrNull() ?: 0L
        val initialSeconds = inputSeconds.toLongOrNull() ?: 0L
        clockMinutes = initialMinutes
        clockSeconds = initialSeconds

        val initialTime = initialMinutes * 60000 + initialSeconds * 1000
        initialMillis = initialTime

        CountdownTimer(initialTime, 1000) { minutes, seconds ->
            onTimeChanged(minutes = minutes, seconds = seconds)
        }.start()
    }

    private fun onTimeChanged(minutes: Long, seconds: Long) {
        clockMinutes = minutes
        clockSeconds = seconds
        remainingMillis = minutes * 60000 + seconds * 1000 - 1000
        if (minutes == 0L && seconds == 0L) {
            initialMillis = 0L
        }
    }
}

class CountdownTimer(
    millisInFuture: Long,
    countDownInterval: Long,
    private val onChanged: (Long, Long) -> Unit
) : CountDownTimer(
    millisInFuture,
    countDownInterval
) {

    override fun onTick(millisUntilFinished: Long) {
        var diff = millisUntilFinished
        val secondsInMilli: Long = 1000
        val minutesInMilli = secondsInMilli * 60

        val newElapsedMinutes = diff / minutesInMilli
        diff %= minutesInMilli

        val newElapsedSeconds = diff / secondsInMilli

        onChanged(newElapsedMinutes, newElapsedSeconds)
    }

    override fun onFinish() {
        onChanged(0L, 0L)
    }
}
