package com.br.kmpdemo.converters

import com.br.kmpdemo.dtos.HourlyDto
import com.br.kmpdemo.models.Hourly
import kotlinx.datetime.Instant

fun HourlyDto?.toHourlyModel() = Hourly(
    time = this?.time?.let { Instant.parse(it) },
    hourlyValues = this?.hourlyValues?.toHourlyValuesModel()
)