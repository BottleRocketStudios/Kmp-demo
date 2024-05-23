package com.br.kmpdemo.converters

import com.br.kmpdemo.dtos.DailyDto
import com.br.kmpdemo.models.Daily
import kotlinx.datetime.Instant

fun DailyDto.toDailyModel() = Daily(
    time = time?.let { Instant.Companion.parse(it) },
    dailyValues = dailyValues?.toDailyValuesModel()
)