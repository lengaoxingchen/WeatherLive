package cn.testrunner.live.logic

import androidx.lifecycle.liveData
import cn.testrunner.live.logic.model.Place
import cn.testrunner.live.logic.model.Weather
import cn.testrunner.live.logic.network.WeatherLiveNetWork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlin.coroutines.CoroutineContext

/**
 * 仓库层统一封装入口的单例类
 */
object Repository {
    /**
     * 获取地点请求的封装
     */
    fun searchPlaces(query: String) = fire(Dispatchers.IO) {
        val placeResponse = WeatherLiveNetWork.searchPlaces(query)
        if (placeResponse.status == "ok") {
            val places = placeResponse.places
            Result.success(places)
        } else {
            Result.failure(RuntimeException("response status is ${placeResponse.status}"))
        }
    }

    /**
     * 刷新天气的方法
     * 将getRealtimeWeather和getDailyWeather进行统一封装,每次请求一次就可以获取实时和未来的天气
     */
    fun refreshWeather(lng: String, lat: String) = fire(Dispatchers.IO) {
        //coroutineScope 创建协程作用域
        coroutineScope {
            val deferredRealtime = async {
                WeatherLiveNetWork.getRealtimeWeather(lng, lat)
            }

            val deferredDaily = async {
                WeatherLiveNetWork.getDailyWeather(lng, lat)
            }
            val realtimeResponse = deferredRealtime.await()
            val dailyResponse = deferredDaily.await()
            if (realtimeResponse.status == "ok" && dailyResponse.status == "ok") {
                val weather = Weather(realtimeResponse.result.realtime, dailyResponse.result.daily)
                Result.success(weather)
            } else {
                Result.failure(
                    java.lang.RuntimeException(
                        "realtime response status is ${realtimeResponse.status}" +
                                "daily response status is ${dailyResponse.status}"
                    )
                )
            }
        }

    }

    //对try...catch进行统一的封装
    private fun <T> fire(context: CoroutineContext, block: suspend () -> Result<T>) = liveData<Result<T>>(context) {
        val result = try {
            block()
        } catch (e: Exception) {
            Result.failure<T>(e)
        }
        emit(result)
    }
}