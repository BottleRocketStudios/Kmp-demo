package com.br.kmpdemo.network.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MinutelyDto(
    val time: String?,
    @SerialName("values") val minutelyValues: MinutelyDataPointValuesDto?,
): Dto
