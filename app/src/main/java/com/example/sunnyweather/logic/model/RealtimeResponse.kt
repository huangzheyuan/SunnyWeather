package com.example.sunnyweather.logic.model

import com.google.gson.annotations.SerializedName

data class RealtimeResponse(val status:String,val result:Result) {
    data class Result(val realtime:Realtime)

    data class Realtime(val skycon:String,val temperature:Float,
    @SerializedName("air_quality")val airquality:AirQuality)

    data class AirQuality(val api:AQI,val pm25:Int)

    data class AQI(val chn:Float)
}