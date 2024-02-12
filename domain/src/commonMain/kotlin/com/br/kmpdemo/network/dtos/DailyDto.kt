package com.br.kmpdemo.network.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DailyDto(
    val time: String?,
    @SerialName("values") val dailyValues: DailyDataPointValuesDto?
): Dto
