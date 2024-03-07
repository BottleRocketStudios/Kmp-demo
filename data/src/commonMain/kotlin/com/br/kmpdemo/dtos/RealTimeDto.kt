package com.br.kmpdemo.dtos

import com.br.kmpdemo.network.dtos.Dto
import kotlinx.serialization.Serializable

@Serializable
data class RealTimeDto(
    val data: RealTimeDataDto?,
    val location: LocationDto?,
): Dto
