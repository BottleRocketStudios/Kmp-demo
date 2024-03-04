package com.br.kmpdemo.converters

import com.br.kmpdemo.models.Hourly
import com.br.kmpdemo.network.dtos.HourlyDto

fun HourlyDto?.toHourlyModel(): Hourly {
    return Hourly(
        time = this?.time,
        hourlyValues = this?.hourlyValues?.toHourlyValuesModel()
    )
}