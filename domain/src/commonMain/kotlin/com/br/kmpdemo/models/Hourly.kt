package com.br.kmpdemo.models

import com.br.kmpdemo.models.domainmodelinterface.DomainModel
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class Hourly(
    val time: Instant?,
    val hourlyValues: HourlyValues?
) : DomainModel
