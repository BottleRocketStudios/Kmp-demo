package com.br.kmpdemo.models

import com.br.kmpdemo.models.domainmodelinterface.DomainModel
import kotlinx.serialization.Serializable

@Serializable
data class Daily(
    val time: String?,
    val dailyValues: DailyValues?
): DomainModel
