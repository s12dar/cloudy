package com.lyvetech.cloudy.di

import com.google.gson.Gson
import com.lyvetech.cloudy.data.remote.retrofit.WeatherApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataModule {

    @Provides
    @Singleton
    fun provideTransNatureServiceApi(
        okHttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(WeatherApiService.BASE_URL)
            .addConverterFactory(converterFactory)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttp3(): OkHttpClient {
//        return OkHttpClient.Builder()
//            .addNetworkInterceptor(StethoInterceptor())
//            .build()

        return OkHttpClient.Builder()
            .addInterceptor { chain ->

                val original = chain.request()
                val originalHttpUrl = original.url

                val url = originalHttpUrl.newBuilder()
                    .addQueryParameter("appid", "3fd0ec00ccaf95a62c2abf73b92eab57")
                    .build()


                val requestBuilder = original.newBuilder()
                    .url(url)

                val request = requestBuilder.build()
                return@addInterceptor chain.proceed(request)
            }
            .readTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()

    @Provides
    @Singleton
    fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }
}