package com.br.kmpdemo.models

import com.br.kmpdemo.models.domainmodelinterface.DomainModel
import kotlinx.serialization.Serializable

@Serializable
data class Hourly(
    val time: String?,
    val hourlyValues: HourlyValues?
) : DomainModel
