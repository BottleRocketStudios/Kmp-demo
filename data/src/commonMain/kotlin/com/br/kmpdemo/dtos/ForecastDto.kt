package com.br.kmpdemo.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForecastDto(
    val timelines: TimelinesDto? = null,
    @SerialName("location") val locationDto: LocationDto? = null
): Dto
