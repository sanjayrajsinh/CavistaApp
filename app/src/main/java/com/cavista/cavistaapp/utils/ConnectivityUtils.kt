package com.photoshoot.photoshootapp.utils.broadcasts

import android.content.Context
import android.net.ConnectivityManager
import android.view.View

object ConnectivityUtils {

    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo != null && connectivityManager
                .activeNetworkInfo.isConnectedOrConnecting

    }

}