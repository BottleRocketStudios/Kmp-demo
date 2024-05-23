package com.br.kmpdemo.dtos

import kotlinx.serialization.Serializable

@Serializable
data class TimelinesDto(
    val hourly: List<HourlyDto?>? = null,
    val minutely: List<MinutelyDto?>? = null,
    val daily: List<DailyDto?>? = null
): Dto
