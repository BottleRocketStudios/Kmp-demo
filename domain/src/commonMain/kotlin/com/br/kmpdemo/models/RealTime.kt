package com.br.kmpdemo.models

import com.br.kmpdemo.models.domainmodelinterface.DomainModel
import kotlinx.serialization.Serializable

@Serializable
data class RealTime(
    val data: RealTimeData?,
    val location: Location?
) : DomainModel
