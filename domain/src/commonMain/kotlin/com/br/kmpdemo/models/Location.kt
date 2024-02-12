package com.br.kmpdemo.models

import com.br.kmpdemo.models.domainmodelinterface.DomainModel
import kotlinx.serialization.Serializable

@Serializable
data class Location(
    val lat: Double?,
    val lon: Double?,
    val name: String?,
    val type: String?
): DomainModel