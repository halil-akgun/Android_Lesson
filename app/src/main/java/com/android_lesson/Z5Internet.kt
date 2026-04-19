package com.android_lesson

import android.util.Log
import javax.inject.Inject

class Z5Internet @Inject constructor(var address: Z5Address) {
//    var address = Z5Address("Ghent", "Belgium")

    fun apply() {
        Log.d("Dagger", "Applying internet at ${address.city}, ${address.country}")
    }
}