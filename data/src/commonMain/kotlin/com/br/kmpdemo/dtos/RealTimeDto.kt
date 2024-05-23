package com.br.kmpdemo.dtos

import kotlinx.serialization.Serializable

@Serializable
data class RealTimeDto(
    val data: RealTimeDataDto?,
    val location: LocationDto?,
): Dto
