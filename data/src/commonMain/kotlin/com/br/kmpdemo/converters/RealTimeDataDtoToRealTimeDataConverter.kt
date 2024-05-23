package com.br.kmpdemo.converters

import com.br.kmpdemo.dtos.RealTimeDataDto
import com.br.kmpdemo.models.RealTimeData

fun RealTimeDataDto?.toRealTimeDataModel() = RealTimeData(
    time = this?.time,
    realTimeValues = this?.realTimeValues.toRealTimeValuesModel(),
)