package com.android_lesson

import dagger.Module
import dagger.Provides

@Module
class Z5AppModule {

    @Provides
    fun provideAddress(): Z5Address {
        return Z5Address("Ghent", "Belgium")
    }
}
