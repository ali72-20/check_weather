package com.example.core.network

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged

class NetworkConnectivityObserver constructor(
    networkRequest: NetworkRequest,
    connectivityManager: ConnectivityManager
){
    val netWorkStatue : Flow<NetWorkStatues> = callbackFlow {
        val networkCallback = object : ConnectivityManager.NetworkCallback(){
            override fun onAvailable(network: Network) {
                trySend(NetWorkStatues.Available)
            }

            override fun onLosing(network: Network, maxMsToLive: Int) {
                trySend(NetWorkStatues.Unavailable)
            }

            override fun onLost(network: Network) {
                 trySend(NetWorkStatues.Unavailable)
            }

            override fun onUnavailable() {
                trySend(NetWorkStatues.Unavailable)
            }
        }
        connectivityManager.registerNetworkCallback(networkRequest,networkCallback)
        awaitClose{
            connectivityManager.unregisterNetworkCallback(networkCallback)
        }
    }.distinctUntilChanged()
}