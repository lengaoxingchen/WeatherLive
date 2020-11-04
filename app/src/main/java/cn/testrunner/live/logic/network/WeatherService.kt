package cn.testrunner.live.logic.network

import cn.testrunner.live.WeatherLiveApplication
import cn.testrunner.live.logic.model.DailyResponse
import cn.testrunner.live.logic.model.RealtimeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * 定义访问天气信息API的Retrofit接口
 */
interface WeatherService {
    /**
     * 获取实时的天气信息
     */
    @GET("v2.5/${WeatherLiveApplication.TOKEN}/{lng},{lat}/realtime.json")
    fun getRealtimeWeather(@Path("lng") lng: String, @Path("lat") lat: String): Call<RealtimeResponse>

    /**
     * 获取未来的天气信息
     */
    @GET("v2.5/${WeatherLiveApplication.TOKEN}/{lng},{lat}/daily.json")
    fun getDailyWeather(@Path("lng") lng: String, @Path("lat") lat: String): Call<DailyResponse>
}