package ru.pk.neuronetestapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.pk.neuronetestapp.data.repository.ResourceManagerImpl
import ru.pk.neuronetestapp.domain.ResourceManager
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BindRepository {
    @Binds
    @Singleton
    abstract fun bindResourceManager(deviceManagerImpl: ResourceManagerImpl): ResourceManager
}