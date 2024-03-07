package com.br.kmpdemo.converters

import com.br.kmpdemo.dtos.HourlyDto
import com.br.kmpdemo.models.Hourly

fun HourlyDto?.toHourlyModel() = Hourly(
    time = this?.time,
    hourlyValues = this?.hourlyValues?.toHourlyValuesModel()
)