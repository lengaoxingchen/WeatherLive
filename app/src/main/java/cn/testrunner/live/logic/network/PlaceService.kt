package cn.testrunner.live.logic.network

import cn.testrunner.live.WeatherLiveApplication
import cn.testrunner.live.logic.model.PlaceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PlaceService {

    /**
     * 用于访问彩云天气城市搜索API的接口
     */
    @GET("v2/place?token=${WeatherLiveApplication.TOKEN}&lang=zh_CN")
    fun searchPlaces(@Query("query") query: String): Call<PlaceResponse>
}