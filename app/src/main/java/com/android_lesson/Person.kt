package com.android_lesson

import java.io.Serializable

// Serializable (or Parcelable) is needed to pass objects between activities
class Person(var name: String, var age: Int) : Serializable {
}