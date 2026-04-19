package com.android_lesson

import android.util.Log
import javax.inject.Inject

class Z5Cargo @Inject constructor(var address: Z5Address) {
//    var address = Z5Address("Ghent", "Belgium")

    fun send() {
        Log.d("Dagger", "Sending cargo to ${address.city}, ${address.country}")
    }
}