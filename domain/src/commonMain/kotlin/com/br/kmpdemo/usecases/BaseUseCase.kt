package com.br.kmpdemo.usecases

import com.br.kmpdemo.usecases.BaseUseCase.*
import org.koin.core.component.KoinComponent

interface BaseUseCase<Request : BaseRequest, Response : BaseResponse> : KoinComponent {
    open class BaseRequest
    open class BaseResponse

    suspend operator fun invoke(request: Request? = null): Result<Response>

    suspend infix fun Request?.assertNotNull(block: suspend (Request) -> Result<Response>): Result<Response> =
        (this?.let { block(it) }) ?: Result.failure(IllegalArgumentException("Request required"))
}