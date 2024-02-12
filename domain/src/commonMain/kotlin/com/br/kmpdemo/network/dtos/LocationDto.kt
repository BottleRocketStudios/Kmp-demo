package com.br.kmpdemo.network.dtos

import kotlinx.serialization.Serializable

@Serializable
data class LocationDto(
    val lat: Double? = null,
    val lon: Double? = null,
    val name: String? = null,
    val type: String? = null
): Dto