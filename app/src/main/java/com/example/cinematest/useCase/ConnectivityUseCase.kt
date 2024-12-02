package com.example.cinematest.useCase

import android.content.Context
import android.net.ConnectivityManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

class ConnectivityUseCase(context: Context) {

    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private val _isConnected = MutableStateFlow(false)
    val isConnected: Flow<Boolean> = _isConnected





    init {
        // Observe network connectivity changes
        connectivityManager.registerDefaultNetworkCallback(object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: android.net.Network) {
                _isConnected.value = true
            }

            override fun onLost(network: android.net.Network) {
                _isConnected.value = false
            }
        })
    }


 /*   fun getConnect(){
        // Observe network connectivity changes
        connectivityManager.registerDefaultNetworkCallback(object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: android.net.Network) {
                _isConnected.value = isInternetAvailable()
            }

            override fun onLost(network: android.net.Network) {
                _isConnected.value = false
            }
        })
    }*/


 /*   init {
        // Обсервер изменений состояния сети
        connectivityManager.registerDefaultNetworkCallback(object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: android.net.Network) {
                _isConnected.value = isInternetAvailable(network)
            }

            override fun onLost(network: android.net.Network) {
                _isConnected.value = false
            }
        })
    }

    private fun isInternetAvailable(network: android.net.Network): Boolean {
        return try {
            val socket = java.net.Socket()
            socket.connect(java.net.InetSocketAddress("8.8.8.8", 53), 1500)
            socket.close()
            true
        } catch (e: IOException) {
            false
        }
    }*/

}