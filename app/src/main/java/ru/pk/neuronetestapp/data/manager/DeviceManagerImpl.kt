package ru.pk.neuronetestapp.data.manager

import android.content.Context
import androidx.datastore.dataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import ru.pk.neuronetestapp.data.local.UserDataSerializer
import ru.pk.neuronetestapp.data.mappers.toUserData
import ru.pk.neuronetestapp.data.mappers.toUserDataModel
import ru.pk.neuronetestapp.domain.manager.DeviceManager
import ru.pk.neuronetestapp.domain.model.UserDataModel
import javax.inject.Inject

class DeviceManagerImpl @Inject constructor(
    @param:ApplicationContext private val context: Context
) : DeviceManager {
    private val Context.dataStore by dataStore(
        fileName = "user-data",
        serializer = UserDataSerializer
    )

    override suspend fun setUserData(userDataModel: UserDataModel) = withContext(Dispatchers.IO) {
        context.dataStore.updateData {
            userDataModel.toUserData()
        }
        Unit
    }

    override suspend fun getUserData() = withContext(Dispatchers.IO) {
        context.dataStore.data.first().toUserDataModel()
    }

}