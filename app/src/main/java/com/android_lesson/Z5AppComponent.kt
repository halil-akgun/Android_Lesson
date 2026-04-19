package com.android_lesson

import dagger.Component

@Component(modules = [Z5AppModule::class])
interface Z5AppComponent {
    fun inject(activity: Z5Activity)
}