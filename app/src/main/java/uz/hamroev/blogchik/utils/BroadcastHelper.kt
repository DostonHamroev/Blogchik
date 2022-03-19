package uz.hamroev.blogchik.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class BroadcastHelper(context: Context) : BroadcastReceiver() {

    var isInternet = false

    override fun onReceive(p0: Context?, intent: Intent?) {
        val networkHelper = NetworkHelper(p0!!)
        isInternet = networkHelper.isNetworkConnected()

    }

}