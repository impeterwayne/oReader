package com.genesys.feature.koreader.di

import com.genesys.feature.koreader.bridge.KoreaderReadingStateBridge
import com.genesys.feature.koreader.bridge.KoreaderStorageBridge
import com.genesys.feature.koreader.runtime.KoreaderRuntime
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object KoreaderModule {

    @Provides
    @Singleton
    fun provideKoreaderRuntime(): KoreaderRuntime = KoreaderRuntime()

    @Provides
    @Singleton
    fun provideKoreaderStorageBridge(): KoreaderStorageBridge = KoreaderStorageBridge()

    @Provides
    @Singleton
    fun provideKoreaderReadingStateBridge(): KoreaderReadingStateBridge = KoreaderReadingStateBridge()
}
