package ru.pk.neuronetestapp.domain.repository

import ru.pk.neuronetestapp.domain.model.NetworkDataModel

interface NetworkRepository {
    suspend fun getDataFromServer(): List<NetworkDataModel>
}