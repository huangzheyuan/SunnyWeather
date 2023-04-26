package com.example.sunnyweather.logic.dao

import android.content.Context
import com.example.sunnyweather.SunnyWeatherApplication
import com.example.sunnyweather.logic.model.Place
import com.google.gson.Gson

object PlaceDao {
    fun savePlace(place:Place){
        sharedPreferences().edit().putString("place",Gson().toJson(place)).commit()
    }

    fun getSavePlace():Place{
        val placeJson= sharedPreferences().getString("place","")
        return Gson().fromJson(placeJson,Place::class.java)
    }

    fun isPlaceSave()= sharedPreferences().contains("place")

    private fun sharedPreferences()=SunnyWeatherApplication.context
        .getSharedPreferences("sunny_weather",Context.MODE_PRIVATE)
}