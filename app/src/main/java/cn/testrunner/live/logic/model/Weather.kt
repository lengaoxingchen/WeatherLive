package cn.testrunner.live.logic.model

/**
 * 用于封装RealTimeResponse和DailyResponse类
 */
data class Weather(val realtime: RealtimeResponse.Realtime, val daily: DailyResponse.Daily)