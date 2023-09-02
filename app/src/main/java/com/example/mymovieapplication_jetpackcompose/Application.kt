package com.example.mymovieapplication_jetpackcompose

import android.app.Application
import dagger.hilt.EntryPoints
import dagger.hilt.android.HiltAndroidApp
import okhttp3.OkHttpClient
import javax.inject.Inject
import javax.inject.Provider

@HiltAndroidApp
class Application : Application() {

    override fun onCreate() {
        super.onCreate()


        // Adding an Network Interceptor for Debugging purpose :
        /*val okHttpClient = OkHttpClient().newBuilder().build()
        AndroidNetworking.initialize(this, okHttpClient)*/
    }


}