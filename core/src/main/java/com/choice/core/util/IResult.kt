package com.choice.core.util

import androidx.annotation.Keep

@Keep
sealed class IResult<out DTO> {
    data class OnSuccess<out DTO>(val response: DTO) : IResult<DTO>()
    data class OnFailed(val throwable: Throwable) : IResult<Nothing>()
    data class OnError(val error: Pair<String, String>) : IResult<Nothing>()
    data class OnLoading<out DTO>(
        val isLoading: Boolean = true,
        val isLoadingText: String = "Waiting.."
    ) : IResult<DTO>()
}