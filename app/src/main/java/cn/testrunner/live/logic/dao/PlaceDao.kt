package cn.testrunner.live.logic.dao

import android.content.Context
import androidx.core.content.edit
import cn.testrunner.live.WeatherLiveApplication
import cn.testrunner.live.logic.model.Place
import com.google.gson.Gson

/**
 * 使用SharedPreferences存储的Place单例类
 */
object PlaceDao {
    /**
     * 用于将Place对象存储到SharedPreferences文件中,Place对象以Json的形式存储
     */
    fun savePlace(place: Place) {
        sharedPreferences().edit {
            putString("place", Gson().toJson(place))
        }
    }

    /**
     * 用于从SharedPreferences文件中读取Place对象的J送存储格式,然后转换为Place对象
     */
    fun getSavedPlace(): Place {
        val placeJson = sharedPreferences().getString("place", "")
        return Gson().fromJson(placeJson, Place::class.java)
    }

    /**
     * 用于判断是否有数据已被存储
     */
    fun isPlaceSaved() = sharedPreferences().contains("place")

    private fun sharedPreferences() =
        WeatherLiveApplication.context.getSharedPreferences("weather_live", Context.MODE_PRIVATE)
}