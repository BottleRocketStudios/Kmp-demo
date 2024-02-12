package com.br.kmpdemo.models.converters

import com.br.kmpdemo.models.Minutely
import com.br.kmpdemo.network.dtos.MinutelyDto

fun MinutelyDto?.toMinutelyModel(): Minutely {
    return Minutely(
        time = this?.time,
        minutelyValues = this?.minutelyValues?.toMinutelyValuesModel()
    )
}