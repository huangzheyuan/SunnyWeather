package com.example.sunnyweather


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sunnyweather.logic.network.SunnyWeatherNetwork
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}