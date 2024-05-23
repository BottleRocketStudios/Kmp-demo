package com.br.kmpdemo.models

class ApiException(
    val code: Int,
    message: String,
    val subCode: Int,
    val subMessage: String,
    val errorNumber: Int
) : RuntimeException(message)