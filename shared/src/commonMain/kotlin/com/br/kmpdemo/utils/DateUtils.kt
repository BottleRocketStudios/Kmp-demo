package com.br.kmpdemo.utils

import kotlinx.datetime.Clock.System.now
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun Instant.localDateTime(): LocalDateTime = toLocalDateTime(timeZone = TimeZone.currentSystemDefault())
fun Instant.date(): LocalDate = localDateTime().date
fun Instant.time(): LocalTime = localDateTime().time
fun Instant.isToday(): Boolean = date() == now().date()
fun Instant.isHour(): Boolean = time().hour == now().time().hour

