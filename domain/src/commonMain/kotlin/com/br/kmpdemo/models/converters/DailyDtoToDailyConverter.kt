package com.br.kmpdemo.models.converters

import com.br.kmpdemo.models.Daily
import com.br.kmpdemo.network.dtos.DailyDto

fun DailyDto?.toDailyModel(): Daily {
    return Daily(
        time = this?.time,
        dailyValues = this?.dailyValues?.toDailyValuesModel()
    )
}