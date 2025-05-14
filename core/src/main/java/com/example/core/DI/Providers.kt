package com.example.core.DI

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.LinkProperties
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET
import android.net.NetworkRequest
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object Providers {
    @Provides
    fun provideConnectivityManger(@ApplicationContext context: Context): ConnectivityManager {
        return context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    @Provides
    fun provideNetworkCallBack(): ConnectivityManager.NetworkCallback {
        val networkCallBack = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
            }

            override fun onLosing(network: Network, maxMsToLive: Int) {
                super.onLosing(network, maxMsToLive)
            }

            override fun onLost(network: Network) {
                super.onLost(network)
            }

            override fun onUnavailable() {
                super.onUnavailable()
            }

            override fun onCapabilitiesChanged(
                network: Network,
                networkCapabilities: NetworkCapabilities
            ) {
                super.onCapabilitiesChanged(network, networkCapabilities)
            }

            override fun onLinkPropertiesChanged(
                network: Network,
                linkProperties: LinkProperties
            ) {
                super.onLinkPropertiesChanged(network, linkProperties)
            }

            override fun onBlockedStatusChanged(
                network: Network,
                blocked: Boolean
            ) {
                super.onBlockedStatusChanged(network, blocked)
            }
        }
        return networkCallBack
    }

    @Provides
    fun provideNetworkRequest(): NetworkRequest {
        return NetworkRequest.Builder().addCapability(NET_CAPABILITY_INTERNET)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_ETHERNET)
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR).build()
    }

}