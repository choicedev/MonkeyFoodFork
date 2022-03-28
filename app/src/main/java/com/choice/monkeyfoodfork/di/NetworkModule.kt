package com.choice.monkeyfoodfork.di

import com.choice.core.modules.Network
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Network.Food2Fork
    fun providesFoodFork(): String = "https://food2fork.ca/"
}