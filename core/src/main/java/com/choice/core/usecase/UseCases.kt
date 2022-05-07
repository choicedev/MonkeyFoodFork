package com.choice.core.usecase

interface UseCases<in I, out R : Any?> {
    suspend operator fun invoke(input: I): R
}