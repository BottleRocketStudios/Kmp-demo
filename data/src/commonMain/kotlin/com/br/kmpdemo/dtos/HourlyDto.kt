package com.br.kmpdemo.dtos

import com.br.kmpdemo.network.dtos.Dto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HourlyDto(
    val time: String?,
    @SerialName("values") val hourlyValues: HourlyDataPointValuesDto?
): Dto
