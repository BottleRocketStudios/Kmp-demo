package com.br.kmpdemo.converters

import com.br.kmpdemo.dtos.MinutelyDto
import com.br.kmpdemo.models.Minutely

fun MinutelyDto?.toMinutelyModel() = Minutely(
    time = this?.time,
    minutelyValues = this?.minutelyValues?.toMinutelyValuesModel()
)