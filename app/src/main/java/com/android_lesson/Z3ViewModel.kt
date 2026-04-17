package com.android_lesson

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class Z3ViewModel : ViewModel() {

//    var result = "0"
    var result = MutableLiveData<String>()

    init {
        result = MutableLiveData<String>("0")
    }

    fun add(num1: Int, num2: Int) {
        result.value = (num1 + num2).toString()
    }

    fun mul(num1: Int, num2: Int) {
        result.value = (num1 * num2).toString()
    }
}