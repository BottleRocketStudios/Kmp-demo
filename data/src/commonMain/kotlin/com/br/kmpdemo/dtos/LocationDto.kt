package com.br.kmpdemo.dtos

import com.br.kmpdemo.network.dtos.Dto
import kotlinx.serialization.Serializable

@Serializable
data class LocationDto(
    val lat: Double? = null,
    val lon: Double? = null,
    val name: String? = null,
    val type: String? = null
): Dto