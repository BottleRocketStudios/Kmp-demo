package com.br.kmpdemo.converters

import com.br.kmpdemo.dtos.LocationDto
import com.br.kmpdemo.models.Location

fun LocationDto?.toLocationModel() = Location(
    lat = this?.lat,
    lon = this?.lon,
    name = this?.name,
    type = this?.type
)