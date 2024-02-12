package com.br.kmpdemo.models

import com.br.kmpdemo.models.domainmodelinterface.DomainModel
import kotlinx.serialization.Serializable

@Serializable
data class Timelines(
    val hourly: List<Hourly?>?,
    val minutely: List<Minutely?>?,
    val daily: List<Daily?>?
): DomainModel
