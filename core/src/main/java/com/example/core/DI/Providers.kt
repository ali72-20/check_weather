package com.example.core.DI

import android.Manifest
import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.LinkProperties
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET
import android.net.NetworkRequest
import androidx.annotation.RequiresPermission
import com.example.core.network.NetworkConnectivityObserver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object Providers {
    @Provides
    fun provideNetworkRequest(): NetworkRequest {
        return NetworkRequest.Builder().addCapability(NET_CAPABILITY_INTERNET)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_ETHERNET)
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR).build()
    }


    @Provides
    fun provideConnectivityManager(
        @ApplicationContext context: Context
    ): ConnectivityManager {
        return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }


    @Provides
    fun provideNetworkCnObserver(
        networkRequest: NetworkRequest,
        connectivityManager: ConnectivityManager
    ): NetworkConnectivityObserver = NetworkConnectivityObserver(networkRequest,connectivityManager)
}