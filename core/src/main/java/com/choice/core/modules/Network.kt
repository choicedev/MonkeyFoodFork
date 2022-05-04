package com.choice.core.modules

import com.choice.core.util.TokenInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier

@Module
@InstallIn(
    SingletonComponent::class
)
class Network {
    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class Food2Fork


    @Provides
    fun provideOkHttpClient(
        tokenInterceptor: TokenInterceptor
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        builder.addInterceptor(loggingInterceptor)
        builder.addInterceptor(tokenInterceptor)
        return builder.build()
    }

    @Provides
    @Network.Food2Fork
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        @Network.Food2Fork baseUrl: String
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .build()
    }
}