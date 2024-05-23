package com.br.kmpdemo.models

import com.br.kmpdemo.models.domainmodelinterface.DomainModel
import kotlinx.serialization.Serializable

@Serializable
data class RealTimeData(
    val time: String?,
    val realTimeValues: RealTimeValues?
): DomainModel
