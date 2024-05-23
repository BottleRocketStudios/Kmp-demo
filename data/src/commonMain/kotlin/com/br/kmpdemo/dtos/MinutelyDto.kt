package com.br.kmpdemo.dtos

import com.br.kmpdemo.network.dtos.Dto
import com.br.kmpdemo.network.dtos.MinutelyDataPointValuesDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MinutelyDto(
    val time: String?,
    @SerialName("values") val minutelyValues: MinutelyDataPointValuesDto?,
): Dto
