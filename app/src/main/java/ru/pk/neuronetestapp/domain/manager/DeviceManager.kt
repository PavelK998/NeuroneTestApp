package ru.pk.neuronetestapp.domain.manager

import ru.pk.neuronetestapp.domain.model.UserDataModel

interface DeviceManager {
    suspend fun setUserData(userDataModel: UserDataModel)

    suspend fun getUserData(): UserDataModel
}