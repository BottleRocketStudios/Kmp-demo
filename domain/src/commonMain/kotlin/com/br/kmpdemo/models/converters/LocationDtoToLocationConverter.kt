package com.br.kmpdemo.models.converters

import com.br.kmpdemo.models.Location
import com.br.kmpdemo.network.dtos.LocationDto

fun LocationDto?.toLocationModel(): Location {
    return Location(
        lat = this?.lat,
        lon = this?.lon,
        name = this?.name,
        type = this?.type
    )
}