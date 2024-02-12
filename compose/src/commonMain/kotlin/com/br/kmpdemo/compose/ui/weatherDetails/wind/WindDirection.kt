package com.br.kmpdemo.compose.ui.weatherDetails.wind

/**
 * "degrees" slightly adjusted from normal to better match up with
 * Cardinal and Intermediate Lines.
 */
enum class WindDirection(val direction: String, val degrees: Double) {
    N("North", 0.0),
    NNE("North-Northeast", 29.5),
    NE("Northeast", 45.0),
    ENE("East-Northeast", 60.5),
    E("East", 90.0),
    ESE("East-Southeast", 119.5),
    SE("Southeast", 135.0),
    SSE("South-Southeast", 150.5),
    S("South", 180.0),
    SSW("South-Southwest", 209.5),
    SW("Southwest", 226.0),
    WSW("West-Southwest", 240.5),
    W("West", 270.0),
    WNW("West-Northwest", 299.5),
    NW("Northwest", 316.0),
    NNW("North-Northwest", 330.5),
}