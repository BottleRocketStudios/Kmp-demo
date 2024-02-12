package com.br.kmpdemo.network.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HourlyDto(
    val time: String?,
    @SerialName("values") val hourlyValues: HourlyDataPointValuesDto?
): Dto
