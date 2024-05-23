package com.br.kmpdemo.models

import com.br.kmpdemo.models.domainmodelinterface.DomainModel
import kotlinx.serialization.Serializable

@Serializable
data class Minutely(
    val time: String?,
    val minutelyValues: MinutelyValues?,
): DomainModel
