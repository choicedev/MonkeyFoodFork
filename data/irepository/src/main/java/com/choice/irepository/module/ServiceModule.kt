package com.choice.irepository.module

import com.choice.core.modules.Network
import com.choice.remote.ApiFoodFork
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
class ServiceModule {

    @Provides
    @Network.Food2Fork
    fun providesApi(
        @Network.Food2Fork retrofit: Retrofit
    ): ApiFoodFork {
        return retrofit.create(ApiFoodFork::class.java)
    }
}