package com.choice.core.util

import androidx.annotation.Keep

@Keep
sealed class IResult<out DTO : Any> {
    data class OnSuccess<out DTO : Any>(val response: DTO) : IResult<DTO>()
    data class OnFailed(val throwable: Throwable) : IResult<Nothing>()
    data class OnError(val error: Pair<String, String>) : IResult<Nothing>()
    data class OnLoading<out DTO : Any>(val isLoading: Boolean, val message: String = "Aguarde...") : IResult<DTO>()
}