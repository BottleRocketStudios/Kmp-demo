package com.br.kmpdemo.models

import com.br.kmpdemo.models.domainmodelinterface.DomainModel
import kotlinx.serialization.Serializable

@Serializable
data class Forecast(
    val timelines: Timelines?,
    val locationDto: Location?
): DomainModel
