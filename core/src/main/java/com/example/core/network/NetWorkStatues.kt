package com.example.core.network

sealed class NetWorkStatues {
    object Available : NetWorkStatues()
    object Unavailable : NetWorkStatues()
}