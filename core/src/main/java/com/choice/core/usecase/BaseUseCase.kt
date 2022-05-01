package com.choice.core.usecase

import com.choice.core.util.IResult
import kotlinx.coroutines.flow.Flow

interface BaseUseCase<in I, out R : Any> {
    suspend operator fun invoke(input: I): Flow<IResult<R>>
}