package com.br.kmpdemo.utils

import kotlinx.datetime.Clock.System.now
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun Instant.localDateTime() = toLocalDateTime(timeZone = TimeZone.currentSystemDefault())
fun Instant.date() = localDateTime().date
fun Instant.time() = localDateTime().time
fun Instant.isToday() = date() == now().date()
fun Instant.isHour() = time().hour == now().time().hour

