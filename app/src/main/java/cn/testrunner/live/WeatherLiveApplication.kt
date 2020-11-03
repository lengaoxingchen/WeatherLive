package cn.testrunner.live

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

/**
 * 使用MVVM这种分层架构的设计,由于从ViewModel层开始就不再持有Activity的引用了,因此经常会出现"缺Context"的情况,
 * 因此下面提供了一种全局获取Context的方式
 */
class WeatherLiveApplication : Application() {
    companion object {

        //开发者平台申请的密钥
        const val TOKEN="GbSU8m8MtDmcFTzE"

        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}