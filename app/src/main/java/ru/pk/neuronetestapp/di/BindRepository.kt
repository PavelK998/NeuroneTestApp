package ru.pk.neuronetestapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.pk.neuronetestapp.data.manager.DeviceManagerImpl
import ru.pk.neuronetestapp.data.manager.ResourceManagerImpl
import ru.pk.neuronetestapp.domain.manager.DeviceManager
import ru.pk.neuronetestapp.domain.manager.ResourceManager
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BindRepository {
    @Binds
    @Singleton
    abstract fun bindResourceManager(resourceManagerImpl: ResourceManagerImpl): ResourceManager

    @Binds
    @Singleton
    abstract fun bindDeviceManager(deviceManagerImpl: DeviceManagerImpl): DeviceManager
}