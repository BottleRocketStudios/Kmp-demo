package com.br.kmpdemo.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RealTimeDataDto(
    val time: String?,
    @SerialName("values") val realTimeValues: RealTimeValuesDto?
): Dto
