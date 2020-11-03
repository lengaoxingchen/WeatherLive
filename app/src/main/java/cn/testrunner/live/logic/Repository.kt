package cn.testrunner.live.logic

import androidx.lifecycle.liveData
import cn.testrunner.live.logic.model.Place
import cn.testrunner.live.logic.network.WeatherLiveNetWork
import kotlinx.coroutines.Dispatchers

/**
 * 仓库层统一封装入口的单例类
 */
object Repository {
    fun searchPlaces(query: String) = liveData(Dispatchers.IO) {
        val result = try {
            val placeResponse = WeatherLiveNetWork.searchPlaces(query)
            if (placeResponse.status == "ok") {
                val places = placeResponse.places
                Result.success(places)
            } else {
                Result.failure(RuntimeException("response status is ${placeResponse.status}"))
            }
        } catch (e: Exception) {
            Result.failure<List<Place>>(e)
        }
        emit(result)
    }
}