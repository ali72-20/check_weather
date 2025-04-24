package com.example.data.api

import android.util.Log
import com.example.data.api.core.ApiConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.logging.HttpLoggingInterceptor

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideGsonFactory():GsonConverterFactory{
        return GsonConverterFactory.create()
    }

    @Provides
    fun provideLoggerInterceptor():HttpLoggingInterceptor{
        return HttpLoggingInterceptor{
            Log.e("Api manger", "Body: $it")
        }.setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    fun provideOkHttpClient(loggerInterceptor:HttpLoggingInterceptor):OkHttpClient{
        return OkHttpClient.Builder().addInterceptor(loggerInterceptor).build()
    }
    @Provides
    fun provideRetrofit(
        client: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder().baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(gsonConverterFactory).client(client).build()
    }

    @Provides
    fun provideWeatherServices(retrofit: Retrofit): WeatherServices {
        return retrofit.create(WeatherServices::class.java)
    }
}
