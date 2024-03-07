package com.br.kmpdemo.converters

import com.br.kmpdemo.dtos.DailyDto
import com.br.kmpdemo.models.Daily

fun DailyDto?.toDailyModel() = Daily(
    time = this?.time,
    dailyValues = this?.dailyValues?.toDailyValuesModel()
)